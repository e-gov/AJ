package ee.degeetia.xrtracker.filter.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class IOUtilTest {

  public static final byte[] TEST_BYTES = "test ^ ` ´ ~ ˇ õäöü".getBytes(IOUtil.UTF_8);

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
  public void testWriteBytes() throws IOException {
    TestOutputStream outputStream = new TestOutputStream();
    IOUtil.writeBytes(TEST_BYTES, outputStream);

    assertArrayEquals(TEST_BYTES, outputStream.toByteArray());
    assertFalse(outputStream.isClosed());
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
