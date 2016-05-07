/*
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

  BLACKLIST("dumonitor.blacklist"),

  DATABASE_JNDI("dumonitor.storage.database.jndi"),
  DATABASE_CONNECTSTRING("dumonitor.storage.database.connectstring"),
  DATABASE_USER("dumonitor.storage.database.user"),
  DATABASE_PASSWORD("dumonitor.storage.database.password"),

  XROAD_PRODUCER("dumonitor.storage.xroad.producer"),
  XROAD_PRODUCERNS("dumonitor.storage.xroad.producerns"),
  XROAD_USERID("dumonitor.storage.xroad.userId"),
  XROAD_SERVICE("dumonitor.storage.xroad.service"),

  QUERY_TURVASERVER_URL("dumonitor.query.xroad.url");

  private String key;
  private String value;

  Property(String key) {
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public Integer getInteger() {
    return PropertyConverter.toInteger(this);
  }

  @Override
  public Date getDate() {
    return PropertyConverter.toDate(this);
  }

  @Override
  public URL getURL() {
    return PropertyConverter.toURL(this);
  }

  @Override
  public String[] getArray() {
    return PropertyConverter.toArray(this);
  }

}
