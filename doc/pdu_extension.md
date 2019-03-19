# X-Road: Personal data usage extension

**Technical Specification**

Version: 0.1.0  
Doc. ID: PR-PDU

---

## Version history

 Date       | Version | Description                                           | Author
 ---------- | ------- | ------------------------------------------------------|--------------------
 23.01.2019 | 0.1.0   | Initial version                                       | Vitali Stupin
 
## Table of Contents

<!-- toc -->
- [X-Road: Personal data usage extension](#x-road-personal-data-usage-extension)
    - [Version history](#version-history)
    - [Table of Contents](#table-of-contents)
    - [License](#license)
    - [1 Introduction](#1-introduction)
    - [2 Format of Messages](#2-format-of-messages)
        - [2.1 Personal Data Usage information](#21-personal-data-usage-information)
        - [2.2 Message Headers](#22-message-headers)
    - [Annex A XML Schema for Personal Data Usage](#annex-a-xml-schema-for-personal-data-usage)
    - [Annex B Example WSDL](#annex-b-example-wsdl)
    - [Annex C Example Messages](#annex-c-example-messages)
        - [C.1 Example Request](#c1-example-request)
        - [C.1 Example Response](#c1-example-response)
<!-- tocstop -->

## License

This document is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License. To view a copy of this license, visit [http://creativecommons.org/licenses/by-sa/3.0/](http://creativecommons.org/licenses/by-sa/3.0/).

## 1 Introduction

This specification describes an extension of X-Road message protocol v4.0 (https://github.com/nordic-institute/X-Road/blob/master/doc/Protocols/pr-mess_x-road_message_protocol.md).

The core idea of this extension is to enable data requesters send additional information along with the X-Road message concerning personal data usage. X-Road service providers could then use this information to configure the Personal Data Usage Monitor (https://github.com/e-gov/AJ) in a way that gives citizens better understanding of the reasons why and the systems by which their data was used.

The existing Personal Data Usage Monitor system configuration depends largely on the decisions of the data source, making it difficult for the data requester to influence the information provided to the citizen. In real world examples, most of the useful information about the reasons for data requests and the actual system used to process said request is rather owned by the data requester than the data source. Every data transaction needs to have an auditable reason which is either governed by the law or an internal process. That makes it possible to include this information in the request.

Current X-Road protocol does not include fields tailored to provide transparency for the average citizen, making it difficult to display easily understood explanations for data usage. Also, hiding logs that can not be seen by citizens should be more granular and configurable, seeing as one system could be used by several authorities with different needs. The need for X-Road protocol extensions became apparent in multiple interviews with X-Road users conducted in late 2018 and early 2019.

The extension describes three distinct elements that could be added to every data request, making it easier for the data source to configure their data monitor in a way that provides most value to citizens and increases overall transparency.

1) Element reason is used to provide a human-readable reason why the request was performed.

2) Element system is used to provide a human-readable name of system that performed the request. In several cases, the registry code of an agency is not sufficient information as one central agency can provide hosting for different systems with different users.

3) Boolean element hidden determines whether the fact that a data request was made MUST be hidden from the person whose data was processed during this request or not. This feature can be used only if service consumer has rights by law to perform queries that cannot me monitored by Personal Data Usage Monitoring system (such as the police or internal/foreign intelligence agencies etc)

This specification describes personal data usage extension version 0.1.

## 2 Format of Messages

### 2.1 Personal Data Usage information

This section describes XML-based data formats for expressing personal data usage. The data structures and elements defined in this section will be located under namespace [http://x-road.eu/xsd/pdu.xsd](http://x-road.eu/xsd/pdu.xsd). The complete XML Schema is shown in [Annex A XML Schema for Personal Data Usage](#annex-a-xml-schema-for-personal-data-usage).

The following listing shows the header of the schema definition.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
    elementFormDefault="qualified"
    targetNamespace="http://x-road.eu/xsd/pdu.xsd"
    xmlns="http://x-road.eu/xsd/pdu.xsd">
```

The `PduType` complex type is used to describe personal data usage information. It consists of three elements – `reason`, `system` and `hidden` from which only `reason` is mandatory and others can be used for additional information.

```xml
<xs:complexType name="PduType">
    <xs:sequence>
        <xs:element minOccurs="1" ref="reason"/>
        <xs:element minOccurs="0" ref="system"/>
        <xs:element minOccurs="0" ref="hidden"/>
    </xs:sequence>
</xs:complexType>
```

Next, we define the elements used in the `PduType`.

Element `reason` is used to provide a human-readable reason why this request was performed.

Element `system` is used to provide a human-readable name of system that performed the request.

If boolean element `hidden` has value `true` then this request MUST be hidden from the person who's data was processed during this request. This feature can be used only if service consumer by law has rights to perform queries that cannot me monitored by Personal Data usage monitoring system.

```xml
<xs:element name="reason" type="xs:string"/>
<xs:element name="system" type="xs:string"/>
<xs:element name="hidden" type="xs:boolean"/>
```

Finally we define the `pdu` element.

```xml
<xs:element name="pdu" type="PduType"/>
```

### 2.2 Message Headers

This section describes additional SOAP headers that are used by the X-Road system. The header fields are described in [Table 1](#Ref_Supported_header_fields).

<a name="Ref_Supported_header_fields" class="anchor"></a>
Table 1. Supported header fields

 Field | Type    | Mandatory /Optional | Description
------ | ------- | ------------------- | ---------------------------------------------------------
 pdu   | PduType | O                   | Additional information for Personal data usage monitoring

## Annex A XML Schema for Personal Data Usage

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
    elementFormDefault="qualified"
    targetNamespace="http://x-road.eu/xsd/pdu.xsd"
    xmlns="http://x-road.eu/xsd/pdu.xsd">
    <xs:element name="reason" type="xs:string">
        <xs:annotation>
            <xs:documentation>Reason for request.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="system" type="xs:string">
        <xs:annotation>
            <xs:documentation>System that made the
                request.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="hidden" type="xs:boolean">
        <xs:annotation>
            <xs:documentation>Request should be hidden from personal data
                monitoring.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:complexType name="PduType">
        <xs:sequence>
            <xs:element minOccurs="1" ref="reason"/>
            <xs:element minOccurs="0" ref="system"/>
            <xs:element minOccurs="0" ref="hidden"/>
        </xs:sequence>
    </xs:complexType>
    <xs:element name="pdu" type="PduType">
        <xs:annotation>
            <xs:documentation>Data required by personal data usage
                monitoring.</xs:documentation>
        </xs:annotation>
    </xs:element>
</xs:schema>
```

## Annex B Example WSDL

```xml
<?xml version="1.0" encoding="UTF-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
    xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
    xmlns:tns="http://v6Example.x-road.eu/producer"
    xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:pdu="http://x-road.eu/xsd/pdu.xsd"
    targetNamespace="http://v6Example.x-road.eu/producer">
    <wsdl:types>
        <schema xmlns="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://v6Example.x-road.eu/producer"
            elementFormDefault="qualified">
            <import namespace="http://x-road.eu/xsd/xroad.xsd"
                schemaLocation="http://x-road.eu/xsd/xroad.xsd"/>
            <import namespace="http://x-road.eu/xsd/pdu.xsd"
                schemaLocation="http://x-road.eu/xsd/pdu.xsd"/>

            <element name="getOperations">
                <complexType>
                    <sequence>
                        <element name="pcode" type="xsd:string">
                            <annotation>
                                <appinfo>
                                    <xrd:title xml:lang="en">Personal
                                        code</xrd:title>
                                </appinfo>
                            </annotation>
                        </element>
                    </sequence>
                </complexType>
            </element>
            <element name="getOperationsResponse">
                <complexType>
                    <sequence>
                        <element name="amount" type="xsd:string">
                            <annotation>
                                <appinfo>
                                    <xrd:title xml:lang="en">Amount of money
                                        received or spent</xrd:title>
                                </appinfo>
                            </annotation>
                        </element>
                    </sequence>
                </complexType>
            </element>
        </schema>
    </wsdl:types>

    <wsdl:message name="requestHeader">
        <wsdl:part name="client" element="xrd:client"/>
        <wsdl:part name="service" element="xrd:service"/>
        <wsdl:part name="pdu" element="pdu:pdu"/>
        <wsdl:part name="id" element="xrd:id"/>
        <wsdl:part name="userId" element="xrd:userId"/>
        <wsdl:part name="requestHash" element="xrd:requestHash"/>
        <wsdl:part name="issue" element="xrd:issue"/>
        <wsdl:part name="protocolVersion" element="xrd:protocolVersion"/>
    </wsdl:message>

    <wsdl:message name="getOperations">
        <wsdl:part name="body" element="tns:getOperations"/>
    </wsdl:message>
    <wsdl:message name="getOperationsResponse">
        <wsdl:part name="body" element="tns:getOperationsResponse"/>
    </wsdl:message>

    <wsdl:portType name="v6ExamplePortType">
        <wsdl:operation name="getOperations">
            <wsdl:documentation>
                <xrd:title>Personal balance operations</xrd:title>
                <xrd:notes>List of all transactions related to specified
                    person.</xrd:notes>
            </wsdl:documentation>
            <wsdl:input message="tns:getOperations"/>
            <wsdl:output message="tns:getOperationsResponse"/>
        </wsdl:operation>
    </wsdl:portType>

    <wsdl:binding name="v6ExampleBinding" type="tns:v6ExamplePortType">
        <soap:binding style="document"
            transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="getOperations">
            <soap:operation soapAction="" style="document"/>
            <xrd:version>v1</xrd:version>
            <wsdl:input>
                <soap:body use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="client" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="service" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="pdu" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="id" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="userId" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="issue" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="protocolVersion" use="literal"/>
            </wsdl:input>
            <wsdl:output>
                <soap:body use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="client" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="service" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="pdu" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="id" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="userId" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="requestHash" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="issue" use="literal"/>
                <soap:header message="tns:requestHeader"
                    part="protocolVersion" use="literal"/>
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>

    <wsdl:service name="v6ExampleService">
        <wsdl:port binding="tns:v6ExampleBinding"
            name="v6ExampleServicePort">
            <soap:address location="http://INSERT_CORRECT_SERVICE_URL"/>
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>
```

## Annex C Example Messages

### C.1 Example Request

```xml
<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope 
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
        xmlns:id="http://x-road.eu/xsd/identifiers"
        xmlns:pdu="http://x-road.eu/xsd/du.xsd">
    <SOAP-ENV:Header>
        <xrd:client id:objectType="SUBSYSTEM">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>MEMBER1</id:memberCode>
            <id:subsystemCode>SUBSYSTEM1</id:subsystemCode>
        </xrd:client>
        <xrd:service id:objectType="SERVICE">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>MEMBER2</id:memberCode>
            <id:subsystemCode>SUBSYSTEM2</id:subsystemCode>
            <id:serviceCode>getRandom</id:serviceCode>
            <id:serviceVersion>v1</id:serviceVersion>
        </xrd:service>
        <pdu:pdu>
            <pdu:reason>Fetching data for tax calculation</pdu:reason>
            <pdu:system>TaxSystem</pdu:system>
            <pdu:hidden>true</pdu:hidden>
        </pdu:pdu>
        <xrd:userId>EE1234567890</xrd:userId>
        <xrd:id>4894e35d-bf0f-44a6-867a-8e51f1daa7e0</xrd:id>
        <xrd:protocolVersion>4.0</xrd:protocolVersion>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
        <ns1:getOperations xmlns:ns1="http://v6Example.x-road.eu/producer">
            <pcode>12345678901</pcode>
        </ns1:getOperations>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### C.1 Example Response

```xml
<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope 
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
        xmlns:id="http://x-road.eu/xsd/identifiers"
        xmlns:pdu="http://x-road.eu/xsd/du.xsd">
    <SOAP-ENV:Header>
        <xrd:client id:objectType="SUBSYSTEM">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>MEMBER1</id:memberCode>
            <id:subsystemCode>SUBSYSTEM1</id:subsystemCode>
        </xrd:client>
        <xrd:service id:objectType="SERVICE">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>MEMBER2</id:memberCode>
            <id:subsystemCode>SUBSYSTEM2</id:subsystemCode>
            <id:serviceCode>getRandom</id:serviceCode>
            <id:serviceVersion>v1</id:serviceVersion>
        </xrd:service>
        <pdu:pdu>
            <pdu:reason>Fetching data for tax calculation</pdu:reason>
            <pdu:system>TaxSystem</pdu:system>
            <pdu:hidden>true</pdu:hidden>
        </pdu:pdu>
        <xrd:userId>EE1234567890</xrd:userId>
        <xrd:id>4894e35d-bf0f-44a6-867a-8e51f1daa7e0</xrd:id>
        <xrd:protocolVersion>4.0</xrd:protocolVersion>
        <xrd:requestHash
            algorithmId="http://www.w3.org/2001/04/xmlenc#sha512">WJGPAG
                v7AebB+yhYgYjkqzsSOjCMf+kvDmMVvq0RiLOyjm8IVxxI1aB31OJG+S
                oYyvAngBYqP34Pt1CjJ4nTJQ==</xrd:requestHash>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
        <ns1:getOperationsResponse
            xmlns:ns1="http://v6Example.x-road.eu/producer">
            <sum>1000 EUR</sum>
            <sum>-100 EUR</sum>
        </ns1:getOperationsResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
