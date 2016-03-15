package ee.degeetia.xrtracker.filter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

public class ServletInitializer {

  private static final Logger LOG = LogManager.getLogger(ServletInitializer.class);

  private final ServletContext servletContext;

  public ServletInitializer(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public void addServlet(Class<? extends Servlet> servletClass, String... servletMappings) {
    String servletName = servletClass.getSimpleName();
    LOG.info("Initializing servlet {} with mappings {}", servletName, servletMappings);

    servletContext.addServlet(servletName, servletClass).addMapping(servletMappings);
  }

}
