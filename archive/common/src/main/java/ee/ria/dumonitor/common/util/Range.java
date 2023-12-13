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
 * Class that holds a minimum and maximum value and performs range-related operations with them.
 *
 * @param <T> the type of values to hold
 */
public class Range<T extends Comparable<T>> {

  private final T min;
  private final T max;

  /**
   * Creates a new Range.
   *
   * @param min the minimum value
   * @param max the maximum value
   */
  public Range(T min, T max) {
    this.min = min;
    this.max = max;
  }

  /**
   * Checks if this range contains the given element.
   *
   * @param elem the element to check
   * @return true if min <= elem <= max
   */
  public boolean contains(T elem) {
    return min.compareTo(elem) <= 0 && elem.compareTo(max) <= 0;
  }

}
