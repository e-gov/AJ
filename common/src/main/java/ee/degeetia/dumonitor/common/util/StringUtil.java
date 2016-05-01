package ee.degeetia.dumonitor.common.util;

import java.util.Collection;
import java.util.Iterator;

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

  /**
   * Joins the collection of strings into a single string using the specified string as a separator.
   *
   * @param collection the collection of strings to join
   * @param separator  the string to use as a separator
   * @return the resulting string
   */
  public static String join(Collection<String> collection, String separator) {
    String result = "";
    Iterator<String> it = collection.iterator();
    while (it.hasNext()) {
      result += it.next();
      if (it.hasNext()) {
        result += separator;
      }
    }
    return result;
  }

}
