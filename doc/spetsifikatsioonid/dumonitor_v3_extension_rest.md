# X-tee: Andmejälgija laiendus REST-protokollile

Tehniline spetsifikatsioon

Versioon: 0.1.0  
Dok. ID: PR-PDU-REST

---

## Versiooniajalugu

 Kuupäev    | Versioon | Kirjeldus         | Autor
 ---------- | -------- | ----------------- | -------------
 2026-08-13 | 0.1.0    | Esialgne versioon | Vitali Stupin

## Sisukord

<!-- toc -->
- [X-tee: Andmejälgija laiendus REST-protokollile](#x-tee-andmejälgija-laiendus-rest-protokollile)
  - [Versiooniajalugu](#versiooniajalugu)
  - [Sisukord](#sisukord)
  - [Litsents](#litsents)
  - [1 Sissejuhatus](#1-sissejuhatus)
  - [2 HTTP päised](#2-http-päised)
    - [2.1 Päiste definitsioonid](#21-päiste-definitsioonid)
      - [Üldised väljad](#üldised-väljad)
      - [Päringu algatanud asutuse väljad](#päringu-algatanud-asutuse-väljad)
      - [Päringu algatanud isiku väljad](#päringu-algatanud-isiku-väljad)
    - [2.2 Piirangud](#22-piirangud)
  - [Lisa A Näidissõnumid](#lisa-a-näidissõnumid)
    - [A.1 Näidispäring päringu algatanud asutusega](#a1-näidispäring-päringu-algatanud-asutusega)
    - [A.2 Näidispäring päringu algatanud isikuga](#a2-näidispäring-päringu-algatanud-isikuga)
<!-- tocstop -->

## Litsents

See dokument on litsentseeritud Creative Commons Attribution-ShareAlike 3.0 Unported litsentsi alusel. Litsentsi vaatamiseks külastage <http://creativecommons.org/licenses/by-sa/3.0/>.

## 1 Sissejuhatus

See spetsifikatsioon kirjeldab X-tee REST-sõnumiprotokolli laiendust <https://github.com/nordic-institute/X-Road/blob/develop/doc/Protocols/pr-rest_x-road_message_protocol_for_rest.md>.

Laienduse eesmärk on võimaldada teenuse tarbijatel saata X-tee REST-päringutega kaasa täiendavat teavet isikuandmete kasutuse kohta, et teenuse osutajad saaksid seda teavet Andmejälgijas <https://github.com/e-gov/AJ> logida.

Kodanikule kuvatav teave Andmejälgijas sõltub suuresti teenuse osutajast. Samas on kõige väärtuslikum kontekst - miks päring tehti ja milline süsteem või isik selle algatas - teada üldjuhul ainult teenuse tarbijale. See laiendus võimaldab tarbijal edastada selle konteksti iga päringuga.

Täiendavad andmed edastatakse HTTP päringupäistena. Kõik laienduse päised kasutavad eesliidet `X-Road-PDU-` (PDU - *Personal Data Usage*, isikuandmete kasutus), mis on kooskõlas baasprotokollides kasutusel oleva `X-Road-*` nimetamiskonventsiooniga. Päiste nimed on HTTP spetsifikatsiooni kohaselt tõstutundmatud.

Laienduse rakendamine on valikuline — teenuse tarbija ja teenuse osutaja peavad selle kasutuses eraldi kokku leppima.

Päiste väljad vastavad Andmejälgija v3 OpenAPI spetsifikatsiooni `UsageCommon` skeemi väljadele. Mitte-ASCII märke (eesti ja muud täpitähed) sisaldavad stringväärtused PEAVAD olema UTF-8 protsent-kodeeritud, järgides sama konventsiooni, mida baasprotokoll kasutab `X-Road-Client` päise jaoks.

## 2 HTTP päised

### 2.1 Päiste definitsioonid

#### Üldised väljad

Tabel 1. Üldised päiseväljad

 Päis                            | Tüüp     | Kohustuslik / Valikuline | Vastab väljale      | Kirjeldus
 ------------------------------- | -------- | ------------------------ | ------------------- | ---------
 `X-Road-PDU-Purpose`            | string   | K                        | `purpose`           | Inimmõistetav tegevuse või sündmuse nimetus, mis selgitab andmetöötluse põhjust (nt teenuse või menetluse nimi).
 `X-Road-PDU-Bulk-Processing`    | boolean  | V                        | `bulkProcessing`    | Seada väärtuseks `true`, kui päring on osa massandmetöötlusest (automatiseeritud taustaprotsessid, perioodilised masspäringud jne). Päise puudumine tähendab `false`.
 `X-Road-PDU-Legal-Basis`        | string   | V                        | `legalBasis`        | Andmetöötluse õiguslik alus (nt kohalduva õigusakti nimetus ja paragrahv).
 `X-Road-PDU-Consent-Reference`  | string   | V                        | `consentReference`  | Viide andmesubjekti nõusolekule, kui päring tehakse nõusoleku alusel.
 `X-Road-PDU-Hidden`             | boolean  | V                        | -                   | Kui väärtus on `true`, näitab see, et see päring PEAB olema peidetud isiku eest, kelle andmeid päringus töödeldi. Seda võib kasutada ainult juhul, kui teenuse tarbijal on seaduse alusel õigus teha päringuid, mida Andmejälgija ei tohi jälgida. Andmejälgija VÕIB rakendada nimekirja süsteemidest, millel on lubatud päringuid peita, või muid meetmeid peitmisvõimaluse kuritarvitamise vältimiseks. Päise puudumine tähendab `false`.
 `X-Road-PDU-Hidden-Reason`      | string   | V                        | `hiddenReason`      | Inimmõistetav põhjendus, miks kirje on isiku eest peidetud (nt viide õigusnormile). Isikule kuvatakse alles pärast avalikustamist. Kasutatakse ainult koos `X-Road-PDU-Hidden: true` päisega.
 `X-Road-PDU-Hidden-Until`       | dateTime | V                        | `hiddenUntil`       | Ajamoment, milleni kirje on isiku eest peidetud. Pärast seda ajahetke on kirje isikule nähtav. ISO 8601 formaat koos ajavööndiga, nt `2030-01-01T00:00:00Z`. Kui päis puudub, avalikustatakse kirje hiljem "disclose" teenuse kaudu. Kasutatakse ainult koos `X-Road-PDU-Hidden: true` päisega.

> **Märkus logimisaja kohta:** `UsageCommon` skeemi välja `logTime` jaoks eraldi päist ei ole määratletud. Teenuse tarbija ja teenuse osutaja logivad andmetöötluse aja iseseisvalt oma süsteemis ning väikesed erinevused kellaaegades on aktsepteeritavad.
>
> **Märkus päringu ID kohta:** `UsageCommon` skeemi välja `queryId` jaoks eraldi päist ei ole määratletud. Teenuse osutaja PEAKS tuletama selle väärtuse baasprotokollide `X-Road-Id` päisest.

#### Päringu algatanud asutuse väljad

Kasutatakse siis, kui päringu algatas asutus. Päis `X-Road-PDU-Initiator-Org-Type` PEAB olema lisatud alati, kui saadetakse mõni `X-Road-PDU-Initiator-Org-*` päis.

Tabel 2. Päringu algatanud asutuse päiseväljad

 Päis                               | Tüüp   | Kohustuslik / Valikuline | Vastab väljale            | Kirjeldus
 ---------------------------------- | ------ | ------------------------ | ------------------------- | ---------
 `X-Road-PDU-Initiator-Org-Type`    | string | K (asutuse puhul)        | `initiatorOrg.type`       | Asutuse tüüp. Lubatud väärtused: `GOV` (Eesti avalik sektor), `COM` (Eesti äriühing), `NGO` (Eesti mittetulundusühing), `NEE` (välismaa asutus).
 `X-Road-PDU-Initiator-Org-Code`    | string | V*                       | `initiatorOrg.code`       | Asutuse registrikood. Kaheksa numbrit, nt `12345678`.
 `X-Road-PDU-Initiator-Org-Name`    | string | V*                       | `initiatorOrg.name`       | Asutuse inimmõistetav nimi.
 `X-Road-PDU-Initiator-Org-System`  | string | V                        | `initiatorOrg.systemName` | Päringu teinud infosüsteemi inimmõistetav nimi (kasulik, kui ühel asutusel on mitu erinevat süsteemi).

\* Asutuse tuvastamiseks PEAB olema täidetud vähemalt üks: `X-Road-PDU-Initiator-Org-Code` või `X-Road-PDU-Initiator-Org-Name`.

#### Päringu algatanud isiku väljad

Kasutatakse siis, kui päringu algatas isik. Vähemalt üks alljärgnevatest päistest PEAB olema lisatud alati, kui saadetakse mõni `X-Road-PDU-Initiator-Person-*` päis.

Tabel 3. Päringu algatanud isiku päiseväljad

 Päis                                           | Tüüp   | Kohustuslik / Valikuline | Vastab väljale                    | Kirjeldus
 ---------------------------------------------- | ------ | ------------------------ | --------------------------------- | ---------
 `X-Road-PDU-Initiator-Person-Code`             | string | V*                       | `initiatorPerson.code`            | Eesti isikukood. Formaat: `EE` ja 11 numbrit, nt `EE12345678901`.
 `X-Road-PDU-Initiator-Person-Alternative-Code` | string | V*                       | `initiatorPerson.alternativeCode` | Alternatiivne isiku identifikaator: töötõendi number või muu unikaalne kood, mille abil päringut sooritanud süsteem suudab koodi tegeliku isikuga seostada; kasutatakse ka välisriigi isiku identifitseerimiskoodide jaoks.
 `X-Road-PDU-Initiator-Person-Name`             | string | V*                       | `initiatorPerson.name`            | Isiku täisnimi.

\* Isiku tuvastamiseks PEAB olema täidetud vähemalt üks neist kolmest päisest.

### 2.2 Piirangud

Järgmised piirangud kehtivad siis, kui päringus saadetakse PDU laienduse päiseid.

1. `X-Road-PDU-Purpose` PEAB olema lisatud.

2. Vähemalt üks algataja PEAB olema tuvastatud - kas päringu algatanud asutus (`X-Road-PDU-Initiator-Org-*`), päringu algatanud isik (`X-Road-PDU-Initiator-Person-*`) või mõlemad.

3. Asutuse tuvastamisel:
   - `X-Road-PDU-Initiator-Org-Type` PEAB olema lisatud.
   - Vähemalt üks `X-Road-PDU-Initiator-Org-Code` või `X-Road-PDU-Initiator-Org-Name` PEAB olema lisatud.

4. Isiku tuvastamisel PEAB olema lisatud vähemalt üks järgnevatest:
   - `X-Road-PDU-Initiator-Person-Code`
   - `X-Road-PDU-Initiator-Person-Alternative-Code`
   - `X-Road-PDU-Initiator-Person-Name`

5. Kui `X-Road-PDU-Hidden-Until` on lisatud, PEAB olema lisatud ka `X-Road-PDU-Hidden-Reason`.

6. Laienduse päised on sõltumatud baasprotokollide `X-Road-UserId` päisest. `X-Road-UserId` tuvastab X-tee päringu esitaja; `X-Road-PDU-Initiator-Person-*` tuvastab ärilise tegevuse algataja (kes võib olla erinev isik, nt esindusõiguse alusel tegutsemisel).

7. Kui teenuse tarbija ei saada ühtegi `X-Road-PDU-*` päist, PEAB teenuse osutaja päringu ikkagi tavapäraselt täitma. Laiendus on baasprotokollide suhtes valikuline.

## Lisa A Näidissõnumid

### A.1 Näidispäring päringu algatanud asutusega

```http
GET /v1/getPersonData?personalCode=EE12345678901 HTTP/1.1
Host: teenusepakkuja.example.com
X-Road-Client: EE/GOV/70000562/DHX
X-Road-Id: 4894e35d-bf0f-44a6-867a-8e51f1daa7e0
X-Road-UserId: EE12345678901
X-Road-PDU-Purpose: Isiku%20ees-%20ja%20perenime%20p%C3%A4ring
X-Road-PDU-Legal-Basis: Tervise%20infos%C3%BCsteemi%20p%C3%B5him%C3%A4%C3%A4rus%20%28%C2%A7nn%29
X-Road-PDU-Initiator-Org-Type: GOV
X-Road-PDU-Initiator-Org-Code: 70000562
X-Road-PDU-Initiator-Org-Name: Tervise%20ja%20Heaolu%20Infos%C3%BCsteemide%20Keskus
X-Road-PDU-Initiator-Org-System: Terviseportaal
```

### A.2 Näidispäring päringu algatanud isikuga

```http
GET /v1/getPersonData?personalCode=EE12345678901 HTTP/1.1
Host: teenusepakkuja.example.com
X-Road-Client: EE/GOV/70000562/DHX
X-Road-Id: 7c3f1a09-22ad-4b8e-a901-1234abcd5678
X-Road-PDU-Purpose: Raviajaloo%20vaatamine
X-Road-PDU-Initiator-Person-Code: EE38001085718
X-Road-PDU-Initiator-Person-Name: Mari%20Maasikas
```
