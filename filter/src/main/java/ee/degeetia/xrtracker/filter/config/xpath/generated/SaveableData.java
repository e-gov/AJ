
package ee.degeetia.xrtracker.filter.config.xpath.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveableData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saveableData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataEntry" type="{xrt.xsd}dataEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveableData", namespace = "xrt.xsd", propOrder = {
    "dataEntry"
})
public class SaveableData {

    @XmlElement(namespace = "xrt.xsd")
    protected List<DataEntry> dataEntry;

    /**
     * Gets the value of the dataEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataEntry }
     * 
     * 
     */
    public List<DataEntry> getDataEntry() {
        if (dataEntry == null) {
            dataEntry = new ArrayList<DataEntry>();
        }
        return this.dataEntry;
    }

}
