package ee.degeetia.xrtracker.filter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

public class ServletInitializer {

  private static final Logger LOG = LogManager.getLogger(ServletInitializer.class);

  public void addServlet(ServletContext context, Class<? extends Servlet> servletClass, String... servletMappings) {
    String servletName = servletClass.getSimpleName();
    LOG.info("Initializing servlet {} with mappings {}", servletName, servletMappings);

    context.addServlet(servletName, servletClass).addMapping(servletMappings);
  }

}
