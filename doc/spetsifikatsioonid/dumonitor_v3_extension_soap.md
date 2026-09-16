# X-tee: Andmejälgija laiendus SOAP-protokollile

Tehniline spetsifikatsioon

Versioon: 0.1.0  
Dok. ID: PR-PDU-SOAP

---

## Versiooniajalugu

 Kuupäev    | Versioon | Kirjeldus         | Autor
 ---------- | -------- | ----------------- | -------------
 2026-08-13 | 0.1.0    | Esialgne versioon | Vitali Stupin

## Sisukord

<!-- toc -->
- [X-tee: Andmejälgija laiendus SOAP-protokollile](#x-tee-andmejälgija-laiendus-soap-protokollile)
  - [Versiooniajalugu](#versiooniajalugu)
  - [Sisukord](#sisukord)
  - [Litsents](#litsents)
  - [1 Sissejuhatus](#1-sissejuhatus)
  - [2 Sõnumite formaat](#2-sõnumite-formaat)
    - [2.1 Isikuandmete kasutuse teave](#21-isikuandmete-kasutuse-teave)
    - [2.2 Sõnumi päised](#22-sõnumi-päised)
  - [3 Piirangud](#3-piirangud)
  - [Lisa A XML-skeem](#lisa-a-xml-skeem)
  - [Lisa B Näidis-WSDL](#lisa-b-näidis-wsdl)
  - [Lisa C Näidissõnumid](#lisa-c-näidissõnumid)
    - [C.1 Näidispäring päringu algatanud asutusega](#c1-näidispäring-päringu-algatanud-asutusega)
    - [C.2 Näidispäring päringu algatanud isikuga](#c2-näidispäring-päringu-algatanud-isikuga)
<!-- tocstop -->

## Litsents

See dokument on litsentseeritud Creative Commons Attribution-ShareAlike 3.0 Unported litsentsi alusel. Litsentsi vaatamiseks külastage <http://creativecommons.org/licenses/by-sa/3.0/>.

## 1 Sissejuhatus

See spetsifikatsioon kirjeldab X-tee SOAP-sõnumiprotokolli v4.0 laiendust <https://github.com/nordic-institute/X-Road/blob/develop/doc/Protocols/pr-mess_x-road_message_protocol.md>.

Laienduse eesmärk on võimaldada teenuse tarbijatel saata X-tee SOAP-päringutega kaasa täiendavat teavet isikuandmete kasutuse kohta (PDU - *Personal Data Usage*, isikuandmete kasutus), et teenuse osutajad saaksid seda teavet Andmejälgijas <https://github.com/e-gov/AJ> logida.

Kodanikule kuvatav teave Andmejälgijas sõltub suuresti teenuse osutajast. Samas on kõige väärtuslikum kontekst - miks päring tehti ja milline süsteem või isik selle algatas - teada üldjuhul ainult teenuse tarbijale. See laiendus võimaldab tarbijal edastada selle konteksti iga päringuga.

Täiendavad andmed edastatakse XML-elemendina SOAP-sõnumi `Header` plokis. Laiendus kasutab nimeruumi `http://x-road.eu/xsd/pdu-v3.xsd`.

Laienduse rakendamine on valikuline — teenuse tarbija ja teenuse osutaja peavad selle kasutuses eraldi kokku leppima.

Väljad vastavad Andmejälgija v3 OpenAPI spetsifikatsiooni `UsageCommon` skeemi väljadele.

> **Märkus logimisaja kohta:** `UsageCommon` skeemi välja `logTime` jaoks eraldi elementi ei ole määratletud. Teenuse tarbija ja teenuse osutaja logivad andmetöötluse aja iseseisvalt oma süsteemis ning väikesed erinevused kellaaegades on aktsepteeritavad.
>
> **Märkus päringu ID kohta:** `UsageCommon` skeemi välja `queryId` jaoks eraldi elementi ei ole määratletud. Teenuse osutaja PEAKS tuletama selle väärtuse X-tee `xrd:id` päiseelemendist.

## 2 Sõnumite formaat

### 2.1 Isikuandmete kasutuse teave

See jaotis kirjeldab XML-põhist andmevormingut isikuandmete kasutuse edastamiseks. Andmestruktuurid ja elemendid asuvad nimeruumis `http://x-road.eu/xsd/pdu-v3.xsd`. Täielik XML-skeem on toodud [Lisas A](#lisa-a-xml-skeem).

Järgmine loetelu näitab skeemi päist.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
    elementFormDefault="qualified"
    targetNamespace="http://x-road.eu/xsd/pdu-v3.xsd"
    xmlns="http://x-road.eu/xsd/pdu-v3.xsd">
```

Kompleksne tüüp `PduV3Type` kirjeldab isikuandmete kasutuse teavet. See koosneb ühest kohustuslikust elemendist - `purpose` - ning mitmest valikulisest elemendist.

```xml
<xs:complexType name="PduV3Type">
    <xs:sequence>
        <xs:element minOccurs="1" ref="purpose"/>
        <xs:element minOccurs="0" ref="bulkProcessing"/>
        <xs:element minOccurs="0" ref="legalBasis"/>
        <xs:element minOccurs="0" ref="consentReference"/>
        <xs:element minOccurs="0" ref="hidden"/>
        <xs:element minOccurs="0" ref="hiddenReason"/>
        <xs:element minOccurs="0" ref="hiddenUntil"/>
        <xs:element minOccurs="0" ref="initiatorOrg"/>
        <xs:element minOccurs="0" ref="initiatorPerson"/>
    </xs:sequence>
</xs:complexType>
```

Elementide kirjeldused:

Element `purpose` on inimmõistetav tegevuse või sündmuse nimetus, mis selgitab andmetöötluse põhjust.

Element `bulkProcessing` on boolean-tüüpi tunnus, mis näitab, kas päring on osa massandmetöötlusest. Puudumine tähendab `false`.

Element `legalBasis` on andmetöötluse õiguslik alus.

Element `consentReference` on viide andmesubjekti nõusolekule.

Element `hidden` on boolean-tüüpi tunnus. Kui väärtus on `true`, näitab see, et see päring PEAB olema peidetud isiku eest, kelle andmeid päringus töödeldi. Seda võib kasutada ainult juhul, kui teenuse tarbijal on seaduse alusel õigus teha päringuid, mida Andmejälgija ei tohi jälgida. Andmejälgija VÕIB rakendada nimekirja süsteemidest, millel on lubatud päringuid peita, või muid meetmeid peitmisvõimaluse kuritarvitamise vältimiseks. Puudumine tähendab `false`.

Element `hiddenReason` on inimmõistetav põhjendus, miks kirje on isiku eest peidetud. Isikule kuvatakse alles pärast avalikustamist. Kasutatakse ainult koos `hidden` elemendiga väärtusega `true`.

Element `hiddenUntil` on ajamoment, milleni kirje on isiku eest peidetud. Pärast seda ajahetke on kirje isikule nähtav. ISO 8601 formaat koos ajavööndiga. Kui element puudub, avalikustatakse kirje hiljem "disclose" teenuse kaudu. Kasutatakse ainult koos `hidden` elemendiga väärtusega `true`.

Element `initiatorOrg` tuvastab päringu algatanud asutuse. Sisaldab alamelemente `type` (kohustuslik), `code` (valikuline), `name` (valikuline) ja `systemName` (valikuline). Asutuse tuvastamiseks PEAB olema täidetud vähemalt üks `code` või `name`.

Element `initiatorPerson` tuvastab päringu algatanud isiku. Sisaldab alamelemente `code` (valikuline), `alternativeCode` (valikuline) ja `name` (valikuline). Isiku tuvastamiseks PEAB olema täidetud vähemalt üks neist kolmest.

### 2.2 Sõnumi päised

See jaotis kirjeldab täiendavat SOAP-päist, mida kasutatakse isikuandmete kasutuse teabe edastamiseks.

Tabel 1. Toetatud päiseväljad

 Väli  | Tüüp        | Kohustuslik / Valikuline | Kirjeldus
 ----- | ----------- | ------------------------ | ---------
 `pdu` | `PduV3Type` | V                        | Täiendav teave isikuandmete kasutuse logimiseks Andmejälgijas

## 3 Piirangud

Järgmised piirangud kehtivad siis, kui päringus saadetakse `pdu` element.

1. `purpose` element PEAB olema lisatud.

2. Vähemalt üks algataja PEAB olema tuvastatud - kas päringu algatanud asutus (`initiatorOrg`), päringu algatanud isik (`initiatorPerson`) või mõlemad.

3. Asutuse tuvastamisel:
   - `initiatorOrg/type` PEAB olema lisatud.
   - Vähemalt üks `initiatorOrg/code` või `initiatorOrg/name` PEAB olema lisatud.

4. Isiku tuvastamisel PEAB olema lisatud vähemalt üks järgnevatest:
   - `initiatorPerson/code`
   - `initiatorPerson/alternativeCode`
   - `initiatorPerson/name`

5. Kui `hiddenUntil` on lisatud, PEAB olema lisatud ka `hiddenReason`.

6. Laienduse `pdu` element on sõltumatu X-tee `xrd:userId` päiseelemendist. `xrd:userId` tuvastab X-tee päringu esitaja; `initiatorPerson` tuvastab ärilise tegevuse algataja (kes võib olla erinev isik, nt esindusõiguse alusel tegutsemisel).

7. Kui teenuse tarbija ei saada `pdu` elementi, PEAB teenuse osutaja päringu ikkagi tavapäraselt täitma. Laiendus on baasprotokollide suhtes valikuline.

## Lisa A XML-skeem

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
    elementFormDefault="qualified"
    targetNamespace="http://x-road.eu/xsd/pdu-v3.xsd"
    xmlns="http://x-road.eu/xsd/pdu-v3.xsd">

    <xs:element name="purpose" type="xs:string">
        <xs:annotation>
            <xs:documentation>Inimmõistetav tegevuse nimetus, mis selgitab andmetöötluse põhjust.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="bulkProcessing" type="xs:boolean">
        <xs:annotation>
            <xs:documentation>Tunnus massandmetöötluse kohta. Puudumine tähendab false.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="legalBasis" type="xs:string">
        <xs:annotation>
            <xs:documentation>Andmetöötluse õiguslik alus.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="consentReference" type="xs:string">
        <xs:annotation>
            <xs:documentation>Viide andmesubjekti nõusolekule.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="hidden" type="xs:boolean">
        <xs:annotation>
            <xs:documentation>Päring tuleb peita andmesubjekti eest. Puudumine tähendab false.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="hiddenReason" type="xs:string">
        <xs:annotation>
            <xs:documentation>Põhjendus peitamise kohta. Kuvatakse andmesubjektile pärast avalikustamist.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="hiddenUntil" type="xs:dateTime">
        <xs:annotation>
            <xs:documentation>Ajamoment, milleni kirje on peidetud. Kui puudub, avalikustatakse "disclose" teenuse kaudu.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="initiatorOrg" type="OrganizationType">
        <xs:annotation>
            <xs:documentation>Päringu algatanud asutus.</xs:documentation>
        </xs:annotation>
    </xs:element>
    <xs:element name="initiatorPerson" type="PersonType">
        <xs:annotation>
            <xs:documentation>Päringu algatanud isik.</xs:documentation>
        </xs:annotation>
    </xs:element>

    <xs:simpleType name="OrgTypeType">
        <xs:restriction base="xs:string">
            <xs:enumeration value="GOV"/>
            <xs:enumeration value="COM"/>
            <xs:enumeration value="NGO"/>
            <xs:enumeration value="NEE"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:complexType name="OrganizationType">
        <xs:sequence>
            <xs:element name="type" type="OrgTypeType"/>
            <xs:element name="code" type="xs:string" minOccurs="0"/>
            <xs:element name="name" type="xs:string" minOccurs="0"/>
            <xs:element name="systemName" type="xs:string" minOccurs="0"/>
        </xs:sequence>
    </xs:complexType>

    <xs:complexType name="PersonType">
        <xs:sequence>
            <xs:element name="code" type="xs:string" minOccurs="0"/>
            <xs:element name="alternativeCode" type="xs:string" minOccurs="0"/>
            <xs:element name="name" type="xs:string" minOccurs="0"/>
        </xs:sequence>
    </xs:complexType>

    <xs:complexType name="PduV3Type">
        <xs:sequence>
            <xs:element minOccurs="1" ref="purpose"/>
            <xs:element minOccurs="0" ref="bulkProcessing"/>
            <xs:element minOccurs="0" ref="legalBasis"/>
            <xs:element minOccurs="0" ref="consentReference"/>
            <xs:element minOccurs="0" ref="hidden"/>
            <xs:element minOccurs="0" ref="hiddenReason"/>
            <xs:element minOccurs="0" ref="hiddenUntil"/>
            <xs:element minOccurs="0" ref="initiatorOrg"/>
            <xs:element minOccurs="0" ref="initiatorPerson"/>
        </xs:sequence>
    </xs:complexType>

    <xs:element name="pdu" type="PduV3Type">
        <xs:annotation>
            <xs:documentation>Andmejälgija jaoks vajalik isikuandmete kasutuse teave.</xs:documentation>
        </xs:annotation>
    </xs:element>

</xs:schema>
```

## Lisa B Näidis-WSDL

```xml
<?xml version="1.0" encoding="UTF-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
    xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
    xmlns:tns="http://v6Example.x-road.eu/producer"
    xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:pdu="http://x-road.eu/xsd/pdu-v3.xsd"
    targetNamespace="http://v6Example.x-road.eu/producer">
    <wsdl:types>
        <schema xmlns="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://v6Example.x-road.eu/producer"
            elementFormDefault="qualified">
            <import namespace="http://x-road.eu/xsd/xroad.xsd"
                schemaLocation="http://x-road.eu/xsd/xroad.xsd"/>
            <import namespace="http://x-road.eu/xsd/pdu-v3.xsd"
                schemaLocation="http://x-road.eu/xsd/pdu-v3.xsd"/>
            <element name="getPersonData">
                <complexType>
                    <sequence>
                        <element name="personalCode" type="xsd:string"/>
                    </sequence>
                </complexType>
            </element>
            <element name="getPersonDataResponse">
                <complexType>
                    <sequence>
                        <element name="name" type="xsd:string"/>
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

    <wsdl:message name="getPersonData">
        <wsdl:part name="body" element="tns:getPersonData"/>
    </wsdl:message>
    <wsdl:message name="getPersonDataResponse">
        <wsdl:part name="body" element="tns:getPersonDataResponse"/>
    </wsdl:message>

    <wsdl:portType name="ExamplePortType">
        <wsdl:operation name="getPersonData">
            <wsdl:input message="tns:getPersonData"/>
            <wsdl:output message="tns:getPersonDataResponse"/>
        </wsdl:operation>
    </wsdl:portType>

    <wsdl:binding name="ExampleBinding" type="tns:ExamplePortType">
        <soap:binding style="document"
            transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="getPersonData">
            <soap:operation soapAction="" style="document"/>
            <xrd:version>v1</xrd:version>
            <wsdl:input>
                <soap:body use="literal"/>
                <soap:header message="tns:requestHeader" part="client" use="literal"/>
                <soap:header message="tns:requestHeader" part="service" use="literal"/>
                <soap:header message="tns:requestHeader" part="pdu" use="literal"/>
                <soap:header message="tns:requestHeader" part="id" use="literal"/>
                <soap:header message="tns:requestHeader" part="userId" use="literal"/>
                <soap:header message="tns:requestHeader" part="issue" use="literal"/>
                <soap:header message="tns:requestHeader" part="protocolVersion" use="literal"/>
            </wsdl:input>
            <wsdl:output>
                <soap:body use="literal"/>
                <soap:header message="tns:requestHeader" part="client" use="literal"/>
                <soap:header message="tns:requestHeader" part="service" use="literal"/>
                <soap:header message="tns:requestHeader" part="pdu" use="literal"/>
                <soap:header message="tns:requestHeader" part="id" use="literal"/>
                <soap:header message="tns:requestHeader" part="userId" use="literal"/>
                <soap:header message="tns:requestHeader" part="requestHash" use="literal"/>
                <soap:header message="tns:requestHeader" part="issue" use="literal"/>
                <soap:header message="tns:requestHeader" part="protocolVersion" use="literal"/>
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>

    <wsdl:service name="ExampleService">
        <wsdl:port binding="tns:ExampleBinding" name="ExampleServicePort">
            <soap:address location="http://INSERT_CORRECT_SERVICE_URL"/>
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>
```

## Lisa C Näidissõnumid

### C.1 Näidispäring päringu algatanud asutusega

```xml
<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
        xmlns:id="http://x-road.eu/xsd/identifiers"
        xmlns:pdu="http://x-road.eu/xsd/pdu-v3.xsd">
    <SOAP-ENV:Header>
        <xrd:client id:objectType="SUBSYSTEM">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>70000562</id:memberCode>
            <id:subsystemCode>DHX</id:subsystemCode>
        </xrd:client>
        <xrd:service id:objectType="SERVICE">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>70008440</id:memberCode>
            <id:subsystemCode>rr</id:subsystemCode>
            <id:serviceCode>getPersonData</id:serviceCode>
            <id:serviceVersion>v1</id:serviceVersion>
        </xrd:service>
        <pdu:pdu>
            <pdu:purpose>Isiku ees- ja perenime päring</pdu:purpose>
            <pdu:legalBasis>Tervise infosüsteemi põhimäärus (§nn)</pdu:legalBasis>
            <pdu:initiatorOrg>
                <pdu:type>GOV</pdu:type>
                <pdu:code>70000562</pdu:code>
                <pdu:name>Tervise ja Heaolu Infosüsteemide Keskus</pdu:name>
                <pdu:systemName>Terviseportaal</pdu:systemName>
            </pdu:initiatorOrg>
        </pdu:pdu>
        <xrd:userId>EE12345678901</xrd:userId>
        <xrd:id>4894e35d-bf0f-44a6-867a-8e51f1daa7e0</xrd:id>
        <xrd:protocolVersion>4.0</xrd:protocolVersion>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
        <tns:getPersonData xmlns:tns="http://v6Example.x-road.eu/producer">
            <personalCode>EE12345678901</personalCode>
        </tns:getPersonData>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### C.2 Näidispäring päringu algatanud isikuga

```xml
<?xml version="1.0" encoding="UTF-8"?>
<SOAP-ENV:Envelope
        xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
        xmlns:id="http://x-road.eu/xsd/identifiers"
        xmlns:pdu="http://x-road.eu/xsd/pdu-v3.xsd">
    <SOAP-ENV:Header>
        <xrd:client id:objectType="SUBSYSTEM">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>70000562</id:memberCode>
            <id:subsystemCode>DHX</id:subsystemCode>
        </xrd:client>
        <xrd:service id:objectType="SERVICE">
            <id:xRoadInstance>EE</id:xRoadInstance>
            <id:memberClass>GOV</id:memberClass>
            <id:memberCode>70008440</id:memberCode>
            <id:subsystemCode>rr</id:subsystemCode>
            <id:serviceCode>getPersonData</id:serviceCode>
            <id:serviceVersion>v1</id:serviceVersion>
        </xrd:service>
        <pdu:pdu>
            <pdu:purpose>Raviajaloo vaatamine</pdu:purpose>
            <pdu:initiatorPerson>
                <pdu:code>EE38001085718</pdu:code>
                <pdu:name>Mari Maasikas</pdu:name>
            </pdu:initiatorPerson>
        </pdu:pdu>
        <xrd:id>7c3f1a09-22ad-4b8e-a901-1234abcd5678</xrd:id>
        <xrd:protocolVersion>4.0</xrd:protocolVersion>
    </SOAP-ENV:Header>
    <SOAP-ENV:Body>
        <tns:getPersonData xmlns:tns="http://v6Example.x-road.eu/producer">
            <personalCode>EE12345678901</personalCode>
        </tns:getPersonData>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
