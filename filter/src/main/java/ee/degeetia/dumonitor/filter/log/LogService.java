package ee.degeetia.dumonitor.filter.log;

import ee.degeetia.dumonitor.common.config.properties.Property;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import ee.degeetia.dumonitor.filter.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class handles sending logs to the logging component.
 */
public class LogService {

  private static final Logger LOG = LogManager.getLogger(LogService.class);

  private HttpClient httpClient = new HttpClient();

  /**
   * Sends the loggable fields to the URL specified by the dumonitor.filter.logger.rest.url property.
   */
  public void createEntry(LogEntry logEntry) {
    try {
      httpClient.post(Property.LOGGER_REST_URL.getURL(), logEntry);
    } catch (HttpException e) {
      LOG.error("Failed to send logs over REST", e);
    }
  }

}
