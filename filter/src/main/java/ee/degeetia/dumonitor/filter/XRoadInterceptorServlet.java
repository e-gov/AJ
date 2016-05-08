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
package ee.degeetia.dumonitor.filter;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.common.util.HttpUtil;
import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.common.util.URLUtil;
import ee.degeetia.dumonitor.filter.http.HttpStatus;
import ee.degeetia.dumonitor.filter.processor.MessageProcessorQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static final Logger LOG = LoggerFactory.getLogger(XRoadInterceptorServlet.class);

  // Request path -> Target WS URL
  private static final Map<String, URL> URL_MAPPINGS = new ConcurrentHashMap<String, URL>();

  static {
    URL_MAPPINGS.put(Property.ANDMEKOGU_INTERCEPTOR_PATH.getValue(), Property.ANDMEKOGU_URL.getURL());
    URL_MAPPINGS.put(Property.TURVASERVER_INTERCEPTOR_PATH.getValue(), Property.TURVASERVER_URL.getURL());
  }

  private final MessageProcessorQueue queue = new MessageProcessorQueue();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    setRuntimeProperties(request);

    HttpURLConnection connection = openConnection(URL_MAPPINGS.get(request.getServletPath()));

    forwardRequest(request, connection);
    forwardResponse(connection, response);
  }

  private HttpURLConnection openConnection(URL url) throws IOException {
    try {
      HttpURLConnection connection = HttpUtil.openConnection(url);
      connection.setDoOutput(true);
      return connection;
    } catch (IOException e) {
      if (LOG.isErrorEnabled()) {
        LOG.error("Error opening connection to " + url, e);
      }
      throw e;
    }
  }

  private void forwardRequest(HttpServletRequest req, HttpURLConnection con) throws IOException {
    HttpUtil.copyRequestHeaders(req, con);
    byte[] data = processMessage(req.getInputStream(), req.getContentType());
    IOUtil.writeBytes(data, con.getOutputStream());
  }

  private void forwardResponse(HttpURLConnection con, HttpServletResponse resp) throws IOException {
    HttpUtil.copyResponseHeaders(con, resp);
    InputStream stream = new HttpStatus(con.getResponseCode()).isError() ? con.getErrorStream() : con.getInputStream();
    byte[] data = processMessage(stream, con.getContentType());
    IOUtil.writeBytes(data, resp.getOutputStream());
  }

  private byte[] processMessage(InputStream inputStream, String contentType) throws IOException {
    byte[] content = IOUtil.readBytes(inputStream);
    queue.submit(content, contentType);
    return content;
  }

  private void setRuntimeProperties(HttpServletRequest request) throws IOException {
    if (!RuntimeProperty.APPLICATION_URL.isSet()) {
      RuntimeProperty.APPLICATION_URL.setValue(URLUtil.getBasePath(request.getRequestURL().toString()));
    }
  }

}
