package ee.degeetia.xrtracker.filter.util;

import org.w3c.dom.Node;

import javax.xml.xpath.*;

/**
 * Utility class for working with XML documents and XPath expressions.
 */
// TODO: validate and compile all configured XPath expressions on application startup
public final class XPathUtil {

  private XPathUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * @param node       the XML document to evaluate
   * @param expression the XPath expression as a String
   * @return <code>true</code> if the given expression evaluates to an existing element in the given node,
   * <code>false</code> otherwise
   */
  public static boolean evaluate(Node node, String expression) {
    XPath xPath = XPathFactory.newInstance().newXPath();
    try {
      XPathExpression xPathExpression = xPath.compile(expression);
      return evaluate(node, xPathExpression);
    } catch (XPathExpressionException e) {
      return false;
    }
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

  /**
   * @param node        the XML document to evaluate
   * @param expressions the XPath expressions as Strings
   * @return <code>true</code> if any of the given expressions evaluates to an existing element in the given node,
   * <code>false</code> otherwise
   */
  public static boolean evaluateAny(Node node, String... expressions) {
    for (String expression : expressions) {
      if (evaluate(node, expression)) {
        return true;
      }
    }
    return false;
  }

}
