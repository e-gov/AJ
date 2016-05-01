package ee.degeetia.dumonitor.filter.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

/**
 * Class for adding servlets to a {@link ServletContext}.
 */
public class ServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(ServletInitializer.class);

  private final ServletContext servletContext;

  /**
   * Creates an instance of ServletInitializer with a target {@link ServletContext} to add servlets to.
   *
   * @param servletContext the target {@link ServletContext}
   */
  public ServletInitializer(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  /**
   * Adds a servlet to the target {@link ServletContext}.
   *
   * @param servletClass    the class of the servlet to add
   * @param servletMappings the URL pattern(s) to map to the servlet being added
   */
  public void addServlet(Class<? extends Servlet> servletClass, String... servletMappings) {
    String servletName = servletClass.getSimpleName();
    LOG.info("Initializing servlet {} with mappings {}", servletName, servletMappings);

    servletContext.addServlet(servletName, servletClass).addMapping(servletMappings);
  }

}
