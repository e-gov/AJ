# Andmejälgija kasutusteabe esitamise protokoll

X-tee andmejälgija analüüs ja disain

Versioon 1.5, 16.09.2025

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
| 1.3.1    | 30.05.2024 | Siim-Sander Virula            | Eemaldatud mitterelevantne OpenAPI kirjeldus                                                |
| 1.4      | 06.03.2025 | Kätlin Tammoja                | Lisatud REST päringute kirjeldused                                                          |
| 1.4.1    | 12.03.2025 | Kätlin Tammoja, Vitali Stupin | Parandatud REST päringute kirjeldused                                                       |
| 1.5    | 16.09.2025 | Riina Soggar-Henk | Täiendatud dokument  

## 2. Sisukord

- [1\. Dokumendi ajalugu](#1-dokumendi-ajalugu)
- [2\. Sisukord](#2-sisukord)
- [3\. Ülevaade](#3-%C3%9Clevaade)
- [4\. Seotud dokumendid](#4-seotud-dokumendid)
- [5\. Mõisted](#5-m%C3%B5isted)
- [6\. Üldistatud kirjeldus](#6-%C3%9Cldistatud-kirjeldus)
- [7\. Arhitektuuriline ülevaade](#7-arhitektuuriline-%C3%BClevaade)
- [8\. REST päringute kirjeldused](#8-rest-p%C3%A4ringute-kirjeldused)
  - [8\.1\. Kasutusteabe küsimine](#81-kasutusteabe-k%C3%BCsimine)
    - [8\.1\.1\. Päringu nimi](#811-p%C3%A4ringu-nimi)
    - [8\.1\.2\. Päringu töö kirjeldus](#812-p%C3%A4ringu-t%C3%B6%C3%B6-kirjeldus)
    - [8\.1\.3\. Päringu sisend](#813-p%C3%A4ringu-sisend)
    - [8\.1\.4\. Päringu väljund](#814-p%C3%A4ringu-v%C3%A4ljund)
    - [8\.1\.5\. Veasituatsioonid](#815-veasituatsioonid)
    - [8\.1\.6\. Päringu näide](#816-p%C3%A4ringu-n%C3%A4ide)
  - [8\.2\. Kasutusteabe ajaperiood](#82-kasutusteabe-ajaperiood)
    - [8\.2\.1\. Päringu nimi](#821-p%C3%A4ringu-nimi)
    - [8\.2\.2\. Päringu töö kirjeldus](#822-p%C3%A4ringu-t%C3%B6%C3%B6-kirjeldus)
    - [8\.2\.3\. Päringu sisend](#823-p%C3%A4ringu-sisend)
    - [8\.2\.4\. Päringu väljund](#824-p%C3%A4ringu-v%C3%A4ljund)
    - [8\.2\.5\. Veasituatsioonid](#825-veasituatsioonid)
    - [8\.2\.6\. Päringu näide](#826-p%C3%A4ringu-n%C3%A4ide)
  - [8\.3\. Kasutusteabe elutukse](#83-kasutusteabe-elutukse)
    - [8\.3\.1\. Päringu nimi](#831-p%C3%A4ringu-nimi)
    - [8\.3\.2\. Päringu töö kirjeldus](#832-p%C3%A4ringu-t%C3%B6%C3%B6-kirjeldus)
    - [8\.3\.3\. Päringu sisend](#833-p%C3%A4ringu-sisend)
    - [8\.3\.4\. Päringu väljund](#834-p%C3%A4ringu-v%C3%A4ljund)
    - [8\.3\.5\. Veasituatsioonid](#835-veasituatsioonid)
    - [8\.3\.6\. Päringu näide](#836-p%C3%A4ringu-n%C3%A4ide)
- [9\. Disaini konstrueerimise kaalutlused](#9-disaini-konstrueerimise-kaalutlused)
- [10\. Vastavusklausel](#10-vastavusklausel)
- [11\. Vastavusmudel](#11-vastavusmudel)
  - [11\.1\. Kasutusteabe lugemise vastavus](#111-kasutusteabe-lugemise-vastavus)

## 3. Ülevaade

Käesolev dokument esitab tarkvaralise lahenduse "Andmejälgija" kasutusteabe esitamise protokolli, mida kasutatakse eesti.ee-st X-tee päringute tegemisel andmesalvestajale.

Kasututeabe esitamise protokoll on spetsifikatsioon, mis määratleb, kuidas toimub Andmesalvestajasse isikuandmete kasutusteabe edastamine ning isikuandmete kasutusteabe küsimine.

Dokument on suunatud arendajatele, kellel on tarvis realiseerida Andmesalvestajaga suhtlemine kas sinna isikuandmete kasutusteabe edastamiseks või siis selle info küsimiseks.

## 4. Seotud dokumendid

X-tee dokumentatsioon:  https://x-tee.ee/docs/live/xroad/, https://x-tee.ee/docs/live/xroad/ug-ss_x-road_6_security_server_user_guide.html#63-enabling-and-disabling-a-service-description

Käesoleva kasutusteabe esitamise protokolli osadeks on:
- REST prototkolli kirjeldus: [dumonitor-openapi.yaml](https://github.com/sipsu1/AJ/blob/master/doc/spetsifikatsioonid/dumonitor-openapi-v2.yaml).

- SOAP protokolli kirjeldus: [dumonitor.wsdl](https://github.com/sipsu1/AJ/blob/master/doc/spetsifikatsioonid/dumonitor.wsdl).
  
Kasutusteabe esitamise protokolli normatiivsed viited:
- X-tee REST sõnumiprotokoll (https://www.x-tee.ee/docs/live/xroad/pr-rest_x-road_message_protocol_for_rest.html)

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

Protokolli päringud baseeruvad REST-protokollil ning vastavad X-tee versioon 7 põhimõtetele.

Päringu algataja ja andmesalvestaja vaheline suhtlus toimub vastavalt X-tee põhimõtetele mõlema osapoole turvaserverite vahendusel. Protokoll toimib olekuvabalt – igas päringus leidub ammendav info päringu vastuse koostamiseks ning puuduvad erinevate päringute omavahelised sõltuvused.

Suhtlus algab alati päringu algataja poolt – päringu algataja esitab päringu ning andmesalvestaja vastab.

![Kasutusteabe esitamise protkolli diagramm](../img/kasutusteabe_esitamise_protokolli_diagramm.png)


## 8. REST päringute kirjeldused

Allpool on toodud seal esitatud findUsage, usagePeriod ja heartbeat päringte lahtiselgitused. Need kolm teenust on kohustuslikud andmejälgija korrektseks tööks.

### 8.1. Kasutusteabe küsimine

#### 8.1.1. Päringu nimi

findUsage

#### 8.1.2. Päringu töö kirjeldus

Päringuga otsitakse andmesalvestaja andmebaasist kasutusteabe kirjeid, mis vastavad sisendis antud piirangutele. Päringu väljundis tagastatakse kõik leitud kirjed.

#### 8.1.3. Päringu sisend

Päringu päises tuleb kasutada X-tee päised vastavalt X-tee REST sõnumiprotokollile. Päis "X-Road-User-Id" ei ole kohustuslik X-tee sõnumiprotokollis, kuid on kohustuslik kasutusteabe küsimisel:

| Element        | Andmetüüp | Kohustuslik | Kirjeldus                                                        |
| -------------- | --------- | ----------- | ---------------------------------------------------------------- |
| X-Road-User-Id | string    | jah         | Päringu algataja isikukood peab olema lisatud X-Road päise sisse |

Päringu sisendis on järgmised elemendid:

| Element     | Andmetüüp | Kohustuslik | Kirjeldus |
| ----------- | --------- | ----------- | --------- |
| userCode    | string    | jah         | Andmesubjekti isikukood, kelle kohta tuleks tagastada kasutusteavet. Võib erineda X-Road-UserId päise väärtusest, kui päring on käivitatud esindusõiguse abil. |
| periodStart | int       | ei          | Tagastada andmetöötluse kirjeid alates näidatud ajast |
| periodEnd   | int       | ei          | Tagastada andmetöötluse kirjeid kuni näidatud ajani |
| offset      | int       | ei          | Jätta vahele näidatud arvu kirjed ja tagastada järgmised (analoogne SQL offset käitumisega) |
| limit       | int       | ei          | Tagastada maksimaalselt näidatud arv kasutusteabe kirjeid (vaikimisi tagastatakse maksimaalselt 1000 kirjet) |

#### 8.1.4. Päringu väljund

Päringu väljundis tagastatakse nimekiri elementidest "usage" ning arv "totalUsages", mis ütleb kui palju neid elemente oli. Iga "usage" element vastab ühele isikuandmete kasutuse kirjele ning selle struktuur on järgnev:

| Element        | Andmetüüp | Kohustuslik | Kirjeldus                                                                         |
| -------------- | --------- | ----------- | --------------------------------------------------------------------------------- |
| logtime        | dateTime  | jah         | Andmetöötluse ajamoment                                                           |
| action         | string    | jah         | Menetluse/tegevuse/sündmuse inimmõistetav nimi, mis seletab andmetöötluse põhjust |
| receiverCode   | string    | jah         | Asutuse registrikood, kellele isikuandmeid edastati või kes isikuandmeid töötles  |
| receiverName   | string    | ei          | Asutuse nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles          |
| receiverSystem | string    | jah         | Infosüsteemi nimi, kellele isikuandmeid edastati või kes isikuandmeid töötles     |

Isikuandmete kasutuse kirjed tuleb tagastada kirje ajamomendi kahanemise järjekorras (hilisem eespool).

#### 8.1.5. Veasituatsioonid

Päringu täitmisel juhtunud vea korral tagastatakse vastav HTTP staatuskood.

#### 8.1.6. Päringu näide

Näidispäring:
```bash
curl -H "X-Road-User-Id:EE12345678901" -H "X-Road-Client:INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM"
"https://SECURITYSERVER:443/r1/INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM/findUsage?userCode=EE12345678901&offset=0&limit=10"
```

Näidisvastus:
```json
{
  "totalUsages": 1,
  "usages": [
    {
      "logtime": "2023-10-01T12:00:00",
      "receiverName": "TEHIK",
      "receiverCode": "12345678",
      "receiverSystem": "Terviseportaal",
      "action": "Isiku ees- ja perenime päring"
    }
  ]
}
```

### 8.2. Kasutusteabe ajaperiood

#### 8.2.1. Päringu nimi

usagePeriod

#### 8.2.2. Päringu töö kirjeldus

Ajaperiood, mille kohta saab pärida kasutusteavet.

#### 8.2.3. Päringu sisend

Päringu päises tuleb kasutada X-tee päised vastavalt X-tee REST sõnumiprotokollile.

Päringul pole sisendparameetreid.

#### 8.2.4. Päringu väljund

Päringu väljundis tagastatakse järgmised elemendid:

| Element     | Andmetüüp | Kohustuslik | Kirjeldus |
| ----------- | --------- | ----------- | --------- |
| periodStart | dateTime  | jah         | Süsteem hoiab andmetöötluse kirjeid alates näidatud ajast |
| periodEnd   | dateTime  | ei          | Süsteem hoiab andmetöötluse kirjeid kuni näidatud ajani (välja puudumine näitab, et kirjeid saab küsida kuni praeguse ajani) |

#### 8.2.5. Veasituatsioonid

Päringu täitmisel juhtunud vea korral tagastatakse vastav HTTP staatuskood.

#### 8.2.6. Päringu näide

Näidispäring:
```bash
curl -H "X-Road-Client:INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM"
"https://SECURITYSERVER:443/r1/INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM/usagePeriod"
```

Näidisvastus:
```json
{
  "periodStart": "2021-01-31T10:20:30"
}
```

### 8.3. Kasutusteabe elutukse

#### 8.3.1. Päringu nimi

heartbeat

#### 8.3.2. Päringu töö kirjeldus

Andmejälgija kasutusteabe elutukse küsimine.

#### 8.3.3. Päringu sisend

Päringu päises tuleb kasutada X-tee päised vastavalt X-tee REST sõnumiprotokollile.

Päringul pole sisendparameetreid.

#### 8.3.4. Päringu väljund

Päringu väljundis tagastatakse järgmised elemendid:

| Element | Andmetüüp | Kohustuslik | Kirjeldus                                     |
| --------| --------- | ----------- | --------------------------------------------- |
| status  | string    | jah         | Elutuukse staatus                             |
| message | string    | ei          | Inimloetav elutuukse staatuse kirjeldav sõnum |

#### 8.3.5. Veasituatsioonid

Päringu täitmisel juhtunud vea korral tagastatakse vastav HTTP staatuskood.

#### 8.3.6. Päringu näide

Näidispäring:
```bash
curl -H "X-Road-Client:INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM"
"https://SECURITYSERVER:443/r1/INSTANCE/MEMBERCLASS/MEMBERCODE/SUBSYSTEM/heartbeat"
```

Näidisvastus:
```json
{
  "status": "OK",
  "message": "API is ready"
}
```

## 9. Disaini konstrueerimise kaalutlused

Andmesalvestajalt kasutusteabe küsimine tuleb realiseerida lehekülje kaupa. Punktis "8.1. Kasutusteabe küsimine" kirjeldatud päringu kasutamisel tuleb vajadusel näidata parameetri "limit" abil soovitavate kirjete maksimumarv (juhul, kui soovitakse vaikimisi kasutatavast maksimumarvust erinevat kirjete arvu). Kui päringu vastuses tagastatakse maksimumarv kirjeid, siis järelikult leiti kirjeid rohkem. Ülejäänud kirjete saamiseks tuleb käivitada sama päring samade otsitingimustega uuesti, näidates parameetri "offset" väärtuseks eelmise päringu poolt tagastatud kirjete arvu. Päringut korratakse analoogse loogikaga (suurendades iga kord "offset" parameetri väärtust juba saadud kirjete arvuni) niikaua, kuni päringu vastuses tagastatud kirjete arv on väiksem maksimumarvust.

## 10. Vastavusklausel

Lahendus on vastavuses kasutusteabe esitamise protokolliga juhul, kui selles on realiseeritud punktis "8. REST Päringute kirjeldused" kirjeldatud teenused vastavalt neis sätestatud nõuetele ning lisaks on arvestatud punktis "9. Disaini konstrueerimise kaalutlused" toodud nõudeid.

## 11. Vastavusmudel

Kasutusteabe esitamise protokollil on üks vastavusprofiil:

### 11.1. Kasutusteabe lugemise vastavus

- Lahendus küsib andmesalvestajalt isikuandmete kasutusteavet punktis "8.1. Kasutusteabe küsimine" kirjeldatud päringu abil ning järgib täiendavalt punktis "9. Disaini konstrueerimise kaalutlused" toodud nõudeid.
