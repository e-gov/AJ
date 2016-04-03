
package ee.degeetia.dumonitor.filter.config.xpath.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for loggableFields complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="loggableFields">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loggableField" type="{xpath_conf_schema.xsd}loggableField" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loggableFields", propOrder = {
    "loggableField"
})
public class LoggableFields {

    protected List<LoggableField> loggableField;

    /**
     * Gets the value of the loggableField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggableField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggableField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoggableField }
     * 
     * 
     */
    public List<LoggableField> getLoggableField() {
        if (loggableField == null) {
            loggableField = new ArrayList<LoggableField>();
        }
        return this.loggableField;
    }

}
