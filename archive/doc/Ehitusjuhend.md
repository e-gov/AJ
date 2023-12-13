Tarkvara ehitusjuhend
=====================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL Regionaalarengu Fond](img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide | Esimene versioon

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sihtrühm](#sihtr%C3%BChm)
  * [Ehitamise sisendid](#ehitamise-sisendid)
  * [Ehituskeskkonna ettevalmistamine](#ehituskeskkonna-ettevalmistamine)
  * [Ehitamine](#ehitamine)
    * [1\. Lähtekoodi allalaadimine](#1-l%C3%A4htekoodi-allalaadimine)
    * [2\. Tarkvara kompileerimine ja ühiktestide käivitamine](#2-tarkvara-kompileerimine-ja-%C3%BChiktestide-k%C3%A4ivitamine)
    * [3\. Tarkvara Ubuntu paigalduspakettide koostamine](#3-tarkvara-ubuntu-paigalduspakettide-koostamine)
    * [4\. Tarkvara ZIP paigalduspakettide koostamine](#4-tarkvara-zip-paigalduspakettide-koostamine)
  * [Ehitamise väljund](#ehitamise-v%C3%A4ljund)
  * [Paigaldamine](#paigaldamine)


## Sihtrühm

Ehitusjuhendi sihtrühmaks on:

* RIA infraosakonna töötajad, kes ehitavad ja koostavad paigalduspaketid publitseerimiseks.
* Andmejälgija tarkvara kasutav andmekogu omanik, kes soovib seda tarkvara ehitada oma tarbeks.

## Ehitamise sisendid

Tarkvara lähtekood on kättesaadav avalikus GitHub repositooriumis https://github.com/e-gov/AJ.

Tarkvara ehitamiseks on vajalik aru saada, millises konfiguratsioonis soovitakse seda paigaldada. Paigaldusvariante on kaks:

* Kõigi tarkvarakomponentide paigaldamine X-tee turvaserverit sisaldavasse serverisse. Sellisel juhul on võimalik kasutada paigaldamisel tarkvara ehitamise väljundina tekkivaid operatsioonisüsteemi Ubuntu 14.04 LTS jaoks loodud paigalduspakette.
* Muud konfiguratsioonid. Sellisel juhul tuleb kasutada paigaldamisel tarkvara ehitamise väljundina tekkivaid WAR-formaadis Java rakendusi. Kui rakendust ehitatakse [tarkvara rakendusjuhises](Rakendusjuhend.md) toetatud konfiguratsiooni jaoks, siis on kõigi seal loetletud konfiguratsioonide korral rakenduse ehitamine sama.

## Ehituskeskkonna ettevalmistamine

Tarkvara ehitamine peab toimuma Ubuntu 14.04 LTS operatsioonisüsteemiga arvutis. Arvutisse peab olema paigaldatud täiendavalt järgmised paketid:

* openjdk-7-jdk
* dh-make
* devscripts
* zip
* git

Kõik muud vajalikud ehitamise abivahendid paigaldab tarkvara ehitusskript automaatselt.

## Ehitamine

Tarkvara ehitamine seisneb järgmistes sammudes (sammud tuleb teostada loetletud järjekorras):

### 1. Lähtekoodi allalaadimine

Avada terminali käsurida ning liikuda kataloogi, mille all soovitakse tarkvara ehitamine läbi viia. Anda seal käsk:

```sh
git clone git@github.com:e-gov/AJ.git
```

Käsu eduka täitmise tulemusena tekib alamkataloog "AJ", mille sees asub tarkvara lähtekood. Kõikide edasiste sammude sooritamiseks tuleb liikuda selle kataloogi sisse.

Juhul, kui soovitakse ehitada tarkvara arendusversiooni (pole soovitatav, kuna pole garanteeritud, et see õnnestub), tuleb anda eelpool toodud käsu
asemel käsk:

```sh
git clone -b develop git@github.com:e-gov/AJ.git
```

### 2. Tarkvara kompileerimine ja ühiktestide käivitamine

Tarkvara kompileerimine ning ühiktestide käivitamine toimub ühe sammuna järgmise käsuga:

```sh
./gradlew build
```

Selle käsu esmakordsel käivitamisel laetakse automaatselt alla erinevad abistavad moodulid, mistõttu käsu täitmine võtab aega.

Käsu täitmisel väljastab skript mitmesuguseid teateid. Edukat kompileerimise ja ühiktestide käivitamise korral kuvatakse kasutajale kõige lõpus teade (teatele järgneb 
info käsu täitmiseks kulunud aja kohta ning potentsiaalselt ka gradle tarkvara poolt antud soovitus kiirema kompileerimise kohta):

```
BUILD SUCCESSFUL
```

Juhul, kui käsu täitmisel juhtus mingi viga, siis kuvatakse teade:

```
BUILD FAILED
```


### 3. Tarkvara Ubuntu paigalduspakettide koostamine

Tarkvara Ubuntu paigalduspakettide koostamine toimub käsuga:

```sh
./gradlew buildDeb
```

Käsu eduka või mitteeduka täitmise tunnused on samad eelmises punktis kirjeldatule.

NB! Antud paigalduspaketid on mõteldud ainult X-tee turvaserverit sisaldava serveri peal paigaldamiseks. 
Nende paigaldamise õnnestumise eeltingimuseks on vastavate X-tee turvaserveri pakettide paigaldamine.

### 4. Tarkvara ZIP paigalduspakettide koostamine

Tarkvara ZIP paigalduspakettide koostamine toimub käsuga:

```sh
./gradlew buildZip
```

Käsu eduka või mitteeduka täitmise tunnused on samad eelmises punktis kirjeldatule.

ZIP paigalduspakette kasutatakse komponentide Java rakendusserverile paigaldamiseks.

## Ehitamise väljund

Peale tarkvara edukat ehitamist tekivad järgmised väljundid (väljundi nimes esinev {v} tähistab tarkvara versiooninumbrit):

| Väljund                   | Asukohakataloog   | Kirjeldus                                                 |
|---------------------------|-------------------|-----------------------------------------------------------|
| dumonitor-filter_{v}.deb  | filter/build/deb  | Eraldusfiltri komponendi Ubuntu paigalduspakett           |
| dumonitor-storage_{v}.deb | storage/build/deb | Andmesalvestaja komponendi Ubuntu paigalduspakett         |
| dumonitor-query_{v}.deb   | query/build/deb   | Esitamise testrakenduse komponendi Ubuntu paigalduspakett |
| dumonitor-filter-{v}.zip  | filter/build/zip  | Eraldusfiltri komponendi Java paigalduspakett             |
| dumonitor-storage-{v}.zip | storage/build/zip | Andmesalvestaja komponendi Java paigalduspakett           |
| dumonitor-query-{v}.zip   | query/build/zip   | Esitamise testrakenduse komponendi Java paigalduspakett   |
| dumonitor-xforms.xml      | xforms            | X-Forms komponent MISP2 ja eesti.ee keskkonna jaoks       |

## Paigaldamine

Tarkvara ehitamise väljundite paigaldamine on kirjeldatud [Tarkvara rakendusjuhendis](Rakendusjuhend.md)
