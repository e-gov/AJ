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
package ee.ria.dumonitor.filter.log;

import java.util.Date;

/**
 * Model class that contains information sent to the logger service.
 */
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

  /**
   * @return the value of the field 'logtime'
   */
  public Date getLogtime() {
    return logtime;
  }

  /**
   * @param logtime the value of the field 'logtime'
   */
  public void setLogtime(Date logtime) {
    this.logtime = logtime;
  }

  /**
   * @return the value of the field 'personcode'
   */
  public String getPersoncode() {
    return personcode;
  }

  /**
   * @param personcode the value of the field 'personcode'
   */
  public void setPersoncode(String personcode) {
    this.personcode = personcode;
  }

  /**
   * @return the value of the field 'action'
   */
  public String getAction() {
    return action;
  }

  /**
   * @param action the value of the field 'action'
   */
  public void setAction(String action) {
    this.action = action;
  }

  /**
   * @return the value of the field 'sender'
   */
  public String getSender() {
    return sender;
  }

  /**
   * @param sender the value of the field 'sender'
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * @return the value of the field 'receiver'
   */
  public String getReceiver() {
    return receiver;
  }

  /**
   * @param receiver the value of the field 'receiver'
   */
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  /**
   * @return the value of the field 'restrictions'
   */
  public String getRestrictions() {
    return restrictions;
  }

  /**
   * @param restrictions the value of the field 'restrictions'
   */
  public void setRestrictions(String restrictions) {
    this.restrictions = restrictions;
  }

  /**
   * @return the value of the field 'sendercode'
   */
  public String getSendercode() {
    return sendercode;
  }

  /**
   * @param sendercode the value of the field 'sendercode'
   */
  public void setSendercode(String sendercode) {
    this.sendercode = sendercode;
  }

  /**
   * @return the value of the field 'receivercode'
   */
  public String getReceivercode() {
    return receivercode;
  }

  /**
   * @param receivercode the value of the field 'receivercode'
   */
  public void setReceivercode(String receivercode) {
    this.receivercode = receivercode;
  }

  /**
   * @return the value of the field 'actioncode'
   */
  public String getActioncode() {
    return actioncode;
  }

  /**
   * @param actioncode the value of the field 'actioncode'
   */
  public void setActioncode(String actioncode) {
    this.actioncode = actioncode;
  }

  /**
   * @return the value of the field 'xroadrequestid'
   */
  public String getXroadrequestid() {
    return xroadrequestid;
  }

  /**
   * @param xroadrequestid the value of the field 'xroadrequestid'
   */
  public void setXroadrequestid(String xroadrequestid) {
    this.xroadrequestid = xroadrequestid;
  }

  /**
   * @return the value of the field 'xroadservice'
   */
  public String getXroadservice() {
    return xroadservice;
  }

  /**
   * @param xroadservice the value of the field 'xroadservice'
   */
  public void setXroadservice(String xroadservice) {
    this.xroadservice = xroadservice;
  }

  /**
   * @return the value of the field 'usercode'
   */
  public String getUsercode() {
    return usercode;
  }

  /**
   * @param usercode the value of the field 'usercode
   */
  public void setUsercode(String usercode) {
    this.usercode = usercode;
  }

}
