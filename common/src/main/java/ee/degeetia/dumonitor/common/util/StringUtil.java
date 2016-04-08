package ee.degeetia.dumonitor.common.util;

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
    return string != null && string.isEmpty();
  }

}
