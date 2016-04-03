package ee.degeetia.dumonitor.filter.util;

import java.io.InputStream;
import java.net.URL;

/**
 * Utility class for loading resources from the classpath.
 */
public final class ResourceUtil {

  private ResourceUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Finds the class path resource with the given name and returns it as a URL.
   *
   * @param name the resource name
   * @return an URL object that points to the resource, or null if the resource could not be found
   */
  public static URL getClasspathResource(String name) {
    return Thread.currentThread().getContextClassLoader().getResource(name);
  }

  /**
   * Finds the class path resource with the given name and returns it as an InputStream.
   *
   * @param name the resource name
   * @return an InputStream object for reading the resource, or null if the resource could not be found
   */
  public static InputStream getClasspathResourceAsStream(String name) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
  }

}
