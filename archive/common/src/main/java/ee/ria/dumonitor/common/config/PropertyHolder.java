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
package ee.ria.dumonitor.common.config;

import java.net.URL;
import java.util.Date;

/**
 * Enum classes containing application properties should implement this interface to be used with {@link PropertyLoader}
 */
public interface PropertyHolder {

  /**
   * @return the key of the property
   */
  String getKey();

  /**
   * @return the string value of the property
   */
  String getValue();

  /**
   * @param value the value of the property
   */
  void setValue(String value);

  /**
   * @return the Integer value of the property
   */
  Integer getInteger();

  /**
   * @return the Date value of the property
   */
  Date getDate();

  /**
   * @return the URL value of the property
   */
  URL getURL();

  /**
   * @return the values of the property as a String array
   */
  String[] getArray();

}
