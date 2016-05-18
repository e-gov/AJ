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
package ee.ria.testutils.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.Servlet;

public class EmbeddedJettyHttpServer extends Server {

  public EmbeddedJettyHttpServer() {
    super(8123);
    WebAppContext context = new WebAppContext();
    context.setContextPath("/");
    context.setBaseResource(new ResourceCollection(new String[]{"src/main/webapp", "src/main/resources"}));
    context.setParentLoaderPriority(true);
    setHandler(context);
  }

  public void createServlet(Class<? extends Servlet> servletClass, String urlPattern) {
    ((WebAppContext) getHandler()).addServlet(servletClass, urlPattern);
  }

  public void createServlet(Servlet servlet, String urlPattern) {
    ((WebAppContext) getHandler()).addServlet(new ServletHolder(servlet), urlPattern);
  }

  public int getPort() {
    int port = getConnectors()[0].getLocalPort();
    if (port == -1) {
      throw new IllegalStateException("Server is not running");
    }
    return port;
  }

}
