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
package ee.ria.dumonitor.common.util;

import org.junit.Test;

import ee.ria.dumonitor.common.util.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class IOUtilTest {

  private static final String TEST_STRING = "test ^ ` ´ ~ ˇ õäöü";
  private static final byte[] TEST_BYTES = TEST_STRING.getBytes(IOUtil.UTF_8);

  @Test
  public void testPipe() throws IOException {
    TestInputStream inputStream = new TestInputStream(TEST_BYTES);
    TestOutputStream outputStream = new TestOutputStream();
    IOUtil.pipe(inputStream, outputStream);

    assertArrayEquals(TEST_BYTES, outputStream.toByteArray());
    assertFalse(inputStream.isClosed());
    assertFalse(outputStream.isClosed());
  }

  @Test
  public void testReadBytes() throws IOException {
    TestInputStream inputStream = new TestInputStream(TEST_BYTES);
    byte[] bytes = IOUtil.readBytes(inputStream);

    assertArrayEquals(TEST_BYTES, bytes);
    assertFalse(inputStream.isClosed());
  }

  @Test
  public void testReadString() throws IOException {
    TestInputStream inputStream = new TestInputStream(TEST_BYTES);
    String string = IOUtil.readString(inputStream);

    assertEquals(TEST_STRING, string);
    assertFalse(inputStream.isClosed());
  }

  @Test
  public void testWriteBytes() throws IOException {
    TestOutputStream outputStream = new TestOutputStream();
    IOUtil.writeBytes(TEST_BYTES, outputStream);

    assertArrayEquals(TEST_BYTES, outputStream.toByteArray());
    assertFalse(outputStream.isClosed());
  }

  @Test
  public void testWriteString() throws IOException {
    TestOutputStream outputStream = new TestOutputStream();
    IOUtil.writeString(TEST_STRING, outputStream);

    assertEquals(TEST_STRING, outputStream.toString(IOUtil.UTF_8.name()));
    assertFalse(outputStream.isClosed());
  }

  @Test
  public void testClose() {
    TestInputStream inputStream = new TestInputStream(new byte[0]);
    assertFalse(inputStream.isClosed());
    IOUtil.close(inputStream);
    assertTrue(inputStream.isClosed());
  }

  private static class TestInputStream extends ByteArrayInputStream {
    private boolean closed;

    public TestInputStream(byte[] bytes) {
      super(bytes);
    }

    @Override
    public void close() throws IOException {
      super.close();
      closed = true;
    }

    public boolean isClosed() {
      return closed;
    }
  }

  private static class TestOutputStream extends ByteArrayOutputStream {
    private boolean closed;

    @Override
    public void close() throws IOException {
      super.close();
      closed = true;
    }

    public boolean isClosed() {
      return closed;
    }
  }

}
