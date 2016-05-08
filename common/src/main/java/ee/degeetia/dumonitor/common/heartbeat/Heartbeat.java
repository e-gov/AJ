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
package ee.degeetia.dumonitor.common.heartbeat;

import java.util.Date;

/**
 * Model class for the heartbeat service.
 */
public class Heartbeat {

  private String systemName;
  private String version;
  private Date buildDate;
  private Date startupTime;
  private Date serverTime;

  /**
   * @return the name of this application
   */
  public String getSystemName() {
    return systemName;
  }

  /**
   * @param systemName the name of this application
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  /**
   * @return the version number of this application
   */
  public String getVersion() {
    return version;
  }

  /**
   * @param version the version number of this application
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * @return the build date of this application
   */
  public Date getBuildDate() {
    return buildDate;
  }

  /**
   * @param buildDate the build date of this application
   */
  public void setBuildDate(Date buildDate) {
    this.buildDate = buildDate;
  }

  /**
   * @return the time at which this application was last started
   */
  public Date getStartupTime() {
    return startupTime;
  }

  /**
   * @param startupTime the time at which this application was last started
   */
  public void setStartupTime(Date startupTime) {
    this.startupTime = startupTime;
  }

  /**
   * @return the server time
   */
  public Date getServerTime() {
    return serverTime;
  }

  /**
   * @param serverTime the server time
   */
  public void setServerTime(Date serverTime) {
    this.serverTime = serverTime;
  }

}
