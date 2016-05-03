/**
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
