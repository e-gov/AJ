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
package ee.ria.dumonitor.filter.config;

import javax.xml.xpath.XPathExpression;

/**
 * @see FilterConfig
 */
public class LoggableField {

  private final String fieldName;
  private final XPathExpression xpath;

  /**
   * Creates a new LoggableField instance from the specified field name and XPath expression.
   *
   * @param fieldName the name of the field
   * @param xpath     the XPath expression that describes the value of the field
   */
  public LoggableField(String fieldName, XPathExpression xpath) {
    this.fieldName = fieldName;
    this.xpath = xpath;
  }

  /**
   * @return the name of the field
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * @return the XPath expression that describes the value of the field
   */
  public XPathExpression getXpath() {
    return xpath;
  }

}
