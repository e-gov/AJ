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

package ee.degeetia.dumonitor.filter.config.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ee.degeetia.dumonitor.filter.config.generated package. 
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

    private final static QName _FilterConfiguration_QNAME = new QName("http://x-road.eu/xsd/dumonitor.xsd", "filterConfiguration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ee.degeetia.dumonitor.filter.config.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filters }
     * 
     */
    public Filters createFilters() {
        return new Filters();
    }

    /**
     * Create an instance of {@link FilterConfiguration }
     * 
     */
    public FilterConfiguration createFilterConfiguration() {
        return new FilterConfiguration();
    }

    /**
     * Create an instance of {@link Namespace }
     * 
     */
    public Namespace createNamespace() {
        return new Namespace();
    }

    /**
     * Create an instance of {@link Namespaces }
     * 
     */
    public Namespaces createNamespaces() {
        return new Namespaces();
    }

    /**
     * Create an instance of {@link Exclusions }
     * 
     */
    public Exclusions createExclusions() {
        return new Exclusions();
    }

    /**
     * Create an instance of {@link LoggableFields }
     * 
     */
    public LoggableFields createLoggableFields() {
        return new LoggableFields();
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://x-road.eu/xsd/dumonitor.xsd", name = "filterConfiguration")
    public JAXBElement<FilterConfiguration> createFilterConfiguration(FilterConfiguration value) {
        return new JAXBElement<FilterConfiguration>(_FilterConfiguration_QNAME, FilterConfiguration.class, null, value);
    }

}
