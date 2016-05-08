/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.degeetia.dumonitor.common.config;

import ee.degeetia.dumonitor.common.util.DateUtil;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.common.util.StringUtil;
import ee.degeetia.dumonitor.common.util.URLUtil;

import java.net.URL;
import java.util.Date;

/**
 * This class contains methods for converting property values from String to other types. All methods throw a
 * RuntimeException if the conversion fails.
 */
public final class PropertyConverter {

  private PropertyConverter() {
    throw new UnsupportedOperationException();
  }

  /**
   * Converts the property's String value to Integer.
   *
   * @param property the property to convert
   * @return the Integer value of the property
   */
  public static Integer toInteger(PropertyHolder property) {
    return convert(property, new Converter<Integer>() {
      @Override
      public Integer convert(String value) throws Exception {
        return Integer.valueOf(value);
      }
    });
  }

  /**
   * Converts the property's String value to Date.
   *
   * @param property the property to convert
   * @return the Date value of the property
   */
  public static Date toDate(PropertyHolder property) {
    return convert(property, new Converter<Date>() {
      @Override
      public Date convert(String value) throws Exception {
        return DateUtil.parse(value);
      }
    });
  }

  /**
   * Converts the property's String value to URL.
   *
   * @param property the property to convert
   * @return the URL value of the property
   */
  public static URL toURL(PropertyHolder property) {
    return convert(property, new Converter<URL>() {
      @Override
      public URL convert(String value) throws Exception {
        return URLUtil.getAbsoluteURL(RuntimeProperty.APPLICATION_URL.getString(), value);
      }
    });
  }

  /**
   * Converts the property's String value to a String array.
   *
   * @param property the property to convert
   * @return the value of the property as a String array
   */
  public static String[] toArray(PropertyHolder property) {
    return convert(property, new Converter<String[]>() {
      @Override
      public String[] convert(String value) throws Exception {
        return StringUtil.split(value, ',');
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
