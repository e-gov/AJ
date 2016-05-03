/**
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

import ee.degeetia.dumonitor.filter.config.generated.FilterConfiguration;
import ee.degeetia.dumonitor.filter.config.generated.Namespace;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

import static ee.degeetia.dumonitor.common.util.ObjectUtil.eq;

class XPathNamespaceContext implements NamespaceContext {
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
