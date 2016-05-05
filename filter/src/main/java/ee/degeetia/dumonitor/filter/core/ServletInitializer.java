/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
