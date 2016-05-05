/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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