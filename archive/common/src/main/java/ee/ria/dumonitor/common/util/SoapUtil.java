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

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Utility class for SOAP parsing.
 */
public final class SoapUtil {

  private SoapUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Creates a {@link SOAPMessage} object from the specified byte array.
   *
   * @param content     the raw message to parse the <code>SOAPMessage</code> from
   * @param contentType the HTTP Content-Type value that corresponds to the message. Required in order to determine if
   *                    the message should be parsed as a standard XML message or a multipart message with attachments.
   * @return the SOAPMessage object parsed from the given byte array
   * @throws SOAPException if the message is not a valid SOAP message
   */
  public static SOAPMessage parseMessage(byte[] content, String contentType) throws SOAPException {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
    try {
      MessageFactory messageFactory = MessageFactory.newInstance();
      MimeHeaders headers = new MimeHeaders();
      headers.addHeader(HttpUtil.HEADER_CONTENT_TYPE, contentType);
      return messageFactory.createMessage(headers, inputStream);
    } catch (IOException e) {
      /* Should never happen - ByteArrayInputStream in this case is always readable and if its contents are invalid,
       * SOAPException will be thrown instead. */
      throw ExceptionUtil.toUnchecked(e);
    } finally {
      IOUtil.close(inputStream);
    }
  }

}
