package ee.degeetia.dumonitor.common.config;

import java.net.URL;
import java.util.Date;

public interface PropertyHolder {

  String getKey();

  String getValue();

  void setValue(String value);

  Integer getInteger();

  Date getDate();

  URL getURL();

}
