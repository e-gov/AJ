package ee.degeetia.dumonitor.filter.config.xpath;

import ee.degeetia.dumonitor.filter.config.properties.Property;
import ee.degeetia.dumonitor.filter.config.xpath.generated.DumonitorXPathConfiguration;
import ee.degeetia.dumonitor.filter.config.xpath.generated.Filter;
import ee.degeetia.dumonitor.filter.config.xpath.generated.LoggableField;
import ee.degeetia.dumonitor.filter.util.IOUtil;
import ee.degeetia.dumonitor.filter.util.ResourceUtil;
import ee.degeetia.dumonitor.filter.util.XPathUtil;
import ee.degeetia.dumonitor.filter.util.XmlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class XPathExpressionsManager {

  private static final Logger LOG = LogManager.getLogger(XPathExpressionsManager.class);

  private static final XPathExpressionsManager INSTANCE = new XPathExpressionsManager();

  private final Map<XPathExpression, Map<String, XPathExpression>> compiledExpressions;

  private XPathExpressionsManager() {
    compiledExpressions = new ConcurrentHashMap<XPathExpression, Map<String, XPathExpression>>();
  }

  public static XPathExpressionsManager getManager() {
    return INSTANCE;
  }

  public void compile() throws XPathExpressionException, JAXBException {
    DumonitorXPathConfiguration configuration = readConfiguration();
    Map<XPathExpression, Map<String, XPathExpression>> compiled = compileExpressions(configuration);
    compiledExpressions.putAll(compiled);
  }

  public Map<XPathExpression, Map<String, XPathExpression>> getCompiledExpressions() {
    return Collections.unmodifiableMap(compiledExpressions);
  }

  private Map<XPathExpression, Map<String, XPathExpression>> compileExpressions(DumonitorXPathConfiguration conf) throws XPathExpressionException {
    Map<XPathExpression, Map<String, XPathExpression>> map =
        new HashMap<XPathExpression, Map<String, XPathExpression>>();
    NamespaceContext namespaceContext = new XPathNamespaceContext(conf.getNamespaces());
    for (Filter filter : conf.getFilters().getFilter()) {
      XPathExpression filterExpression = compile(filter.getXpath(), namespaceContext);
      Map<String, XPathExpression> fieldmap = new HashMap<String, XPathExpression>();
      for (LoggableField loggableField : filter.getLoggableFields().getLoggableField()) {
        fieldmap.put(loggableField.getFieldName(), compile(loggableField.getXpath(), namespaceContext));
      }
      map.put(filterExpression, fieldmap);
    }
    return map;
  }

  private DumonitorXPathConfiguration readConfiguration() throws JAXBException {
    String confFile = Property.XPATH_CONFIGURATION_FILE.getString();
    LOG.info("Reading XPath configuration file {}", confFile);
    InputStream inputStream = ResourceUtil.getClasspathResourceAsStream(confFile);
    try {
      return XmlUtil.unmarshal(inputStream, DumonitorXPathConfiguration.class);
    } finally {
      IOUtil.close(inputStream);
    }
  }

  private XPathExpression compile(String expression,
                                  NamespaceContext namespaceContext) throws XPathExpressionException {
    LOG.info("Compiling XPath expression: {}", expression);
    return XPathUtil.compile(expression, namespaceContext);
  }

}
