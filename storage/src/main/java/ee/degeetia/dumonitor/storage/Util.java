package ee.degeetia.dumonitor.storage;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.common.config.PropertyLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;

import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import org.json.*;
import java.sql.*;

public class Util  {  
  
 
  
  /*
   * Initialize request handling 
   *
   */
  
  public static Context initRequest(HttpServletRequest req, HttpServletResponse resp,
                                    String contentType, Class classObject) 
  throws ServletException, IOException {    
    resp.setContentType(contentType);             
    ServletOutputStream os = resp.getOutputStream();    
    Logger log = LogManager.getLogger(classObject);
    Map<String, String> inParams = new HashMap<String,String>(); // parsed params stored here
    Context context = new Context(req,resp,os,log,contentType,inParams);    
    boolean ok=Util.loadProperties(context);    
    if (ok) return context;
    else return null;
  }
  
  /* 
   * Use the general configuration file loading methods in common
   */
  
  public static boolean loadProperties(Context context) 
  throws ServletException, IOException {
    PropertyLoader propertyLoader = new PropertyLoader();
    try {
      //propertyLoader.loadProperties(Property.class, "dumonitor.properties", "default.properties");      
      propertyLoader.loadProperties(Property.class, "default.properties");
      if (Property.DATABASE_CONNECTSTRING.getValue()==null ||
          Property.DATABASE_USER.getValue()==null ||
          Property.DATABASE_PASSWORD.getValue()==null) {
        showError(context,1,
                 "failed to load database connect string, user or password configuration properties");    
        return false;    
      }
      //propertyLoader.loadProperties("asas");
      return true;
    } catch (Exception e) {
      showError(context, 1,"failed to load configuration properties: "+e.getMessage());
      return false;
    }
  }
  
  /*
   *  Universal request input parser, data stored to context
   */
  
  public static boolean parseInput(HttpServletRequest req, HttpServletResponse resp, 
                                   Context context, String[] inKeys, boolean isPost) 
  throws ServletException, IOException {
    boolean ok;
    
    String ctype = req.getHeader("Content-Type");    
    if (!isPost) return Util.parseCgi(context, inKeys);
    //if (ctype==null || (!ctype.equals("application/json") && !ctype.equals("text/xml"))) {
    if (ctype==null || (!ctype.contains("json") && 
                        !ctype.contains("xml") &&
                        !context.contentType.contains("xml"))) {
      // default: parse input as cgi key=value parametets
      ok=Util.parseCgi(context, inKeys);
    //} else if (ctype.equals("application/json")) {
    } else if (ctype.contains("json")) {             
      // parse input as json
      ok=Util.parseJson(context, inKeys);
    //} else if (ctype.equals("text/xml")) {    
    } else if (ctype.contains("xml") || 
               context.contentType.contains("xml")) {   
      // parse input as xml
      ok=Util.parseXml(context, inKeys);              
    } else {
      ok=false;
      showError(context, 2, "unknown content-type in http");
    }
    return ok;
  }
    
  
  /*
   *  Parse input as cgi key=value parameters  
   */

  public static boolean parseCgi(Context context, String[] inKeys) 
  throws ServletException, IOException {
    String key,inp;
    
    for (int i=0; i<inKeys.length; i++) {
      key=inKeys[i];
      context.inParams.put(key, null);  // default value is null  
      inp = context.req.getParameter(key); // cgi parameter
      context.inParams.put(key, inp);  
    }  
    return true;    
  }
  
  /*
   *  Parse input as json {"key":"value"} parameters
   */
  
  public static boolean parseJson(Context context, String[] inKeys) 
  throws ServletException, IOException {
    String inp, key;
    JSONObject jsonObject; 
  
    for (int i=0; i<inKeys.length; i++) context.inParams.put(inKeys[i], null);    
    Set<String> set = context.inParams.keySet();
    Iterator iter = set.iterator();  
      
    StringBuffer jb = new StringBuffer();
    try {              
      BufferedReader reader = context.req.getReader();
      String line;
      while ((line = reader.readLine()) != null)
        jb.append(line);
    } catch (Exception e) { 
      //throw new IOException("Error reading request string");
      Util.showError(context, 2, "cannot read input");      
      return false;      
    } 
    try {      
      jsonObject = new JSONObject(jb.toString());    
      for (int i=0; i<inKeys.length; i++) {
        key=inKeys[i];
        context.inParams.put(key, null);  // default value is null          
        if (jsonObject.has(key)) {
          inp = (String) jsonObject.getString(key); // json parameter
          if (inp!=null) context.inParams.put(key, inp);  
        }  
      }                        
    } catch (Exception e) {        
      //throw new IOException("Error parsing JSON request string");
      Util.showError(context, 3, "failed to parse input json: "+e.getMessage());
      return false;  
    }        
    return true;
  }
  
   /*
   *  Parse input as XML (normally from xroad)
   */

