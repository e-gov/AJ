package ee.degeetia.xrtracker.filter.util;

import org.w3c.dom.Node;

import javax.xml.xpath.*;

/**
 * Utility class for working with XML documents and XPath expressions.
 */
public final class XPathUtil {

  private XPathUtil() {
    throw new UnsupportedOperationException();
  }

  public static XPathExpression compile(String expression) throws XPathExpressionException {
    XPath xPath = XPathFactory.newInstance().newXPath();
    return xPath.compile(expression);
  }

  /**
   * @param node       the XML document to evaluate
   * @param expression the compiled XPath expression
   * @return <code>true</code> if the given expression evaluates to an existing element in the given node,
   * <code>false</code> otherwise
   */
  public static boolean evaluate(Node node, XPathExpression expression) {
    try {
      Boolean result = (Boolean) expression.evaluate(node, XPathConstants.BOOLEAN);
      return result != null && result;
    } catch (XPathExpressionException e) {
      return false;
    }
  }

  public static String getValue(Node node, XPathExpression expression) {
    try {
      return (String) expression.evaluate(node, XPathConstants.STRING);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

}
