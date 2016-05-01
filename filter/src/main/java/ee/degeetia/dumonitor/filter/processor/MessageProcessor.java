package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.common.util.ObjectMapper;
import ee.degeetia.dumonitor.common.util.SoapUtil;
import ee.degeetia.dumonitor.common.util.XPathUtil;
import ee.degeetia.dumonitor.filter.config.FilterConfigurationManager;
import ee.degeetia.dumonitor.filter.log.LogEntry;
import ee.degeetia.dumonitor.filter.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathExpression;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class takes care of parsing the raw message content and determining if the message contains information that
 * should be logged.
 */
public class MessageProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

  private LogService logService = new LogService();

  private ObjectMapper<LogEntry> logEntryMapper = new ObjectMapper<LogEntry>(LogEntry.class);

  /**
   * Parses the raw message content to a SOAPMessage and applies XPath expressions defined in the filter configuration
   * file to determine if the message contains information that should be logged. If it does, creates a log entry and
   * sends it to the configured logger service.
   *
   * @param content     the raw message content
   * @param contentType the type of the message (usually text/xml)
   */
  public void process(byte[] content, String contentType) {
    try {
      SOAPMessage message = SoapUtil.parseMessage(content, contentType);

      Map<String, String> loggableFields = getLoggableFields(message.getSOAPPart());

      if (!loggableFields.isEmpty()) {
        LogEntry logEntry = logEntryMapper.map(loggableFields);
        logEntry.setLogtime(new Date());
        logService.createEntry(logEntry);
      }
    } catch (Exception e) {
      if (LOG.isErrorEnabled()) {
        LOG.error("Failed to process message: " + new String(content, IOUtil.UTF_8), e);
      }
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
