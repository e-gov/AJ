/*
 * MIT License Copyright (c) 2016 Estonian Information System Authority (RIA)
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ee.ria.dumonitor.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ee.ria.dumonitor.common.config.Property;
import ee.ria.dumonitor.common.config.PropertyLoader;

/**
 * Generic utilities for servlets: query parsing, database handling, xml parsing, errors etc
 *
 */
public final class Util {

  private static final int ERRCODE_2 = 2;
  private static final int ERRCODE_3 = 3;
  private static final int ERRCODE_4 = 4;
  private static final int ERRCODE_5 = 5;
  private static final int ERRCODE_6 = 6;
  private static final int ERRCODE_7 = 7;
  private static final int ERRCODE_8 = 8;
  private static final int ERRCODE_9 = 9;
  private static final int MAX_TECH_ERROR = 10;
  
  /**
   * Class with only static methods - no instantiation is allowed.
   */
  private Util() {
  }

  /**
   * Initialize request handling
   *
   * @param req http Request object from servlet
   * @param resp http Response object from servlet
   * @param contentType http request content type
   * @param classObject Caller class object - used for logging
   * @return Context global vars
   * @throws IOException In case there was error when opening output stream
   */
  public static Context initRequest(HttpServletRequest req, HttpServletResponse resp, String contentType,
      Class<?> classObject) throws IOException {
    resp.setContentType(contentType);
    resp.setCharacterEncoding("UTF-8");
    PrintWriter w = resp.getWriter();
    Logger log = LoggerFactory.getLogger(classObject);
    Map<String, String> inParams = new HashMap<String, String>(); // parsed
                                                                  // params
                                                                  // stored here
    Context context = new Context(req, resp, w, log, contentType, inParams);
    boolean ok = Util.loadProperties(context);
    if (ok)
      return context;
    else
      return null;
  }

  /**
   * Use the general configuration file loading methods in common
   *
   * @param context globals
   * @return true if loading ok, false if fails
   * @throws IOException generic catchall
   */
  public static boolean loadProperties(Context context) throws IOException {
    try {
      PropertyLoader.loadProperties(Property.class, "default.properties", "dumonitor.properties");
      if (Property.DATABASE_CONNECTSTRING.getValue() == null || Property.DATABASE_USER.getValue() == null
          || Property.DATABASE_PASSWORD.getValue() == null) {
        showError(context, 1, "failed to load database connect string, user or password configuration properties");
        return false;
      }
      return true;
    } catch (Exception e) {
      showError(context, 1, "failed to load configuration properties: " + e.getMessage());
      return false;
    }
  }

  /**
   * Universal request input parser (cgi,json or xml) data stored to context
   *
   * @param req http Request object from servlet
   * @param resp http Response object from servlet
   * @param context global vars
   * @param inKeys string array of possible input params
   * @param isPost true iff a post request, false iff get
   * @return boolean true if input parsing succeeded, false otherwise
   * @throws IOException generic catchall
   */
  public static boolean parseInput(HttpServletRequest req, HttpServletResponse resp, Context context, String[] inKeys,
      boolean isPost) throws IOException {
    boolean ok;

    String ctype = req.getHeader("Content-Type");
    if (!isPost)
      return Util.parseCgi(context, inKeys);
    // if (ctype==null || (!ctype.equals("application/json") &&
    // !ctype.equals("text/xml"))) {
    if (ctype == null || !ctype.contains("json") && !ctype.contains("xml") && !context.contentType.contains("xml"))
      // default: parse input as cgi key=value parametets
      ok = Util.parseCgi(context, inKeys);
    // } else if (ctype.equals("application/json")) {
    else if (ctype.contains("json"))
      // parse input as json
      ok = Util.parseJson(context, inKeys);
    // } else if (ctype.equals("text/xml")) {
    else if (ctype.contains("xml") || context.contentType.contains("xml"))
      // parse input as xml
      ok = Util.parseXml(context, inKeys);
    else {
      ok = false;
      showError(context, 2, "unknown content-type in http");
    }
    return ok;
  }

  /**
   * Parse input as cgi key=value parameters
   *
   * @param context globals
   * @param inKeys string array of possible input params
   * @return boolean always true for now
   * @throws IOException generic catchall
   */
  public static boolean parseCgi(Context context, String[] inKeys) throws IOException {
    String key, inp;

    for (int i = 0; i < inKeys.length; i++) {
      key = inKeys[i];
      context.inParams.put(key, null); // default value is null
      inp = context.req.getParameter(key); // cgi parameter
      context.inParams.put(key, inp);
    }
    return true;
  }

