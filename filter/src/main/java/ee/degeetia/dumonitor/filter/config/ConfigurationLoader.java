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
package ee.degeetia.dumonitor.filter.config;

import ee.degeetia.dumonitor.common.util.*;
import ee.degeetia.dumonitor.filter.config.generated.FilterConfiguration;
import ee.degeetia.dumonitor.filter.config.generated.LoggableFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that loads the filter configuration file (XML) and precompiles the XPath expressions in it.
 */
public final class ConfigurationLoader {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationLoader.class);

  private static FilterConfiguration[] loadedConfigurations;

  private ConfigurationLoader() {
    throw new UnsupportedOperationException();
  }

  /**
   * Loads the specified filter configuration files from the classpath and unmarshals the XML contents into
   * corresponding JAXB objects. This method must be called at application startup.
   * <p>
   * This method throws a RuntimeException if an XML file cannot be parsed or an XPath expression cannot be compiled
   *
   * @param filenames an array of filenames to load configurations from
   */
  public static void loadConfiguration(String... filenames) {
    loadedConfigurations = new FilterConfiguration[filenames.length];

    for (int i = 0; i < filenames.length; i++) {
      String filename = filenames[i];
      try {
        loadedConfigurations[i] = readConfigurationFile(filename);
      } catch (JAXBException e) {
        ExceptionUtil.uncheck("Failed to read XPath configuration from " + filename, e);
      }
    }

    precompile(); // Dry run to discover errors at application startup
  }

  /**
   * Compiles the XPath expressions from loaded configuration files and merges the loaded configurations (default,
   * custom, etc) into a single object.
   * <p>
   * Note that the XPathExpression class (instances of which the returned object contains) is not thread safe.
   * <p>
   * This method throws a RuntimeException if an XPath expression cannot be compiled
   *
   * @return the filter configuration object with compiled XPath expressions
   */
  public static synchronized FilterConfig precompile() {
    try {
      List<Filter> filters = compileFilters(loadedConfigurations);
      List<Exclusion> exclusions = compileExclusions(loadedConfigurations);
      return new FilterConfig(filters, exclusions);
    } catch (XPathExpressionException e) {
      throw ExceptionUtil.toUnchecked("Failed to compile XPath expressions", e);
    }
  }

  private static FilterConfiguration readConfigurationFile(String filename) throws JAXBException {
    LOG.debug("Parsing configuration file {}", filename);
    InputStream inputStream = ResourceUtil.getClasspathResourceAsStream(filename);
    try {
      return XmlUtil.unmarshal(inputStream, FilterConfiguration.class);
    } finally {
      IOUtil.close(inputStream);
    }
  }

  private static List<Filter> compileFilters(FilterConfiguration... configurations) throws XPathExpressionException {
    Map<String, XPathExpression> defaults = compileDefaults(configurations);
    return compileFilters(defaults, configurations);
  }


  private static Map<String, XPathExpression> compileDefaults(FilterConfiguration... configurations) throws
      XPathExpressionException {
    Map<String, XPathExpression> defaults = new HashMap<String, XPathExpression>();
    for (FilterConfiguration configuration : configurations) {
      XPathNamespaceContext nsCtx = new XPathNamespaceContext(configuration);

      if (configuration.getDefaults() != null) {
        defaults.putAll(compileExpressions(configuration.getDefaults(), nsCtx));
      }
    }
    return defaults;
  }

  private static List<Filter> compileFilters(Map<String, XPathExpression> defaults,
                                             FilterConfiguration... configurations) throws XPathExpressionException {
    List<Filter> filters = new ArrayList<Filter>();
    for (FilterConfiguration configuration : configurations) {
      XPathNamespaceContext nsCtx = new XPathNamespaceContext(configuration);

      if (configuration.getFilters() != null) {
        for (ee.degeetia.dumonitor.filter.config.generated.Filter filter : configuration.getFilters().getFilter()) {
          filters.add(compileFilter(filter, defaults, nsCtx));
        }
      }
    }
    return filters;
  }

  private static Filter compileFilter(ee.degeetia.dumonitor.filter.config.generated.Filter filter,
                                      Map<String, XPathExpression> defaults,
                                      XPathNamespaceContext nsCtx) throws XPathExpressionException {
    Map<String, XPathExpression> expressions = new HashMap<String, XPathExpression>(defaults);
    expressions.putAll(compileExpressions(filter.getLoggableFields(), nsCtx));

    List<LoggableField> loggableFields = new ArrayList<LoggableField>();
    for (Entry<String, XPathExpression> entry : expressions.entrySet()) {
      loggableFields.add(new LoggableField(entry.getKey(), entry.getValue()));
    }
    return new Filter(XPathUtil.compile(filter.getXpath(), nsCtx), loggableFields);
  }


  private static List<Exclusion> compileExclusions(FilterConfiguration... configurations) throws
      XPathExpressionException {
    BlacklistFunctionResolver functionResolver = new BlacklistFunctionResolver();

    List<Exclusion> exclusions = new ArrayList<Exclusion>();
    for (FilterConfiguration configuration : configurations) {
      XPathNamespaceContext nsCtx = new XPathNamespaceContext(configuration);

      if (configuration.getExclusions() != null) {
        for (String exclusion : configuration.getExclusions().getExclusion()) {
          exclusions.add(new Exclusion(XPathUtil.compile(exclusion, nsCtx, functionResolver)));
        }
      }
    }
    return exclusions;
  }

  private static Map<String, XPathExpression> compileExpressions(LoggableFields fields,
                                                                 NamespaceContext nsCtx) throws
      XPathExpressionException {
    Map<String, XPathExpression> compiled = new HashMap<String, XPathExpression>();
    if (fields.getPersoncode() != null) {
      compiled.put("personcode", compile(fields.getPersoncode(), nsCtx));
    }
    if (fields.getAction() != null) {
      compiled.put("action", compile(fields.getAction(), nsCtx));
    }
    if (fields.getSender() != null) {
      compiled.put("sender", compile(fields.getSender(), nsCtx));
    }
    if (fields.getReceiver() != null) {
      compiled.put("receiver", compile(fields.getReceiver(), nsCtx));
    }
    if (fields.getRestrictions() != null) {
      compiled.put("restrictions", compile(fields.getRestrictions(), nsCtx));
    }
    if (fields.getSendercode() != null) {
      compiled.put("sendercode", compile(fields.getSendercode(), nsCtx));
    }
    if (fields.getReceivercode() != null) {
      compiled.put("receivercode", compile(fields.getReceivercode(), nsCtx));
    }
    if (fields.getActioncode() != null) {
      compiled.put("actioncode", compile(fields.getActioncode(), nsCtx));
    }
    if (fields.getXroadrequestid() != null) {
      compiled.put("xroadrequestid", compile(fields.getXroadrequestid(), nsCtx));
    }
    if (fields.getXroadservice() != null) {
      compiled.put("xroadservice", compile(fields.getXroadservice(), nsCtx));
    }
    if (fields.getUsercode() != null) {
      compiled.put("usercode", compile(fields.getUsercode(), nsCtx));
    }
    return compiled;
  }

  private static XPathExpression compile(String expression, NamespaceContext nsCtx) throws XPathExpressionException {
    LOG.debug("Compiling XPath expression: {}", expression);
    return XPathUtil.compile(expression, nsCtx);
  }

}
