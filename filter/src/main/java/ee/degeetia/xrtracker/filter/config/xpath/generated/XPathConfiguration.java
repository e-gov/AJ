
package ee.degeetia.xrtracker.filter.config.xpath.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for xPathConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xPathConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expressionSuite" type="{xrt.xsd}expressionSuite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xPathConfiguration", namespace = "xrt.xsd", propOrder = {
    "expressionSuite"
})
public class XPathConfiguration {

    @XmlElement(namespace = "xrt.xsd")
    protected List<ExpressionSuite> expressionSuite;

    /**
     * Gets the value of the expressionSuite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expressionSuite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpressionSuite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpressionSuite }
     * 
     * 
     */
    public List<ExpressionSuite> getExpressionSuite() {
        if (expressionSuite == null) {
            expressionSuite = new ArrayList<ExpressionSuite>();
        }
        return this.expressionSuite;
    }

}