  /**
   * Parse input as json {"key":"value"} parameters
   *
   * @param context globals
   * @param inKeys string array of possible input params
   * @return boolean true if input parsing succeeded, false otherwise
   * @throws IOException generic catchall
   */
  public static boolean parseJson(Context context, String[] inKeys) throws IOException {
    String inp, key;
    JSONObject jsonObject;

    for (int i = 0; i < inKeys.length; i++) {
      context.inParams.put(inKeys[i], null);
    }

    StringBuffer jb = new StringBuffer();
    try {
      BufferedReader reader = context.req.getReader();
      String line;
      while ((line = reader.readLine()) != null) {
        jb.append(line);
      }
    } catch (Exception e) {
      // throw new IOException("Error reading request string");
      Util.showError(context, 2, "cannot read input");
      return false;
    }
    try {
      jsonObject = new JSONObject(jb.toString());
      for (int i = 0; i < inKeys.length; i++) {
        key = inKeys[i];
        context.inParams.put(key, null); // default value is null
        if (jsonObject.has(key)) {
          inp = jsonObject.getString(key); // json parameter
          if (inp != null)
            context.inParams.put(key, inp);
        }
      }
    } catch (Exception e) {
      // throw new IOException("Error parsing JSON request string");
      Util.showError(context, ERRCODE_3, "failed to parse input json: " + e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Parse input as XML (normally from xroad)
   * 
   * @param context globals
   * @param inKeys string array of possible input params
   * @return boolean true if input parsing succeeded, false otherwise
   * @throws IOException generic catchall
   */
  public static boolean parseXml(Context context, String[] inKeys) throws IOException {
    String xml;
    StringBuffer jb = new StringBuffer();
    try {
      BufferedReader reader = context.req.getReader();
      String line;
      while ((line = reader.readLine()) != null) {
        jb.append(line);
      }
    } catch (Exception e) {
      Util.showError(context, ERRCODE_2, "cannot read input");
      return false;
    }
    xml = jb.toString();
    context.xmlstr = xml;
    // context.os.println("|"+xml+"|");
    DocumentBuilder db;
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      db = dbf.newDocumentBuilder();
    } catch (Exception e) {
      Util.showError(context, ERRCODE_4, "preparing xml parsing failed: " + e.getMessage());
      return false;
    }
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));
    Document doc;
    try {
      doc = db.parse(is);
    } catch (Exception e) {
      Util.showError(context, ERRCODE_5, "parsing xml failed: " + e.getMessage());
      return false;
    }
    context.xmldoc = doc;
    return true;
  }

  /**
   * Create database connection
   * 
   * @param context globals
   * @return Connection db connection object if ok, null if fails
   * @throws IOException generic catchall
   */
  public static Connection createDbConnection(Context context) throws IOException {
    Connection conn = null;

    // check if there is JNDI context in use for datasource:
    if (Property.DATABASE_JNDI.getValue() != null && Property.DATABASE_JNDI.getValue().length() > 0)
      try {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/" + Property.DATABASE_JNDI.getValue());
        if (ds == null)
          throw new Exception("No data source");
        conn = ds.getConnection();
      } catch (Exception e) {
        Util.showError(context, ERRCODE_7, "Failed to connect to JNDI data source");
        return null;
      }
    else {
      // check whether the driver present
      try {
        Class.forName("org.postgresql.Driver");
      } catch (ClassNotFoundException e) {
        Util.showError(context, ERRCODE_7, "failed to find the PostgreSQL JDBC Driver");
        return null;
      }
      // create db connection
      try {
        conn = DriverManager.getConnection(Property.DATABASE_CONNECTSTRING.getValue(),
            Property.DATABASE_USER.getValue(), Property.DATABASE_PASSWORD.getValue());
      } catch (Exception e) {
        Util.showError(context, ERRCODE_8, "failed to connect to the database");
        return null;
      }
    }
    return conn;
  }

