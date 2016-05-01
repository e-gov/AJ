package ee.degeetia.dumonitor.filter.config;

import ee.degeetia.dumonitor.common.util.XPathUtil;
import ee.degeetia.dumonitor.filter.config.generated.LoggableFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;
import java.util.Map;

public class FilterConfigurationXPathCompiler {

  private static final Logger LOG = LoggerFactory.getLogger(FilterConfigurationXPathCompiler.class);

  private final LoggableFields fields;
  private final NamespaceContext namespaceContext;

  public FilterConfigurationXPathCompiler(LoggableFields fields, NamespaceContext namespaceContext) {
    this.fields = fields;
    this.namespaceContext = namespaceContext;
  }

  public Map<String, XPathExpression> compile() throws XPathExpressionException {
    Map<String, XPathExpression> map = new HashMap<String, XPathExpression>();
    if (fields.getPersoncode() != null) {
      map.put("personcode", compile(fields.getPersoncode(), namespaceContext));
    }
    if (fields.getAction() != null) {
      map.put("action", compile(fields.getAction(), namespaceContext));
    }
    if (fields.getSender() != null) {
      map.put("sender", compile(fields.getSender(), namespaceContext));
    }
    if (fields.getReceiver() != null) {
      map.put("receiver", compile(fields.getReceiver(), namespaceContext));
    }
    if (fields.getRestrictions() != null) {
      map.put("restrictions", compile(fields.getRestrictions(), namespaceContext));
    }
    if (fields.getSendercode() != null) {
      map.put("sendercode", compile(fields.getSendercode(), namespaceContext));
    }
    if (fields.getReceivercode() != null) {
      map.put("receivercode", compile(fields.getReceivercode(), namespaceContext));
    }
    if (fields.getActioncode() != null) {
      map.put("actioncode", compile(fields.getActioncode(), namespaceContext));
    }
    if (fields.getXroadrequestid() != null) {
      map.put("xroadrequestid", compile(fields.getXroadrequestid(), namespaceContext));
    }
    if (fields.getXroadservice() != null) {
      map.put("xroadservice", compile(fields.getXroadservice(), namespaceContext));
    }
    if (fields.getUsercode() != null) {
      map.put("usercode", compile(fields.getUsercode(), namespaceContext));
    }
    return map;
  }

  private XPathExpression compile(String expression, NamespaceContext namespaceCtx) throws XPathExpressionException {
    LOG.info("Compiling XPath expression: {}", expression);
    return XPathUtil.compile(expression, namespaceCtx);
  }

}

