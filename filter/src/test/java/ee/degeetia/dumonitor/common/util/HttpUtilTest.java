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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

import static org.mockito.Mockito.*;

public class HttpUtilTest {

  @Test
  public void testCopyRequestHeaders() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpURLConnection connection = mock(HttpURLConnection.class);

    when(request.getMethod()).thenReturn("POST");
    when(request.getContentType()).thenReturn("text/xml");
    when(request.getContentLength()).thenReturn(128);

    HttpUtil.copyRequestHeaders(request, connection);

    verify(connection).setRequestMethod("POST");
    verify(connection).setRequestProperty("Content-Type", "text/xml");
    verify(connection).setRequestProperty("Content-Length", "128");

    verifyNoMoreInteractions(connection);
  }

  @Test
  public void testCopyResponseHeaders() throws IOException {
    HttpURLConnection connection = mock(HttpURLConnection.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    when(connection.getResponseCode()).thenReturn(200);
    when(connection.getContentType()).thenReturn("text/xml");
    when(connection.getContentLength()).thenReturn(128);

    HttpUtil.copyResponseHeaders(connection, response);

    verify(response).setStatus(200);
    verify(response).setContentType("text/xml");
    verify(response).setContentLength(128);

    verifyNoMoreInteractions(response);
  }

}
