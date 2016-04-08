package ee.degeetia.dumonitor.storage;

import ee.degeetia.dumonitor.common.config.properties.Property;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import org.json.*;

public class Query extends HttpServlet {

  private static final long serialVersionUID = 1L;

  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {"personcode","action",
     "sender","receiver","restrictions",
     "sendercode","receivercode","actioncode",
     "xroadrequestid","xroadservice","usercode"};
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {    
    Util.processGet(req, resp, "handleQueryParams", inKeys);  
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    Util.processPost(req, resp, "handleQueryParams", inKeys);   
  }
  
  /*
   *  Store parsed parameters passed as hashmap  
   */
  
  public static void handleQueryParams(Context context) 
  throws ServletException, IOException {                        
    
    // check whether the driver present
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      Util.showError(context, 3, "failed to find the PostgreSQL JDBC Driver");
      return;
    }
    
    // create db connection    
    /*
    String url = "jdbc:postgresql://localhost/test";
    Properties props = new Properties();
    props.setProperty("user","fred");
    props.setProperty("password","secret");
    props.setProperty("ssl","true");
    Connection conn = DriverManager.getConnection(url, props);
    String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
    Connection conn = DriverManager.getConnection(url);
    */
    Connection conn=null;
    try {
      conn = DriverManager.getConnection(
         Property.DATABASE_CONNECTSTRING.getString(),
         Property.DATABASE_USER.getString(),
         Property.DATABASE_PASSWORD.getString());
    } catch(Exception e) {
      Util.showError(context, 4, "failed to connect to the database");
      return;
    }  
    
    // query data
    
    try {                  
      String sql = "select "
      + "personcode,to_char(logtime,'YYYY-MM-DD HH24:MI:SS') as logtime,action,"
      + "sender,receiver,restrictions,sendercode,receivercode,"
      + "actioncode,xroadrequestid,xroadservice,usercode"     
      + " from ajlog order by id";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);     

      // execute select SQL stetement   
      ResultSet rs = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();      
      JSONArray array = new JSONArray();
      JSONObject obj=null; 
      while ( rs.next() ) {
        int numColumns = rs.getMetaData().getColumnCount();
        obj = new JSONObject();
        for ( int i = 1 ; i <= numColumns ; i++ ) {
          // Column numbers start at 1.          
          obj.put(rsmd.getColumnName(i), rs.getObject(i));
        }
        array.put(obj);        
      }
      context.os.println(array.toString(1));      
     
          
    } catch(Exception e) {
      Util.showError(context, 5, "database query error: "+e.getMessage());
      return;    
    } finally {
        //It's important to close the connection when you are done with it
        try { conn.close(); } catch (Throwable ignore) { 
          /* Propagate the original exception
          instead of this one that you may want just logged */ }
    }
  }  
  
  
}
