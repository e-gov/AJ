package ee.degeetia.xrtracker.filter.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public final class XmlUtil {

  private XmlUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * @param inputStream InputStream to unmarshal XML from.
   * @param type        JAXB class of the returned object. Must be in the same package as other relevant JAXB classes.
   * @return unmarshalled JAXB object
   */
  @SuppressWarnings("unchecked")
  public static <T> T unmarshal(InputStream inputStream, Class<T> type) throws JAXBException {
    Unmarshaller unmarshaller = JAXBContext.newInstance(type.getPackage().getName()).createUnmarshaller();
    return ((JAXBElement<T>) unmarshaller.unmarshal(inputStream)).getValue();
  }

}
