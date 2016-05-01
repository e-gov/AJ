package ee.degeetia.dumonitor.common.config;

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
public class PropertyLoader {

  private static final Logger LOG = LoggerFactory.getLogger(PropertyLoader.class);

  /**
   * Loads properties from given files into enum values. The enum class must implement {@link PropertyHolder}.
   * <p>
   * Files are ordered by priority (descending). Properties from higher priority files overwrite those from lower
   * priority files.
   * <p>
   * Properties that are found from the files but are not defined in the enum class are set as system properties.
   *
   * @param propertyClass the enum class to load properties into
   * @param <T>           type of the enum class
   * @param filenames     filenames (relative to classpath) that specify where to load properties from
   * @throws IOException if an I/O error occurs when loading the properties
   */

  public <T extends Enum<T> & PropertyHolder> void loadProperties(Class<T> propertyClass,
                                                                  String... filenames) throws IOException {
    Properties properties = new Properties();
    Set<String> definedProperties = new HashSet<String>();
    Set<String> missingProperties = new HashSet<String>();

    for (int i = filenames.length - 1; i >= 0; i--) {
      InputStream propertiesFile = ResourceUtil.getClasspathResourceAsStream(filenames[i]);
      if (propertiesFile == null) {
        throw new IllegalStateException("Properties file " + filenames[i] + " not found on classpath");
      }
      LOG.info("Loading properties from classpath resource {}", filenames[i]);
      try {
        properties.load(propertiesFile);
      } finally {
        IOUtil.close(propertiesFile);
      }
    }

    for (T property : propertyClass.getEnumConstants()) {
      definedProperties.add(property.getKey());
      String value = properties.getProperty(property.getKey());
      if (StringUtil.isEmpty(value)) {
        missingProperties.add(property.getKey());
      } else {
        LOG.info("Loaded property {} = {}", property.getKey(), value);
        property.setValue(value);
      }
    }

    if (!missingProperties.isEmpty()) {
      throw new IllegalStateException("Missing properties: " + StringUtil.join(missingProperties, ", "));
    }

    for (String propertyName : properties.stringPropertyNames()) {
      if (!definedProperties.contains(propertyName)) {
        System.setProperty(propertyName, properties.getProperty(propertyName));
      }
    }
  }

}
