package ee.degeetia.dumonitor.storage;

import ee.degeetia.dumonitor.common.config.properties.Property;
import ee.degeetia.dumonitor.common.config.properties.RuntimeProperty;
import ee.degeetia.dumonitor.common.config.properties.PropertyLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

/*
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
//import java.io.File;
*/

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class Util  {  
  
  /* 
   * generic get request handling, calls dispatch to invoke the real procedure
   */
  
  public static void processGet(HttpServletRequest req, HttpServletResponse resp, 
                         String action, String[] inKeys) 
  throws ServletException, IOException {
    boolean ok;
    
    Context context=Util.initRequest(req,resp);
    if (context==null) return;
    
    ok=Util.parseCgi(context, inKeys); // parse input as cgi key=value parameters    
    if (ok && context.inParams!=null) {
      dispatch(context, action);     
      context.os.flush();
      context.os.close();  
    }  
  }
  
  /* 
   * generic post request handling, calls dispatch to invoke the real procedure
   */
  
  public static void processPost(HttpServletRequest req, HttpServletResponse resp, 
                         String action, String[] inKeys) 
  throws ServletException, IOException {
    boolean ok;

    Context context=Util.initRequest(req,resp);
    if (context==null) return;
    
    String ctype = req.getHeader("Content-Type");    
    //if (ctype==null || (!ctype.equals("application/json") && !ctype.equals("text/xml"))) {
    if (ctype==null || (!ctype.contains("json") && !ctype.contains("xml"))) {
      // default: parse input as cgi key=value parametets
      ok=Util.parseCgi(context, inKeys);
    //} else if (ctype.equals("application/json")) {
    } else if (ctype.contains("json")) {  
      // parse input as json
      ok=Util.parseJson(context, inKeys);
    //} else if (ctype.equals("text/xml")) {    
    } else if (ctype.contains("xml")) {   
      // parse input as json
      ok=Util.parseXml(context, inKeys);              
    } else {
      ok=false;
      showError(context, 14,"unknown content-type in http");
    }
    if (ok && context.inParams!=null) {
      dispatch(context, action);  
      context.os.flush();
      context.os.close();  
    }  
  }
  
  /* 
   * dispatch calls real procedure based on action string
   */
  
  public static void dispatch(Context context, String action) 
  throws ServletException, IOException  {
    if (action.equals("handleQueryParams")) {
      Query.handleQueryParams(context);
    } else if (action.equals("handleStoreParams")) {
      Store.handleStoreParams(context);  
    } else if (action.equals("handleXroad")) {
      Xroad.handleXroad(context);  
    } else {
      showError(context, 10, "wrong action "+action+" given to dispatch");
    }      
  }
  
  
  /*
   * Initialize request handling 
   *
   */
  
  public static Context initRequest(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {    
    resp.setContentType("text/plain");             
    ServletOutputStream os = resp.getOutputStream();    
    Logger log = LogManager.getLogger(Store.class);
    Map<String, String> inParams = new HashMap(); // parsed params stored here
    Context context = new Context(req,resp,os,log,inParams);    
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
      propertyLoader.loadProperties("dumonitor.properties", "test.properties", "default.properties");
      if (Property.DATABASE_CONNECTSTRING.getString()==null ||
          Property.DATABASE_USER.getString()==null ||
          Property.DATABASE_PASSWORD.getString()==null) {
        showError(context,1,
                 "failed to load database connect string, user or password configuration properties");    
        return false;    
      }
      //propertyLoader.loadProperties("asas");
      return true;
    } catch (Exception e) {
      showError(context, 1,"failed to load configuration properties");
      return false;
    }
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
      Util.showError(context, 1, "cannot read input");      
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
      Util.showError(context, 2, "failed to parse input json: "+e.getMessage());
      return false;  
    }        
    return true;
  }
  
   /*
   *  Parse input as XMl from xroad
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
      //throw new IOException("Error reading request string");
      Util.showError(context, 1, "cannot read input");      
      return false;      
    } 
    xml=jb.toString();     
    //context.os.println("|"+xml+"|");    
    DocumentBuilder db;    
    try {
      db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (Exception e) {
      Util.showError(context, 15, "preparing xml parsing failed: "+e.getMessage());
      return false;
    } 
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));
    Document doc;
    try {
      doc = db.parse(is);
    } catch (Exception e) {
      Util.showError(context, 15, "parsing xml failed: "+e.getMessage());
      return false;
    }      
    context.xmldoc=doc;
    return true;    
  }  
    
  /*
  public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }
  */
  
  /*
   *  Log and output error message
   */
  
  public static void showError(Context context, int code, String msg)  
  throws ServletException, IOException {
    //resp.sendError(resp.SC_BAD_REQUEST, msg); // possible alternative to json output
    context.log.error("errcode {} errmessage {}", code, msg);
    context.os.println("{\"errcode\":"+code+", \"errmessage\":\""+msg+"\"}");
    context.os.flush();
    context.os.close();          
  }
  
  /*
   *  Output trivial OK message
   */
  
  public static void showOK(Context context) 
  throws ServletException, IOException {    
    context.os.println("{\"ok\":1}");
    context.os.flush();
    context.os.close();          
  }
  
}
