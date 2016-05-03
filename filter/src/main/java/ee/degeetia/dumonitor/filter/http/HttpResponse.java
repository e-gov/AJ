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
package ee.degeetia.dumonitor.filter.http;

/**
 * Contains the response status and response body of HTTP requests performed by {@link HttpClient}.
 *
 * @param <T> type of the response body
 */
public class HttpResponse<T> {

  private final HttpStatus status;
  private final T body;

  /**
   * Creates an instance of HttpResponse.
   *
   * @param status the HTTP status of the response
   * @param body   the body of the response
   */
  public HttpResponse(HttpStatus status, T body) {
    this.status = status;
    this.body = body;
  }

  /**
   * Creates an instance of HttpResponse with an empty response body.
   *
   * @param status the HTTP status of the response
   */
  public HttpResponse(HttpStatus status) {
    this(status, null);
  }

  /**
   * @return the HTTP status of the response
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * @return the body of the response, or null if the body is empty
   */
  public T getBody() {
    return body;
  }

}
