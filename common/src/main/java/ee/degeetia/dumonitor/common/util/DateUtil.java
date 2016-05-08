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
package ee.degeetia.dumonitor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for Date operations.
 */
public final class DateUtil {

  public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

  private DateUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Attempts to parse the input string to a Date.
   *
   * @param iso8601Date date in ISO-8601 format (yyyy-MM-dd'T'HH:mm:ssZ)
   * @return Date parsed from the string
   * @throws ParseException if the input string cannot be parsed
   */
  public static Date parse(String iso8601Date) throws ParseException {
    return new ISO8601DateFormat().parse(iso8601Date);
  }

  private static final class ISO8601DateFormat extends SimpleDateFormat {
    ISO8601DateFormat() {
      super(ISO_8601_FORMAT);
    }
  }

}
