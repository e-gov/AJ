package ee.degeetia.dumonitor.filter.core;

import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.filter.heartbeat.HeartbeatServlet;
import ee.degeetia.dumonitor.filter.XRoadInterceptorServlet;
import ee.degeetia.dumonitor.filter.config.FilterConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Date;

/**
 * Listens for changes to the servlet context. Actions related to application startup and shutdown should be taken in
 * this class.
 */
public class ApplicationLifecycleListener implements ServletContextListener {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationLifecycleListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    RuntimeProperty.APPLICATION_STARTUP_TIME.setValue(new Date());

    loadProperties();
    loadXmlConfiguration();
    addServlets(sce.getServletContext());

    LOG.info("Application started");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOG.info("Stopping application");
    ExecutorManager.shutdownAll();
  }

  private void loadProperties() {
    PropertyLoader propertyLoader = new PropertyLoader();
    try {
      propertyLoader.loadProperties(Property.class, "dumonitor.properties", "default.properties");
      propertyLoader.loadProperties(BuildProperty.class, "build.properties");
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
                                  Property.ANDMEKOGU_INTERCEPTOR_PATH.getValue(),
                                  Property.TURVASERVER_INTERCEPTOR_PATH.getValue());
    servletInitializer.addServlet(HeartbeatServlet.class, Property.HEARTBEAT_PATH.getValue());
  }

}
