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
    assertEquals(new Integer(123), object.getTestInteger());
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