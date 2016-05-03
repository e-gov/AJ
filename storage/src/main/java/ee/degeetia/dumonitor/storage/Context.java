package ee.degeetia.dumonitor.storage;

import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Context {
    // general globals
    public HttpServletRequest req; 
    public HttpServletResponse resp;
    public ServletOutputStream os; // stream to write output
    public Logger log; // logging class
    public String contentType; // response http content type 
    public Map<String, String> inParams; // parsed params stored here   
  
    // xroad globals
    public String xmlstr; // xml request string stored here for xroad  
    public Document xmldoc; // parsed xml stored here for xroad  
    public Node xmlheader; // parsed request header stored here for xroad 
    public String xrdRequest="";  // converted to str from parsed request 
    public String xrdVersion="old"; // for new this will be set to "4.0"
    // these come from the request SOAP xrd header for both versions:
    public String xrdUserId="";
    public String xrdId="";  
    // these come from the request SOAP xrd header for old version:
    public String xrdConsumer=""; 
    public String xrdProducer="";
    public String xrdService="";
    public String xrdIssue=""; 
    // these come from the request SOAP xrd header for new version:
    public String xrdClientMemberCode="";    
  
    public Context(HttpServletRequest req, HttpServletResponse resp,
        ServletOutputStream os, Logger log,  
        String contentType, Map<String, String> inParams) {
      this.req = req;
      this.resp = resp;
      this.os = os;
      this.log = log;     
      this.contentType=contentType;    
      this.inParams = inParams;           
    }
}