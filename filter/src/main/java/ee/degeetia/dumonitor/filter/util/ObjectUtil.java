package ee.degeetia.dumonitor.filter.util;

public final class ObjectUtil {

  private ObjectUtil() {
    throw new UnsupportedOperationException();
  }

  public static boolean eq(Object a, Object b) {
    return a == null ? b == null : a.equals(b);
  }

  public static <T> T coalesce(T a, T b) {
    return a != null ? a : b;
  }

}
