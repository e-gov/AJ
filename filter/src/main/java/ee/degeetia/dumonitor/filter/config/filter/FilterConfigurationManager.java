package ee.degeetia.dumonitor.filter.config.filter;

import ee.degeetia.dumonitor.filter.config.filter.generated.Filter;
import ee.degeetia.dumonitor.filter.config.filter.generated.FilterConfiguration;
import ee.degeetia.dumonitor.filter.config.filter.generated.LoggableFields;
import ee.degeetia.dumonitor.filter.config.filter.generated.Namespace;
import ee.degeetia.dumonitor.filter.config.properties.Property;
import ee.degeetia.dumonitor.filter.util.IOUtil;
import ee.degeetia.dumonitor.filter.util.ResourceUtil;
import ee.degeetia.dumonitor.filter.util.XPathUtil;
import ee.degeetia.dumonitor.filter.util.XmlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ee.degeetia.dumonitor.filter.util.ObjectUtil.eq;

public final class FilterConfigurationManager {

  private static final Logger LOG = LogManager.getLogger(FilterConfigurationManager.class);

  private static final FilterConfigurationManager INSTANCE = new FilterConfigurationManager();

  private final Map<XPathExpression, Map<String, XPathExpression>> compiledExpressions;

  private FilterConfigurationManager() {
    compiledExpressions = new ConcurrentHashMap<XPathExpression, Map<String, XPathExpression>>();
  }

  public static FilterConfigurationManager getManager() {
    return INSTANCE;
  }

  public void loadConfiguration() throws XPathExpressionException, JAXBException {
    FilterConfiguration defaultConf = readConfigurationFile("filter-defaults.xml");
    FilterConfiguration configuration = readConfigurationFile(Property.FILTER_CONFIGURATION_FILE.getString());
    Map<XPathExpression, Map<String, XPathExpression>> compiled = compileExpressions(defaultConf, configuration);
    compiledExpressions.putAll(compiled);
  }

  public Map<XPathExpression, Map<String, XPathExpression>> getCompiledExpressions() {
    return Collections.unmodifiableMap(compiledExpressions);
  }

  private FilterConfiguration readConfigurationFile(String filename) throws JAXBException {
    LOG.info("Parsing configuration file {}", filename);
    InputStream inputStream = ResourceUtil.getClasspathResourceAsStream(filename);
    try {
      return XmlUtil.unmarshal(inputStream, FilterConfiguration.class);
    } finally {
      IOUtil.close(inputStream);
    }
  }

  private Map<XPathExpression, Map<String, XPathExpression>> compileExpressions(FilterConfiguration defaultConf,
                                                                                FilterConfiguration conf) throws XPathExpressionException {
    Map<XPathExpression, Map<String, XPathExpression>> compiled =
        new HashMap<XPathExpression, Map<String, XPathExpression>>();

    Map<String, XPathExpression> compiledDefaults = compileDefaults(defaultConf);

    if (conf.getDefaults() != null) {
      compiledDefaults.putAll(compileDefaults(conf));
    }

    for (Filter filter : conf.getFilters().getFilter()) {
      XPathExpression compiledXPath = XPathUtil.compile(filter.getXpath(), new XPathNamespaceContext(conf));

      Map<String, XPathExpression> compiledFields = new HashMap<String, XPathExpression>();
      compiledFields.putAll(compiledDefaults);
      compiledFields.putAll(compileFields(conf, filter.getLoggableFields()));

      compiled.put(compiledXPath, compiledFields);
    }

    return compiled;
  }

  private Map<String, XPathExpression> compileDefaults(FilterConfiguration configuration) throws XPathExpressionException {
    return compileFields(configuration, configuration.getDefaults());
  }

  private Map<String, XPathExpression> compileFields(FilterConfiguration configuration,
                                                     LoggableFields fields) throws XPathExpressionException {
    return new FilterConfigurationXPathCompiler(fields, new XPathNamespaceContext(configuration)).compile();
  }

  private class XPathNamespaceContext implements NamespaceContext {
    private final FilterConfiguration filterConfiguration;

    XPathNamespaceContext(FilterConfiguration filterConfiguration) {
      this.filterConfiguration = filterConfiguration;
    }

    @Override
    public String getNamespaceURI(String prefix) {
      for (Namespace namespace : filterConfiguration.getNamespaces().getNamespace()) {
        if (eq(prefix, namespace.getPrefix())) {
          return namespace.getUri();
        }
      }
      return XMLConstants.NULL_NS_URI;
    }

    @Override
    public String getPrefix(String namespaceURI) {
      throw new UnsupportedOperationException(); // Not used for XPath processing
    }

    @Override
    public Iterator getPrefixes(String namespaceURI) {
      throw new UnsupportedOperationException(); // Not used for XPath processing
    }
  }
}
