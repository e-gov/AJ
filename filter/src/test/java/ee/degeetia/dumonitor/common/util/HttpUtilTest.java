package ee.degeetia.dumonitor.common.util;

import org.junit.Test;

import ee.degeetia.dumonitor.common.util.HttpUtil;

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
