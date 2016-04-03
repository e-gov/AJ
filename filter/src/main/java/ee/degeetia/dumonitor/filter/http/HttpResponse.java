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
