package ee.degeetia.dumonitor.filter;

import ee.degeetia.dumonitor.filter.config.properties.Property;
import ee.degeetia.dumonitor.filter.config.properties.RuntimeProperty;
import ee.degeetia.dumonitor.filter.processor.MessageProcessorQueue;
import ee.degeetia.dumonitor.filter.util.HttpUtil;
import ee.degeetia.dumonitor.filter.util.IOUtil;
import ee.degeetia.dumonitor.filter.util.URLUtil;
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
 * The class where the main logic happens in this application. Forwards all POST requests unchanged to the server
 * specified in properties and returns the response, again unchanged. Message contents are submitted to a queue for
 * later processing in separate threads.
 */
public class XRoadInterceptorServlet extends HttpServlet {

  private static final Logger LOG = LogManager.getLogger(XRoadInterceptorServlet.class);

  // Request path -> Target WS URL
  private static final Map<String, URL> URL_MAPPINGS = new ConcurrentHashMap<String, URL>();

  static {
    URL_MAPPINGS.put(Property.ANDMEKOGU_INTERCEPTOR_PATH.getString(), Property.ANDMEKOGU_URL.getURL());
    URL_MAPPINGS.put(Property.TURVASERVER_INTERCEPTOR_PATH.getString(), Property.TURVASERVER_URL.getURL());
  }

  private final MessageProcessorQueue queue = new MessageProcessorQueue();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    setRuntimeProperties(request);

    HttpURLConnection connection = openConnection(URL_MAPPINGS.get(request.getServletPath()));

    forwardRequest(request, connection, processRequest(request));
    forwardResponse(response, connection, processResponse(connection));
  }

  // TODO: might also be HttpsURLConnection
  private HttpURLConnection openConnection(URL url) throws IOException {
    try {
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
    queue.submit(content, contentType);
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

  private void setRuntimeProperties(HttpServletRequest request) throws IOException {
    if (!RuntimeProperty.APPLICATION_URL.isSet()) {
      RuntimeProperty.APPLICATION_URL.setValue(URLUtil.withoutPath(request.getRequestURL().toString()));
    }
  }

}
