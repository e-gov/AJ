package ee.degeetia.testutils.jetty;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.servlet.Servlet;

public class EmbeddedJettyIntegrationTest {

  private static EmbeddedJettyServer server = new EmbeddedJettyServer();

  protected EmbeddedJettyIntegrationTest() {
  }

  @BeforeClass
  public static void startServer() throws Exception {
    server.start();
  }

  @AfterClass
  public static void stopServer() throws Exception {
    server.stop();
  }

  /**
   * Use in public static {@link BeforeClass} methods to create test servlets for integration tests
   */
  protected static void createServlet(Servlet servlet, String urlPattern) {
    server.createServlet(servlet, urlPattern);
  }

  /**
   * @see #createServlet(Servlet, String)
   */
  protected static void createServlet(Class<? extends Servlet> servletClass, String urlPattern) {
    server.createServlet(servletClass, urlPattern);
  }

}
