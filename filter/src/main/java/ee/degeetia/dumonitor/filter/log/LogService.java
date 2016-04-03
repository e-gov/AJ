package ee.degeetia.dumonitor.filter.log;

import ee.degeetia.dumonitor.filter.config.properties.Property;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import ee.degeetia.dumonitor.filter.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LogService {

  private static final Logger LOG = LogManager.getLogger(LogService.class);

  private HttpClient httpClient = new HttpClient();

  public void createEntry(Map<String, String> fields) {
    try {
      httpClient.post(Property.LOGGER_REST_URL.getURL(), fields);
    } catch (HttpException e) {
      LOG.error("Failed to send logs over REST", e);
    }
  }

}
