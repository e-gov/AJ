/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.degeetia.dumonitor.common.util;

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
