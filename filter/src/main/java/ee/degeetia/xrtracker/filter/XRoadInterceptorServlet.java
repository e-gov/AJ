package ee.degeetia.xrtracker.filter;

import ee.degeetia.xrtracker.filter.core.config.Property;
import ee.degeetia.xrtracker.filter.util.HttpUtil;
import ee.degeetia.xrtracker.filter.util.IOUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class where the main logic happens in this application.
 */
public class XRoadInterceptorServlet extends HttpServlet {

  private static final Logger LOG = LogManager.getLogger(XRoadInterceptorServlet.class);

  // Request path -> Target WS URL
  private static final Map<String, String> URL_MAPPINGS = new ConcurrentHashMap<String, String>();

  static {
    URL_MAPPINGS.put(Property.ANDMEKOGU_INTERCEPTOR_PATH.getValue(), Property.ANDMEKOGU_URL.getValue());
    URL_MAPPINGS.put(Property.TURVASERVER_INTERCEPTOR_PATH.getValue(), Property.TURVASERVER_URL.getValue());
  }

  private final XRoadMessageProcessor messageProcessor = new XRoadMessageProcessor();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpURLConnection connection = openConnection(URL_MAPPINGS.get(request.getRequestURI()));

    forwardRequest(request, connection, processRequest(request));
    forwardResponse(response, connection, processResponse(connection));
  }

  // TODO: might also be HttpsURLConnection
  private HttpURLConnection openConnection(String url) throws IOException {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setDoOutput(true);
      return connection;
    } catch (IOException e) {
      LOG.error("Error opening HTTP connection to " + url, e);
      throw e;
    }
  }

  private byte[] processRequest(HttpServletRequest request) throws IOException {
    return processMessage(request.getInputStream(), request.getContentType());
  }

  private byte[] processResponse(HttpURLConnection connection) throws IOException {
    return processMessage(connection.getInputStream(), connection.getContentType());
  }

  private byte[] processMessage(InputStream inputStream, String contentType) throws IOException {
    byte[] content = IOUtil.readBytes(inputStream);
    messageProcessor.submitForProcessing(content, contentType);
    return content;
  }

  private void forwardRequest(HttpServletRequest req, HttpURLConnection con, byte[] data) throws IOException {
    HttpUtil.copyRequestHeaders(req, con);
    IOUtil.writeBytes(data, con.getOutputStream());
  }

  private void forwardResponse(HttpServletResponse resp, HttpURLConnection con, byte[] data) throws IOException {
    HttpUtil.copyResponseHeaders(con, resp);
    IOUtil.writeBytes(data, resp.getOutputStream());
  }

}
