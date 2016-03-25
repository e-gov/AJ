package ee.degeetia.xrtracker.filter.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Class for mapping properties from a {@link Map} to a POJO.
 *
 * @param <T> the type of the POJO. Must have a default constructor and setters for all fields.
 */
public class ObjectMapper<T> {

  private static final Logger LOG = LogManager.getLogger(ObjectMapper.class);

  private final Class<T> objectClass;

  public ObjectMapper(Class<T> objectClass) {
    this.objectClass = objectClass;
  }

  /**
   * Matches keys from <code>properties</code> with field names in {@link T} and writes the values to the fields using
   * the corresponding setters. If a matching setter (with the correct name and parameter type) is not found, then the
   * key is ignored.
   *
   * @param properties the Map to read properties from
   * @return the mapped object
   */
  public T map(Map<String, ?> properties) {
    String className = objectClass.getSimpleName();
    T object;

    try {
      object = objectClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw ExceptionUtil.toUnchecked("Failed to create an instance of " + className, e);
    }

    for (Map.Entry<String, ?> property : properties.entrySet()) {
      String fieldName = property.getKey();
      Object fieldValue = property.getValue();

      Method setter;

      try {
        setter = objectClass.getMethod(getSetterName(fieldName), fieldValue.getClass());
      } catch (NoSuchMethodException e) {
        LOG.debug("No setter for field {} in {}", fieldName, className);
        continue;
      }

      try {
        setter.invoke(object, fieldValue);
      } catch (Exception e) {
        throw ExceptionUtil.toUnchecked("Failed to invoke setter for field " + fieldName + " in " + className, e);
      }
    }

    return object;
  }

  private String getSetterName(String fieldName) {
    return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

}
