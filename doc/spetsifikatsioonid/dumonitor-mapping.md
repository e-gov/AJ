# Andmejälgija v3 teenuse koostamine v1 ja v2 logide põhjal

Versioon: 0.1.0

---

## Versiooniajalugu

 Kuupäev    | Versioon | Kirjeldus         | Autor
 ---------- | -------- | ----------------- | -------------
 2026-10-27 | 0.1.0    | Esialgne versioon | Vitali Stupin

## Sisukord

<!-- toc -->
- [Andmejälgija v3 teenuse koostamine v1 ja v2 logide põhjal](#andmejälgija-v3-teenuse-koostamine-v1-ja-v2-logide-põhjal)
  - [Versiooniajalugu](#versiooniajalugu)
  - [Sisukord](#sisukord)
  - [1 Sissejuhatus](#1-sissejuhatus)
  - [2 Lühiülevaade](#2-lühiülevaade)
  - [3 Kirje liigi valimine](#3-kirje-liigi-valimine)
  - [4 Väljade vastavus](#4-väljade-vastavus)
    - [4.1 Üldised väljad](#41-üldised-väljad)
    - [4.2 Päringu algataja](#42-päringu-algataja)
    - [4.3 Andmete allikas](#43-andmete-allikas)
    - [4.4 Asutuse tüüp](#44-asutuse-tüüp)
  - [5 Kuvamine kasutajaliideses](#5-kuvamine-kasutajaliideses)
  - [6 Näited](#6-näited)
    - [6.1 v1 kirje](#61-v1-kirje)
    - [6.2 v2 kirje](#62-v2-kirje)
<!-- tocstop -->

## 1 Sissejuhatus

Dokument on mõeldud teenuse osutajatele, kes on varem teostanud Andmejälgija protokolli v1 või v2 versiooni ning soovivad hinnata, kuidas olemasolevate logide põhjal v3 teenust koostada.

Dokumendis kirjeldatakse iga v3 kasutusteabe kirje välja kohta, millisest v1 või v2 logiväljast saab selle väärtuse võtta. Päringu parameetreid ja teenuste loetelu dokumendis ei käsitleta, need on kirjeldatud v3 spetsifikatsioonis.

Protokolli versioonid:

- v1 - SOAP protokoll [dumonitor.wsdl](dumonitor.wsdl).
- v2 - REST protokoll [dumonitor-openapi.yaml](dumonitor-openapi.yaml), vt ka [Kasutusteabe_esitamise_protokoll.md](Kasutusteabe_esitamise_protokoll.md).
- v3 - REST protokoll [dumonitor-openapi-v3.yaml](dumonitor-openapi-v3.yaml).

## 2 Lühiülevaade

v1 või v2 kirje põhjal saab koostada v3 kirje järgmiselt:

1. Paigutada kirje massiivi `dataResponses` (vt [3 Kirje liigi valimine](#3-kirje-liigi-valimine)).
2. Nimetada väljad ümber: `logtime` → `logTime`, `action` → `purpose`.
3. Paigutada isikuandmete vastuvõtja andmed päringu algataja objekti `initiatorOrg` või `initiatorPerson` alla (vt [4.2 Päringu algataja](#42-päringu-algataja)).
4. Lisada väli `dataSource`, mille väärtus on teenuse osutaja ise. Väärtus on kõigi kirjete puhul sama.

Kõik ülejäänud v3 väljad on valikulised ning need võib jätta tagastamata.

## 3 Kirje liigi valimine

v3 protokollis paigutatakse iga kirje ühte neljast massiivist. v1 ja v2 protokollis kirje liiki ei eristatud.

v1 ja v2 kirjed paigutatakse vaikimisi massiivi `dataResponses`. Kui teenuse osutaja logide põhjal on teada, et isikuandmeid töötles teenuse osutaja ise (näiteks tema töötaja või sisemine infosüsteem), võib kirje paigutada massiivi `internalProcessings`.

Massiive `dataRequests` ja `dataProcessings` v1 ja v2 kirjete puhul üldjuhul ei kasutata.

## 4 Väljade vastavus

### 4.1 Üldised väljad

 v1 väli   | v2 väli   | v3 väli   | Märkused
 --------- | --------- | --------- | --------
 `logtime` | `logtime` | `logTime` | Nime muutus.
 `action`  | `action`  | `purpose` | Nime muutus.

Väljad `logTime` ja `purpose` on v3 protokollis kohustuslikud. v1 protokollis olid need formaalselt valikulised, kuid praktikas on need olemas igal v1 kirjel.

Ülejäänud väljad (`logSourceSystem`, `bulkProcessing`, `legalBasis`, `consentReference`, `queryId`, `queryGroupId`, `hiddenReason`, `hiddenUntil`) on v3 protokollis uued ja valikulised. Need võib jätta tagastamata.

### 4.2 Päringu algataja

v3 kirjel PEAB olema vähemalt üks väli `initiatorOrg` või `initiatorPerson`. Andmekogu vastuste (`dataResponses`) puhul on päringu algataja see osapool, kellele isikuandmeid edastati, ehk v1 ja v2 protokolli vastuvõtja.

**v1 kirjed.** v1 protokollis oli vastuvõtja kohta ainult üks tekstiväli `receiver`, mille sisu ei olnud reguleeritud. Väli võib sisaldada näiteks asutuse või isiku nime, registrikoodi, isikukoodi või nende kombinatsiooni. Väli `receiver` on formaalselt valikuline, kuid praktikas on see olemas igal v1 kirjel.

Välja `receiver` väärtus paigutatakse vaikimisi muutmata kujul välja `initiatorOrg.name`. Väljadel `name` ei ole vormingu piiranguid ning väärtus kuvatakse andmesubjektile sellisena, nagu see logis on. Andmejälgija kasutajaliides kuvab vähemalt esialgu väljade `initiatorOrg` ja `initiatorPerson` andmed ühes ühises veerus "Andmete saaja", mistõttu ei ole asutuse ja isiku eristamine kuvamise jaoks oluline.

Kui teenuse osutaja soovib vanade v1 logikirjete põhjal vastuvõtja andmeid täpsemalt esitada, võib ta välja `receiver` sisu osadeks jagada. Sel juhul paigutatakse kood välja `code` ning ülejäänud sisu (näiteks nimi) välja `name`. Koodi ei tohi jätta välja `name` väärtusesse, kuna kasutajaliides kuvab nime ja koodi koos ning kood kuvataks siis topelt. Kui pärast koodi eemaldamist ei jää nimeks midagi järele, jäetakse väli `name` tagastamata.

Kui välja `receiver` sisust on võimalik eraldada vastuvõtja infosüsteemi nimi, paigutatakse see välja `initiatorOrg.systemName`. Ka infosüsteemi nime ei tohi jätta välja `name` väärtusesse, kuna kasutajaliides kuvab ka infosüsteemi koos nimega.

Vastuvõtja liigi tuvastamiseks SOOVITATAKSE kontrollida, kas välja `receiver` sisu sisaldab 11-kohalist numbrit, mis vastab kehtiva Eesti isikukoodi vormingule (esimene number 1-6, numbrid 2-7 moodustavad kehtiva sünnikuupäeva ning viimane number on kehtiv kontrollnumber). Sel juhul võib eeldada, et vastuvõtja on isik, ning:

- paigutada leitud isikukood eesliitega `EE` välja `initiatorPerson.code`;
- paigutada välja `receiver` ülejäänud sisu ilma isikukoodita välja `initiatorPerson.name`.

 v1 väli    | v3 väli                   | Märkused
 ---------- | ------------------------- | --------
 `receiver` | `initiatorOrg.name`       | Vaikimisi kogu väärtus muutmata kujul. Kui väärtusest eraldati kood või infosüsteemi nimi, siis ainult ülejäänud sisu.
 `receiver` | `initiatorOrg.code`       | Kui sisust on võimalik eraldada asutuse registrikood.
 `receiver` | `initiatorOrg.systemName` | Kui sisust on võimalik eraldada infosüsteemi nimi.
 `receiver` | `initiatorPerson.name`    | Kui sisust leiti isikukood, siis ülejäänud sisu ilma isikukoodita.
 `receiver` | `initiatorPerson.code`    | Kui sisust leiti isikukood. Peab vastama mustrile `^EE[0-9]{11}$`.

Näiteks väärtus `Mari Maasikas 38001085718` esitatakse kujul:

```json
"initiatorPerson": {
  "code": "EE38001085718",
  "name": "Mari Maasikas"
}
```

**v2 kirjed.** Objekt valitakse vastuvõtja koodi (`receiverCode`) järgi:

- kui kood on Eesti isikukood, kasutatakse objekti `initiatorPerson` (isikukoodi tuvastamise kohta vt v1 kirjete kirjeldust ülal);
- muul juhul kasutatakse objekti `initiatorOrg`. Kood võib olla nii Eesti registrikood kui ka välismaa asutuse registrikood, mistõttu ei pea see olema 8-kohaline.

 v2 väli          | v3 väli                   | Märkused
 ---------------- | ------------------------- | --------
 `receiverCode`   | `initiatorOrg.code`       | -
 `receiverName`   | `initiatorOrg.name`       | -
 `receiverSystem` | `initiatorOrg.systemName` | -
 `receiverCode`   | `initiatorPerson.code`    | Peab vastama mustrile `^EE[0-9]{11}$`. Kui logis on isikukood ilma eesliiteta `EE`, tuleb eesliide lisada.
 `receiverName`   | `initiatorPerson.name`    | -

### 4.3 Andmete allikas

Väli `dataSource` on kohustuslik kõigi kirje liikide puhul. Massiivide `dataResponses` ja `internalProcessings` kirjete puhul on andmete allikas teenuse osutaja ise ning väärtus on kõigi kirjete puhul sama.

v1 ja v2 protokolli puhul kuvas kasutajaliides isikuandmete allikana liitumisel seadistatud andmeid. Et v3 kirjete kuvamine ei muutuks, PEAKS välja `dataSource.systemName` täitma teenuse osutaja infosüsteemi nimega, mis vastab liitumisel seadistatud nimele.

### 4.4 Asutuse tüüp

v1 ja v2 kirjetes asutuse tüüpi ei olnud. v3 protokollis PEAKS väli `type` olema täidetud, kui tüüp on teada, kuid vanade logikirjete puhul võib selle jätta tagastamata. Tüübi saab tuletada järgmistest allikatest:

- **Teenuse osutaja ise** (`dataSource`) - tüüp on teada ja kõigi kirjete puhul sama.
- **X-tee kliendi identifikaator** - kui logides on päringu teinud X-tee kliendi identifikaator (nt `EE/GOV/70000562/...`), vastab X-tee liikmeklass (`GOV`, `COM`, `NGO`, `NEE`) otse välja `type` väärtusele.

Kui tüüpi ei saa tuletada, jäetakse väli tagastamata.

## 5 Kuvamine kasutajaliideses

Andmejälgija kasutajaliideses kuvatakse andmesubjektile iga kirje kohta järgmised põhiväljad. Tabel näitab, millistest v1, v2 ja v3 väljadest kuvatav info pärineb.

 Kasutajaliidese väli | v1 | v2 | v3
 -------------------- | -- | -- | --
 Päringu aeg | `logtime` | `logtime` | `logTime`
 Isikuandmete allikas | Liitumisel seadistatud andmed | Liitumisel seadistatud andmed | `dataSource.name`, `dataSource.code` ja `dataSource.systemName`
 Andmete liikumise suund või töötluse liik (ikoon) | Eeldatakse `dataResponses`, ehk andmete väljastamine andmekogust | Eeldatakse `dataResponses`, ehk andmete väljastamine andmekogust | Massiiv, kuhu kirje on paigutatud (`dataRequests`, `dataResponses`, `dataProcessings` või `internalProcessings`)
 Andmete saaja, tema kood ja infosüsteem | `receiver` | `receiverName`, `receiverCode` ja `receiverSystem` | `initiatorOrg.name`, `initiatorOrg.code` ja `initiatorOrg.systemName` või `initiatorPerson.name` ja `initiatorPerson.code`
 Päringu nimetus | `action` | `action` | `purpose`

Seega kuvatakse v1 ja v2 logide põhjal koostatud v3 kirjed kasutajaliideses samamoodi nagu v1 ja v2 kirjed, kui need on koostatud selle dokumendi järgi: kirje on massiivis `dataResponses`, väli `dataSource` kirjeldab teenuse osutajat ning vastuvõtja andmed on väljadel `initiatorOrg` või `initiatorPerson`.

## 6 Näited

Näidetes on teenuse osutaja riigiasutus registrikoodiga `70000562`.

Väli `dataSource` ei tule v1 ega v2 kirjest. Massiivi `dataResponses` kirjete puhul kirjeldab see teenuse osutajat ennast ning on kõigi kirjete puhul sama (vt [4.3 Andmete allikas](#43-andmete-allikas)). Väärtuse saab määrata teenuse seadistuses, näiteks teenuse osutaja X-tee liikme identifikaatori `EE/GOV/70000562` põhjal, millest tuletatakse `type` (`GOV`) ja `code` (`70000562`). Infosüsteemi nimi `systemName` vastab liitumisel seadistatud nimele.

### 6.1 v1 kirje

v1 vastus (SOAP sõnumi keha):

```xml
<findUsageResponse xmlns="http://dumonitor.x-road.eu/producer">
    <usage>
        <logtime>2016-05-09T12:00:00Z</logtime>
        <action>Isiku aadressi päring</action>
        <receiver>Maksu- ja Tolliamet</receiver>
    </usage>
</findUsageResponse>
```

Vastav v3 vastus:

```json
{
  "dataResponses": [
    {
      "logTime": "2016-05-09T12:00:00Z",
      "purpose": "Isiku aadressi päring",
      "initiatorOrg": {
        "name": "Maksu- ja Tolliamet"
      },
      "dataSource": {
        "type": "GOV",
        "code": "70000562",
        "systemName": "Näidisandmekogu"
      }
    }
  ]
}
```

### 6.2 v2 kirje

v2 vastus:

```json
{
  "totalUsages": 1,
  "usages": [
    {
      "logtime": "2023-10-01T12:00:00Z",
      "action": "Isiku ees- ja perenime päring",
      "receiverCode": "12345678",
      "receiverName": "TEHIK",
      "receiverSystem": "Terviseportaal"
    }
  ]
}
```

Vastav v3 vastus:

```json
{
  "dataResponses": [
    {
      "logTime": "2023-10-01T12:00:00Z",
      "purpose": "Isiku ees- ja perenime päring",
      "initiatorOrg": {
        "code": "12345678",
        "name": "TEHIK",
        "systemName": "Terviseportaal"
      },
      "dataSource": {
        "type": "GOV",
        "code": "70000562",
        "systemName": "Näidisandmekogu"
      }
    }
  ]
}
```
