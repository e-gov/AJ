
package ee.degeetia.dumonitor.filter.config.xpath.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loggableFields" type="{xpath_conf_schema.xsd}loggableFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filter", propOrder = {
    "xpath",
    "loggableFields"
})
public class Filter {

    @XmlElement(required = true)
    protected String xpath;
    @XmlElement(required = true)
    protected LoggableFields loggableFields;

    /**
     * Gets the value of the xpath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * Sets the value of the xpath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpath(String value) {
        this.xpath = value;
    }

    /**
     * Gets the value of the loggableFields property.
     * 
     * @return
     *     possible object is
     *     {@link LoggableFields }
     *     
     */
    public LoggableFields getLoggableFields() {
        return loggableFields;
    }

    /**
     * Sets the value of the loggableFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoggableFields }
     *     
     */
    public void setLoggableFields(LoggableFields value) {
        this.loggableFields = value;
    }

}
