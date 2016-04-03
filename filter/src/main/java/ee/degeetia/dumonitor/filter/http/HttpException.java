package ee.degeetia.dumonitor.filter.http;

/**
 * An exception thrown by {@link HttpClient} in case an HTTP request could not be performed.
 */
public class HttpException extends Exception {

  /**
   * Creates a new HttpException
   *
   * @param message the detail message
   * @param cause   the cause
   */
  public HttpException(String message, Throwable cause) {
    super(message, cause);
  }

}
