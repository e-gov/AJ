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
package ee.degeetia.dumonitor.common.config;

import ee.degeetia.dumonitor.common.util.ExceptionUtil;
import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.common.util.ResourceUtil;
import ee.degeetia.dumonitor.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * This class loads properties from .properties files into enum values.
 */
public final class PropertyLoader {

  private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);

  private PropertyLoader() {
    throw new UnsupportedOperationException();
  }

  /**
   * Loads properties from given files into enum values. The enum class must implement {@link PropertyHolder}.
   * <p>
   * Files are ordered by priority (ascending). Properties from higher priority files overwrite those from lower
   * priority files.
   * <p>
   * Properties that are found from the files but are not defined in the enum class are set as system properties.
   *
   * @param propertyClass the enum class to load properties into
   * @param <T>           type of the enum class
   * @param filenames     filenames (relative to classpath) that specify where to load properties from
   */

  public static <T extends Enum<T> & PropertyHolder> void loadProperties(Class<T> propertyClass, String... filenames) {
    Properties properties = new Properties();
    Set<String> definedProperties = new HashSet<String>();

    for (String filename : filenames) {
      InputStream propertiesFile = ResourceUtil.getClasspathResourceAsStream(filename);
      if (propertiesFile == null) {
        throw new IllegalStateException("Properties file " + filename + " not found on classpath");
      }
      LOG.debug("Loading properties from classpath resource {}", filename);
      try {
        properties.load(propertiesFile);
      } catch (IOException e) {
        throw ExceptionUtil.toUnchecked("Failed to load properties from " + filename, e);
      } finally {
        IOUtil.close(propertiesFile);
      }
    }

    for (T property : propertyClass.getEnumConstants()) {
      definedProperties.add(property.getKey());
      String value = properties.getProperty(property.getKey());
      if (!StringUtil.isEmpty(value)) {
        LOG.debug("Loaded property {} = {}", property.getKey(), value);
        property.setValue(value);
      }
    }

    for (String propertyName : properties.stringPropertyNames()) {
      if (!definedProperties.contains(propertyName)) {
        System.setProperty(propertyName, properties.getProperty(propertyName));
      }
    }
  }

  /**
   * Verifies that all given properties contain nonempty value.
   * @param properties properties to test
   */
  public static void requireProperties(PropertyHolder[] properties) {
    Set<String> missingProperties = new HashSet<String>();
    for (PropertyHolder property : properties) {
      if (StringUtil.isEmpty(property.getValue())) {
        missingProperties.add(property.getKey());
      }
    }

    if (!missingProperties.isEmpty()) {
      throw new IllegalStateException("Missing properties: " + StringUtil.join(missingProperties, ", "));
    }
  }
}
