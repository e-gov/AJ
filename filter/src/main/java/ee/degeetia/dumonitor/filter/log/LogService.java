package ee.degeetia.dumonitor.filter.log;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import ee.degeetia.dumonitor.filter.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles sending logs to the logging component.
 */
public class LogService {

  private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

  private HttpClient httpClient = new HttpClient();

  /**
   * Sends the loggable fields to the URL specified by the dumonitor.filter.logger.rest.url property.
   *
   * @param logEntry the log entry to create
   */
  public void createEntry(LogEntry logEntry) {
    try {
      httpClient.post(Property.LOGGER_REST_URL.getURL(), logEntry);
    } catch (HttpException e) {
      LOG.error("Failed to send logs over REST", e);
    }
  }

}
