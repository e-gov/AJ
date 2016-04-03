package ee.degeetia.dumonitor.filter.http;

import ee.degeetia.dumonitor.filter.util.Range;

/**
 * A class that contains the code and the corresponding message of an HTTP status.
 */
public class HttpStatus {

  private static final Range<Integer> INFORMATIONAL_CODES = new Range<Integer>(100, 199);
  private static final Range<Integer> SUCCESS_CODES = new Range<Integer>(200, 299);
  private static final Range<Integer> REDIRECTION_CODES = new Range<Integer>(300, 399);
  private static final Range<Integer> CLIENT_ERROR_CODES = new Range<Integer>(400, 499);
  private static final Range<Integer> SERVER_ERROR_CODES = new Range<Integer>(500, 599);

  private static final Range<Integer> VALID_CODES = new Range<Integer>(100, 599);

  private final int code;
  private final String message;

  /**
   * Creates a new HttpStatus instance.
   *
   * @param code    the HTTP status code
   * @param message the HTTP response message that goes along with the code
   */
  public HttpStatus(int code, String message) {
    if (!VALID_CODES.contains(code)) {
      throw new IllegalArgumentException("Invalid HTTP status code " + code);
    }
    this.code = code;
    this.message = message;
  }

  /**
   * @return the HTTP status code
   */
  public int getCode() {
    return code;
  }

  /**
   * @return the HTTP status message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return true if the HTTP status code is 1XX (informational)
   */
  public boolean isInformational() {
    return INFORMATIONAL_CODES.contains(code);
  }

  /**
   * @return true if the HTTP status code is 2XX (success)
   */
  public boolean isSuccess() {
    return SUCCESS_CODES.contains(code);
  }

  /**
   * @return true if the HTTP status code is 3XX (redirection)
   */
  public boolean isRedirection() {
    return REDIRECTION_CODES.contains(code);
  }

  /**
   * @return true if the HTTP status code is 4XX (client error)
   */
  public boolean isClientError() {
    return CLIENT_ERROR_CODES.contains(code);
  }

  /**
   * @return true if the HTTP status code is 5XX (server error)
   */
  public boolean isServerError() {
    return SERVER_ERROR_CODES.contains(code);
  }

}
