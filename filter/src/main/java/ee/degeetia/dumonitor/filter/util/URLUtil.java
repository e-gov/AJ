package ee.degeetia.dumonitor.filter.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utility class for dealing with URL-s.
 */
public final class URLUtil {

  private URLUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * @param baseUrl base application URL (including protocol, hostname and port)
   * @param url     absolute or relative URL
   * @return URL object representing the absolute URL
   * @throws MalformedURLException if a valid URL could not be constructed from the parameters
   */
  public static URL getAbsoluteURL(String baseUrl, String url) throws MalformedURLException {
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      try {
        return new URL(baseUrl + url);
      } catch (MalformedURLException e1) {
        try {
          return new URL(baseUrl + '/' + url);
        } catch (MalformedURLException e2) {
          throw e;
        }
      }
    }
  }

  public static String withoutPath(String urlStr) throws MalformedURLException {
    URL url = new URL(urlStr);
    return url.getProtocol() + "://" + url.getAuthority();
  }

}
