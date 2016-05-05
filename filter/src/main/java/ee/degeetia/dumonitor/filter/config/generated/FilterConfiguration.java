/*
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

package ee.degeetia.dumonitor.filter.config.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="namespaces" type="{http://x-road.eu/xsd/dumonitor.xsd}Namespaces" minOccurs="0"/>
 *         &lt;element name="exclusions" type="{http://x-road.eu/xsd/dumonitor.xsd}Exclusions" minOccurs="0"/>
 *         &lt;element name="filters" type="{http://x-road.eu/xsd/dumonitor.xsd}Filters" minOccurs="0"/>
 *         &lt;element name="defaults" type="{http://x-road.eu/xsd/dumonitor.xsd}LoggableFields" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterConfiguration", propOrder = {

})
public class FilterConfiguration {

    protected Namespaces namespaces;
    protected Exclusions exclusions;
    protected Filters filters;
    protected LoggableFields defaults;

    /**
     * Gets the value of the namespaces property.
     * 
     * @return
     *     possible object is
     *     {@link Namespaces }
     *     
     */
    public Namespaces getNamespaces() {
        return namespaces;
    }

    /**
     * Sets the value of the namespaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link Namespaces }
     *     
     */
    public void setNamespaces(Namespaces value) {
        this.namespaces = value;
    }

    /**
     * Gets the value of the exclusions property.
     * 
     * @return
     *     possible object is
     *     {@link Exclusions }
     *     
     */
    public Exclusions getExclusions() {
        return exclusions;
    }

    /**
     * Sets the value of the exclusions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Exclusions }
     *     
     */
    public void setExclusions(Exclusions value) {
        this.exclusions = value;
    }

    /**
     * Gets the value of the filters property.
     * 
     * @return
     *     possible object is
     *     {@link Filters }
     *     
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * Sets the value of the filters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Filters }
     *     
     */
    public void setFilters(Filters value) {
        this.filters = value;
    }

    /**
     * Gets the value of the defaults property.
     * 
     * @return
     *     possible object is
     *     {@link LoggableFields }
     *     
     */
    public LoggableFields getDefaults() {
        return defaults;
    }

    /**
     * Sets the value of the defaults property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoggableFields }
     *     
     */
    public void setDefaults(LoggableFields value) {
        this.defaults = value;
    }

}
