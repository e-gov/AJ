package ee.degeetia.xrtracker.filter.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

public final class HttpUtil {

  public static final String HEADER_CONTENT_TYPE = "Content-Type";
  public static final String HEADER_CONTENT_LENGTH = "Content-Length";

  private HttpUtil() {
    throw new UnsupportedOperationException();
  }

  public static void copyRequestHeaders(HttpServletRequest from, HttpURLConnection to) throws IOException {
    to.setRequestMethod(from.getMethod());
    to.setRequestProperty(HEADER_CONTENT_TYPE, from.getContentType());
    to.setRequestProperty(HEADER_CONTENT_LENGTH, Integer.toString(from.getContentLength()));
  }

  public static void copyResponseHeaders(HttpURLConnection from, HttpServletResponse to) throws IOException {
    to.setStatus(from.getResponseCode());
    to.setContentType(from.getContentType());
    to.setContentLength(from.getContentLength());
  }

}
