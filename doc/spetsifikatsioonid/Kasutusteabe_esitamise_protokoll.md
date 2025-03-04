# Andmejälgija kasutusteabe esitamise protokoll

X-tee andmejälgija analüüs ja disain

Versioon 1.4, 06.03.2025

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ ja FocusIT OÜ

![EL Regionaalarengu Fond](../img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## 1. Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                         | Märkused                                                                                    |
| -------- | ---------- | ----------------------------- | ------------------------------------------------------------------------------------------- |
| 1.0      | 20.12.2015 | Ivo Mehide                    | Esimene versioon                                                                            |
| 1.1      | 13.06.2016 | Tanel Tammet                  | Uuendused ja täpsustused peale süsteemi valmimist                                           |
| 1.2      | 03.07.2016 | Ivo Mehide                    | WSDL viidud vastavusse lahenduses kasutatavaga                                              |
| 1.2.1    | 29.09.2016 | Priit Parmakson               | Lisatud nõue esitada kirjed ajamomendi kahanemise järjekorras (vt jaotis 8.1.4). Vt Issue 2 |
| 1.3      | 30.03.2021 | Vitali Stupin, Sander Randorg | Lisatud OpenAPI teenuse kirjeldus                                                           |
| 1.3.1    | 30.05.2024 | Siim-Sander Virula            | Eemaldatud mitterelevantne OpenAPI kirjeldus  
| 1.4      | 06.03.2025 | Kätlin Tammoja                | Lisatud REST päringute kirjeldused                                       |

## 2. Sisukord

- [1\. Dokumendi ajalugu](#1-dokumendi-ajalugu)
- [2\. Sisukord](#2-sisukord)
- [3\. Ülevaade](#3-%C3%9Clevaade)
- [4\. Seotud dokumendid](#4-seotud-dokumendid)
- [5\. Mõisted](#5-m%C3%B5isted)
- [6\. Üldistatud kirjeldus](#6-%C3%9Cldistatud-kirjeldus)
- [7\. Arhitektuuriline ülevaade](#7-arhitektuuriline-%C3%BClevaade)
- [8\. REST päringute kirjeldused](#9-rest-p%C3%A4ringute-kirjeldused)
  - [8\.1\. Kasutusteabe küsimine](#91-kasutusteabe-k%C3%BCsimine)
    - [8\.1\.1\. Päringu nimi](#911-p%C3%A4ringu-nimi)
    - [8\.1\.2\. Päringu töö kirjeldus](#912-p%C3%A4ringu-t%C3%B6%C3%B6-kirjeldus)
  - [8\.1\.3\. Päringu sisend](#913-p%C3%A4ringu-sisend)
  - [8\.1\.4\. Päringu väljund](#914-p%C3%A4ringu-v%C3%A4ljund)
  - [8\.1\.5\. Veasituatsioonid](#915-veasituatsioonid)
  - [8\.1\.6\. Päringu näide](#916-p%C3%A4ringu-n%C3%A4ide)
- [9\. Disaini konstrueerimise kaalutlused](#10-disaini-konstrueerimise-kaalutlused)
- [10\. Vastavusklausel](#11-vastavusklausel)
- [11\. Vastavusmudel](#12-vastavusmudel)
  - [11\.1\. Kasutusteabe lugemise vastavus](#121-kasutusteabe-lugemise-vastavus)
- [12\. LISA: dumonitor-openapi\.yaml](#15-lisa-dumonitor-openapiyaml)

## 3. Ülevaade

Käesolev dokument esitab tarkvaralise lahenduse "Andmejälgija" kasutusteabe esitamise protokolli, mida kasutatakse eesti.ee-st X-tee päringute tegemisel andmesalvestajale.

Kasututeabe esitamise protokoll on spetsifikatsioon, mis määratleb, kuidas toimub Andmesalvestajasse isikuandmete kasutusteabe edastamine ning isikuandmete kasutusteabe küsimine.

Dokument on suunatud arendajatele, kellel on tarvis realiseerida Andmesalvestajaga suhtlemine kas sinna isikuandmete kasutusteabe edastamiseks või siis selle info küsimiseks.

## 4. Seotud dokumendid

Käesoleva kasutusteabe esitamise protokolli lahutamatuteks osadeks on:


- Kasutusteabe esitamise protokolli OpenAPI-kirjeldus "dumonitor-openapi.yaml" (esitatud lõpus lisana)

Kasutusteabe esitamise protokolli normatiivsed viited:
- X-tee REST protokoll (https://www.x-tee.ee/docs/live/xroad/pr-rest_x-road_message_protocol_for_rest.html)

## 5. Mõisted

- Isikuandmete kasutusteave - faktiline info andmekogus teostatud isikuandmete töötlemise kohta.
- Andmesalvestaja – tarkvaraline lahendus, mille eesmärgiks on isikuandmete kasutusteabe haldamine.
- Andmekogu infosüsteem – infosüsteem, mille koosseisus on andmesalvestaja ja milles hoitakse selle infosüsteemi poolt hallatava andmekoguga seotud isikuandmete kasutusteavet.
- Päringu algataja – tarkvaraline lahendus, mis soovib andmesalvestajast kasutusteavet küsida: konkreetselt mõeldakse siin eesti.ee infosüsteemi, mis kasutab küsimiseks REST protokolli üle X-tee.
- Päringu algataja infosüsteem – päringu algataja tarkvaralist lahendust sisaldav infosüsteem.
- Turvaserver – X-tee turvaserver, mille ülesandeks on vahendada päringuid X-tee võrgu ja asutuse siseste X-teed kasutavate lahenduste või teenuste vahel.

## 6. Üldistatud kirjeldus

Kasutusteabe esitamise protokolli kasutatakse esmajoones eesti.ee keskkonna poolt, võimaldamaks kodanikel esitada päringuid temaga seotud isikuandmete kasutusteabe kohta. Kuid lisaks sellele on võimalik seda protokolli kasutada ükskõik millisel osapoolel, kellele peetakse vajalikuks tagada juurdepääs kasutusteabe infole. Protokolli abil realiseeritakse andmesalvestaja kasutatavast andmekogust kasutusteabe pärimine ning selle tagastamine. Protokoll on universaalne meetod kõigi andmekogude poolt ühesugustel alustel kasutusteabe info küsimiseks ning tagastamiseks.

Protokoll toimib üle X-tee.

## 7. Arhitektuuriline ülevaade

Protokollis on kaks interaktsiooni osapoolt:

1. Päringu algataja - kasutusteavet küsiv osapool, asub päringu algataja infosüsteemis
2. Andmesalvestaja – kasutusteavet tagastav osapool, asub andmekogu infosüsteemis

Protokolli üldine tööpõhimõte on päring-vastus loogikaga: päringu algataja poolt esitatakse päring, mis sisaldab täpsemat infot soovitava kasutusteabe või tegevuse kohta, ning andmesalvestaja tagastab päringu vastuses päringule vastava tulemuse.

Protokolli päringud baseeruvad REST-protokollil ning vastavad X-tee versioon 6 põhimõtetele.

Päringu algataja ja andmesalvestaja vaheline suhtlus toimub vastavalt X-tee põhimõtetele mõlema osapoole turvaserverite vahendusel. Protokoll toimib olekuvabalt – igas päringus leidub ammendav info päringu vastuse koostamiseks ning puuduvad erinevate päringute omavahelised sõltuvused.

Suhtlus algab alati päringu algataja poolt – päringu algataja esitab päringu ning andmesalvestaja vastab.

![Kasutusteabe esitamise protkolli diagramm](../img/kasutusteabe_esitamise_protokolli_diagramm.png)


## 8. REST päringute kirjeldused

Päringute täpsed spetsifikatsioonid on toodud OpenAPI-kirjelduse failis "dumonitor-openapi.yaml". Allpool on toodud seal esitatud findUsage päringu lahtiseletus.

#### 8.1. Kasutusteabe küsimine

##### 9.1.1. Päringu nimi

findUsage

##### 8.1.2. Päringu töö kirjeldus

Päringuga otsitakse andmesalvestaja andmebaasist kasutusteabe kirjeid, mis vastavad sisendis antud piirangutele. Päringu väljundis tagastatakse kõik leitud kirjed.

#### 8.1.3. Päringu sisend

Päringu päises on järgmised elemendid:

| Element        | Andmetüüp | Kohustuslik | Kirjeldus                                                                                                    |
| -------------- | --------- | ----------- | ------------------------------------------------------------------------------------------------------------ |
| X-Road-User-Id | string    | jah         | Päringu algataja isikukood                                                                                   |
| X-Road-Client  | string    | jah         | Info turvaserveri keskkonna, asutuse member class, member code ja alamsüsteemi subsystem code väärtuse kohta |

Päringu sisendis on järgmised elemendid:

| Element     | Andmetüüp | Kohustuslik | Kirjeldus                                                                                                                                                      |
| ----------- | --------- | ----------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| userCode    | string    | jah         | Andmesubjekti isikukood, kelle kohta tuleks tagastada kasutusteavet. Võib erineda X-Road-UserId päise väärtusest, kui päring on käivitatud esindusõiguse abil. |
| offset      | int       | ei          | Tagastada kirjed, mille järjekorranumber on alates näidatud täisarvust (esimese kirje järjekorranumber on 0).                                                  |
| limit       | int       | ei          | Tagastata maksimaalselt näidatud arv kirjeid (vaikimisi tagastatakse maksimaalselt 100 kirjet).                                                                |
| periodStart | int       | ei          | Tagastada andmetöötluse kirjeid alates näidatud ajast                                                                                                          |
| periodEnd   | int       | ei          | Tagastada andmetöötluse kirjeid kuni näidatud ajani                                                                                                            |

#### 8.1.4. Päringu väljund

Päringu väljundis tagastatakse nimekiri elementidest "usage" ning arv "totalUsages", mis ütleb kui palju neid elemente oli. Iga element vastab ühele isikuandmete kasutuse kirjele ning selle struktuur on järgnev:

| Element        | Andmetüüp | Kirjeldus                                                                        |
| -------------- | --------- | -------------------------------------------------------------------------------- |
| logtime        | dateTime  | Kirje ajamoment.                                                                 |
| action         | string    | Menetluse/tegevuse inimmõistetav nimi.                                           |
| receiverName   | string    | Asutuse nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles         |
| receiverSystem | string    | Infosüsteemi nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles    |
| receiverCode   | string    | Asutuse registrikood, kellele isikuandmeid edastati või kes isikuandmeid töötles |

Isikuandmete kasutuse kirjed tuleb tagastada kirje ajamomendi kahanemise järjekorras (hilisem eespool).

#### 8.1.5. Veasituatsioonid

Päringu täitmisel juhtunud vea korral tagastatakse vastav HTTP staatuskood.

#### 8.1.6. Päringu näide

Näidispäring:

```
curl -H "X-Road-User-Id:EE12345678901" -H "X-Road-Client:INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM"
"https://SECURITYSERVER:443/r1/INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM/findUsage?userCode=EE12345678901&offset=0&limit=10"
```

Näidisvastus:

```
{
	"totalUsages": 1,
	"usages": [
		{
		"logtime": "2023-10-01T12:00:00Z",
		"receiverName": "TEHIK",
		"receiverCode": "12345678",
		"receiverSystem": "Terviseportaal",
		"action": "Isiku ees- ja perenime päring"
		}
  	]
}
```

## 9. Disaini konstrueerimise kaalutlused

Andmesalvestajalt kasutusteabe küsimine tuleb realiseerida lehekülje kaupa.
Punktis "8.1 Kasutusteabe küsimine" kirjeldatud päringu kasutamisel tuleb vajadusel näidata parameetri
"limit" abil soovitavate kirjete maksimumarv (juhul, kui soovitakse vaikimisi kasutatavast maksimumarvust erinevat
kirjete arvu). Kui päringu vastuses tagastatakse maksimumarv kirjeid, siis järelikult leiti kirjeid rohkem.
Ülejäänud kirjete saamiseks tuleb käivitada sama päring samade otsitingimustega uuesti,
näidates parameetri "offset" väärtuseks eelmise päringu poolt tagastatud kirjete arv + 1 (esimese kirje järjekorranumber on 0). Päringut korratakse
analoogse loogikaga (suurendades iga kord "offset" parameetri väärtust maksimumkirjete arvu võrra) niikaua, kuni
päringu vastuses tagastatud kirjete arv on väiksem maksimumarvust.

## 10. Vastavusklausel

Lahendus on vastavuses kasutusteabe esitamise protokolliga juhul, kui selles on realiseeritud punktis "8 Päringute kirjeldused" kirjeldatud teenused vastavalt neis sätestatud nõuetele ning lisaks on arvestatud punktis "9 Disaini konstrueerimise kaalutlused" toodud nõudeid.

## 11. Vastavusmudel

Kasutusteabe esitamise protokollil on üks vastavusprofiil:

### 11.1. Kasutusteabe lugemise vastavus

- Lahendus küsib andmesalvestajalt isikuandmete kasutusteavet punktis "Kasutusteabe küsimine" kirjeldatud päringu abil ning järgib täiendavalt punktis "Disaini konstrueerimise kaalutlused" toodud nõudeid.



## 12. LISA: dumonitor-openapi.yaml

```yaml
openapi: 3.0.3
info:
  title: Andmejälgija kasutusteabe teenus
  description: Andmejälgija teenus, mille käest küsib eesti.ee andmekogus toimunud isikuandmete töötluse infot ehk kasutusteavet
  contact:
    email: help@ria.ee
  license:
    name: MIT License
    url: https://opensource.org/licenses/MIT
  version: 2.0.0
tags:
  - name: usage
    description: Andmejälgija kasutusteave
paths:
  /findUsage:
    get:
      tags:
        - usage
      summary: Kasutusteabe küsimine
      description: Andmejälgija kasutusteabe küsimine andmesubjektile kuvamiseks eesti.ee-s
      operationId: findUsage
      parameters:
        - name: X-Road-UserId
          in: header
          description: Päringu algataja isikukood peab olema lisatud X-Road päise sisse
          schema:
            type: string
            example: EE12345678901
            externalDocs:
              description: Täiendav informatsioon X-Road päiste kohta
              url: https://x-tee.ee/docs/live/xroad/pr-rest_x-road_message_protocol_for_rest.html
          required: true
        - name: user_code
          in: query
          description: Andmesubjekti isikukood, kelle kohta tuleks tagastada kasutusteavet. Võib erineda X-Road-UserId päise väärtusest, kui päring on käivitatud esindusõiguse abil.
          schema:
            type: string
            example: EE12345678901
          required: true
        - name: period_start
          in: query
          description: Tagastada andmetöötluse kirjeid alates näidatud ajast
          required: false
          example: '2021-01-31T10:20:30'
          schema:
            type: string
            format: date-time
        - name: period_end
          in: query
          description: Tagastada andmetöötluse kirjeid kuni näidatud ajani
          required: false
          example: '2021-01-31T10:20:30'
          schema:
            type: string
            format: date-time
        - name: offset
          in: query
          description: Jätta vahele näidatud arvu kirjed ja tagastada järgmised (analoogne SQL offset käitumisega).
          required: false
          schema:
            type: integer
            format: int32
            default: 0
        - name: limit
          in: query
          description: Tagastada maksimaalselt näidatud arv kasutusteabe kirjeid (vaikimisi tagastatakse maksimaalselt 1000 kirjet).
          required: false
          schema:
            type: integer
            format: int32
            default: 1000
      responses:
        '200':
          description: Edukas päring
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/FindUsageResponse'
        '400':
          description: Päringu sisu ei vasta nõuetele
        '500':
          description: Päringu täitmisel tekkis viga
  /usagePeriod:
    get:
      tags:
        - usage
      summary: Kasutusteabe ajaperiood
      description: Ajaperiood, mille kohta saab pärida kasutusteavet
      operationId: usagePeriod
      responses:
        '200':
          description: Edukas päring
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UsagePeriodResponse'
        '400':
          description: Päringu sisu ei vasta nõuetele
        '500':
          description: Päringu täitmisel tekkis viga
  /heartbeat:
    get:
      tags:
        - usage
      summary: Kasutusteabe elutukse
      description: Andmejälgija kasutusteabe elutukse küsimine
      operationId: heartbeat
      responses:
        '200':
          description: Edukas päring
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/HeartbeatResponse'
        '400':
          description: Päringu sisu ei vasta nõuetele
        '500':
          description: Päringu täitmisel tekkis viga
components:
  schemas:
    FindUsageResponse:
      type: object
      properties:
        totalUsages:
          description: Kirjete koguarv (tagastatud kirjete arv saab olla kirjete koguarvust väiksem, koguarv ei ole mõjutatud limit ja offset väärtustega)
          type: integer
          format: int32
          example: 1000
        usages:
          type: array
          items:
            $ref: '#/components/schemas/Usage'
    Usage:
      type: object
      required:
        - logtime
        - action
        - receiverCode
        - receiverSystem
      properties:
        logtime:
          description: Andmetöötluse ajamoment
          type: string
          format: date-time
          example: '2021-01-31T10:20:30'
        action:
          description: Menetluse/tegevuse/sündmuse inimmõistetav nimi, mis seletab andmetöötluse põhjust
          type: string
          example: Isiku ees- ja perenime päring
        receiverCode:
          description: Asutuse registrikood, kellele isikuandmeid edastati või kes isikuandmeid töötles
          type: string
          example: '12345678'
        receiverName:
          description: Asutuse nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles
          type: string
          example: TEHIK
        receiverSystem:
          description: Infosüsteemi nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles
          type: string
          example: Terviseportaal
    UsagePeriodResponse:
      type: object
      properties:
        period_start:
          description: Süsteem hoiab andmetöötluse kirjeid alates näidatud ajast
          type: string
          format: date-time
          example: '2021-01-31T10:20:30'
        period_end:
          description: Süsteem hoiab andmetöötluse kirjeid kuni näidatud ajani
          type: string
          format: date-time
          example: '2021-01-31T10:20:30'
    HeartbeatResponse:
      type: object
      properties:
        status:
          description: Elutuukse staatus
          type: string
          example: OK
          enum:
            - OK
            - FAIL
        message:
          description: Inimloetav elutuukse staatuse kirjeldav sõnum
          type: string
          example: API is ready
```
