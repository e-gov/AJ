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
package ee.degeetia.dumonitor.filter.http;

import ee.degeetia.testutils.jetty.EmbeddedJettyHttpServer;
import ee.degeetia.testutils.jetty.EmbeddedJettyIntegrationTest;
import ee.degeetia.testutils.servlet.MirroringServlet;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HttpClientIntegrationTest extends EmbeddedJettyIntegrationTest {

  private HttpClient httpClient = new HttpClient();

  private URL testEndpoint;

  public HttpClientIntegrationTest() {
    super(new EmbeddedJettyHttpServer());
  }

  @Before
  public void setTestEndpoint() throws Exception {
    testEndpoint = new URL(getApplicationUrl() + "/test/rest");
  }

  @Before
  public void createServerMock() {
    createServlet(MirroringServlet.class, testEndpoint.getPath());
  }

  @Test
  public void testPostObject() throws Exception {
    TestObject request = new TestObject();
    request.setString("stringValue");
    request.setNumber(123.456);
    request.setArray(new int[]{123, 456});
    request.setList(Arrays.asList(1, 2, 3));

    HttpResponse<TestObject> response = httpClient.post(testEndpoint, request, TestObject.class);
    HttpStatus status = response.getStatus();
    TestObject body = response.getBody();

    assertEquals(200, status.getCode());
    assertEquals("OK", status.getMessage());

    assertEquals("stringValue", body.getString());
    assertEquals(123.456, body.getNumber(), 0);
    assertArrayEquals(new int[]{123, 456}, body.getArray());
    assertEquals(Arrays.asList(1, 2, 3), body.getList());
  }

  @Test
  public void testPostMap() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("string", "stringValue");
    map.put("number", 123.456);
    HttpResponse<TestObject> response = httpClient.post(testEndpoint, map, TestObject.class);
    HttpStatus status = response.getStatus();
    TestObject body = response.getBody();

    assertEquals(200, status.getCode());
    assertEquals("OK", status.getMessage());

    assertEquals("stringValue", body.getString());
    assertEquals(123.456, body.getNumber(), 0);
  }

  private static class TestObject {
    private String string;
    private double number;
    private int[] array;
    private List<Integer> list;

    String getString() {
      return string;
    }

    void setString(String string) {
      this.string = string;
    }

    int[] getArray() {
      return array;
    }

    void setArray(int[] array) {
      this.array = array;
    }

    double getNumber() {
      return number;
    }

    void setNumber(double number) {
      this.number = number;
    }

    List<Integer> getList() {
      return list;
    }

    void setList(List<Integer> list) {
      this.list = list;
    }
  }

}
