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
package ee.degeetia.dumonitor.query;

import java.net.MalformedURLException;
import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.servlets.ProxyServlet;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;

/**
 * Proxy forwards SOAP messages from the browser test app to x-road
 * and returns the answers received
 *
 */
public class Proxy extends ProxyServlet {
  
  URI proxyServer;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    // Get proxy URL from configuration:
    loadProperties();
  }

  @Override
  protected HttpURI proxyHttpURI(String scheme, String serverName, int serverPort, String uri)
      throws MalformedURLException {
    return new HttpURI(proxyServer.resolve(uri));
  }

  protected void loadProperties() {
    PropertyLoader.loadProperties(Property.class, "dumonitor.properties", "default.properties");
    try {
      proxyServer = URI.create(Property.QUERY_TURVASERVER_URL.getValue());
    } catch (IllegalArgumentException e) {
      ExceptionUtil.uncheck("Invalid security server URL", e);
    }
  }
}
