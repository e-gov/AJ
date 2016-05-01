package ee.degeetia.dumonitor.common.config;

import java.net.URL;
import java.util.Date;

public enum BuildProperty implements PropertyHolder {

  NAME("name"),
  VERSION("version"),
  BUILD_DATE("buildDate");

  private String key;
  private String value;

  BuildProperty(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getInteger() {
    return PropertyConverter.toInteger(this);
  }

  public Date getDate() {
    return PropertyConverter.toDate(this);
  }

  public URL getURL() {
    return PropertyConverter.toURL(this);
  }

}
