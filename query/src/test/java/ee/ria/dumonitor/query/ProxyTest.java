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
package ee.ria.dumonitor.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.eclipse.jetty.http.HttpURI;
import org.junit.Test;

import ee.ria.dumonitor.common.config.Property;
import ee.ria.dumonitor.query.Proxy;

/**
 *
 */
public class ProxyTest {

  private static final int PROXY_PORT = 1234;

  /**
   *
   * @throws ServletException
   */
  @Test
  public void testInitServletConfig() throws ServletException {
    Proxy p = new Proxy();

    p.loadProperties();
    // Kontrollime tulemust:
    assertNotNull("Vigane konfiguratsioon", Property.QUERY_TURVASERVER_URL.getValue());
    assertNotNull("Proxy serveri URL on NULL", p.proxyServer);
    assertEquals("Proxy serveri URL pole õige", Property.QUERY_TURVASERVER_URL.getValue(), p.proxyServer.toString());
  }

  /**
   * Ühiktest proxyHttpURI meetodi jaoks
   * 
   * @throws MalformedURLException
   * @throws ServletException
   */
  @Test
  public void testProxyHttpURIStringStringIntString() throws MalformedURLException, ServletException {

    Proxy p = new Proxy();
    p.loadProperties();
    assertNotNull("Vigane konfiguratsioon", Property.QUERY_TURVASERVER_URL.getValue());

    String path = "/test/123?param=value&param2=value2";
    String requiredResult = Property.QUERY_TURVASERVER_URL.getValue();
    if (requiredResult.charAt(requiredResult.length() - 1) == '/')
      requiredResult = requiredResult.substring(0, requiredResult.length() - 1);
    requiredResult = requiredResult + path;

    HttpURI result = p.proxyHttpURI("http", "mingi.server.ee", PROXY_PORT, path);

    String resultStr = result.toString();

    assertNotNull("Teisendatud URL on null", result);
    assertNotNull("Teisendatud URL on vigane", resultStr);
    assertEquals("Teisendus on vigane", requiredResult, resultStr);
  }

}
