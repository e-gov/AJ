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
package ee.degeetia.dumonitor.storage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ee.degeetia.dumonitor.common.config.Property;

/**
 * 
 *
 */
public class Xroad extends HttpServlet {

  private static final int ERRCODE_9 = 9;
  private static final int ERRCODE_10 = 10;
  private static final int ERRCODE_11 = 11;
  private static final int ERRCODE_12 = 12;
  
  private static final int ARGPOS_1 = 1;
  private static final int ARGPOS_2 = 2;
  private static final int ARGPOS_3 = 3;
  private static final int LIMIT_MAX = 1000;
  private static final int LIMIT_DEFAULT = 1000;
  private static final long serialVersionUID = 1L;
  
  private Context context; // global vars are here

  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {
      "personcode", "action", "sender", "receiver", "restrictions", "sendercode", "receivercode", "actioncode",
      "xroadrequestid", "xroadservice", "usercode"
  };

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    context = Util.initRequest(req, resp, "text/xml", Xroad.class);
    Util.showError(context, 2, "get method not allowed for xroad requests");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    handleRequest(req, resp, true);
  }

  /**
   * First function to handle the request, wrapping all the action
   * 
   * @param req http Request object from servlet
   * @param resp http Response object from servlet
   * @param isPost true iff a post request, false iff get
   */
  private void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost)
      throws ServletException, IOException {
    try {
      context = Util.initRequest(req, resp, "text/xml", Xroad.class);
      if (context == null)
        return;
      boolean ok = Util.parseInput(req, resp, context, inKeys, isPost);
      if (!ok || context.inParams == null)
        return;
      handleXroad();
    } catch (Exception e) {
      Util.showError(context, ERRCODE_9, "unexpected error: " + e.getMessage());
    }
    context.os.flush();
    context.os.close();
  }

  /**
   * Main handler: parse the query, call fetchData, wrap result into envelope
   *
   * @throws ServletException generic catchall
   * @throws IOException generic catchall
   */
  public void handleXroad() throws ServletException, IOException {
    Document doc = context.xmldoc;
    try {
      if (!Util.parseXroadHeader(context, doc))
        return; // store header fields to context
      Node body = Util.getTag(context, doc, "Body", "http://schemas.xmlsoap.org/soap/envelope/");
      if (body == null) {
        Util.showError(context, ERRCODE_9, "message Body tag not found");
        return;
      }

      String resns = Property.XROAD_PRODUCERNS.getValue();

      Node request = null;
      if (context.xrdVersion.equals("old"))
        request = Util.getTag(context, doc, "request", null);
      else
        request = Util.getTag(context, doc, "findUsage", resns);
      if (request == null) {
        Util.showError(context, ERRCODE_9, "message request tag not found");
        return;
      }
      String requestStr = Util.nodeToString(request);
      if (requestStr == null) {
        Util.showError(context, ERRCODE_9, "failed to convert message request to string");
        return;
      }
      context.xrdRequest = requestStr;

      String offsets = Util.getTagText(context, body, "offset", null);
      String limits = Util.getTagText(context, body, "limit", null);
      int offset = 0;
      int limit = LIMIT_DEFAULT;
      try {
        offset = Math.abs(Integer.parseInt(offsets));
      } catch (Exception e) {
        offset = 0;
      }
      try {
        limit = Math.abs(Integer.parseInt(limits));
      } catch (Exception e) {
        limit = LIMIT_DEFAULT;
      }
      if (limit > LIMIT_MAX)
        limit = LIMIT_MAX;

      String personcode = context.xrdUserId;
      if (personcode == null) {
        Util.showError(context, ERRCODE_11, "Message userId not found");
        return;
      }
      personcode = personcode.trim();
      if (personcode == null || personcode.equals("")) {
        Util.showError(context, ERRCODE_12, "Message userId was empty");
        return;
      }

      String result = fetchData(personcode, offset, limit);
      String restag;
      if (context.xrdVersion.equals("old"))
        restag = "response";
      else
        restag = "findUsageResponse";
      if (resns != null && !resns.equals("") && !context.xrdVersion.equals("old")) {
        result = "<p:" + restag + " xmlns:p=\"" + resns.trim() + "\">\n" + result + "</p:" + restag + ">";
      } else {
        result = "<" + restag + ">\n" + result + "</" + restag + ">";
      }
      String envelope;
      if (context.xrdVersion.equals("old")) {
        envelope = Strs.xroadMessage.replace("{header}", Util.createSoapHeader(context));
        envelope = envelope.replace("{producerns}", Property.XROAD_PRODUCERNS.getValue());
        envelope = envelope.replace("{request}", requestStr);
        envelope = envelope.replace("{response}", result);
      } else {
        envelope = Strs.xroad40Message.replace("{header}", Util.nodeToString(context.xmlheader));
        envelope = envelope.replace("{producerns}", Property.XROAD_PRODUCERNS.getValue());
        envelope = envelope.replace("{response}", result);
      }
      context.os.println(envelope);
      context.os.flush();
      context.os.close();
    } catch (java.lang.NullPointerException e) {
      Util.showError(context, ERRCODE_9, "unknown error: " + e.getMessage());
      context.os.flush();
      context.os.close();
    }
  }

  /**
   * Query the database and build a partial result to be wrapped in the soap
   * envelope later
   *
   * @param personcode person to search for from logs
   * @param offset resultset starts from here (0,1,...)
   * @param limit max size of resultset
   * @return String result xml to be wrapped into envelope later
   * @throws ServletException generic catchall
   * @throws IOException generic catchall
   */
  public String fetchData(String personcode, int offset, int limit) throws ServletException, IOException {
    String result = null;

    Connection conn = Util.createDbConnection(context);
    if (conn == null)
      return null;

    // query data

    try {
      // show just logtime, action, receiver
      String sql = "select " + "to_char(logtime,'YYYY-MM-DDTHH24:MI:SS') as logtime,action,receiver" + " from ajlog "
          + " where personcode=? " + " and ((restrictions is null) or restrictions!='P') "
          + " order by id desc limit ? offset ?";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(ARGPOS_1, personcode);
      preparedStatement.setInt(ARGPOS_2, limit);
      preparedStatement.setInt(ARGPOS_3, offset);
      // context.os.println(preparedStatement.toString());

      // execute select SQL stetement
      ResultSet rs = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      result = "";
      while (rs.next()) {
        int numColumns = rs.getMetaData().getColumnCount();
        String row = "<usage>";
        for (int i = 1; i <= numColumns; i++) {
          // Column numbers start at 1.
          row += "<" + rsmd.getColumnName(i) + ">";
          if (rs.getObject(i) != null) {
            row += Util.cleanXmlStr(rs.getObject(i) + "");
          }
          row += "</" + rsmd.getColumnName(i) + ">";
        }
        row += "</usage>\n";
        result += row;
      }
    } catch (Exception e) {
      Util.showError(context, ERRCODE_10, "database query error: " + e.getMessage());
      return null;
    } finally {
      // It's important to close the connection when you are done with it
      try {
        conn.close();
      } catch (Throwable ignore) {
        Util.showError(context, ERRCODE_9, "cannot close database ");
      }
    }
    return result;
  }

}
