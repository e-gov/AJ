package ee.degeetia.dumonitor.filter.log;

import java.util.Date;

public class LogEntry {

  private Date logtime;
  private String personcode;
  private String action;
  private String sender;
  private String receiver;
  private String restrictions;
  private String sendercode;
  private String receivercode;
  private String actioncode;
  private String xroadrequestid;
  private String xroadservice;
  private String usercode;

  public Date getLogtime() {
    return logtime;
  }

  public void setLogtime(Date logtime) {
    this.logtime = logtime;
  }

  public String getPersoncode() {
    return personcode;
  }

  public void setPersoncode(String personcode) {
    this.personcode = personcode;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getRestrictions() {
    return restrictions;
  }

  public void setRestrictions(String restrictions) {
    this.restrictions = restrictions;
  }

  public String getSendercode() {
    return sendercode;
  }

  public void setSendercode(String sendercode) {
    this.sendercode = sendercode;
  }

  public String getReceivercode() {
    return receivercode;
  }

  public void setReceivercode(String receivercode) {
    this.receivercode = receivercode;
  }

  public String getActioncode() {
    return actioncode;
  }

  public void setActioncode(String actioncode) {
    this.actioncode = actioncode;
  }

  public String getXroadrequestid() {
    return xroadrequestid;
  }

  public void setXroadrequestid(String xroadrequestid) {
    this.xroadrequestid = xroadrequestid;
  }

  public String getXroadservice() {
    return xroadservice;
  }

  public void setXroadservice(String xroadservice) {
    this.xroadservice = xroadservice;
  }

  public String getUsercode() {
    return usercode;
  }

  public void setUsercode(String usercode) {
    this.usercode = usercode;
  }

}