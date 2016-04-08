package ee.degeetia.dumonitor.common.config.properties;

import java.net.MalformedURLException;
import java.net.URL;

import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.common.util.URLUtil;

public enum Property {

  FILTER_CONFIGURATION_FILE("dumonitor.filter.configuration.file"),
  TURVASERVER_URL("dumonitor.filter.turvaserver.url"),
  ANDMEKOGU_URL("dumonitor.filter.andmekogu.url"),
  TURVASERVER_INTERCEPTOR_PATH("dumonitor.filter.turvaserver.interceptor.path"),
  ANDMEKOGU_INTERCEPTOR_PATH("dumonitor.filter.andmekogu.interceptor.path"),
  LOGGER_REST_URL("dumonitor.filter.logger.rest.url"),
  EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS("dumonitor.filter.executor.shutdown.timeout.seconds"),
 
  DATABASE_CONNECTSTRING("dumonitor.storage.database.connectstring"),
  DATABASE_USER("dumonitor.storage.database.user"),
  DATABASE_PASSWORD("dumonitor.storage.database.password");
  
  private String key;
  private String value;

  Property(String key) {
    this.key = key;
  }

  public String getString() {
    return value;
  }

  public int getInt() {
    return Integer.parseInt(value);
  }

  public URL getURL() {
    try {
      return URLUtil.getAbsoluteURL(RuntimeProperty.APPLICATION_URL.getValue(), value);
    } catch (MalformedURLException e) {
      throw ExceptionUtil.toUnchecked("Invalid URL", e);
    }
  }

  protected String getKey() {
    return key;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
