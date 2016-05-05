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