package ee.degeetia.dumonitor.common.config;

import java.net.URL;
import java.util.Date;

/**
 * Properties that are loaded from a .properties file
 */
public enum Property implements PropertyHolder {

  FILTER_CONFIGURATION_FILE("dumonitor.filter.configuration.file"),
  TURVASERVER_URL("dumonitor.filter.turvaserver.url"),
  ANDMEKOGU_URL("dumonitor.filter.andmekogu.url"),
  TURVASERVER_INTERCEPTOR_PATH("dumonitor.filter.turvaserver.interceptor.path"),
  ANDMEKOGU_INTERCEPTOR_PATH("dumonitor.filter.andmekogu.interceptor.path"),
  LOGGER_REST_URL("dumonitor.filter.logger.rest.url"),
  HEARTBEAT_PATH("dumonitor.filter.heartbeat.path"),

  EXECUTOR_THREAD_POOL_SIZE("dumonitor.filter.executor.thread.pool.size"),
  EXECUTOR_QUEUE_CAPACITY("dumonitor.filter.executor.queue.capacity"),
  EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS("dumonitor.filter.executor.shutdown.timeout.seconds"),

  DATABASE_CONNECTSTRING("dumonitor.storage.database.connectstring"),
  DATABASE_USER("dumonitor.storage.database.user"),
  DATABASE_PASSWORD("dumonitor.storage.database.password"),

  XROAD_PRODUCER("dumonitor.storage.xroad.producer"),
  XROAD_PRODUCERNS("dumonitor.storage.xroad.producerns"),
  XROAD_USERID("dumonitor.storage.xroad.userId"),
  XROAD_SERVICE("dumonitor.storage.xroad.service");

  private String key;
  private String value;

  Property(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Integer getInteger() {
    return PropertyConverter.toInteger(this);
  }

  public Date getDate() {
    return PropertyConverter.toDate(this);
  }

  public URL getURL() {
    return PropertyConverter.toURL(this);
  }

}
