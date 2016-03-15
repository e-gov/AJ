
package ee.degeetia.xrtracker.filter.config.xpath.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xpath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEntry", namespace = "xrt.xsd", propOrder = {
    "xpath",
    "dataKey"
})
public class DataEntry {

    @XmlElement(namespace = "xrt.xsd", required = true)
    protected String xpath;
    @XmlElement(namespace = "xrt.xsd", required = true)
    protected String dataKey;

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
     * Gets the value of the dataKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataKey() {
        return dataKey;
    }

    /**
     * Sets the value of the dataKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataKey(String value) {
        this.dataKey = value;
    }

}
