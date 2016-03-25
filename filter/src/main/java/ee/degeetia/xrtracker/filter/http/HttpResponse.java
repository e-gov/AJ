package ee.degeetia.xrtracker.filter.http;

public class HttpResponse<T> {

  private final HttpStatus status;
  private final T body;

  public HttpResponse(HttpStatus status, T body) {
    this.status = status;
    this.body = body;
  }

  public HttpResponse(HttpStatus status) {
    this(status, null);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public T getBody() {
    return body;
  }

}
