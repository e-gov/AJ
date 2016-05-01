package ee.degeetia.dumonitor.common.config;

import java.util.Date;

/**
 * Properties that are not preconfigured but discovered at runtime instead.
 */
public enum RuntimeProperty {

  APPLICATION_URL,
  APPLICATION_STARTUP_TIME;

  private Object value;

  public Object getValue() {
    return value;
  }

  public String getString() {
    return (String) value;
  }

  public Date getDate() {
    return (Date) value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public boolean isSet() {
    return value != null;
  }
}
