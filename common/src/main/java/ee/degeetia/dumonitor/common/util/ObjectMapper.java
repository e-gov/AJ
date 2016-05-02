/**
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Class for mapping properties from a {@link Map} to a POJO.
 *
 * @param <T> the type of the POJO. Must have a default constructor and setters for all fields.
 */
public class ObjectMapper<T> {

  private static final Logger LOG = LoggerFactory.getLogger(ObjectMapper.class);

  private final Class<T> objectClass;

  /**
   * Creates an instance of ObjectMapper for the specified class.
   *
   * @param objectClass the type of objects this ObjectMapper will map
   */
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
