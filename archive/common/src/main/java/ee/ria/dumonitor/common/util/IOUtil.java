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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Utility class for I/O operations.
 */
public final class IOUtil {

  public static final Charset UTF_8 = Charset.forName("UTF-8");

  private static final Logger LOG = LoggerFactory.getLogger(IOUtil.class);

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
      if (LOG.isErrorEnabled()) {
        LOG.error("Failed to close " + closeable.getClass().getSimpleName(), e);
      }
    }
  }

}
