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
package ee.degeetia.dumonitor.common.util;

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

  /**
   * Returns the base path of the input URL.
   *
   * @param urlStr the input URL as a String
   * @return the base path of the input URL
   * @throws MalformedURLException if the input String does not represent a valid URL
   */
  public static String getBasePath(String urlStr) throws MalformedURLException {
    URL url = new URL(urlStr);
    return url.getProtocol() + "://" + url.getAuthority();
  }

}
