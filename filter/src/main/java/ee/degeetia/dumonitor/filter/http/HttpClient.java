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

import com.google.gson.Gson;

import ee.degeetia.dumonitor.common.util.GsonFactory;
import ee.degeetia.dumonitor.common.util.HttpUtil;
import ee.degeetia.dumonitor.common.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple class for performing HTTP requests using JSON for encoding data.
 */
public class HttpClient {

  private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

  private final Gson gson = GsonFactory.createGson();

  /**
   * Sends an HTTP GET request to the specified URL.
   *
   * @param url          the URL to which to send the request
   * @param responseType the type of response to expect
   * @param <T>          the type of response to expect
   * @return a {@link HttpResponse} object containing the response status and response body
   * @throws HttpException if something goes wrong when performing the request
   */
  public <T> HttpResponse<T> get(URL url, Class<T> responseType) throws HttpException {
    return execute(url, null, "GET", responseType);
  }

  /**
   * Sends an HTTP POST request to the specified URL.
   *
   * @param url          the URL to which to send the request
   * @param body         the request body which will be JSON-encoded
   * @param responseType the type of response to expect
   * @param <T>          the type of response to expect
   * @return a {@link HttpResponse} object containing the response status and response body
   * @throws HttpException if something goes wrong when performing the request
   */
  public <T> HttpResponse<T> post(URL url, Object body, Class<T> responseType) throws HttpException {
    return execute(url, body, "POST", responseType);
  }

  /**
   * Sends an HTTP POST request to the specified URL.
   *
   * @param url  the URL to which to send the request
   * @param body the request body which will be JSON-encoded
   * @return a {@link HttpResponse} object containing the response status
   * @throws HttpException if something goes wrong when performing the request
   */
  public HttpResponse<Void> post(URL url, Object body) throws HttpException {
    return execute(url, body, "POST", null);
  }

  private <T> HttpResponse<T> execute(URL url, Object body, String method, Class<T> responseType) throws HttpException {
    HttpURLConnection connection = null;
    try {
      connection = HttpUtil.openConnection(url);

      if (body != null) {
        String json = gson.toJson(body);
        sendHeaders(connection, method, "application/json", json.length());
        sendBody(connection, json);
      }

      return getResponse(connection, responseType);
    } catch (Exception e) {
      throw new HttpException("Failed to perform " + method + " request", e);
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  private void sendHeaders(HttpURLConnection connection,
                           String method,
                           String contentType,
                           int contentLength) throws IOException {
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestMethod(method);
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

  private <T> HttpResponse<T> getResponse(HttpURLConnection connection, Class<T> responseType) throws IOException {
    HttpStatus status = getStatus(connection);

    if (responseType == null) {
      return new HttpResponse<T>(status);
    }

    InputStream inputStream = connection.getInputStream();
    try {
      T responseBody = gson.fromJson(IOUtil.readString(inputStream), responseType);
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

}
