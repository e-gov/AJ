package ee.degeetia.xrtracker.filter.log;

import ee.degeetia.xrtracker.filter.config.properties.Property;
import ee.degeetia.xrtracker.filter.http.HttpClient;
import ee.degeetia.xrtracker.filter.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: interface
public class LogService {

  private static final Logger LOG = LogManager.getLogger(LogService.class);

  private HttpClient httpClient = new HttpClient();

  public void createEntry(LogEntry logEntry) {
    try {
      httpClient.post(Property.LOGGER_REST_URL.getURL(), logEntry);
    } catch (HttpException e) {
      LOG.error("Failed to send logs over REST", e);
    }
  }

}
