package ee.degeetia.dumonitor.common.util;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static ee.degeetia.dumonitor.common.util.ObjectUtil.eq;

/**
 * Utility class for HTTP related actions.
 */
public final class HttpUtil {

  public static final String HEADER_CONTENT_TYPE = "Content-Type";
  public static final String HEADER_CONTENT_LENGTH = "Content-Length";

  private HttpUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Copies the request headers from an {@link HttpServletRequest} to an {@link HttpURLConnection}.
   *
   * @param from the {@link HttpServletRequest} to copy from
   * @param to   the {@link HttpURLConnection} to copy to
   * @throws IOException if an I/O error occurs
   */
  public static void copyRequestHeaders(HttpServletRequest from, HttpURLConnection to) throws IOException {
    to.setRequestMethod(from.getMethod());
    to.setRequestProperty(HEADER_CONTENT_TYPE, from.getContentType());
    to.setRequestProperty(HEADER_CONTENT_LENGTH, Integer.toString(from.getContentLength()));
  }

  /**
   * Copies the response headers from an {@link HttpURLConnection} to an {@link HttpServletResponse}.
   *
   * @param from the {@link HttpURLConnection} to copy from
   * @param to   the {@link HttpServletResponse} to copy to
   * @throws IOException if an I/O error occurs
   */
  public static void copyResponseHeaders(HttpURLConnection from, HttpServletResponse to) throws IOException {
    to.setStatus(from.getResponseCode());
    to.setContentType(from.getContentType());
    to.setContentLength(from.getContentLength());
  }

  /**
   * Opens an HTTP or HTTPS connection depending on the protocol of the URL.
   *
   * @param url the URL to connect to
   * @return the HttpURLConnection object
   * @throws IOException if an I/O error occurs
   */
  public static HttpURLConnection openConnection(URL url) throws IOException {
    if (!eq(url.getProtocol(), "http", "https")) {
      throw new IllegalArgumentException("Invalid protocol: " + url.getProtocol());
    }
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    if (httpURLConnection instanceof HttpsURLConnection) {
      HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
      SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
    }
    return httpURLConnection;
  }

}
