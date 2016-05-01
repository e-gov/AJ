package ee.degeetia.dumonitor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for Date operations.
 */
public final class DateUtil {

  public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

  private DateUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Attempts to parse the input string to a Date.
   *
   * @param iso8601Date date in ISO-8601 format (yyyy-MM-dd'T'HH:mm:ssZ)
   * @return Date parsed from the string
   * @throws ParseException if the input string cannot be parsed
   */
  public static Date parse(String iso8601Date) throws ParseException {
    return new ISO8601DateFormat().parse(iso8601Date);
  }

  /**
   * Creates a new Date object from the input Date, with the specified number of seconds added to it.
   *
   * @param date    the input Date
   * @param seconds the number of seconds to add
   * @return the result Date
   */
  public static Date addSeconds(Date date, int seconds) {
    return new Date(date.getTime() + seconds * 1000);
  }

  private static class ISO8601DateFormat extends SimpleDateFormat {
    private ISO8601DateFormat() {
      super(ISO_8601_FORMAT);
    }
  }

}
