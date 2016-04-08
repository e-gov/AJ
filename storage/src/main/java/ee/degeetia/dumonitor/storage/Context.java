package ee.degeetia.dumonitor.storage;

import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

public class Context {
    public HttpServletRequest req; 
    public HttpServletResponse resp;
    public ServletOutputStream os; // stream to write output
    public Logger log; // logging class
    public Map<String, String> inParams; // parsed params stored here   
    public Document xmldoc; // parsed xml stored here for xroad
    public Context(HttpServletRequest req, HttpServletResponse resp,
        ServletOutputStream os, Logger log, Map<String, String> inParams) {
      this.req = req;
      this.resp = resp;
      this.os = os;
      this.log = log;     
      this.inParams = inParams;           
    }
}