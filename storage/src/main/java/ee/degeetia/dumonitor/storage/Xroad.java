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

import java.sql.*;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Xroad extends HttpServlet {

  private static final long serialVersionUID = 1L;

  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {"personcode","action",
     "sender","receiver","restrictions",
     "sendercode","receivercode","actioncode",
     "xroadrequestid","xroadservice","usercode"};
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {    
    resp.setContentType("text/xml");             
    ServletOutputStream os = resp.getOutputStream();    
    Logger log = LogManager.getLogger(Store.class);
    String err="<error>get method not allowed for xroad requests</error>";
    os.println(err);
    os.flush();
    os.close();  
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    Util.processPost(req, resp, "handleXroad", inKeys);   
  }
  
  /*
   *  Store parsed parameters passed as hashmap  
   */
  
  public static void handleXroad(Context context) 
  throws ServletException, IOException {                            
    Document doc=context.xmldoc;
    String result="";
    result=traverseNode(context, 0, doc);
    result="<result>\n"+result+"</result>";
    context.os.println(result);   
    context.os.flush();
    context.os.close();
  }  
  
  public static String traverseNode(Context context, int depth, Node node) 
  throws ServletException, IOException { 
    String result="";
    try {
      result=result+"depth "+depth+": "+node.getNodeName()+"\n";
      NodeList nodeList = node.getChildNodes();
      for (int i = 0; i < nodeList.getLength(); i++) {
          Node currentNode = nodeList.item(i);
          if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
              //calls this method for all the children which is Element
              result=result+traverseNode(context,depth+1,currentNode);
          }
      }
    } catch (Exception e) {
      Util.showError(context,15,"error traversing xml tree: "+e.getMessage());
    }
    return result;
  }  
  
}
