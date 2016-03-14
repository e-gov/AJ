package ee.degeetia.xrtracker.filter.util;

import java.io.InputStream;
import java.net.URL;

/**
 * Utility class for loading resources from the classpath.
 */
public final class ResourceUtil {

  private ResourceUtil() {
    throw new UnsupportedOperationException();
  }

  public static URL getClasspathResource(String name) {
    return Thread.currentThread().getContextClassLoader().getResource(name);
  }

  public static InputStream getClasspathResourceAsStream(String name) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
  }

}
