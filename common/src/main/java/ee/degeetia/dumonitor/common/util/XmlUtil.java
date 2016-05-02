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
package ee.degeetia.dumonitor.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Utility class for XML related actions.
 */
public final class XmlUtil {

  private XmlUtil() {
    throw new UnsupportedOperationException();
  }

  /**
   * Attempts to unmarshal an XML document from an InputStream.
   *
   * @param inputStream InputStream to unmarshal XML from.
   * @param type        JAXB class of the returned object. Must be in the same package as other relevant JAXB classes.
   * @param <T>         the type of the returned object
   * @return the unmarshalled JAXB object
   * @throws JAXBException if an unexpected error occurs while unmarshalling
   */
  public static <T> T unmarshal(InputStream inputStream, Class<T> type) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(type.getPackage().getName());
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    @SuppressWarnings("unchecked")
    JAXBElement<T> jaxbElement = (JAXBElement<T>) unmarshaller.unmarshal(inputStream);
    return jaxbElement.getValue();
  }

}
