package ee.degeetia.dumonitor.filter.util;

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
