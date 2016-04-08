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
