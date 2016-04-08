package ee.degeetia.testutils.jetty;

import org.junit.After;
import org.junit.Before;

import javax.servlet.Servlet;

public class EmbeddedJettyIntegrationTest {

  private final EmbeddedJettyHttpServer server;

  protected EmbeddedJettyIntegrationTest(EmbeddedJettyHttpServer server) {
    this.server = server;
  }

  @Before
  public void startServer() throws Exception {
    server.start();
  }

  @After
  public void stopServer() throws Exception {
    server.stop();
  }

  protected String getApplicationUrl() {
    return "http://localhost:" + server.getPort();
  }

  /**
   * Use in public {@link Before} methods to create test servlets for integration tests
   */
  protected void createServlet(Servlet servlet, String urlPattern) {
    server.createServlet(servlet, urlPattern);
  }

  /**
   * @see #createServlet(Servlet, String)
   */
  protected void createServlet(Class<? extends Servlet> servletClass, String urlPattern) {
    server.createServlet(servletClass, urlPattern);
  }

}
