Tarkvara paigaldamise juhend
============================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](img/EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                    | Märkused
|----------|------------|--------------------------|----------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide, Tanel Tammet | Esimene versioon

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sissejuhatus](#sissejuhatus)
  * [Andmejälgija tarkvara paigaldamine](#andmej%C3%A4lgija-tarkvara-paigaldamine)
    * [Üldised nõuded](#%C3%9Cldised-n%C3%B5uded)
    * [Rakenduse paigaldamine X\-tee turvaserverisse](#rakenduse-paigaldamine-x-tee-turvaserverisse)
    * [Rakenduse paigaldamine Java rakendusserverisse](#rakenduse-paigaldamine-java-rakendusserverisse)
      * [Andmebaasi skeemi tekitamine](#andmebaasi-skeemi-tekitamine)
    * [MISP2 X\-forms faili paigaldamine](#misp2-x-forms-faili-paigaldamine)
    * [Rakenduse eemaldamine](#rakenduse-eemaldamine)
      * [X\-tee turvaserverisse paigaldatud rakenduse komponentide eemaldamine](#x-tee-turvaserverisse-paigaldatud-rakenduse-komponentide-eemaldamine)
    * [Java rakendusserverisse paigaldatud rakenduse komponentide eemaldamine](#java-rakendusserverisse-paigaldatud-rakenduse-komponentide-eemaldamine)
  * [Häälestamine](#h%C3%A4%C3%A4lestamine)
    * [Andmesalvestaja komponendi häälestamine](#andmesalvestaja-komponendi-h%C3%A4%C3%A4lestamine)
    * [Eraldusfiltri komponendi häälestamine](#eraldusfiltri-komponendi-h%C3%A4%C3%A4lestamine)
    * [Esitamise testrakenduse häälestamine](#esitamise-testrakenduse-h%C3%A4%C3%A4lestamine)


## Sissejuhatus

Antud dokument on [rakendusjuhendi](Rakendusjuhend.md) üks osa ning annab ammendavad ja samm-sammulised juhised andmejälgija tarkvara kõigi komponentide paigaldamiseks, konfigureerimiseks, eemaldamiseks. See on vormistatud eraldiseisva dokumendina hoidmaks rakendusjuhendi põhidokumendi mahtu madalal ja läbi selle tagades dokumentide parema loetavuse.

## Andmejälgija tarkvara paigaldamine

### Üldised nõuded

Tarkvara on võimalik paigaldada järgmistel võimalikel viisidel:

* X-tee turvaserverisse
* Java rakendusserverisse

X-tee turvaserverisse paigaldamisel eeldatakse, et turvaserver töötab Ubuntu 14.04 LTS operatsioonisüsteemi käitavas serveris.

Java rakendusserverisse paigaldamisel on tagaatud rakenduse töötamine Ubuntu 14.04 LTS operatsioonisüsteemi käitavas serveris selle koosseisu kuuluva Jetty 8 rakendusserveriga. Tarkvara on võimalik kasutada ka teistes ühilduvates Java rakendusserverites (näiteks Tomcat), kuid nende antud juhendis
nende kasutamise üksikasju ei kajastata.

### Rakenduse paigaldamine X-tee turvaserverisse

X-tee turvaserverisse paigaldamisel tuleb kasutada viimaseid DEB paigalduspakette, mis on kättesaadavad lingilt https://github.com/e-gov/AJ/releases.

Paigaldaja peab otsustama, millised andmejälgija komponendid ta turvaserverisse paigaldab. Valitud komponentide DEB failid on vaja laadida alla ning paigutada turvaserveri mingisse kataloogi.
 
Tarkvarakomponendi paigaldmiseks tuleb avada turvaserveris käsurida, liikuda kataloogi, kuhu on tarkvara alla laetud, ning anda käsk:

```sh
sudo dpkg -i {paketinimi}
```

Kus {paketinimi} on allalaetud DEB faili nimi.

Paketid paigalduvad automaatselt ilma täiendavaid küsimusi paigalduse käigus esitamata. Kõik paketid eeldavad, et eelnevalt on turvaserverisse paigaldatud paketid "nginx-common" ja "xroad-jetty9" (turvaserveri korrektse paigalduse korral peavad need paketid olema juba serveris peal).

Peale pakettide paigaldamist tuleb komponendid häälestada, täpsed juhised selleks on toodud punktis "Häälestamine". 

Alljärgnevas on toodud paketi paigaldusest sõltuvate konfiguratsiooni elementide väärtused. Seal {turvaserver} tähistab turvaserveri DNS-nime või IP-aadressi, mille abil tema poole pöördutakse asutuse sisevõrgust.

Andmesalvestaja komponent:

| Konfiguratsiooni element                    | Väärtus                                                |
|---------------------------------------------|--------------------------------------------------------|
| Andmesalvestaja URL HTTP kasutamise korral  | http://{turvaserver}:4080/dumonitor-storage            |
| Andmesalvestaja URL HTTPS kasutamise korral | https://{turvaserver}:4443/dumonitor-storage           |
| Konfiguratsioonifaili asukoht               | /usr/share/xroad/jetty9/resources/dumonitor.properties |
| Andmebaasi skriptide kataloog               | /usr/share/dumonitor/storage/database                  |

Andmesalvestaja andmebaas:

| Konfiguratsiooni element   | Väärtus       |
|----------------------------|---------------|
| Andmebaasi nimi            | dumonitor     |
| Andmebaasi omanik          | dumonitor     |
| Andmebaasi omaniku parool  | aj22p         |
| Andmebaasi kasutaja        | dumonitor_app |
| Andmebaasi kasutaja parool | aj22p         |


Eraldusfiltri komponent:

| Konfiguratsiooni element                      | Väärtus                                                |
|-----------------------------------------------|--------------------------------------------------------|
| Eraldusfiltri URL HTTP kasutamise korral      | http://{turvaserver}:4080/dumonitor-filter             |
| Eraldusfiltri URL HTTPS kasutamise korral     | https://{turvaserver}:4443/dumonitor-filter            |
| Konfiguratsioonifaili asukoht                 | /usr/share/xroad/jetty9/resources/dumonitor.properties |
| Eraldusfiltri filtrikirjelduste faili asukoht | /usr/share/xroad/jetty9/resources/dumonitor-filter.xml |

Esitamise testrakenduse komponent:

| Konfiguratsiooni element                            | Väärtus                                                |
|-----------------------------------------------------|--------------------------------------------------------|
| Esitamise testrakenduse URL HTTP kasutamise korral  | http://{turvaserver}:4080/dumonitor-query              |
| Esitamise testrakenduse URL HTTPS kasutamise korral | https://{turvaserver}:4443/dumonitor-query             |
| Konfiguratsioonifaili asukoht                       | /usr/share/xroad/jetty9/resources/dumonitor.properties |

### Rakenduse paigaldamine Java rakendusserverisse

Rakenduse poolt on toetatud selle paigaldamine Ubuntu 14.04 LTS operatsioonisüsteemis töötavasse Jetty 8 rakendusserverisse. Võimalik on ka kasutada
teisi rakendusservereid (näiteks Tomcat), nende korral tuleb järgida vastava rakendusserveri tootja poolt antud juhiseid WAR rakenduste
paigaldamise ja häälestamise kohta. Käesolevas paigaldusjuhendis on eeldatud, et rakendusserver on juba paigaldatud ja häälestatud.

Java rakendusserverisse paigaldamiseks tuleb kasutada rakenduse komponentide viimaseid ZIP paigalduspakette, 
mis on kättesaadavad lingilt https://github.com/e-gov/AJ/releases.

Paigaldaja peab otsustama, millised andmejälgija komponendid ta Java rakendusserverisse paigaldab. Igat komponenti on ka võimalik 
paigaldada teistest sõltumatult töötavasse Java rakendusserverisse.

Komponendi paigaldamiseks tuleb esmalt allalaetud ZIP fail lahti pakkida. ZIP fail tekitab lahtipakkides vastava komponendi nimega alamkataloogi. Selles
alamkataloogis leiduv "war" laiendiga fail on komponendi Java rakendus ning tuleb paigaldada rakendusserveri "webapps" kataloogi. Jetty 8 korral
tuleb selleks see fail kopeerida kataloogi "/var/lib/jetty8/webapps". Lisaks leidub samas ka fail "dumonitor.properties". See fail tuleb kopeerida kataloogi "/usr/share/jetty8/resources". Kõikdel andmejälgija komponentidel on see konfiguratsioonifail täpselt ühe ja sama sisuga. Kui serverisse paigaldatakse
mitu andmejälgija komponenti korraga, siis piisab ainult ühega kaasa tulnud faili kopeerimisest.

Eraldusfiltri komponendiga on täiendavalt kaasas fail "dumonitor-filter.xml". See fail tuleb samuti kopeerida kataloogi "/usr/share/jetty8/resources".

Andmesalvestaja komponendil on täiendavalt kaasas kataloog "database", kus leiduvad skriptid andmesalvestaja andmebaasi tekitamiseks. Nende skriptide kasutamine on täpsemalt kirjeldatud punktis "Häälestamine".

Alljärgnevas on toodud paketi paigaldusest sõltuvate konfiguratsiooni elementide väärtused. Seal {server} tähistab turvaserveri DNS-nime või IP-aadressi, mille abil tema poole pöördutakse asutuse sisevõrgust.

Andmesalvestaja komponent:

| Konfiguratsiooni element      | Väärtus                                          |
|-------------------------------|--------------------------------------------------|
| Andmesalvestaja URL           | http://{server}:8080/dumonitor-storage           |
| Konfiguratsioonifaili asukoht | /usr/share/jetty8/resources/dumonitor.properties |
| Andmebaasi skriptide kataloog | /usr/share/dumonitor/storage/database            |

Eraldusfiltri komponent:

| Konfiguratsiooni element                      | Väärtus                                          |
|-----------------------------------------------|--------------------------------------------------|
| Eraldusfiltri URL                             | http://{server}:8080/dumonitor-filter            |
| Konfiguratsioonifaili asukoht                 | /usr/share/jetty8/resources/dumonitor.properties |
| Eraldusfiltri filtrikirjelduste faili asukoht | /usr/share/jetty8/resources/dumonitor-filter.xml |

Esitamise testrakenduse komponent:

| Konfiguratsiooni element      | Väärtus                                          |
|-------------------------------|--------------------------------------------------|
| Esitamise testrakenduse URL   | http://{server}:8080/dumonitor-query             |
| Konfiguratsioonifaili asukoht | /usr/share/jetty8/resources/dumonitor.properties |

#### Andmebaasi skeemi tekitamine

Andmesalvestaja komponent vajab oma tööks andmebaasimootorit. Toetatud on PostgreSQL andmebaasimootor v9.3. Andmebaasimootor võib asuda
andmesalvestaja komponendiga samas masinas või ka eraldi masinas. Andmesalvestaja ZIP paigalduspaketis on kaasas alamkataloog "database", milles on andmebaasi tekitamiseks vajalikud skriptid. 
Need tuleb andmebaasimootorit jooksutavas arvutis kasutaja "postgre" õigustes käivitada järgmiselt 
(andmebaasimootor peab käsu andmeisel töötama):

```sh
sudo -u postgres sh -c "./createdb.sh ; ./createschema.sh ; ./privileges.sh"
```

Alljärgnevas on toodud paigaldusest sõltuvate andmesalvestaja andmebaasi konfiguratsiooni elementide väärtused:

| Konfiguratsiooni element   | Väärtus       |
|----------------------------|---------------|
| Andmebaasi nimi            | dumonitor     |
| Andmebaasi omanik          | dumonitor     |
| Andmebaasi omaniku parool  | aj22p         |
| Andmebaasi kasutaja        | dumonitor_app |
| Andmebaasi kasutaja parool | aj22p         |

Andmebaasi kasutajate paroolid on toodangulahenduse korral mõistlik ära muuta.

### MISP2 X-forms faili paigaldamine

Andmesalvestaja komponendiga on kaasas ka MISP2 X-forms fail, mida saab kasutada nii MISP2 tarkvara kui eesti.ee keskkonna jaoks. Fail on allalaetav
peale andmesalvesteaja komponendi käivitamist lingilt "{andmesalvestajaUrl}/dumonitor-xforms.xml", kus {andmesalvestajaUrl} tähistab konfigurastsiooni elemendi "Andmesalvestaja URL" väärtust.

Antud fail tuleb paigaldada MISP2 tarkvarasse vastavalt selle tarkvara kohta tootja poolt antud juhistele.

### Rakenduse eemaldamine

#### X-tee turvaserverisse paigaldatud rakenduse komponentide eemaldamine

Komponendid saab eemaldada käsuga:

```sh
sudo apt-get remove {paketinimi}
```

Kus {paketinimi} on paigaldatud paketi nimi (ilma versiooninumbrita nime lõpus). Kuna komponendid jagavad omavahel konfiguratsioonifaile, on vaja juhul,
kui eemaldatakse kõik paketid, kustutada veel täiendavalt käsitsi ära järgmised konfiguratsioonifailid:

* /usr/share/xroad/jetty8/resources/dumonitor*
* /etc/nginx/sites-enabled/dumonitor

Lisaks teostab komponentide paigaldusskript järgmisi muudatusi teiste pakettide konfiguratsioonifailides, mis tuleb vajadusel muuta tagasi:

* Faili "/etc/xroad/jetty/start.ini" lõppu lisati rida "--module=resources". Kui seda jetty moodulit teised rakendused ei kasuta, võib selle rea eemaldada.
* Failis "/etc/xroad/jetty/jetty-public.xml" muudeti elemendi "//Set[@name='extractWars']" väärtus "false" pealt "true" peale. Vajdusel tuleb see tagasi väärtustada.
* Kui paigaldati ka andmesalvestaja komponent, siis on serveris töötavasse PostgreSQL andmebaasimootorisse tekitatud juurde andmebaas nimega "dumonitor" ning kasutajatunnused nimedega "dumonitor" ja "dumonitor_app". Need tuleb vajaduse möödudes eemaldada.

Muudatuste jõustamiseks tuleb teha restart ka "jetty" ja "nginx" teenustele.

Andmebaasi eemaldamine andmebaasimootorist toimub andmebaasi administreerimise juhendis ettenähtud viisil.

### Java rakendusserverisse paigaldatud rakenduse komponentide eemaldamine

Komponentide paigaldamise käigus tekitatud failid tuleb eemaldada. Sõltuvalt rakendusserveri konfiguratsioonist võib olla vajalik ka rakendusserverile
restardi tegemine.

## Häälestamine

Kõigi komponentide üldine häälestamine käib ühise konfugiratsioonifaili "dumonitor.properties" muutmise teel. Faili asukoht sõltub komponendi paigaldamise viisist, vt eespool toodud paigaldamise juhiseid, faili asukohta märgib seal konfiguratsiooni element "Konfiguratsioonifaili asukoht". Faili näol on tegemist standardse Java properties-failiga, kus sübol '#' tähendab kommentaari algust ning igal real on kirjas parameetrid kujul "{param}={väärtus}", kus {param} tähistab parameetri nime ja {väärtus} tähistab parameetrile omistatavat väärtust. Parameetri nime ja väärtust eraldab omavahel sümbol '=', selle sümboli ümber olevaid tühikuid ignoreeritakse.

Lisaks on eraldusfiltri komponendil olemas ka eraldi konfiguratsioonifail "dumonitor-filter.xml", mille abil kirjeldatakse päringute sisu baasilt logimist juhtivad filtrid.

### Andmesalvestaja komponendi häälestamine

Andmesalvestaja konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Andmesalvestaja X-tee teenuse nimeruum. Kui on vaja kasutada vaikeväärtusest erinevat XML nimeruumi.
* Andmebaasi ühenduse JNDI nimi. Juhul, kui soovitakse andmebaasi ühendus kirjeldata JNDI nime kaudu (vt täpsemalt https://wiki.eclipse.org/Jetty/Feature/JNDI). 
* Andmebaasi ühendusstring. Juhul, kui soovitakse andmebaasi ühendus luua vahetult konfiguratsioonifailis kasutajatunnuse ja parooli ära näitamisega.


Andmesalvestaja tööd juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                               | Vaikeväärtus                        | Väärtustamine                                  |
|------------------------------------------|-------------------------------------|------------------------------------------------|
| dumonitor.storage.xroad.producerns       | http://dumonitor.x-road.eu/producer | Andmesalvestaja X-tee teenuse nimeruum |
| dumonitor.storage.database.jndi          |                                     | Andmebaasi ühenduse JNDI nimi, täita juhul, kui soovitakse andmebaasiühendust JNDI kaudu |
| dumonitor.storage.database.connectstring |                                     | Andmebaasi ühendusstring, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli äranäitamisega |
| dumonitor.storage.database.user          |                                     | Andmebaasi kasutaja tunnus, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli äranäitamisega |
| dumonitor.storage.database.password      |                                     | Andmebaasi kasutaja parool, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli äranäitamisega |

Juhul, kui konfiguratsioonifailis näidatakse ära andmebaasi kasutaja parool, on vaja hoolitseda selle faili salajasuse eest - selleks tuleb faili lugemisõigus jätta alles ainult Jetty rakendusserveri kasutajatunnusele.

Peale andmesalvestaja häälestamist ja käivitamist tuleb andmesalvestaja X-tee teenus ära kirjeldada ka X-tee turvaserveris. Selleks on kõige lihtsam lisada X-tee turvaserveri konfiguratsiooni juurde täiendav WSDL, näidates WSDL faili asukohaks 
"{andmesalvestajaUrl}/dumonitor.wsdl", kus {andmesalvestajaUrl} tähistab konfiguratsiooni elementi "Andmesalvestaja URL". Seejärel tuleb turvaserveris ära muuta selle WSDL all teenuse "findUsage" URL, muutes algses URLis serveri nime "TURVASERVER" ümber selle serveri nimeks,
kus andmesalvestaja teenus paikneb (lisades vajadusel ka pordi numbri ja/või muutes protokolli "http" pealt "https" peale).

### Eraldusfiltri komponendi häälestamine

Eraldusfiltri konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Turvaserveri URL. URL, mille kaudu tehakse andmekogu poolt pöördumised teiste X-tee liikmete teenuste poole.
* Andmekogu URL. URL, mille kaudu pääseb ligi andmekogu teenustele.
* Eraldusfiltri paralleelsete lõimede arv. Kui see arv on 0, siis haldab eraldusfilter ise dünaamiliselt thread'ide arvu - tekitab neid vastavalt koormusele juurde või võtab vähemaks. Kui see arv erineb nullist, siis käivitatakse näidatud arv threade päringute töötlemiseks. Antud parameeter reguleerib päringute filtris töötlemist, mitte HTTP päringute vastuvõtmist ja edastamist. Viimast reguleeritakse rakendusserveri vastavate parameetrite abil.
* Eraldusfiltri sõnumite järjekorra suurus. Maksimaalne sõnumite arv, mis võib olla sõnumite lõimede poolt töötlemise ootel.
Kui ootel sõnumite arv läheb sellest numbrist suuremaks, siis jätab eraldusfilter seda arvu ületavad sõnumid filtrites rakendamata (kuid vahendab need endistviisi edasi teisele osapoolele) ning edastab rakendusserveri logisse vastava veateate.
* Must nimekiri. X-tee osapoolte koodid, kellelt saadud ja kellele edastatud sõnumeid läbi filtri töötluse ei lasta (ja jäävad sedasi logimata).

Eraldusfiltri tööd juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                                 | Vaikeväärtus         | Väärtustamine 
|--------------------------------------------|----------------------|----------------------------------------------------|
| dumonitor.filter.configuration.file        | dumonitor-filter.xml | Eraldusfiltri filtrikirjelduste faili asukoht  |
| dumonitor.filter.turvaserver.url           | http://localhost/    | Turvaserveri URL                                   |
| dumonitor.filter.andmekogu.url             |                      | Andmekogu URL                       |
| dumonitor.filter.logger.rest.url           |                      | Andmesalvestaja komponendi REST logimisteenuse URL. Saadakse andmesalvestaja komponendi URLile suffiksi "/rest" liitmisega. |
| dumonitor.filter.executor.thread.pool.size | 0                    | Eraldusfiltri paralleelsete lõimede arv |
| dumonitor.filter.executor.queue.capacity   | 2147483647           | Eraldusfiltri sõnumite järjekorra suurus |
| dumonitor.blacklist                        |                      | Must nimekiri |

Lisaks on võimalik kirjeldada konfiguratsioonifailis järgmisi süsteemseid parameetreid HTTPS päringute juhtimiseks, kui andmekogu ja/või turvaserveri URL on HTTPS protokolli põhine (nende tähenduse kohta vt vastavat Java dokumentatsiooni):

* javax.net.ssl.keyStoreType
* javax.net.ssl.trustStoreType
* javax.net.ssl.keyStore
* javax.net.ssl.trustStore
* javax.net.ssl.keyStorePassword
* javax.net.ssl.trustStorePassword

Rakendus logib oma tegevust [SLF4J](http://www.slf4j.org/) abil ning selle all kasutatakse Apache Log4J 2 raamistikku. Logimise häälestamine tuleb teostada vastavalt Log4J raamistiku tootja poolt antud juhistele (vt http://logging.apache.org/log4j/2.x/manual/).

### Esitamise testrakenduse häälestamine

Esitamise testrakenduse konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Turvaserveri URL. URL, mille kaudu teeb esitamise testrakendus pöördumisi X-tee andmekogude poole.

Esitamise testrakendust juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                | Vaikeväärtus      | Väärtustamine    | 
|---------------------------|-------------------|------------------|
| dumonitor.query.xroad.url | http://localhost/ | Turvaserveri URL |

