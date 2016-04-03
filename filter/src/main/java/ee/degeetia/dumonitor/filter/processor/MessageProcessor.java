package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.filter.config.filter.FilterConfigurationManager;
import ee.degeetia.dumonitor.filter.log.LogService;
import ee.degeetia.dumonitor.filter.util.SoapUtil;
import ee.degeetia.dumonitor.filter.util.XPathUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathExpression;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MessageProcessor {

  private LogService logService = new LogService();

  public void process(byte[] content, String contentType) throws Exception {
    SOAPMessage message = SoapUtil.parseMessage(content, contentType);

    Map<String, String> loggableFields = getLoggableFields(message.getSOAPPart());

    if (loggableFields.size() > 0) {
      logService.createEntry(loggableFields);
    }
  }

  private Map<String, String> getLoggableFields(Node node) {
    Map<String, String> loggableFields = new HashMap<String, String>();

    Map<XPathExpression, Map<String, XPathExpression>> xPathExpressions =
        FilterConfigurationManager.getManager().getCompiledExpressions();

    for (Entry<XPathExpression, Map<String, XPathExpression>> entry : xPathExpressions.entrySet()) {
      XPathExpression filter = entry.getKey();
      Map<String, XPathExpression> fields = entry.getValue();

      if (XPathUtil.evaluate(node, filter)) {
        for (Entry<String, XPathExpression> field : fields.entrySet()) {
          loggableFields.put(field.getKey(), XPathUtil.getValue(node, field.getValue()));
        }
        break;
      }

    }

    return loggableFields;
  }

}
