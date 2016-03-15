package ee.degeetia.xrtracker.filter.config.xpath;

import ee.degeetia.xrtracker.filter.config.xpath.generated.DataEntry;
import ee.degeetia.xrtracker.filter.config.xpath.generated.ExpressionSuite;
import ee.degeetia.xrtracker.filter.config.xpath.generated.XPathConfiguration;
import ee.degeetia.xrtracker.filter.util.ExceptionUtil;
import ee.degeetia.xrtracker.filter.util.ResourceUtil;
import ee.degeetia.xrtracker.filter.util.XPathUtil;
import ee.degeetia.xrtracker.filter.util.XmlUtil;

import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class XPathExpressionsConfiguration {

  private static final String XPATH_EXPRESSIONS_FILE = "xpath.xml";

  private static final ConcurrentMap<XPathExpression, Map<String, XPathExpression>> MAP;

  static {
    MAP = new ConcurrentHashMap<XPathExpression, Map<String, XPathExpression>>();
    MAP.putAll(compileExpressions(tryUnmarshal(XPATH_EXPRESSIONS_FILE)));
  }

  public Set<XPathExpression> getFilters() {
    return new HashSet<XPathExpression>(MAP.keySet());
  }

  public Set<Entry<String, XPathExpression>> getSaveables(XPathExpression filter) {
    return new HashSet<Entry<String, XPathExpression>>(MAP.get(filter).entrySet());
  }

  private static XPathConfiguration tryUnmarshal(String filename) {
    try {
      return XmlUtil.unmarshal(ResourceUtil.getClasspathResourceAsStream(filename), XPathConfiguration.class);
    } catch (JAXBException e) {
      throw ExceptionUtil.toUnchecked("Failed to unmarhsal " + filename, e);
    }
  }

  private static Map<XPathExpression, Map<String, XPathExpression>> compileExpressions(XPathConfiguration xPathConfiguration) {
    Map<XPathExpression, Map<String, XPathExpression>> map =
        new HashMap<XPathExpression, Map<String, XPathExpression>>();
    for (ExpressionSuite expressionSuite : xPathConfiguration.getExpressionSuite()) {
      try {
        XPathExpression filter = XPathUtil.compile(expressionSuite.getFilterExpression());
        Map<String, XPathExpression> dataMap = new HashMap<String, XPathExpression>();
        for (DataEntry dataEntry : expressionSuite.getSaveableData().getDataEntry()) {
          dataMap.put(dataEntry.getDataKey(), XPathUtil.compile(dataEntry.getXpath()));
        }
        map.put(filter, dataMap);
      } catch (XPathExpressionException e) {
        throw ExceptionUtil.toUnchecked("Unable to compile XPath expression", e);
      }
    }
    return map;
  }

}
