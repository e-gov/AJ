/**
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

package ee.degeetia.dumonitor.filter.config.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoggableFields complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoggableFields">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="personcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="restrictions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receivercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actioncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xroadrequestid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xroadservice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usercode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoggableFields", propOrder = {

})
public class LoggableFields {

    protected String personcode;
    protected String action;
    protected String sender;
    protected String receiver;
    protected String restrictions;
    protected String sendercode;
    protected String receivercode;
    protected String actioncode;
    protected String xroadrequestid;
    protected String xroadservice;
    protected String usercode;

    /**
     * Gets the value of the personcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersoncode() {
        return personcode;
    }

    /**
     * Sets the value of the personcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersoncode(String value) {
        this.personcode = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestrictions(String value) {
        this.restrictions = value;
    }

    /**
     * Gets the value of the sendercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendercode() {
        return sendercode;
    }

    /**
     * Sets the value of the sendercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendercode(String value) {
        this.sendercode = value;
    }

    /**
     * Gets the value of the receivercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivercode() {
        return receivercode;
    }

    /**
     * Sets the value of the receivercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivercode(String value) {
        this.receivercode = value;
    }

    /**
     * Gets the value of the actioncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActioncode() {
        return actioncode;
    }

    /**
     * Sets the value of the actioncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActioncode(String value) {
        this.actioncode = value;
    }

    /**
     * Gets the value of the xroadrequestid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXroadrequestid() {
        return xroadrequestid;
    }

    /**
     * Sets the value of the xroadrequestid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXroadrequestid(String value) {
        this.xroadrequestid = value;
    }

    /**
     * Gets the value of the xroadservice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXroadservice() {
        return xroadservice;
    }

    /**
     * Sets the value of the xroadservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXroadservice(String value) {
        this.xroadservice = value;
    }

    /**
     * Gets the value of the usercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * Sets the value of the usercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsercode(String value) {
        this.usercode = value;
    }

}
