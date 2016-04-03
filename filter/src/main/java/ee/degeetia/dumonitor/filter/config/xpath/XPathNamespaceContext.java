package ee.degeetia.dumonitor.filter.config.xpath;


import ee.degeetia.dumonitor.filter.config.xpath.generated.Namespace;
import ee.degeetia.dumonitor.filter.config.xpath.generated.Namespaces;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.Iterator;

import static ee.degeetia.dumonitor.filter.util.EqualsUtil.eq;

class XPathNamespaceContext implements NamespaceContext {

  private final Namespaces namespaces;

  XPathNamespaceContext(Namespaces namespaces) {
    this.namespaces = namespaces;
  }

  @Override
  public String getNamespaceURI(String prefix) {
    for (Namespace namespace : namespaces.getNamespace()) {
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
