package ee.degeetia.dumonitor.storage;

import ee.degeetia.dumonitor.common.config.properties.Property;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.sql.*;

public class Xroad extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static Context context; // global vars are here

  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {"personcode","action",
     "sender","receiver","restrictions",
     "sendercode","receivercode","actioncode",
     "xroadrequestid","xroadservice","usercode"};
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {           
    context=Util.initRequest(req,resp,"text/xml",Xroad.class);    
    Util.showError(context, 2, "get method not allowed for xroad requests");      
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    handleRequest(req, resp, true);    
  }
  
  private void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost)
  throws ServletException, IOException {
    try {
      context=Util.initRequest(req,resp,"text/xml",Xroad.class);
      if (context==null) return;
      boolean ok=Util.parseInput(req, resp, context, inKeys, isPost); 
      if (!ok || context.inParams==null) return;      
      handleXroad(); 
    } catch (Exception e) {
      Util.showError(context,9,"unexpected error: "+e.getMessage()); 
    }     
    context.os.flush();
    context.os.close();
  }  
  
  /*
   *  Main handler
   */
  
  public static void handleXroad() 
  throws ServletException, IOException {                            
    Document doc=context.xmldoc;
    try {
      if (!Util.parseXroadHeader(context, doc)) return; // store header fields to context
      Node body=Util.getTag(context, doc, 
                      "Body", "http://schemas.xmlsoap.org/soap/envelope/");
      if (body==null) {
        Util.showError(context,9,"message Body tag not found");
        return;
      }   
      Node request=Util.getTag(context, doc, "request", null);
      if (body==null) {
        Util.showError(context,9,"message request tag not found");
        return;
      } 
      String requestStr=Util.nodeToString(request);
      if (requestStr==null) {
        Util.showError(context,9,"failed to convert message request to string");
        return;
      } 
      context.xrdRequest=requestStr;
      String personcode=Util.getTagText(context, body, "personcode", null);        
      if (personcode==null) {
        Util.showError(context,11,"Message personcode not found");
        return;
      }
      personcode=personcode.trim();
      if (personcode==null || personcode.equals("")) {
        Util.showError(context,12,"Message personcode was empty");
        return;
      }
      String result=fetchData(personcode);
      result="<response>\n"+result+"</response>"; 
      String envelope=Strs.xroadMessage.replace("{header}",Util.createSoapHeader(context));
      envelope=envelope.replace("{producerns}",Property.XROAD_PRODUCERNS.getString());
      envelope=envelope.replace("{request}",requestStr);
      envelope=envelope.replace("{response}",result);     
      context.os.println(envelope);   
      context.os.flush();
      context.os.close();
    } catch (java.lang.NullPointerException e) {
      Util.showError(context,9,"unknown error: "+e.getMessage()); 
      context.os.flush();
      context.os.close();
    }      
  }  
  
  /*
   *  Query the database and build a partial result to 
   *  be wrapped in the soap envelope later
   */
  
  public static String fetchData(String personcode) 
  throws ServletException, IOException {                        
    String result=null;
          
    Connection conn = Util.createDbConnection(context);
    if (conn==null) return null;    
    
    // set pagination values
    
    int offset=0;
    int limit=100;  
    
    // query data
    
    try {                  
      String sql = "select "
      + "personcode,to_char(logtime,'YYYY-MM-DD HH24:MI:SS') as logtime,action,"
      + "sender,receiver,restrictions,sendercode,receivercode "     
      + " from ajlog "
      + " where personcode=? "
      + " and ((restrictions is null) or restrictions!='P') "
      + " order by id desc limit ? offset ?";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);  
      preparedStatement.setString(1, personcode);  
      preparedStatement.setInt(2, limit);
      preparedStatement.setInt(3, offset);
      
      context.os.println(preparedStatement.toString());
      
      // execute select SQL stetement   
      ResultSet rs = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();      
      result="";
      while ( rs.next() ) {
        int numColumns = rs.getMetaData().getColumnCount();
        String row="<item>";
        for ( int i = 1 ; i <= numColumns ; i++ ) {
          // Column numbers start at 1.
          row+="<"+rsmd.getColumnName(i)+">";
          if (rs.getObject(i)!=null) {
            row+=Util.cleanXmlStr(rs.getObject(i)+"");
          }  
          row+="</"+rsmd.getColumnName(i)+">";          
        }
        row+="</item>\n";
        result+=row;        
      }                
    } catch(Exception e) {
      Util.showError(context, 10, "database query error: "+e.getMessage());
      return null;    
    } finally {
        //It's important to close the connection when you are done with it
        try { conn.close(); } catch (Throwable ignore) { 
          /* Propagate the original exception
          instead of this one that you may want just logged */ }
    }
    return result;
  }  
  
}
