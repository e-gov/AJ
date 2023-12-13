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
package ee.ria.dumonitor.common.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for working with XML documents and XPath expressions.
 */
public final class XPathUtil {

  private XPathUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Compiles an XPath expression for later evaluation.
   *
   * @param expression       the expression to compile as a String
   * @param namespaceContext the namespace context to use
   * @return the compiled XPathExpression object
   * @throws XPathExpressionException if the expression does not compile
   */
  public static XPathExpression compile(String expression,
                                        NamespaceContext namespaceContext) throws XPathExpressionException {
    return createXPath(namespaceContext, null).compile(expression);
  }

  /**
   * Compiles an XPath expression for later evaluation.
   *
   * @param expression       the expression to compile as a String
   * @param namespaceContext the namespace context to use
   * @param functionResolver the function resolver to use
   * @return the compiled XPathExpression object
   * @throws XPathExpressionException if the expression does not compile
   */
  public static XPathExpression compile(String expression,
                                        NamespaceContext namespaceContext,
                                        XPathFunctionResolver functionResolver) throws XPathExpressionException {
    return createXPath(namespaceContext, functionResolver).compile(expression);
  }

  /**
   * Evaluates the XPath expression in the specified context and returns whether such element was found.
   *
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
   * Evaluates the XPath expression in the specified context and returns the found element as a String.
   *
   * @param node       the XML document to evaluate
   * @param expression the compiled XPath expression
   * @return the element if it was found, or null
   */
  public static String getStringValue(Node node, XPathExpression expression) {
    try {
      return (String) expression.evaluate(node, XPathConstants.STRING);
    } catch (XPathExpressionException e) {
      return null;
    }
  }

  /**
   * Evaluates the XPath expression in the specified context and returns the found items as a List.
   *
   * @param node       the XML document to evaluate
   * @param expression the compiled XPath expression
   * @return the list of elements found
   */
  public static List<String> getListValue(Node node, XPathExpression expression) {
    try {
      NodeList nodeList = (NodeList) expression.evaluate(node, XPathConstants.NODESET);
      List<String> list = new ArrayList<String>(nodeList.getLength());
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node item = nodeList.item(i);
        list.add(item.getFirstChild().getNodeValue());
      }
      return list;
    } catch (XPathExpressionException e) {
      // Try to evaluate in string context:
      String value = getStringValue(node, expression);
      if (value != null) {
          List<String> list = new ArrayList<String>(1);
          list.add(value);
          return list;
      }
      return Collections.emptyList();
    }
  }

  private static XPath createXPath(NamespaceContext namespaceContext, XPathFunctionResolver functionResolver) {
    XPath xPath = XPathFactory.newInstance().newXPath();
    if (namespaceContext != null) {
      xPath.setNamespaceContext(namespaceContext);
    }
    if (functionResolver != null) {
      xPath.setXPathFunctionResolver(functionResolver);
    }
    return xPath;
  }
}
