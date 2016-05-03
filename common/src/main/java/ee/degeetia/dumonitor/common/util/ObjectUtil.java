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

/**
 * Utility class for operations with Objects.
 */
public final class ObjectUtil {

  private ObjectUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Null safe equals method.
   *
   * @param a left hand side
   * @param b right hand side
   * @return true if a and b are both null or equal
   */
  public static boolean eq(Object a, Object b) {
    return a == null ? b == null : a.equals(b);
  }

  /**
   * Null safe equals method.
   *
   * @param object  the object on which to call equals
   * @param options array of objects to use as parameters to equals
   * @return true if any of the options is equal to the object
   * @see #eq(Object, Object)
   */
  public static boolean eq(Object object, Object... options) {
    for (Object option : options) {
      if (eq(object, option)) {
        return true;
      }
    }
    return false;
  }

}
