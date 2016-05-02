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

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ObjectMapperTest {

  @Test
  public void testMapping() {
    ObjectMapper<TestObject> mapper = new ObjectMapper<TestObject>(TestObject.class);
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("testString", "test");
    properties.put("testInteger", 123);
    TestObject object = mapper.map(properties);
    assertEquals("test", object.getTestString());
    assertEquals(Integer.valueOf(123), object.getTestInteger());
  }

  @Test
  public void testMissingField() {
    ObjectMapper<TestObject> mapper = new ObjectMapper<TestObject>(TestObject.class);
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("missingField", "test");
    TestObject object = mapper.map(properties);
    assertNull(object.getTestString());
    assertNull(object.getTestInteger());
  }

  @Test
  public void testInvalidType() {
    ObjectMapper<TestObject> mapper = new ObjectMapper<TestObject>(TestObject.class);
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("testInteger", "string");
    TestObject object = mapper.map(properties);
    assertNull(object.getTestString());
    assertNull(object.getTestInteger());
  }

  static class TestObject {
    private String testString;
    private Integer testInteger;

    public String getTestString() {
      return testString;
    }

    public void setTestString(String testString) {
      this.testString = testString;
    }

    public Integer getTestInteger() {
      return testInteger;
    }

    public void setTestInteger(Integer testInteger) {
      this.testInteger = testInteger;
    }
  }

}