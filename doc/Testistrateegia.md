# Testistrateegia

**DUMonitor**

Versioon 1.0, 17.04.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 17.04.2016 | Ivo Mehide | Esimene versioon

## Mõisted

* CI - DUMonitor jaoks häälestatud pidevintegratsiooni keskkond.
 

## Sissejuhatus

DUMonitor on tarkvara hõlbustamaks andmekogudel registreerida isikuandmete kasutamist. 

DUMonitor testistrateegia dokument kirjeldab üldise strateegia DUMonitor arenduse käigus tarkvara testide läbiviimisel ja testimistulemuste hindamisel. Testimisega kaetakse järgmist tüüpi testid:

* Ühiktestid
* Integratsioonitestid
* Koormustestid

## Testide läbiviimise põhimõtted

### Ühiktestid

Ühiktestide abil veendutakse tarkvarakoodi korrektses realiseerimises. Testid programmeeritakse samaaegselt tarkvarakoodi arendusega arendajate poolt. Testid käivitatakse arendaja arvutis tarkvarakoodi kompileerimise käigus, samuti ka pidevintegratsiooni keskkonnas tarkvarakoodi kompileerimise käigus.

### Integratsioonitestid

Integratsioonitestide abil veendutakse, et tarkvarakomponentide vaheline kommunikatsioon toimib - nii DUMonitor tarkvara komponentide omavaheline kommunikatsioon kui ka väliste tarkvarade ja DUMonitor tarkvara komponentide vaheline kommunikatsioon.

Testimiseks luuakse SOAPui tarkvara abil spetsiaalsed makettrakendused. Testide käivitamiseks kasutatakse Apache jMeter tarkvara. Testide käivitamiseks paigaldatakse CI keskkonnas spetsiaalselt loodud virtuaalmasinatesse DUMonitor tarkvara komponendid ja makettrakendused. Testid käivitatakse CI serverist Apache jMeter tarkvara abil.

### Koormustestid

Koormustestide abil veendutakse tarkvara võimes töötada ettenähtud koormusel. Testide läbiviimiseks kasutatakse integratsioonitestide jaoks loodud lahendusi. Testimiseks luuakse eraldi Apache jMeter testiskript, mis toimib analoogselt integratsioonitesti jaoks loodud skriptiga, kuid mida täiendatakse paralleelsete käivitustega.  Testide kävitatakse CI serverist Apache jMeter tarkvara abil.

## Testkeskkond

### Riistvara ja baastarkvara

Testide läbiviimiseks on vajalik järgmise infotehnoloogilise kompleksi paigaldamine:

* CI server (aj-jenkins.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jenkins CI tarkvara, sellel paigaldatud "Git", "Publish Over SSH" moodulid
 * openjdk-7
* Eraldusfiltri virtuaalmasin (aj-02.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-6
* Andmesalvestaja virtuaalmasin (aj-03.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-6
* Test andmekogu virtuaalmasin (aj-04.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-7
* Sisekontrollija testrakenduse virtuaalmasin (aj-06.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-7
* MISP ja esitaja testrakenduse virtuaalmasin (aj-07.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-7
* X-tee keskserveri virtuaalmasin (aj08.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee keskserveri tarkvara
* X-tee keskserverit, MISPi, esitaja testrakendust ja integratsioonitestide klientrakendust teenindav X-tee turvaserveri virtuaalmasin (aj-09.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee turvaserveri tarkvara
* Test andmekogu teenindav X-tee turvaserveri virtuaalmasin (aj-10.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee turvaserveri tarkvara

### Testkeskkonna konfiguratsioon

Testide läbiviimiseks on vajalik järgmise konfiguratsiooni paigaldamine

* CI server:
 * Häälestatud laadima DUmonitor tarkvara lähtekoodi Githubi repositooriumist
 * Koodi kompileerimine
 * Kompileeritud tarkvarakoodi paigaldamine:
  * DUMonitor filtri komponent - aj-02.ci.kit serverisse
  * DUmonitor andmesalvestaja komponent - aj-03.ci.kit serverisse
  * Test andmekogu komponent - aj-04.ci.kit serverisse
  * Sisekontrollija testrakendus - aj-07.ci.kit serverisse


## Rollid

* Täitja arendaja: Arendab ühiktestid, viib läbi ühiktestimise.
* Täitja projektijuht: Loob testskriptid ja testrakenduse, häälestab CI keskkonna, koostab testiplaani, käivitab testid ja dokumenteerib tulemused.
* Tellija projektijuht: Korraldab vastuvõtutestimise ja testitulemuste ülevaatamise.
* Tellija süsteemiadministraator: Häälestab testimiseks vajalikud serverid ja süsteemitarkvara.
* Tellija testijuht: kooskõlastab testistrateegia ja testiplaani.
