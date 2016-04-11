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
     "xroadrequestid","xroadservice","usercode",
     "offset","limit","from_date","to_date",
     "callback"};
  
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
    
    // set pagination values
    
    int offset=0;
    int limit=100;  
    
    try {
      offset=Math.abs(Integer.parseInt(context.inParams.get("offset")));
    } catch (Exception e) {} 
    try {
      limit=Math.abs(Integer.parseInt(context.inParams.get("limit")));
    } catch (Exception e) {}  
    if (limit>1000) limit=1000;   
      
    // prepare filter data
    
    String where=" where 1=1 ";      
    String[] paramarr=new String[20];
    int paramnr=1;          
      
    // create filters
    
    for(int i=0;i<inKeys.length;i++) {
      // ignore non-sql parameters
      if (inKeys[i].equals("offset") || 
          inKeys[i].equals("limit") ||
          inKeys[i].equals("callback") ) continue;
      // here we have only sql parameters
      if (context.inParams.get(inKeys[i])!=null) {
        if (inKeys[i].equals("personcode")) {
          where=where+" and "+inKeys[i]+"=? ";           
        } else if (inKeys[i].equals("from_date")) {
          where=where+" and logtime>=to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') ";    
        } else if (inKeys[i].equals("to_date")) {
          where=where+" and logtime<=to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') ";          
        } else {
          where=where+" and "+inKeys[i]+" like ? ";
        }
        paramarr[paramnr]=inKeys[i];
        paramnr++;        
      }
    }
       
    //context.os.println("paramnr: "+paramnr);
    //context.os.println("where: "+where);
    
    // query data
    
    try {                  
      String sql = "select "
      + "personcode,to_char(logtime,'YYYY-MM-DD HH24:MI:SS') as logtime,action,"
      + "sender,receiver,restrictions,sendercode,receivercode "     
      + " from ajlog "
      + where
      + " order by id desc limit ? offset ?";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);  
      for(int i=1;i<paramnr;i++) {
        //context.os.println("i: "+i+" "+paramarr[i]+" "+context.inParams.get(paramarr[i]));
        if (paramarr[i].equals("personcode") || 
            paramarr[i].equals("from_date") || 
            paramarr[i].equals("to_date")) {
          preparedStatement.setString(i,context.inParams.get(paramarr[i]));
        } else {
          preparedStatement.setString(i,"%"+context.inParams.get(paramarr[i])+"%");
        }          
      }      
      preparedStatement.setInt(paramnr, limit);
      preparedStatement.setInt(paramnr+1, offset);
      
      //context.os.println(preparedStatement.toString());
      
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
      // handle potential javascript callback parameter
      if (context.inParams.get("callback")!=null) {
        context.os.println(context.inParams.get("callback")+"(");
      }
      context.os.println(array.toString(1));      
      if (context.inParams.get("callback")!=null) {
        context.os.println(");");
      }
          
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
