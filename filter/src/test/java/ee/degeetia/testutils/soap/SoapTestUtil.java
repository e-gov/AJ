package ee.degeetia.testutils.soap;

import ee.degeetia.xrtracker.filter.util.IOUtil;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

  public static byte[] createByteArray(XmlElement... xmlElements) throws SOAPException, IOException {
    SOAPMessage message = createMessage(xmlElements);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      message.writeTo(out);
      return out.toByteArray();
    } finally {
      IOUtil.close(out);
    }
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
