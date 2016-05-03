Tarkvara rakendusjuhend
=======================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](img/EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                    | Märkused
|----------|------------|--------------------------|----------------------------------------------
| 1.0      | 09.05.2016 | Tanel Tammet, Ivo Mehide | Esimene versioon

## Sisukord

Rakendusjuhend:

1. kirjeldab samm-sammuliselt AJ kasutuselevõtmise protsessi asutuses, nii ärilisest kui ka tehnilisest vaatepunktist;
2. annab andmekogu omanikule soovitused AJ komponentide valikuks ja kasutamiseks, sh selle kohta, milliseid andmeedastamisi ja –kasutamisi logida;
3. võtab soovitusi andes arvesse andmekogu suurust jm olulisi parameetreid;
4. annab ammendavad, samm-sammulised juhised AJ kõigi komponentide paigaldamiseks ja konfigureerimiseks ja ka eemaldamiseks;
5. kirjeldab AJ haldamistoiminguid;
6. tõstatab infoturbe probleeme ja suunab turvaanalüüsi dokumenti lugema;
7. on hõlpsasti kasutatava struktuuriga ja professionaalselt vormistatud

## Tarkvara paigaldamine

### Üldised nõuded

Tarkvara on mõteldud töötama Ubuntu 14.04 LTS operatsioonisüsteemi käitavas serveris. 
X-tee turvaserverisse paigaldamise korral on nõutav X-tee v6 põhise turvaserveri kasutamine. X-tee turvaserverist 
väljapoole paigaldamisel on nõutav rakendusserveri Jetty v8 kasutamine.

### Paigaldamise ülevaade

Tarkvara paigaldamine seisneb selle üksikute komponentide paigaldamises. Sõltuvalt soovist on võimalik:

1. Paigaldada kõik komponendid
2. Paigaldada ainult üks komponent
3. Paigaldada ükskõik millised kaks komponenti kolmest

Järgnevas toodud kirjelduses on iga komponendi paigaldamist käsitletud eraldi ning iga komponendi all on toodud ka 
kirjeldused, kuidas toimub paigaldamine kõigil eelpool mainitud juhtudel.

Tarkvara paigalduspaketid on alla leitavad: https://github.com/e-gov/AJ/releases

Pakettidest tuleks valida kõige viimasemad versioonid. 

Tarkvara komponentide paigaldamine on soovitatav viia läbi allpool toodud järjestuses.

### Andmesalvestaja komponendi paigaldamine

#### Komponendi paigaldamine X-tee turvaserverisse

X-tee turvaserverisse paigaldamisel tuleb kasutada paigalduspaketti "dumonitor-storage_{v}.deb", kus {v} tähistab versiooninumbrit.

Paigaldamiseks tuleb pakett laadida X-tee turvaserverisse mingisse kataloogi ning seal kataloogis anda käsurealt käsklus (käsus tuleb {v} 
asemel kirjutada allalaadidud paketi versiooninumber):

```sh
sudo dpkg -i dumonitor-storage_{v}.deb
```

Komponendi häälestamine on kirjeldatud punktis "Komponentide häälestamine". Häälestamisel tuleb arvestada järgmise paigaldusinfoga 
({turvaserver} tähistab turvaserveri DNS-nime või IP-aadressi, mille abil tema poole pöördutakse asutuse sisevõrgust):

| Infoelement                                      | Väärtus                                                |
|--------------------------------------------------|--------------------------------------------------------|
| Andmesalvestaja baas-URL HTTP kasutamise korral  | http://{turvaserver}:4080/dumonitor-storage            |
| Andmesalvestaja baas-URL HTTPS kasutamise korral | https://{turvaserver}:4443/dumonitor-storage           |
| Konfiguratsioonifaili asukoht                    | /usr/share/xroad/jetty9/resources/dumonitor.properties |

#### Komponendi paigaldamine mujale

Kui komponenti ei paigaldata X-tee turvaserverisse, tuleb kasutada paigalduspaketti "dumonitor-storage-{v}.zip".

Paigalduspakett tuleb pakkida lahti. Tekkinud kataloogist tuleb kopeerida failid järgmiselt:

| Fail                  | Asukoht                                                                |
|-----------------------|------------------------------------------------------------------------|
| dumonitor-storage.war | Java rakendusserveri webapps kataloogi "/var/lib/jetty8/webapps"       |
| dumonitor.properties  | Java rakendusserveri classpath kataloogi "/usr/share/jetty8/resources" |

#### Andmebaasi skeemi tekitamine

Andmesalvestaja komponent vajab oma tööks andmebaasimootorit. Toetatud on PostgreSQL andmebaasimootor v9.3. Andmebaasimootor võib asuda
andmesalvestaja komponendiga samas masinas või ka eraldi masinas. Eelmises punktis mainitud paigalduspaketi lahtipakkimisel tekkinud kataloogi
alla tekkis ka alamkataloog "database", milles on andmebaasi tekitamiseks vajalikud skriptid. 
Need tuleb andmebaasimootorit jooksutavas arvutis postgre kasutajaõigustes käivitada järgmiselt:

```sh
sudo -u postgres sh -c "./createdb.sh ; ./createschema.sh ; ./privileges.sh"
```

### Eraldusfiltri komponendi paigaldamine

#### Komponendi paigaldamine X-tee turvaserverisse

X-tee turvaserverisse paigaldamisel tuleb kasutada paigalduspaketti "dumonitor-filter_{v}.deb", kus {v} tähistab versiooninumbrit.

Paigaldamiseks tuleb pakett laadida X-tee turvaserverisse mingisse kataloogi ning seal kataloogis anda käsurealt käsklus (käsus tuleb {v} 
asemel kirjutada allalaadidud paketi versiooninumber):

```sh
sudo dpkg -i dumonitor-filter_{v}.deb
```

Komponendi häälestamine on kirjeldatud punktis "Komponentide häälestamine". Häälestamisel tuleb arvestada järgmise paigaldusinfoga 
({turvaserver} tähistab turvaserveri DNS-nime või IP-aadressi, mille abil tema poole pöördutakse asutuse sisevõrgust):

| Infoelement                                         | Väärtus                                                |
|-----------------------------------------------------|--------------------------------------------------------|
| Eraldusfiltri baas-URL                              | http://{turvaserver}:4080/dumonitor-filter             |
| Eraldusfiltri baas-URL (HTTPS ühenduse kasutamisel) | https://{turvaserver}:4443/dumonitor-filter            |
| Konfiguratsioonifaili asukoht                       | /usr/share/xroad/jetty9/resources/dumonitor.properties |

#### Komponendi paigaldamine mujale

Kui komponenti ei paigaldata X-tee turvaserverisse, tuleb kasutada paigalduspaketti "dumonitor-filter-{v}.zip".

Paigalduspakett tuleb pakkida lahti. Tekkinud kataloogist tuleb kopeerida failid järgmiselt:

| Fail                 | Asukoht                                                                |
|----------------------|------------------------------------------------------------------------|
| dumonitor-filter.war | Java rakendusserveri webapps kataloogi "/var/lib/jetty8/webapps"       |
| dumonitor.properties | Java rakendusserveri classpath kataloogi "/usr/share/jetty8/resources" |

### Esitamise testrakenduse paigaldamine

#### Komponendi paigaldamine X-tee turvaserverisse

X-tee turvaserverisse paigaldamisel tuleb kasutada paigalduspaketti "dumonitor-query_{v}.deb", kus {v} tähistab versiooninumbrit.

Paigaldamiseks tuleb pakett laadida X-tee turvaserverisse mingisse kataloogi ning seal kataloogis anda käsurealt käsklus (käsus tuleb {v} 
asemel kirjutada allalaadidud paketi versiooninumber):

```sh
sudo dpkg -i dumonitor-query_{v}.deb
```

Komponendi häälestamine on kirjeldatud punktis "Komponentide häälestamine". Häälestamisel tuleb arvestada järgmise paigaldusinfoga 
({turvaserver} tähistab turvaserveri DNS-nime või IP-aadressi, mille abil tema poole pöördutakse asutuse sisevõrgust):

| Infoelement                                    | Väärtus                                    |
|------------------------------------------------|--------------------------------------------|
| Eraldusfiltri baas-URL HTTP kasutamise korra   | http://{turvaserver}:4080/dumonitor-query  |
| Eraldusfiltri baas-URL HTTPS kasutamise korral | https://{turvaserver}:4443/dumonitor-query |
| Konfiguratsioonifaili asukoht                  | /usr/share/xroad/jetty9/resources          |


#### Komponendi paigaldamine mujale

Kui komponenti ei paigaldata X-tee turvaserverisse, tuleb kasutada paigalduspaketti "dumonitor-query-{v}.zip".

Paigalduspakett tuleb pakkida lahti. Tekkinud kataloogist tuleb kopeerida failid järgmiselt:

| Fail                 | Asukoht                                                                |
|----------------------|------------------------------------------------------------------------|
| dumonitor-query.war  | Java rakendusserveri webapps kataloogi "/var/lib/jetty8/webapps"       |
| dumonitor.properties | Java rakendusserveri classpath kataloogi "/usr/share/jetty8/resources" |


### Komponentide häälestamine

Kõik komponendid jagavad omavahel ühist konfiguratsioonifaili "dumonitor.properties". Konfiguratsioonifail
peab asuma Java rakendusserveri globaalsel classpath'il asuvas kataloogis.

#### Edastusfiltri komponendi häälestamine

Konfiguratsioonifailis on kasutusel järgmised parameetrid:

| Parameeter                                         | Algväärtus           | Kirjeldus
|----------------------------------------------------|----------------------|----------------------------------------------------|
| dumonitor.filter.configuration.file                | dumonitor-filter.xml | Eraldusfiltri filtrite konfiguratsioonifaili nimi  |
| dumonitor.filter.turvaserver.url                   | http://localhost/    | Turvaserveri URL                                   |
| dumonitor.filter.andmekogu.url                     |                      | Andmekogu adapterserveri URL                       |
| dumonitor.filter.logger.rest.url                   |                      | Andmesalvestaja komponendi REST logimisteenuse URL |
| dumonitor.filter.executor.thread.pool.size         | 0                    | Filtri poolt paralleelselt töös hoitavate päringuid töötlevate thread'ide arv (0 tähendab, et toimub threadide dünaamiline haldamine) |
| dumonitor.filter.executor.queue.capacity           | 2147483647           | Filtri poolt maksimaalselt hõivatav päringute järjekorra suurus baitides |
| dumonitor.filter.executor.shutdown.timeout.seconds | 10                   | Filtri komponendi poolt shutdown käsu saamisel oodatav ajavahemik parallelselt töötavate päringute töötlemise lõpetamiseks |
| dumonitor.blacklist                                |                      | Nimekiri X-tee osapoolte koodidest, kelle päringuid filtri komponent ei logi |

Lisaks on võimalik määrata järgmisi süsteemseid parameetreid HTTPS päringute juhtimiseks, kui andmekogu ja/või turvaserveri URL
on HTTPS protokolli põhine (nende tähenduse kohta vt vastavat Java dokumentatsiooni):

* javax.net.ssl.keyStoreType
* javax.net.ssl.trustStoreType
* javax.net.ssl.keyStore
* javax.net.ssl.trustStore
* javax.net.ssl.keyStorePassword
* javax.net.ssl.trustStorePassword

#### Andmesalvestaja komponendi häälestamine

Konfiguratsioonifailis on kasutusel järgmised parameetrid:

| Parameeter                               | Algväärtus                          | Kirjeldus                                 |
|------------------------------------------|-------------------------------------|-------------------------------------------|
| dumonitor.storage.xroad.producerns       | http://dumonitor.x-road.eu/producer | X-tee teenuses kasutatav päringu nimeruum |
| dumonitor.storage.database.jndi          |                                     | Logi andmebaasi ühenduse JNDI string. Kui on olemas, siis muid andmebaasiparameetreid ei kasutata |
| dumonitor.storage.database.connectstring |                                     | Logi andmebaasi JDBC sisselogimise string |
| dumonitor.storage.database.user          | dumonitor_app                       | Logi andmebaasi kasutajatunnus            |
| dumonitor.storage.database.password      | aj22p                               | Logi andmebaasi kasutaja parool           |

#### Esitamise testrakenduse häälestamine

| Parameeter                | Algväärtus        | Kirjeldus              |
|---------------------------|-------------------|------------------------|
| dumonitor.query.xroad.url | http://localhost/ | X-tee turvaserveri URL |

