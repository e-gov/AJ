package ee.degeetia.dumonitor.common.config;

import ee.degeetia.dumonitor.common.util.DateUtil;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.common.util.URLUtil;

import java.net.URL;
import java.util.Date;

public final class PropertyConverter {

  private PropertyConverter() {
    throw new UnsupportedOperationException();
  }

  public static Integer toInteger(PropertyHolder property) {
    return convert(property, new Converter<Integer>() {
      @Override
      public Integer convert(String value) throws Exception {
        return Integer.valueOf(value);
      }
    });
  }

  public static Date toDate(PropertyHolder property) {
    return convert(property, new Converter<Date>() {
      @Override
      public Date convert(String value) throws Exception {
        return DateUtil.parse(value);
      }
    });
  }

  public static URL toURL(PropertyHolder property) {
    return convert(property, new Converter<URL>() {
      @Override
      public URL convert(String value) throws Exception {
        return URLUtil.getAbsoluteURL(RuntimeProperty.APPLICATION_URL.getString(), value);
      }
    });
  }

  private static <T> T convert(PropertyHolder property, Converter<T> converter) {
    if (property.getValue() == null) {
      return null;
    }
    try {
      return converter.convert(property.getValue());
    } catch (Exception e) {
      throw ExceptionUtil.toUnchecked("Failed to convert property " + property.getKey(), e);
    }
  }

  private interface Converter<T> {
    T convert(String value) throws Exception;
  }

}
