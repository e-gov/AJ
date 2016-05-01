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

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Date getBuildDate() {
    return buildDate;
  }

  public void setBuildDate(Date buildDate) {
    this.buildDate = buildDate;
  }

  public Date getStartupTime() {
    return startupTime;
  }

  public void setStartupTime(Date startupTime) {
    this.startupTime = startupTime;
  }

  public Date getServerTime() {
    return serverTime;
  }

  public void setServerTime(Date serverTime) {
    this.serverTime = serverTime;
  }

}
