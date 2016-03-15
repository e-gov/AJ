package ee.degeetia.xrtracker.filter.processor;

import ee.degeetia.xrtracker.filter.config.xpath.XPathExpressionsConfiguration;
import ee.degeetia.xrtracker.filter.log.LogEntry;
import ee.degeetia.xrtracker.filter.log.LogService;
import ee.degeetia.xrtracker.filter.util.ObjectMapper;
import ee.degeetia.xrtracker.filter.util.SoapUtil;
import ee.degeetia.xrtracker.filter.util.XPathUtil;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathExpression;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MessageProcessor {

  private LogService logService = new LogService();

  private final XPathExpressionsConfiguration xPathConfiguration = new XPathExpressionsConfiguration();

  public void process(byte[] content, String contentType) throws Exception {
    SOAPMessage message = SoapUtil.parseMessage(content, contentType);
    SOAPBody body = message.getSOAPBody();

    Map<String, String> saveableData = getSaveableData(body);

    if (saveableData.size() > 0) {
      ObjectMapper<LogEntry> objectMapper = new ObjectMapper<LogEntry>(LogEntry.class);
      LogEntry logEntry = objectMapper.map(saveableData);
      logService.createEntry(logEntry);
    }
  }

  private Map<String, String> getSaveableData(SOAPBody body) {
    Map<String, String> saveableData = new HashMap<String, String>();
    for (XPathExpression filter : xPathConfiguration.getFilters()) {
      if (XPathUtil.evaluate(body, filter)) {
        for (Entry<String, XPathExpression> saveable : xPathConfiguration.getSaveables(filter)) {
          saveableData.put(saveable.getKey(), XPathUtil.getValue(body, saveable.getValue()));
        }
        break;
      }
    }
    return saveableData;
  }

}
