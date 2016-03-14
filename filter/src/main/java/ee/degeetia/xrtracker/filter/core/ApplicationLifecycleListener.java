package ee.degeetia.xrtracker.filter.core;

import ee.degeetia.xrtracker.filter.XRoadInterceptorServlet;
import ee.degeetia.xrtracker.filter.core.config.Property;
import ee.degeetia.xrtracker.filter.core.config.PropertyLoader;
import ee.degeetia.xrtracker.filter.util.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class ApplicationLifecycleListener implements ServletContextListener {

  private static final Logger LOG = LogManager.getLogger(ApplicationLifecycleListener.class);

  private final PropertyLoader propertyLoader = new PropertyLoader();
  private final ServletInitializer servletInitializer = new ServletInitializer();
  private final ExecutorManager executorManager = ExecutorManager.getManager();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      propertyLoader.loadProperties("test.properties", "default.properties");
    } catch (IOException e) {
      ExceptionUtil.uncheck("Failed to load properties", e);
    }

    servletInitializer.addServlet(sce.getServletContext(),
                                  XRoadInterceptorServlet.class,
                                  Property.ANDMEKOGU_INTERCEPTOR_PATH.getValue(),
                                  Property.TURVASERVER_INTERCEPTOR_PATH.getValue());

    LOG.info("Application started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOG.info("Stopping application");
    executorManager.shutdownAll();
  }

}
