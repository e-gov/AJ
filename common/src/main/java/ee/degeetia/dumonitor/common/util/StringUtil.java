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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility class for String operations.
 */
public final class StringUtil {

  private StringUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * @param string the String to check
   * @return true if the input String is null or empty
   */
  public static boolean isEmpty(String string) {
    return string == null || string.isEmpty();
  }

  /**
   * Joins the collection of strings into a single string using the specified string as a separator.
   *
   * @param collection the collection of strings to join
   * @param separator  the string to use as a separator
   * @return the resulting string
   */
  public static String join(Collection<String> collection, String separator) {
    StringBuilder sb = new StringBuilder();
    Iterator<String> it = collection.iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(separator);
      }
    }
    return sb.toString();
  }

  /**
   * Splits the given string using the specified separator and returns the result as a String array. Each element in the
   * array is also trimmed of leading/trailing whitespace.
   * <p>
   * For example, <code>split("abc,def", ',')</code> and <code>split("abc , def", ',')</code> will both return
   * <code>["abc", "def"]</code>.
   *
   * @param string    the String to split
   * @param separator the char to use as a separator
   * @return the result as a String array
   */
  public static String[] split(String string, char separator) {
    if (isEmpty(string)) {
      return new String[]{string};
    }
    String pattern = Pattern.quote(String.valueOf(separator));
    List<String> result = new ArrayList<String>();
    for (String s : string.split(pattern)) {
      String trimmed = s.trim();
      if (!isEmpty(trimmed)) {
        result.add(trimmed);
      }
    }
    return result.toArray(new String[result.size()]);
  }

}
