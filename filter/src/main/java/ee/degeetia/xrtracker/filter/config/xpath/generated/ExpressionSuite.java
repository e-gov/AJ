
package ee.degeetia.xrtracker.filter.config.xpath.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for expressionSuite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="expressionSuite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filterExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="saveableData" type="{xrt.xsd}saveableData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expressionSuite", namespace = "xrt.xsd", propOrder = {
    "filterExpression",
    "saveableData"
})
public class ExpressionSuite {

    @XmlElement(namespace = "xrt.xsd", required = true)
    protected String filterExpression;
    @XmlElement(namespace = "xrt.xsd", required = true)
    protected SaveableData saveableData;

    /**
     * Gets the value of the filterExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterExpression() {
        return filterExpression;
    }

    /**
     * Sets the value of the filterExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterExpression(String value) {
        this.filterExpression = value;
    }

    /**
     * Gets the value of the saveableData property.
     * 
     * @return
     *     possible object is
     *     {@link SaveableData }
     *     
     */
    public SaveableData getSaveableData() {
        return saveableData;
    }

    /**
     * Sets the value of the saveableData property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveableData }
     *     
     */
    public void setSaveableData(SaveableData value) {
        this.saveableData = value;
    }

}
