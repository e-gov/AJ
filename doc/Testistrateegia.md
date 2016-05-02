# Testistrateegia

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](img/EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide | Esimene versioon

## Sisukord

  * [Mõisted](#m%C3%B5isted)
  * [Sissejuhatus](#sissejuhatus)
  * [Testide läbiviimise põhimõtted](#testide-l%C3%A4biviimise-p%C3%B5him%C3%B5tted)
    * [Ühiktestid](#%C3%9Chiktestid)
    * [Integratsioonitestid](#integratsioonitestid)
    * [Koormustestid](#koormustestid)
  * [Testkeskkond](#testkeskkond)
    * [Riistvara ja baastarkvara](#riistvara-ja-baastarkvara)
  * [Rollid](#rollid)


## Mõisted

* CI - DUMonitor jaoks häälestatud pidevintegratsiooni keskkond.
 

## Sissejuhatus

DUMonitor on tarkvara, mis hõlbustab andmekogudel isikuandmete kasutamise registreerimist. 

DUMonitor testistrateegia dokument kirjeldab üldise strateegia DUMonitor arenduse käigus tarkvara testide läbiviimisel ja testimistulemuste hindamisel. Testimine viiakse läbi järgmist tüüpi testidega:

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

Integratsioonitestide ja koormustestide läbiviimiseks luuakse spetsiaalne CI keskkond, kuhu paigaldatakse erinevad tarkvara komponendid ning testimiseks vajalikud lahendused.

Testkeskkond koosneb järgmistest komponentidest:

* CI tarvara - testide automaatse läbiviimise vahend
* DUMonitori eraldusfilter
* DUMonitori andmesalvestaja
* Test andmekogu - eraldusfiltri testimiseks kasutatav andmekogu
* MISP - eraldusfiltri eesti.ee keskkonna jaoks tehtud xforms lahenduse testimiseks
* Esitaja testrakendus - lihtne rakendus DUMonitori andmesalvestajale päringute tegemiseks
* X-tee keskserver - testkeskkonna jaoks loodud eraldiseisva X-tee instantsi tekitamiseks
* X-tee turvaserver, mis teenindab test andmekogu (läbi DUMonitor eraldusfiltri)
* X-tee turvaserver, mis teenindab test andmekogu kliente (MISP, esitaja testrakendus ja CI tarkvara poolt käivitatavad klientpäringud) ning X-tee keskserverit

Testkeskkonna paigaldamise täpne kirjeldus on toodud eraldiseisvas dokumendis [Testide paigaldamise, käivitamise ja täiendamise juhend](Testide_paigaldamine.md)

### Riistvara ja baastarkvara

Testide läbiviimiseks on vajalik järgmise infotehnoloogilise kompleksi paigaldamine:

* CI server (aj-jenkins.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jenkins CI tarkvara, sellel paigaldatud "Git", "Publish Over SSH" moodulid
 * openjdk-7
* Eraldusfiltri virtuaalmasin (aj02.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-6
* Andmesalvestaja virtuaalmasin (aj03.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-6
* Test andmekogu virtuaalmasin (aj04.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-7
* MISP ja esitaja testrakenduse virtuaalmasin (aj07.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * Jetty 8 rakendusserver
 * openjdk-7
* X-tee keskserveri virtuaalmasin (aj08.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee keskserveri tarkvara
* X-tee keskserverit, MISPi, esitaja testrakendust ja integratsioonitestide klientrakendust teenindav X-tee turvaserveri virtuaalmasin (aj09.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee turvaserveri tarkvara
* Test andmekogu teenindav X-tee turvaserveri virtuaalmasin (aj10.ci.kit):
 * Opsüsteem: Ubuntu 14.04 LTS
 * X-tee turvaserveri tarkvara

## Rollid

* Täitja arendaja: Arendab ühiktestid, viib läbi ühiktestimise.
* Täitja projektijuht: Loob testskriptid ja testrakenduse, häälestab CI keskkonna, koostab testiplaani, käivitab testid ja dokumenteerib tulemused.
* Tellija projektijuht: Korraldab vastuvõtutestimise ja testitulemuste ülevaatamise.
* Tellija süsteemiadministraator: Häälestab testimiseks vajalikud serverid ja süsteemitarkvara.
* Tellija testijuht: kooskõlastab testistrateegia ja testiplaani.
