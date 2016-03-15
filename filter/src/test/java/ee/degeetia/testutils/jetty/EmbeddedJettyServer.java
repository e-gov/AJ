package ee.degeetia.testutils.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.Servlet;

public class EmbeddedJettyServer extends Server {

  public EmbeddedJettyServer() {
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

}
