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
package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.common.util.ObjectMapper;
import ee.degeetia.dumonitor.common.util.SoapUtil;
import ee.degeetia.dumonitor.common.util.XPathUtil;
import ee.degeetia.dumonitor.filter.config.*;
import ee.degeetia.dumonitor.filter.log.LogEntry;
import ee.degeetia.dumonitor.filter.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * This class takes care of parsing the raw message content and determining if the message contains information that
 * should be logged.
 */
public class MessageProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

  private LogService logService = new LogService();

  private ObjectMapper<LogEntry> logEntryMapper = new ObjectMapper<LogEntry>(LogEntry.class);

  /* We want to use precompiled XPath expressions, but the XPathExpression class is not thread safe. Synchronizing
   * is not an option because it makes threads wait and could potentially cause a bottleneck, so we precompile the
   * expressions separately for each thread. If threads are pooled, this should result in optimal performance. */
  private ThreadLocal<FilterConfig> config = new ThreadLocal<FilterConfig>() {
    @Override
    protected FilterConfig initialValue() {
      return ConfigurationLoader.precompile();
    }
  };

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

      if (isLoggable(message.getSOAPPart())) {
        Map<String, String> loggableFields = getLoggableFields(message.getSOAPPart());
        if (!loggableFields.isEmpty()) {
          logService.createEntry(logEntryMapper.map(loggableFields));
        }
      }
    } catch (Exception e) {
      if (LOG.isErrorEnabled()) {
        LOG.error("Failed to process message: " + new String(content, IOUtil.UTF_8), e);
      }
    }
  }

  private boolean isLoggable(Node node) {
    for (Exclusion exclusion : config.get().getExclusions()) {
      if (XPathUtil.evaluate(node, exclusion.getExpression())) {
        return false;
      }
    }
    return true;
  }

  private Map<String, String> getLoggableFields(Node node) {
    Map<String, String> loggableFields = new HashMap<String, String>();
    for (Filter filter : config.get().getFilters()) {
      if (XPathUtil.evaluate(node, filter.getXpath())) {
        for (LoggableField field : filter.getLoggableFields()) {
          loggableFields.put(field.getFieldName(), XPathUtil.getValue(node, field.getXpath()));
        }
        break;
      }
    }
    return loggableFields;
  }

}
