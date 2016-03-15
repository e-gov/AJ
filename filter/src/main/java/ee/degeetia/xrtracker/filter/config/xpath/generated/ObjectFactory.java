
package ee.degeetia.xrtracker.filter.config.xpath.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ee.degeetia.xrtracker.filter.core.config.xpath package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _XPathConfiguration_QNAME = new QName("xrt.xsd", "xPathConfiguration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ee.degeetia.xrtracker.filter.core.config.xpath
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataEntry }
     * 
     */
    public DataEntry createDataEntry() {
        return new DataEntry();
    }

    /**
     * Create an instance of {@link XPathConfiguration }
     * 
     */
    public XPathConfiguration createXPathConfiguration() {
        return new XPathConfiguration();
    }

    /**
     * Create an instance of {@link SaveableData }
     * 
     */
    public SaveableData createSaveableData() {
        return new SaveableData();
    }

    /**
     * Create an instance of {@link ExpressionSuite }
     * 
     */
    public ExpressionSuite createExpressionSuite() {
        return new ExpressionSuite();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XPathConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "xrt.xsd", name = "xPathConfiguration")
    public JAXBElement<XPathConfiguration> createXPathConfiguration(XPathConfiguration value) {
        return new JAXBElement<XPathConfiguration>(_XPathConfiguration_QNAME, XPathConfiguration.class, null, value);
    }

}
