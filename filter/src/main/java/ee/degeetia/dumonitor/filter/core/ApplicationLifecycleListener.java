package ee.degeetia.dumonitor.filter.core;

import ee.degeetia.dumonitor.common.config.properties.Property;
import ee.degeetia.dumonitor.common.config.properties.PropertyLoader;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.filter.XRoadInterceptorServlet;
import ee.degeetia.dumonitor.filter.config.filter.FilterConfigurationManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Listens for changes to the servlet context. Actions related to application startup and shutdown should be taken in
 * this class.
 */
public class ApplicationLifecycleListener implements ServletContextListener {

  private static final Logger LOG = LogManager.getLogger(ApplicationLifecycleListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    loadProperties();
    loadXmlConfiguration();
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
      propertyLoader.loadProperties("dumonitor.properties", "test.properties", "default.properties");
    } catch (IOException e) {
      ExceptionUtil.uncheck("Failed to load properties", e);
    }
  }

  private void loadXmlConfiguration() {
    try {
      FilterConfigurationManager.getManager().loadConfiguration();
    } catch (JAXBException e) {
      ExceptionUtil.uncheck("Failed to read XPath configuration", e);
    } catch (XPathExpressionException e) {
      ExceptionUtil.uncheck("Failed to compile XPath expressions", e);
    }
  }

  private void addServlets(ServletContext servletContext) {
    ServletInitializer servletInitializer = new ServletInitializer(servletContext);
    servletInitializer.addServlet(XRoadInterceptorServlet.class,
                                  Property.ANDMEKOGU_INTERCEPTOR_PATH.getString(),
                                  Property.TURVASERVER_INTERCEPTOR_PATH.getString());
  }

}
