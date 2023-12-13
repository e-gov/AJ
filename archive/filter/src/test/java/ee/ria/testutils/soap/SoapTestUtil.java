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
package ee.ria.testutils.soap;

import javax.xml.soap.*;

import ee.ria.dumonitor.common.util.IOUtil;

public final class SoapTestUtil {

  private SoapTestUtil() {
    throw new UnsupportedOperationException();
  }

  public static SOAPMessage createMessage(XmlElement... xmlElements) throws SOAPException {
    SOAPMessage message = MessageFactory.newInstance().createMessage();

    message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, IOUtil.UTF_8.name());
    message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");

    SOAPBody soapBody = message.getSOAPBody();
    for (XmlElement xmlElement : xmlElements) {
      addChildElement(soapBody, xmlElement);
    }

    message.saveChanges();
    return message;
  }

  private static void addChildElement(SOAPElement soapElement, XmlElement xmlElement) throws SOAPException {
    SOAPElement childElement = soapElement.addChildElement(xmlElement.elementName);
    if (xmlElement.value instanceof String) {
      childElement.addTextNode((String) xmlElement.value);
    } else if (xmlElement.value instanceof XmlElement) {
      addChildElement(childElement, (XmlElement) xmlElement.value);
    }
  }

  public static class XmlElement {
    private final String elementName;
    private final Object value;

    public XmlElement(String elementName, String textValue) {
      this.elementName = elementName;
      this.value = textValue;
    }

    public XmlElement(String elementName, XmlElement xmlElement) {
      this.elementName = elementName;
      this.value = xmlElement;
    }
  }

}
