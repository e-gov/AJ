package ee.degeetia.xrtracker.filter.http;

import com.google.gson.Gson;
import ee.degeetia.xrtracker.filter.util.HttpUtil;
import ee.degeetia.xrtracker.filter.util.IOUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

  private static final Logger LOG = LogManager.getLogger(HttpClient.class);

  public <T> HttpResponse<T> post(String url, Object body, Class<T> responseBodyType) throws HttpException {
    return performRequest(url, body, HttpMethod.POST, responseBodyType);
  }

  public HttpResponse<Void> post(String url, Object body) throws HttpException {
    return performRequest(url, body, HttpMethod.POST, null);
  }

  private <T> HttpResponse<T> performRequest(String url,
                                             Object object,
                                             HttpMethod method,
                                             Class<T> responseBodyType) throws HttpException {
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) new URL(url).openConnection();

      String json = serialize(object);
      sendHeaders(connection, method, "application/json", json.length());
      sendBody(connection, json);

      return getResponse(connection, responseBodyType);
    } catch (Exception e) {
      throw new HttpException("Failed to perform " + method + " request", e);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  private void sendHeaders(HttpURLConnection connection,
                           HttpMethod method,
                           String contentType,
                           int contentLength) throws IOException {
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestMethod(method.name());
    connection.setRequestProperty(HttpUtil.HEADER_CONTENT_TYPE, contentType);
    connection.setRequestProperty(HttpUtil.HEADER_CONTENT_LENGTH, Integer.toString(contentLength));
  }

  private void sendBody(HttpURLConnection connection, String json) throws IOException {
    OutputStream outputStream = null;
    try {
      outputStream = connection.getOutputStream();
      IOUtil.writeString(json, outputStream);
      outputStream.flush();
    } finally {
      IOUtil.close(outputStream);
    }
  }

  private <T> HttpResponse<T> getResponse(HttpURLConnection connection, Class<T> responseBodyType) throws IOException {
    HttpStatus status = getStatus(connection);

    if (responseBodyType == null) {
      return new HttpResponse<T>(status);
    }

    InputStream inputStream = connection.getInputStream();
    try {
      T responseBody = deserialize(IOUtil.readString(inputStream), responseBodyType);
      return new HttpResponse<T>(status, responseBody);
    } finally {
      IOUtil.close(inputStream);
    }
  }

  private HttpStatus getStatus(HttpURLConnection connection) throws IOException {
    HttpStatus status = new HttpStatus(connection.getResponseCode(), connection.getResponseMessage());
    if (status.isClientError() || status.isServerError()) {
      LOG.error("Response status is {}: {}", status.getCode(), status.getMessage());
    }
    return status;
  }

  private String serialize(Object object) {
    return new Gson().toJson(object);
  }

  private <T> T deserialize(String json, Class<T> type) {
    return new Gson().fromJson(json, type);
  }

}
