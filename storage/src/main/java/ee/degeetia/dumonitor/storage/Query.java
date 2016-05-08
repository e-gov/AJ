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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Query is a REST service used by the internal use webpage for searching from the log database:
 * cgi or json parameters, json output
 */
public class Query extends HttpServlet {
  
  private static final int ERRCODE_9 = 9; // technical error
  private static final int ERRCODE_10 = 10; // nontechnical error
  
  private static final int MAX_PARAMS = 20;
  private static final int LIMIT_MAX = 1000;
  private static final int LIMIT_DEFAULT = 100;
  private static final long serialVersionUID = 1L;
  
  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {
      "personcode", "action", "sender", "receiver", "restrictions", "sendercode", "receivercode", "actioncode",
      "xroadrequestid", "xroadservice", "usercode", "offset", "limit", "from_date", "to_date", "callback"
  };

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    handleRequest(req, resp, false);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    handleRequest(req, resp, true);
  }

  /**
   * First function to handle the request, wrapping all the action:
   * parse input, call handler, flush and close connection
   * 
   * @param req http Request object from servlet
   * @param resp http Response object from servlet
   * @param isPost true iff a post request, false iff get
   */
  private void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost)
  throws ServletException, IOException {
    Context context = null;
    try {
      context = Util.initRequest(req, resp, "application/json", Query.class);
      if (context == null) return;
      boolean ok = Util.parseInput(req, resp, context, inKeys, isPost);
      if (!ok || context.inParams == null) return;
      handleQueryParams(context);
    } catch (Exception e) {
      Util.showError(context, ERRCODE_9, "unexpected error: " + e.getMessage());
    }
    context.os.flush();
    context.os.close();
  }

  /**
   * Store parsed parameters passed as hashmap
   * 
   * @param context Request context
   * @throws ServletException generic catchall
   * @throws IOException generic catchall
   */
  public void handleQueryParams(Context context) throws ServletException, IOException {
    Connection conn = Util.createDbConnection(context);
    if (conn == null)
      return;

    // set pagination values

    int offset = 0;
    int limit = LIMIT_DEFAULT;

    try {
      offset = Math.abs(Integer.parseInt(context.inParams.get("offset")));
    } catch (Exception e) {
      offset = 0;
    }
    try {
      limit = Math.abs(Integer.parseInt(context.inParams.get("limit")));
    } catch (Exception e) {
      limit = LIMIT_DEFAULT;
    }
    if (limit > LIMIT_MAX)
      limit = LIMIT_MAX;

    // prepare filter data

    String where = " where 1=1 ";
    String[] paramarr = new String[MAX_PARAMS];
    int paramnr = 1;

    // create filters

    for (int i = 0; i < inKeys.length; i++) {
      // ignore non-sql parameters
      if (inKeys[i].equals("offset") || inKeys[i].equals("limit") || inKeys[i].equals("callback"))
        continue;
      // here we have only sql parameters
      if (context.inParams.get(inKeys[i]) != null) {
        if (inKeys[i].equals("personcode")) {
          where = where + " and " + inKeys[i] + "=? ";
        } else if (inKeys[i].equals("from_date")) {
          where = where + " and logtime>=to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') ";
        } else if (inKeys[i].equals("to_date")) {
          where = where + " and logtime<=to_timestamp(?,'YYYY-MM-DD HH24:MI:SS') ";
        } else {
          where = where + " and " + inKeys[i] + " like ? ";
        }
        paramarr[paramnr] = inKeys[i];
        paramnr++;
      }
    }

    // query data

    try {
      String sql = "select " + "personcode,to_char(logtime,'YYYY-MM-DD HH24:MI:SS') as logtime,action,"
          + "sender,receiver,restrictions,sendercode,receivercode,actioncode,"
          + "xroadrequestid,xroadservice,usercode " + " from ajlog " + where
          + " order by id desc limit ? offset ?";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      for (int i = 1; i < paramnr; i++) {
        // context.os.println("i: "+i+" "+paramarr[i]+"
        // "+context.inParams.get(paramarr[i]));
        if (paramarr[i].equals("personcode") || paramarr[i].equals("from_date") || paramarr[i].equals("to_date")) {
          preparedStatement.setString(i, context.inParams.get(paramarr[i]));
        } else {
          preparedStatement.setString(i, "%" + context.inParams.get(paramarr[i]) + "%");
        }
      }
      preparedStatement.setInt(paramnr, limit);
      preparedStatement.setInt(paramnr + 1, offset);

      // context.os.println(preparedStatement.toString());

      // execute select SQL stetement
      ResultSet rs = preparedStatement.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      JSONArray array = new JSONArray();
      JSONObject obj = null;
      while (rs.next()) {
        int numColumns = rs.getMetaData().getColumnCount();
        obj = new JSONObject();
        for (int i = 1; i <= numColumns; i++) {
          // Column numbers start at 1.
          obj.put(rsmd.getColumnName(i), rs.getObject(i));
        }
        array.put(obj);
      }
      // handle potential javascript callback parameter
      if (context.inParams.get("callback") != null) {
        context.os.println(context.inParams.get("callback") + "(");
      }
      context.os.println(array.toString(1));
      if (context.inParams.get("callback") != null) {
        context.os.println(");");
      }
    } catch (SQLException e) {
      Util.showError(context, ERRCODE_10, "database error: " + e.getMessage());
    } catch (JSONException e) {
      Util.showError(context, ERRCODE_10, "json formatting error: " + e.getMessage());
    } catch (IOException e) {
      Util.showError(context, ERRCODE_10, "response sending error: " + e.getMessage());
    } catch (Exception e) {
      Util.showError(context, ERRCODE_10, "query execution error: " + e.getMessage());
    } finally {
      // It's important to close the connection when you are done with it
      try {
        conn.close();
      } catch (Throwable ignore) {
        Util.showError(context, ERRCODE_9, "cannot close database ");
      }
    }
  }
}