  /**
   * Log and output error message
   *
   * For XML, errors with code < 10 are considered technical
   * 
   * @param context globals
   * @param code numeric errcode
   * @param msg string errmessage
   * @throws IOException generic catchall
   */
  public static void showError(Context context, int code, String msg) throws IOException {
    // resp.sendError(resp.SC_BAD_REQUEST, msg); // possible alternative to json
    // output
    String err;
    context.log.error("errcode {} errmessage {}", code, msg);
    if (context.contentType.contains("json")) {
      // json error format
      // handle potential javascript callback parameter
      if (context.inParams.get("callback") != null)
        context.w.println(context.inParams.get("callback") + "(");
      msg = msg.replace("\"", "'").replace("\n", " ").replace("\r", " ");
      context.w.println("{\"errcode\":" + code + ", \"errmessage\":\"" + msg + "\"}");
      if (context.inParams.get("callback") != null)
        context.w.println(");");
    } else {
      // xml error format for xroad
      if (code < MAX_TECH_ERROR) {
        // Technical error: no request obtained or used, no header inserted
        err = Strs.xroadTechErr;
        err = err.replace("{faultCode}", "" + code).replace("{faultString}", cleanXmlStr(msg));
        context.w.println(err);
      } else {
        // Normal error: pass request, insert header
        if (context.xrdVersion.equals("old")) {
          err = Strs.xroadErr.replace("{header}", createSoapHeader(context));
          err = err.replace("{producerns}", Property.XROAD_PRODUCERNS.getValue());
          err = err.replace("{request}", context.xrdRequest);
          err = err.replace("{faultCode}", "" + code).replace("{faultString}", cleanXmlStr(msg));
        } else {
          err = Strs.xroad40Err.replace("{header}", Util.nodeToString(context.xmlheader));
          err = err.replace("{producerns}", Property.XROAD_PRODUCERNS.getValue());
          err = err.replace("{faultCode}", "" + code).replace("{faultString}", cleanXmlStr(msg));
        }
        context.w.println(err);
      }
    }
    context.w.flush();
    context.w.close();
  }

  /**
   * Remove <,>,&," from (xml) string: used mostly for errmessages
   *
   * @param msg input string message
   * @return String cleaned string
   */
  public static String cleanXmlStr(String msg) {
    if (msg == null || msg.equals(""))
      return msg;
    msg = msg.replace("<", " ").replace(">", " ").replace("&", " ").replace("\"", "'");
    return msg;
  }

  /**
   * Output trivial OK message
   * 
   * @param context globals
   * @throws IOException generic catchall
   */
  public static void showOK(Context context) throws IOException {
    if (context.contentType.contains("json"))
      context.w.println("{\"ok\":1}");
    else
      context.w.println("<ok>1</ok>");
    context.w.flush();
    context.w.close();
  }

