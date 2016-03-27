package ee.degeetia.xrtracker.filter.http;

/**
 * A class that contains the code and the corresponding message of an HTTP status.
 */
public class HttpStatus {

  private final int code;
  private final String message;


  public HttpStatus(int code, String message) {
    if (code < 100 || code > 599) {
      throw new IllegalArgumentException("Invalid HTTP status code " + code);
    }
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public boolean isInformational() {
    return 100 <= code && code <= 199;
  }

  public boolean isSuccess() {
    return 200 <= code && code <= 299;
  }

  public boolean isRedirection() {
    return 300 <= code && code <= 399;
  }

  public boolean isClientError() {
    return 400 <= code && code <= 499;
  }

  public boolean isServerError() {
    return 500 <= code && code <= 599;
  }

}
