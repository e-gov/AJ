package ee.degeetia.dumonitor.filter.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Utility class for I/O operations.
 */
public final class IOUtil {

  public static final Charset UTF_8 = Charset.forName("UTF-8");

  private static final Logger LOG = LogManager.getLogger(IOUtil.class);

  private static final int BUFFER_SIZE = 4096;

  private IOUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Writes the contents of <code>inputStream</code> directly to <code>outputStream</code>. Streams are <u>not</u>
   * closed after the operation.
   *
   * @param inputStream  the <code>InputStream</code> to read from
   * @param outputStream the <code>OutputStream</code> to write to
   * @throws IOException if an I/O error occurs
   */
  public static void pipe(InputStream inputStream, OutputStream outputStream) throws IOException {
    int len;
    byte[] buffer = new byte[BUFFER_SIZE];
    while ((len = inputStream.read(buffer)) > -1) {
      outputStream.write(buffer, 0, len);
    }
  }

  /**
   * Returns the contents of <code>inputStream</code> as a byte array. <code>inputStream</code> is <u>not</u> closed
   * after the operation.
   *
   * @param inputStream the <code>InputStream</code> to read from
   * @return the contents of <code>inputStream</code> as a byte array
   * @throws IOException if an I/O error occurs
   */
  public static byte[] readBytes(InputStream inputStream) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      pipe(inputStream, outputStream);
      return outputStream.toByteArray();
    } finally {
      close(outputStream);
    }
  }

  /**
   * Returns the contents of <code>inputStream</code> as a String. <code>inputStream</code> is <u>not</u> closed after
   * the operation.
   *
   * @param inputStream the <code>InputStream</code> to read from
   * @return the contents of <code>inputStream</code> as a String
   * @throws IOException if an I/O error occurs
   */
  public static String readString(InputStream inputStream) throws IOException {
    return new String(readBytes(inputStream), UTF_8);
  }

  /**
   * Writes the contents of <code>bytes</code> to <code>outputStream</code>. <code>outputStream</code> is <u>not</u>
   * closed after the operation.
   *
   * @param bytes        the byte array to write
   * @param outputStream the <code>OutputStream</code> to write to
   * @throws IOException if an I/O error occurs
   */
  public static void writeBytes(byte[] bytes, OutputStream outputStream) throws IOException {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
    try {
      pipe(inputStream, outputStream);
    } finally {
      close(inputStream);
    }
  }

  /**
   * Writes the contents of <code>string</code> to <code>outputStream</code>. <code>outputStream</code> is <u>not</u>
   * closed after the operation.
   *
   * @param string       the String to write
   * @param outputStream the <code>OutputStream</code> to write to
   * @throws IOException if an I/O error occurs
   */
  public static void writeString(String string, OutputStream outputStream) throws IOException {
    writeBytes(string.getBytes(UTF_8), outputStream);
  }

  /**
   * Attempts to close a stream, removing the need for a null check and try/catch in <code>finally</code> blocks. If an
   * I/O error occurs, catches and logs the error.
   *
   * @param closeable the stream to close
   */
  public static void close(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException e) {
      LOG.error("Failed to close " + closeable.getClass().getSimpleName(), e);
    }
  }

}
