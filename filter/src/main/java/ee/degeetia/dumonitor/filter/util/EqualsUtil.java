package ee.degeetia.dumonitor.filter.util;

public final class EqualsUtil {

  private EqualsUtil() {
    throw new UnsupportedOperationException();
  }

  public static boolean eq(Object a, Object b) {
    return a == null ? b == null : a.equals(b);
  }

}
