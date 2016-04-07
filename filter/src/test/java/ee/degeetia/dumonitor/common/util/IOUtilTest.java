package ee.degeetia.dumonitor.common.util;

import org.junit.Test;

import ee.degeetia.dumonitor.common.util.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class IOUtilTest {

  public static final String TEST_STRING = "test ^ ` ´ ~ ˇ õäöü";
  public static final byte[] TEST_BYTES = TEST_STRING.getBytes(IOUtil.UTF_8);

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

  private class TestInputStream extends ByteArrayInputStream {
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

  private class TestOutputStream extends ByteArrayOutputStream {
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
