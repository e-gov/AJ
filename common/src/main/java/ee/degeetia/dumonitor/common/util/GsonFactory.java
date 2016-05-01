package ee.degeetia.dumonitor.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Factory class for creating Gson instances.
 */
public final class GsonFactory {

  private GsonFactory() {
    throw new UnsupportedOperationException();
  }

  /**
   * @return a new Gson instance with the required configuration for this application.
   */
  public static Gson createGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.setDateFormat(DateUtil.ISO_8601_FORMAT);
    return builder.create();
  }

}
