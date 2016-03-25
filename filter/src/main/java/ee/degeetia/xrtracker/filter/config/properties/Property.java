package ee.degeetia.xrtracker.filter.config.properties;

import ee.degeetia.xrtracker.filter.util.ExceptionUtil;

import java.net.MalformedURLException;
import java.net.URL;

public enum Property {

  TURVASERVER_URL("xrtracker.filter.turvaserver.url", true),
  ANDMEKOGU_URL("xrtracker.filter.andmekogu.url", true),
  TURVASERVER_INTERCEPTOR_PATH("xrtracker.filter.turvaserver.interceptor.path", true),
  ANDMEKOGU_INTERCEPTOR_PATH("xrtracker.filter.andmekogu.interceptor.path", true),
  LOGGER_REST_URL("xrtracker.filter.logger.rest.url", true);

  private String key;
  private String value;
  private boolean required;

  Property(String key, boolean required) {
    this.key = key;
    this.required = required;
  }

  public String getString() {
    return value;
  }

  // TODO: validate URL at application startup
  public URL getURL() {
    try {
      return new URL(value);
    } catch (MalformedURLException e) {
      throw ExceptionUtil.toUnchecked("Invalid URL", e);
    }
  }

  protected String getKey() {
    return key;
  }

  protected boolean isRequired() {
    return required;
  }

  protected void setValue(String value) {
    this.value = value;
  }

}
