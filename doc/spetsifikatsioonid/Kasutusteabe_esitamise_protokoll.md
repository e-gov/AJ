# Andmejälgija kasutusteabe esitamise protokoll

X-tee andmejälgija analüüs ja disain

Versioon 1.3, 03.07.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ ja FocusIT OÜ

![EL Regionaalarengu Fond](../img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## 1. Dokumendi ajalugu

| Versioon | Kuupäev | Autor | Märkused |
| --- | --- | --- | --- |
| 1.0 | 20.12.2015 | Ivo Mehide | Esimene versioon |
| 1.1 | 13.06.2016 | Tanel Tammet | Uuendused ja täpsustused peale süsteemi valmimist 
| 1.2 | 03.07.2016 | Ivo Mehide | WSDL viidud vastavusse lahenduses kasutatavaga 

## 2. Sisukord

  * [1\. Dokumendi ajalugu](#1-dokumendi-ajalugu)
  * [2\. Sisukord](#2-sisukord)
  * [3\. Ülevaade](#3-%C3%9Clevaade)
  * [4\. Seotud dokumendid](#4-seotud-dokumendid)
  * [5\. Mõisted](#5-m%C3%B5isted)
  * [6\. Üldistatud kirjeldus](#6-%C3%9Cldistatud-kirjeldus)
  * [7\. Arhitektuuriline ülevaade](#7-arhitektuuriline-%C3%BClevaade)
  * [8\. Päringute kirjeldused](#8-p%C3%A4ringute-kirjeldused)
      * [8\.1\. Kasutusteabe küsimine](#81-kasutusteabe-k%C3%BCsimine)
        * [8\.1\.1\. Päringu nimi](#811-p%C3%A4ringu-nimi)
        * [8\.1\.2\. Päringu töö kirjeldus](#812-p%C3%A4ringu-t%C3%B6%C3%B6-kirjeldus)
      * [8\.1\.3\. Päringu sisend](#813-p%C3%A4ringu-sisend)
      * [8\.1\.4\. Päringu väljund](#814-p%C3%A4ringu-v%C3%A4ljund)
      * [8\.1\.5\. Veasituatsioonid](#815-veasituatsioonid)
  * [9\. Disaini konstrueerimise kaalutlused](#9-disaini-konstrueerimise-kaalutlused)
  * [10\. Vastavusklausel](#10-vastavusklausel)
  * [11\. Vastavusmudel](#11-vastavusmudel)
    * [11\.1\. Kasutusteabe lugemise vastavus](#111-kasutusteabe-lugemise-vastavus)
  * [12\. LISA: tracker\.xsd](#12-lisa-trackerxsd)
  * [13\. LISA: tracker\.wsdl](#13-lisa-trackerwsdl)

## 3. Ülevaade

Käesolev dokument esitab tarkvaralise lahenduse "Andmejälgija" kasutusteabe esitamise protokolli, mida kasutatakse eesti.ee-st X-tee päringute tegemisel andmesalvestajale.

Andmesalvestaja REST-liidest infosüsteemist andmesalvestajasse info salvestamiseks ja sisemiste päringute tegemiseks käesolev dokument ei käsitle.

Kasututeabe esitamise protokoll on spetsifikatsioon, mis määratleb, kuidas toimub Andmesalvestajasse isikuandmete kasutusteabe edastamine ning isikuandmete kasutusteabe küsimine.

Dokument on suunatud arendajatele, kellel on tarvis realiseerida Andmesalvestajaga suhtlemine kas sinna isikuandmete kasutusteabe edastamiseks või siis selle info küsimiseks.

## 4. Seotud dokumendid

Käesoleva kasutusteabe esitamise protokolli lahutamatuteks osadeks on:

- Kasutusteabe esitamise protokolli WSDL-kirjeldus "dumonitor.wsdl" (esitatud lõpus lisana)

Kasutusteabe esitamise protokolli normatiivsed viited:

- X-tee v6 sõnumiprotokoll (https://www.ria.ee/x-tee-juhendid/#v6-regulatsioon)

## 5. Mõisted

- Isikuandmete kasutusteave - faktiline info andmekogus teostatud isikuandmete töötlemise kohta.
- Andmesalvestaja – tarkvaraline lahendus, mille eesmärgiks on isikuandmete kasutusteabe haldamine.
- Andmekogu infosüsteem – infosüsteem, mille koosseisus on andmesalvestaja ja milles hoitakse selle infosüsteemi poolt hallatava andmekoguga seotud isikuandmete kasutusteavet.
- Päringu algataja – tarkvaraline lahendus, mis soovib andmesalvestajast kasutusteavet küsida: konkreetselt mõeldakse siin eesti.ee infosüsteemi, mis kasutab küsimiseks SOAP protokolli üle X-tee.
- Päringu algataja infosüsteem – päringu algataja tarkvaralist lahendust sisaldav infosüsteem.
- Turvaserver – X-tee turvaserver, mille ülesandeks on vahendada päringuid X-tee võrgu ja asutuse siseste X-teed kasutavate lahenduste või teenuste vahel.

## 6. Üldistatud kirjeldus

Kasutusteabe esitamise protokolli kasutatakse esmajoones eesti.ee keskkonna poolt, võimaldamaks kodanikel esitada päringuid temaga seotud isikuandmete kasutusteabe kohta. Kuid lisaks sellele on võimalik seda protokolli kasutada ükskõik millisel osapoolel, kellele peetakse vajalikuks tagada juurdepääs kasutusteabe infole. Protokolli abil realiseeritakse andmesalvestaja kasutatavast andmekogust kasutusteabe pärimine ning selle tagastamine. Protokoll on universaalne meetod kõigi andmekogude poolt ühesugustel alustel kasutusteabe info küsimiseks ning tagastamiseks.

Protokoll toimib üle X-tee versioon 6 sõnumiprotokolli.

## 7. Arhitektuuriline ülevaade

Protokollis on kaks interaktsiooni osapoolt:

1. Päringu algataja - kasutusteavet küsiv osapool, asub päringu algataja infosüsteemis
2. Andmesalvestaja – kasutusteavet tagastav osapool, asub andmekogu infosüsteemis

Protokolli üldine tööpõhimõte on päring-vastus loogikaga: päringu algataja poolt esitatakse päring, mis sisaldab täpsemat infot soovitava kasutusteabe või tegevuse kohta, ning andmesalvestaja tagastab päringu vastuses päringule vastava tulemuse.

Protokolli päringud baseeruvad SOAP-protokollil ning vastavad X-tee versioon 6 põhimõtetele.

Päringu algataja ja andmesalvestaja vaheline suhtlus toimub vastavalt X-tee põhimõtetele mõlema osapoole turvaserverite vahendusel. Protokoll toimib olekuvabalt – igas päringus leidub ammendav info päringu vastuse koostamiseks ning puuduvad erinevate päringute omavahelised sõltuvused.

Suhtlus algab alati päringu algataja poolt – päringu algataja esitab päringu ning andmesalvestaja vastab.

![Kasutusteabe esitamise protkolli diagramm](../img/kasutusteabe_esitamise_protokolli_diagramm.png)

## 8. Päringute kirjeldused

Päringute täpsed spetsifikatsioonid on toodud WSDL-kirjelduse failis "dumonitor.wsdl". Allpool on toodud seal esitatud päringute lahtiseletused.

#### 8.1. Kasutusteabe küsimine

##### 8.1.1. Päringu nimi

findUsage

##### 8.1.2. Päringu töö kirjeldus

Päringuga otsitakse andmesalvestaja andmebaasist kasutusteabe kirjeid, mis vastavad sisendis antud piirangutele. Päringu väljundis tagastatakse kõik leitud kirjed.

#### 8.1.3. Päringu sisend

Päringu sisendis on järgmised elemendid:

| Element | Andmetüüp | Kohustuslik | Kirjeldus |
| --- | --- | --- | --- |
| offset | int | ei | Tagastada kirjed, mille järjekorranumber on alates näidatud täisarvust (esimese kirje järjekorranumber on 1). |
| limit | int | ei | Tagastata maksimaalselt näidatud arv kirjeid (vaikimisi tagastatakse maksimaalselt 100 kirjet). |

#### 8.1.4. Päringu väljund

Päringu väljundis tagastatakse nimekiri elementidest "usage", kus iga element vastab ühele isikuandmete kasutuse kirjele ning mille struktuur on järgnev:

| Element | Andmetüüp | Kirjeldus |
| --- | --- | --- |
| logtime | dateTime | Kirje ajamoment. |
| action | string | Menetluse/tegevuse inimmõistetav nimi. |
| receiver | string | Asutuse kood, kellele isikuandmeid edastati. |

#### 8.1.5. Veasituatsioonid

Päringu sisu nõuetele mittevastavuse korral tekitatakse SOAP Fault, millel on Fault Code väärtuseks "Sender".

Päringu täitmisel juhtunud vea korral tekitatakse SOAP Fault, millel on Fault Code väärtuseks "Receiver".

## 9. Disaini konstrueerimise kaalutlused

Andmesalvestajalt kasutusteabe küsimine tuleb realiseerida lehekülje kaupa. Punktis "Kasutusteabe küsimine" kirjeldatud päringu kasutamisel tuleb vajadusel näidata parameetri "recordCount" abil soovitavate kirjete maksimumarv (juhul, kui soovitakse vaikimisi kasutatavast maksimumarvust erinevat kirjete arvu). Kui päringu vastuses tagastatakse maksimumarv kirjeid, siis järelikult leiti kirjeid rohkem. Ülejäänud kirjete saamiseks tuleb käivitada sama päring samade otsitingimustega uuesti, näidates parameetri "recordFrom" väärtuseks eelmise päringu poolt tagastatud kirjete arv + 1. Päringut korratakse analoogse loogikaga (suurendades iga kord "recordFrom" parameetri väärtust maksimumkirjete arvu võrra) niikaua, kuni päringu vastuses tagastatud kirjete arv on väiksem maksimumarvust.

## 10. Vastavusklausel

Lahendus on vastavuses kasutusteabe esitamise protokolliga juhul, kui selles on realiseeritud punktis "Päringute kirjeldused" kirjeldatud teenused vastavalt neis sätestatud nõuetele ning lisaks on arvestatud punktis "Disaini konstrueerimise kaalutlused" toodud nõudeid.

## 11. Vastavusmudel

Kasutusteabe esitamise protokollil on üks vastavusprofiil:

### 11.1. Kasutusteabe lugemise vastavus

- Lahendus küsib andmesalvestajalt isikuandmete kasutusteavet punktis "Kasutusteabe küsimine" kirjeldatud päringu abil ning järgib täiendavalt punktis "Disaini konstrueerimise kaalutlused" toodud nõudeid.

## 12. LISA: tracker.wsdl

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<wsdl:definitions name="dumonitor"
	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
	xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xrd="http://x-road.eu/xsd/xroad.xsd"
	targetNamespace="http://dumonitor.x-road.eu/producer" xmlns:tns="http://dumonitor.x-road.eu/producer">
	<wsdl:types>
		<xsd:schema targetNamespace="http://dumonitor.x-road.eu/producer">
			<xsd:import namespace="http://x-road.eu/xsd/xroad.xsd"
				schemaLocation="http://x-road.eu/xsd/xroad.xsd" />
			<xsd:complexType name="ResponseFields">
				<xsd:sequence>
					<xsd:element type="xsd:dateTime" name="logtime"
						minOccurs="0" maxOccurs="1">
						<xsd:annotation>
							<xsd:appinfo>
								<xrd:title>Isikuandmete töötlemise aeg</xrd:title>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:element>
					<xsd:element type="xsd:string" name="action" minOccurs="0"
						maxOccurs="1">
						<xsd:annotation>
							<xsd:appinfo>
								<xrd:title>Tegevus</xrd:title>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:element>
					<xsd:element type="xsd:string" name="receiver"
						minOccurs="0" maxOccurs="1">
						<xsd:annotation>
							<xsd:appinfo>
								<xrd:title>Isikuandmeid vastu võtnud osapool</xrd:title>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:element>
				</xsd:sequence>
			</xsd:complexType>
			<xsd:complexType name="QueryFields">
				<xsd:sequence>
					<xsd:element type="xsd:integer" name="offset"
						minOccurs="0" maxOccurs="1">
						<xsd:annotation>
							<xsd:appinfo>
								<xrd:title>Esimese tagastatava kirje jrknr</xrd:title>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:element>
					<xsd:element type="xsd:integer" name="limit" minOccurs="0"
						maxOccurs="1">
						<xsd:annotation>
							<xsd:appinfo>
								<xrd:title>Tagastatavate kirjete arv</xrd:title>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:element>
				</xsd:sequence>
			</xsd:complexType>
			<xsd:element name="findUsage" type="tns:QueryFields" />
			<xsd:element name="findUsageResponse">
				<xsd:complexType>
					<xsd:sequence>
						<xsd:element name="usage" type="tns:ResponseFields"
							maxOccurs="unbounded" minOccurs="0" />
					</xsd:sequence>
				</xsd:complexType>
			</xsd:element>
		</xsd:schema>
	</wsdl:types>
	<wsdl:message name="requestHeader">
		<wsdl:part name="client" element="xrd:client" />
		<wsdl:part name="service" element="xrd:service" />
		<wsdl:part name="id" element="xrd:id" />
		<wsdl:part name="userId" element="xrd:userId" />
		<wsdl:part name="requestHash" element="xrd:requestHash" />
		<wsdl:part name="issue" element="xrd:issue" />
		<wsdl:part name="protocolVersion" element="xrd:protocolVersion" />
	</wsdl:message>
	<wsdl:message name="findUsage">
		<wsdl:part name="body" element="tns:findUsage" />
	</wsdl:message>
	<wsdl:message name="findUsageResponse">
		<wsdl:part name="body" element="tns:findUsageResponse" />
	</wsdl:message>
	<wsdl:portType name="dumonitorPortType">
		<wsdl:operation name="findUsage">
			<wsdl:documentation>
				<xrd:title>Päring andmekogust isikuandmete töötlemise kohta
				</xrd:title>
			</wsdl:documentation>
			<wsdl:input message="tns:findUsage" />
			<wsdl:output message="tns:findUsageResponse" />
		</wsdl:operation>
	</wsdl:portType>
	<wsdl:binding name="dumonitorBinding" type="tns:dumonitorPortType">
		<soap:binding style="document"
			transport="http://schemas.xmlsoap.org/soap/http" />
		<wsdl:operation name="findUsage">
			<soap:operation soapAction="" style="document" />
			<xrd:version>v1</xrd:version>
			<wsdl:input>
				<soap:body use="literal" />
				<soap:header message="tns:requestHeader" part="client"
					use="literal" />
				<soap:header message="tns:requestHeader" part="service"
					use="literal" />
				<soap:header message="tns:requestHeader" part="id" use="literal" />
				<soap:header message="tns:requestHeader" part="userId"
					use="literal" />
				<soap:header message="tns:requestHeader" part="issue"
					use="literal" />
				<soap:header message="tns:requestHeader" part="protocolVersion"
					use="literal" />
			</wsdl:input>
			<wsdl:output>
				<soap:body use="literal" />
				<soap:header message="tns:requestHeader" part="client"
					use="literal" />
				<soap:header message="tns:requestHeader" part="service"
					use="literal" />
				<soap:header message="tns:requestHeader" part="id" use="literal" />
				<soap:header message="tns:requestHeader" part="userId"
					use="literal" />
				<soap:header message="tns:requestHeader" part="requestHash"
					use="literal" />
				<soap:header message="tns:requestHeader" part="issue"
					use="literal" />
				<soap:header message="tns:requestHeader" part="protocolVersion"
					use="literal" />
			</wsdl:output>
		</wsdl:operation>
	</wsdl:binding>
	<wsdl:service name="dumonitorService">
		<wsdl:port binding="tns:dumonitorBinding" name="dumonitorServicePort">
			<soap:address location="http://INSERT_CORRECT_SERVICE_URL" />
		</wsdl:port>
	</wsdl:service>
</wsdl:definitions>
```
