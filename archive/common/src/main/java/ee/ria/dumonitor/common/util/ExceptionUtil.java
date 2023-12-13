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
package ee.ria.dumonitor.common.util;

/**
 * Utility class for exception handling.
 */
public final class ExceptionUtil {

  private ExceptionUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Wraps a checked exception into an unchecked exception.
   *
   * @param throwable the checked exception to wrap
   * @return unchecked exception
   */
  public static RuntimeException toUnchecked(Throwable throwable) {
    return new RuntimeException(throwable);
  }

  /**
   * Wraps a checked exception into an unchecked exception.
   *
   * @param message   the detail message of the unchecked exception
   * @param throwable the checked exception to wrap
   * @return unchecked exception
   */
  public static RuntimeException toUnchecked(String message, Throwable throwable) {
    return new RuntimeException(message, throwable);
  }

  /**
   * Wraps a checked exception into an unchecked exception and throws it.
   *
   * @param message   the detail message of the unchecked exception
   * @param throwable the checked exception to wrap
   */
  public static void uncheck(String message, Throwable throwable) {
    throw toUnchecked(message, throwable);
  }

}