  /*
   * ========== Parsed XML processing utils ===============
   *
  */

 
  /**
   * Find and return the first instance of an internal node with a given tag and namespace
   *
   * @param context globals
   * @param node xml doc node to search from
   * @param name tag name
   * @param ns tag namespace
   * @return Node found node or null
   * @throws IOException generic catchall
   */
  public static Node getTag(Context context, Node node, String name, String ns) throws IOException {
    if (node == null)
      return null;
    try {
      if (isNode(node, name, ns))
        return node;
      NodeList nodeList = node.getChildNodes();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node currentNode = nodeList.item(i);
        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
          Node result = getTag(context, currentNode, name, ns);
          if (result != null)
            return result;
        }
      }
    } catch (Exception e) {
      Util.showError(context, ERRCODE_6, "error traversing xml tree: " + e.getMessage());
    }
    return null;
  }

  /**
   * Find and return the internal text from the first instance of an internal node
   * with a given tag and namespace
   *
   * @param context globals
   * @param node xml doc  node to search from
   * @param name tag name
   * @param ns tag namespace
   * @return Node found node or null
   * @throws IOException generic catchall
   */
  public static String getTagText(Context context, Node node, String name, String ns)
      throws IOException {
    Node foundNode = getTag(context, node, name, ns);
    if (foundNode == null)
      return null;
    String result = foundNode.getTextContent();
    return result;
  }

  /**
   * Check whether passed xml node has given tag name and namespace
   *
   * @param node xml doc node to check
   * @param name tag name
   * @param ns tag namespace
   * @return boolean true if has given tag name and namespace
   */
  public static boolean isNode(Node node, String name, String ns) {
    if (node == null)
      return false;
    if (node.getNodeType() != Node.ELEMENT_NODE)
      return false;
    if (ns != null && (node.getNamespaceURI() == null || !node.getNamespaceURI().equals(ns)))
      return false;
    // if (ns!=null && ! node.getNamespaceURI().equals(ns)) return false;
    String nodename = node.getNodeName();
    if (nodename.contains(":")) {
      String[] parts = nodename.split(":");
      if (parts[1].equals(name))
        return true;
    } else if (nodename.equals(name))
      return true;
    return false;
  }

  /**
   * Convert given xml node to string
   *
   * @param node xml doc node to convert
   * @return String textual form of the node
   * @throws IOException generic catchall
   */
  public static String nodeToString(Node node) throws IOException {
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

  /**
   * Parse and check X-road header: values stored to context globals.
   * While crucial for old x-road, not really important for the new xroad.
   *
   * @param context globals
   * @param node xml node to search the head from
   * @return boolean true if header was OK, false otherwise
   * @throws IOException generic catchall
   */
  public static boolean parseXroadHeader(Context context, Node node) throws IOException {
    String xrdns = "http://x-road.ee/xsd/x-road.xsd"; // old version
    String newxrdns = "http://x-road.eu/xsd/xroad.xsd"; // new version
    String idns = "http://x-road.eu/xsd/identifiers"; // used by new version
    if (node == null) {
      showError(context, ERRCODE_9, "xml message was empty");
      return false;
    }
    Node headerNode = getTag(context, node, "Header", "http://schemas.xmlsoap.org/soap/envelope/");
    if (headerNode == null) {
      showError(context, ERRCODE_9, "Message Header tag not found");
      return false;
    }
    context.xmlheader = headerNode;
    // determine xroad version
    String version = getTagText(context, headerNode, "protocolVersion", newxrdns);
    if (version == null || !version.equals("4.0")) {
      context.xrdVersion = "old"; // 2.0, 3.0, 3.1
      // parse old xroad version header
      String consumer = getTagText(context, headerNode, "consumer", xrdns);
      String producer = getTagText(context, headerNode, "producer", xrdns);
      String userId = getTagText(context, headerNode, "userId", xrdns);
      String id = getTagText(context, headerNode, "id", xrdns);
      String service = getTagText(context, headerNode, "service", xrdns);
      String issue = getTagText(context, headerNode, "issue", xrdns);
      if (producer == null) {
        showError(context, ERRCODE_9, "Message header producer not found");
        return false;
      }
      if (id == null) {
        showError(context, ERRCODE_9, "Message header id not found");
        return false;
      }
      context.xrdConsumer = consumer;
      context.xrdProducer = producer;
      context.xrdUserId = userId;
      context.xrdId = id;
      context.xrdService = service;
      context.xrdIssue = issue;
      return true;
    } else {
      // new xroad version
      context.xrdVersion = "4.0";
      // parse new xroad version header
      String userId = getTagText(context, headerNode, "userId", newxrdns);
      String id = getTagText(context, headerNode, "id", newxrdns);
      if (id == null) {
        showError(context, ERRCODE_9, "Message header id not found");
        return false;
      }
      Node headerClientNode = getTag(context, headerNode, "client", newxrdns);
      if (headerClientNode == null) {
        showError(context, ERRCODE_9, "Message client tag not found in header");
        return false;
      }
      String memberCode = getTagText(context, headerClientNode, "memberCode", idns);
      if (memberCode == null) {
        showError(context, ERRCODE_9, "Message header client memberCode not found");
        return false;
      }
      context.xrdUserId = userId;
      context.xrdId = id;
      context.xrdClientMemberCode = memberCode;
      return true;
    }
  }

  /**
   * Composing the SOAP header as string for the old x-road: not used for the new x-road
   * 
   * @param context globals
   * @return String header string to be included in a template later
   * @throws IOException generic catcall
   */
  public static String createSoapHeader(Context context) throws IOException {
    String header;
    if (context.xrdVersion.equals("4.0")) {
      // new xroad
      showError(context, ERRCODE_9, "Err in code: should not create header for new xroad");
      return "";
    } else {
      // old xroad
      header = Strs.xroadHeader;
      // from request
      header = header.replace("{consumer}", context.xrdProducer);
      header = header.replace("{consumer}", context.xrdProducer);
      header = header.replace("{id}", context.xrdId);
      // our values from configuration
      header = header.replace("{producer}", Property.XROAD_PRODUCER.getValue());
      header = header.replace("{userId}", Property.XROAD_USERID.getValue());
      header = header.replace("{service}", Property.XROAD_SERVICE.getValue());
    }
    return header;
  }
}
