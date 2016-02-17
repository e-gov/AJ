---
layout: page
---

## Andmejälgija juurutuse tüüpkava

**Lühendid**: AJ - andmejälgija; ER - eraldusfilter; AS - andmesalvestaja

**Eeldus:** Andmejälgija töötab X-tee versioonis 6. 

Andmejälgija dokumentatsioon: [analüüsi ja disaini projekti tulemused](http://x-road.eu/docs/PersonalDataMonitor/) (täieneb)

| Nr | Tegevus | Tulemus | Täitjad | Tähtaeg | Konsultatsiooni vajadus |
|----|---------|---------|---------|---------|-------------------------|
| 1  | Planeerimine | AJ juurutuskava on koostatud (võib põhineda tüüpkaval) | andmekogu ärijuht, asutuse IT juht | |
|    | - juurutusressursi planeerimine | AJ juurutamiseks vajalike osapoolte (tehnilise taristu haldaja, haldus, arendaja) nõutav panus on hinnatud ja kohustumused saadud. | andmekogu ärijuht, asutuse IT juht | |
|    | - ajaplaneerimine | AJ juurutuse tähtaeg on seatud; tähtsamate tegevuste orienteeruvad ajad on määratud. | andmekogu ärijuht, asutuse IT juht | | |
| 2   | Jälgitavate X-tee andmevahetuste valik  | X-tee teenused, mille kaudu toimuvat andmevahetust otsustatakse jälgida, on välja selgitatud. | andmekogu ärijuht, analüütik või arhitekt | | |
|     | - teenuste dokumentatsiooni kohendamine (vajadusel) | Eraldusfiltri rakendamise eelduseks on täpne arusaamine teenuses liikuvate andmete tähendusest (semantikast). Teenused, millele AJ rakendatakse, on tähenduselt selged. | analüütik | | |
| 3   | Jälgitavate sisemiste andmekasutuste valik | AJ-s võib salvestada ja kodanikele kättesaadavaks teha ka neid andmekogu andmekasutusi, mis ei toimu üle X-tee, vaid muude liideste kaudu. Otsus, kas sisemisi andmekasutusi AJ-s registreeritakse, on tehtud. | andmekogu ärijuht, analüütik või arhitekt | | |
| 4  | Paigalduslahenduse valik     | AJ paigalduse variant on valitud. Esitatakse skeemina. | arhitekt | | |
|    | - kooskõlastamine tehnilise taristu haldajaga | Paigaldusvariant on infra suhtes OK. | andmekogu tehnilise taristu eest vastutaja | | |
|    | - kooskõlastamine oma arendajaga | Paigaldusvariant on arenduse suhtes OK. | arenduspartner | | |
|    | - kooskõlastamine oma haldusosakonnaga | Paigaldusvariant on andmekogu halduse suhtes OK. | andmekogu halduse eest vastutaja | | |
| 5   | Eraldusfiltri (ER) häälestusfaili koostamine | Töö tulemuseks on ER häälestusfail, mis määrab, millised andmeliikumised X-tee liiklusest kinni püütakse ja Andmesalvestajas registreeritakse (tehniliselt XPath filtrid). Töö sisendiks on p 3 koostatud loetelu teenustest ja teenuste dokumentatsioon.  | arenduspartner | | |
| 6   | Sisemise andmekasutuse osa arendus (sõltuvalt p 4 tehtud otsusest) | Arendatud on tarkvarakomponent, mis saadab andmekogust andmeid sisemise andmekasutuse kohta Andmesalvestaja (AS) vastavasse REST liidesesse. | arenduspartner | | |
| 7   | Paigaldamine | AJ komponendid on paigaldatud, vastavalt p 4 valitud paigalduslahendusele. | andmekogu tehnilise taristu eest vastutaja | | |
|     | - Andmesalvestaja (AS) paigaldamine | Andmesalvestaja on paigaldatud. | andmekogu tehnilise taristu eest vastutaja | | |
|     | -- sh sisekontrolli liidese paigaldamine (kui otsustatakse seda kasutada) | Sisekontrolli liides on paigaldatud. Pääsuõigused sisekontrolöridele antud. | andmekogu tehnilise taristu eest vastutaja | | |
|     | - Eraldusfiltri (ER) paigaldamine | Eraldusfilter on paigaldatud. | andmekogu tehnilise taristu eest vastutaja | | |
|     | - ühendamine | AJ komponendid - ER ja AS on ühendatud turvaserveriga (-serveritega), üksteisega ja andmekoguga (andmekogu X-tee adapterserveriga). | andmekogu tehnilise taristu eest vastutaja | | |
| 8   | TESTIMINE | Paigaldatud lahenduse toimivus on kontrollitud. | testimisteenuse osutaja, asutuse kvaliteediosakond || |
| 9   | Sisemiste kordade täiendamine | AJ käitamisega seotud asutuse sisemised korrad ja protseduurid on täiendatud. | andmekogu halduse eest vastutaja | | |
| 10  | Partner-andmekogude haldajate teavitamine | Jälgitavas andmeliikluses osalevate teiste andmekogude haldajaid on teavitatud ja nendega on kokku lepitud, kuidas tehakse koostööd kodanike võimalikele päringutele vastamisel. | andmekogu ärijuht | | |
| 11  | Andmekogu tutvustustekst ja täiendavate küsimuste esitamiseks vajalik kontaktteave on eesti.ee-le edastatud | vt AJ [AJ täiendatud tehniline kontseptsioon](http://x-road.eu/docs/PersonalDataMonitor/3_AJ%20t%c3%a4iendatud%20tehniline%20kontseptsioon%20v1.1.docx), j 9. | andmekogu ärijuht | | |
| 12  | Andmesalvestaja X-tee teenusele ligipääsu avamine (eesti.ee vaatamiskomponendile) | Kodanik saab eesti.ee koosseisus oleva vaatamiskomponendi kaudu hakata jälgima, kuidas tema andmeid on andmekogus kasutatud. (Teenuse avamine kodanikule.) | andmekogu halduse eest vastutaja | | | 
| 13  | AJ-t tutvustav teavitus andmekogu kasutajatele | Teave AJ-st on jõudnud kodanikeni, kelle andmeid andmekogus töödeldakse. | andmekogu halduse eest vastutaja | | |
