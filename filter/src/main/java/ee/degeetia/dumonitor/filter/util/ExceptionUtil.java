package ee.degeetia.dumonitor.filter.util;

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
