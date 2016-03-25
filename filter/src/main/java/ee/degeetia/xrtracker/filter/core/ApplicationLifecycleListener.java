package ee.degeetia.xrtracker.filter.core;

import ee.degeetia.xrtracker.filter.XRoadInterceptorServlet;
import ee.degeetia.xrtracker.filter.config.properties.Property;
import ee.degeetia.xrtracker.filter.config.properties.PropertyLoader;
import ee.degeetia.xrtracker.filter.config.xpath.XPathExpressionsConfiguration;
import ee.degeetia.xrtracker.filter.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class ApplicationLifecycleListener implements ServletContextListener {

  private static final Logger LOG = LogManager.getLogger(ApplicationLifecycleListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    loadProperties();
    compileXPathExpressions();
    addServlets(sce.getServletContext());

    LOG.info("Application started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOG.info("Stopping application");
    ExecutorManager.getManager().shutdownAll();
  }

  private void loadProperties() {
    PropertyLoader propertyLoader = new PropertyLoader();
    try {
      propertyLoader.loadProperties("test.properties", "default.properties");
    } catch (IOException e) {
      ExceptionUtil.uncheck("Failed to load properties", e);
    }
  }

  private void compileXPathExpressions() {
    new XPathExpressionsConfiguration(); // FIXME: compilation is currently started by static block
  }

  private void addServlets(ServletContext servletContext) {
    ServletInitializer servletInitializer = new ServletInitializer(servletContext);
    servletInitializer.addServlet(XRoadInterceptorServlet.class,
                                  Property.ANDMEKOGU_INTERCEPTOR_PATH.getString(),
                                  Property.TURVASERVER_INTERCEPTOR_PATH.getString());
  }

}