  public static boolean parseXml(Context context, String[] inKeys) 
  throws ServletException, IOException {
    String xml;    
    StringBuffer jb = new StringBuffer();
    try {              
      BufferedReader reader = context.req.getReader();
      String line;
      while ((line = reader.readLine()) != null)
        jb.append(line);
    } catch (Exception e) { 
      Util.showError(context, 2, "cannot read input");      
      return false;      
    } 
    xml=jb.toString();     
    context.xmlstr=xml;
    //context.os.println("|"+xml+"|");    
    DocumentBuilder db;                
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      db = dbf.newDocumentBuilder();
    } catch (Exception e) {
      Util.showError(context, 4, "preparing xml parsing failed: "+e.getMessage());
      return false;
    } 
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));
    Document doc;
    try {
      doc = db.parse(is);
    } catch (Exception e) {
      Util.showError(context, 5, "parsing xml failed: "+e.getMessage());
      return false;
    }      
    context.xmldoc=doc;
    return true;    
  }      
  
  /*
   * Create database connection 
   *
   */
  
  public static Connection createDbConnection(Context context) 
  throws ServletException, IOException {  
    // check whether the driver present
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      Util.showError(context, 7, "failed to find the PostgreSQL JDBC Driver");
      return null;
    }    
    // create db connection    
    Connection conn=null;
    try {
      conn = DriverManager.getConnection(
         Property.DATABASE_CONNECTSTRING.getValue(),
         Property.DATABASE_USER.getValue(),
         Property.DATABASE_PASSWORD.getValue());
    } catch(Exception e) {
      Util.showError(context, 8, "failed to connect to the database");
      return null;
    }  
    return conn;
  }
    
  /*
   *  Log and output error message
   * 
   *  For XML, errors with code < 10 are considered technical
   */
  
  public static void showError(Context context, int code, String msg)  
  throws ServletException, IOException {
    //resp.sendError(resp.SC_BAD_REQUEST, msg); // possible alternative to json output
    String err;
    context.log.error("errcode {} errmessage {}", code, msg);
    if (context.contentType.contains("json")) {
      // json error format
      // handle potential javascript callback parameter
      if (context.inParams.get("callback")!=null) {
        context.os.println(context.inParams.get("callback")+"(");
      }
      msg=msg.replace("\"","'").replace("\n"," ").replace("\r"," ");
      context.os.println("{\"errcode\":"+code+", \"errmessage\":\""+msg+"\"}");      
      if (context.inParams.get("callback")!=null) {
        context.os.println(");");
      }      
    } else {
      // xml error format for xroad
      if (code<10) {
        // Technical error: no request obtained or used, no header inserted
        err=Strs.xroadTechErr;
        err=err.replace("{faultCode}",""+code).replace("{faultString}",cleanXmlStr(msg));
        context.os.println(err);
      } else {        
        // Normal error: pass request, insert header
        if (context.xrdVersion.equals("old")) {            
          err=Strs.xroadErr.replace("{header}",createSoapHeader(context));
          err=err.replace("{producerns}",Property.XROAD_PRODUCERNS.getValue());
          err=err.replace("{request}",context.xrdRequest);
          err=err.replace("{faultCode}",""+code).replace("{faultString}",cleanXmlStr(msg));
        } else {      
          err=Strs.xroad40Err.replace("{header}",Util.nodeToString(context.xmlheader));
          err=err.replace("{producerns}",Property.XROAD_PRODUCERNS.getValue());          
          err=err.replace("{faultCode}",""+code).replace("{faultString}",cleanXmlStr(msg));
        }
        context.os.println(err);
      };        
    }
    context.os.flush();
    context.os.close();          
  }
  
  public static String cleanXmlStr(String msg) {
    if (msg==null || msg.equals("")) return msg;
    msg=msg.replace("<"," ").replace(">"," ").replace("&"," ").replace("\"","'");
    return msg;
  }
  
  /*
   *  Output trivial OK message
   */
  
  public static void showOK(Context context) 
  throws ServletException, IOException {  
    if (context.contentType.contains("json")) {    
      context.os.println("{\"ok\":1}");
    } else {
      context.os.println("<ok>1</ok>");
    } 
    context.os.flush();
    context.os.close();          
  }
  
   /*
   * Parsed XML processing utils 
   *
   */
  
  public static Node getTag(Context context, Node node, String name, String ns) 
  throws ServletException, IOException {
    if (node==null) return null;
    try {            
      if (isNode(node, name, ns)) return node;
      NodeList nodeList = node.getChildNodes();
      for (int i = 0; i < nodeList.getLength(); i++) {        
          Node currentNode = nodeList.item(i);
          if (currentNode.getNodeType() == Node.ELEMENT_NODE) {             
            Node result=getTag(context, currentNode, name, ns);
            if (result!=null) return result;
          }
      }
    } catch (Exception e) {
      Util.showError(context,6,"error traversing xml tree: "+e.getMessage());
    }
    return null;
  } 
  
  public static String getTagText(Context context, Node node, String name, String ns) 
  throws ServletException, IOException {
    Node foundNode=getTag(context, node, name, ns);
    if (foundNode==null) return null;
    String result=foundNode.getTextContent();    
    return result;
  }
  
  public static boolean isNode(Node node, String name, String ns) {
    if (node==null) return false;
    if (node.getNodeType() != Node.ELEMENT_NODE) return false;
    if (ns!=null && (node.getNamespaceURI()==null ||
                    ! node.getNamespaceURI().equals(ns)))  return false;
    //if (ns!=null && ! node.getNamespaceURI().equals(ns))  return false;
    String nodename=node.getNodeName();
    if (nodename.contains(":")) {
      String[] parts = nodename.split(":");
      if (parts[1].equals(name)) return true;
    } else {
      if (nodename.equals(name)) return true;
    }      
    return false;  
  }  
  
  public static String nodeToString(Node node)
  throws ServletException, IOException {  
    StringWriter sw = new StringWriter();
    try {
      Transformer t = TransformerFactory.newInstance().newTransformer();
      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      t.transform(new DOMSource(node), new StreamResult(sw));
    } catch (Exception e) {   
      return null;
    }
    return sw.toString();
  }
  
  public static boolean parseXroadHeader(Context context, Node node) 
  throws ServletException, IOException {  
    String xrdns="http://x-road.ee/xsd/x-road.xsd"; // old version      
    String newxrdns="http://x-road.eu/xsd/xroad.xsd"; // new version
    String idns="http://x-road.eu/xsd/identifiers"; // used by new version
    if (node==null) {
      showError(context,9,"xml message was empty");
      return false;
    }  
    Node headerNode=getTag(context, node, 
                           "Header", "http://schemas.xmlsoap.org/soap/envelope/");    
    if (headerNode==null) {
      showError(context,9,"Message Header tag not found");
      return false;
    }
    context.xmlheader=headerNode;
    // determine xroad version
    String version=getTagText(context, headerNode, "protocolVersion", newxrdns);
    if (version==null || !version.equals("4.0")) {
      context.xrdVersion="old"; // 2.0, 3.0, 3.1
      // parse old xroad version header            
      String consumer=getTagText(context, headerNode, "consumer", xrdns);
      String producer=getTagText(context, headerNode, "producer", xrdns);
      String userId=getTagText(context, headerNode, "userId", xrdns);
      String id=getTagText(context, headerNode, "id", xrdns);
      String service=getTagText(context, headerNode, "service", xrdns);
      String issue=getTagText(context, headerNode, "issue", xrdns);
      if (producer==null) {
        showError(context,9,"Message header producer not found");
        return false;
      }
      if (id==null) {
        showError(context,9,"Message header id not found");
        return false;
      } 
      context.xrdConsumer=consumer;
      context.xrdProducer=producer;
      context.xrdUserId=userId;
      context.xrdId=id;
      context.xrdService=service;
      context.xrdIssue=issue;    
      return true;
    } else {      
      // new xroad version
      context.xrdVersion="4.0";
      // parse new xroad version header                
      String userId=getTagText(context, headerNode, "userId", newxrdns);
      String id=getTagText(context, headerNode, "id", newxrdns);
      if (id==null) {
        showError(context,9,"Message header id not found");
        return false;
      }       
      Node headerClientNode=getTag(context, headerNode, "client", newxrdns);    
      if (headerClientNode==null) {
        showError(context,9,"Message client tag not found in header");
        return false;
      }      
      String memberCode=getTagText(context, headerClientNode, "memberCode", idns);
      if (memberCode==null) {
        showError(context,9,"Message header client memberCode not found");
        return false;
      }
      context.xrdUserId=userId;
      context.xrdId=id;
      context.xrdClientMemberCode=memberCode;  
      return true;    
    }
  }      
  
   /*
   * Composing SOAP messages
   *
   */
  
  public static String createSoapHeader(Context context) 
  throws ServletException, IOException {  
    String header;
    if (context.xrdVersion.equals("4.0")) {
      // new xroad
      showError(context,9,"Err in code: should not create header for new xroad");
      return "";      
    } else {
      // old xroad
      header=Strs.xroadHeader;
      // from request    
      header=header.replace("{consumer}",context.xrdProducer);
      header=header.replace("{consumer}",context.xrdProducer);
      header=header.replace("{id}",context.xrdId);    
      // our values from configuration
      header=header.replace("{producer}",Property.XROAD_PRODUCER.getValue());
      header=header.replace("{userId}",Property.XROAD_USERID.getValue());
      header=header.replace("{service}",Property.XROAD_SERVICE.getValue());
    }  
    return header;
  }
}
