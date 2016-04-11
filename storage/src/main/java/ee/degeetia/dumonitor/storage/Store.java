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

public class Store extends HttpServlet {

  private static final long serialVersionUID = 1L;

  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {"personcode","action",
      "sender","receiver","restrictions",
      "sendercode","receivercode","actioncode",
      "xroadrequestid","xroadservice","usercode"};
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {    
    Util.processGet(req, resp, "handleStoreParams", inKeys);  
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    Util.processPost(req, resp, "handleStoreParams", inKeys);   
  }   
     
  
  /*
   *  Store parsed parameters passed as hashmap  
   */
  
  public static void handleStoreParams(Context context) 
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
    
    // insert data
    
    try {                  
      String sql = "insert into ajlog"
      + "(personcode,action,"
      + "sender,receiver,restrictions,sendercode,receivercode,"
      + "actioncode,xroadrequestid,xroadservice,usercode)"
      + " values "
      + "(?,?, ?,?,?,?,?, ?,?,?,?)";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      for (int i=0; i<inKeys.length; i++) {
        if (context.inParams.get(inKeys[i])!=null) {
          preparedStatement.setString(i+1, context.inParams.get(inKeys[i]));          
        } else {
          preparedStatement.setNull(i+1, java.sql.Types.VARCHAR);
        }
      }  

      // execute insert SQL stetement
      int n = preparedStatement.executeUpdate();      
      if (n!=1) {
        Util.showError(context, 6, "record not stored");
      } else {
        Util.showOK(context);
      }
          
    } catch(Exception e) {
      Util.showError(context, 5, "database storage error: "+e.getMessage());
      return;    
    } finally {
        //It's important to close the connection when you are done with it
        try { conn.close(); } catch (Throwable ignore) { 
          /* Propagate the original exception
          instead of this one that you may want just logged */ }
    }
  }  
  
}
