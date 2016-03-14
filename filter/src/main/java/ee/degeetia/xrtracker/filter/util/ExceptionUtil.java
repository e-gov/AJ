package ee.degeetia.xrtracker.filter.util;

public final class ExceptionUtil {

  private ExceptionUtil() {
    throw new UnsupportedOperationException();
  }

  public static RuntimeException toUnchecked(String message, Throwable throwable) {
    return new RuntimeException(message, throwable);
  }

  public static void uncheck(String message, Throwable throwable) {
    throw toUnchecked(message, throwable);
  }

}
