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
  
  // common xroad configuration
  XROAD_USERID("dumonitor.storage.xroad.userId"),
  XROAD_PRODUCERNS("dumonitor.storage.xroad.producerns"),
  
  // old xroad configuration
  XROAD_PRODUCER("dumonitor.storage.xroad.producer"),
  XROAD_SERVICE("dumonitor.storage.xroad.service"),
    
  // new xroad configuration
  XROAD_MEMBER_XROAD_INSTANCE("dumonitor.storage.xroad.member.xroadinstance"),
  XROAD_MEMBER_MEMBER_CLASS("dumonitor.storage.xroad.member.memberclass"),
  XROAD_MEMBER_MEMBER_CODE("dumonitor.storage.xroad.member.membercode"),
  XROAD_MEMBER_MEMBER_SUBSYSTEM_CODE("dumonitor.storage.xroad.member.subsystemcode"),
  
  XROAD_SERVICE_XROAD_INSTANCE("dumonitor.storage.xroad.service.xroadinstance"),
  XROAD_SERVICE_MEMBER_CLASS("dumonitor.storage.xroad.service.memberclass"),
  XROAD_SERVICE_MEMBER_CODE("dumonitor.storage.xroad.service.membercode"),
  XROAD_SERVICE_MEMBER_SUBSYSTEM_CODE("dumonitor.storage.xroad.service.subsystemcode"),
  XROAD_SERVICE_SERVICE_CODE("dumonitor.storage.xroad.service.servicecode"),
  XROAD_SERVICE_SERVICE_VERSION("dumonitor.storage.xroad.service.serviceversion"),
  
  QUERY_TURVASERVER_URL("dumonitor.query.xroad.url");

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
