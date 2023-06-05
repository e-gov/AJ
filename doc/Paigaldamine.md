Tarkvara paigaldamise juhend
============================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL Regionaalarengu Fond](img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                    | Märkused
|----------|------------|--------------------------|-----------------------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide, Tanel Tammet | Esimene versioon
| 1.1      | 27.12.2016 | Piret Elm, Vitali Stupin | Eraldusfiltris vaikeväärtuste musta nimekirja täpsustamine
| 1.3      | 06.04.2017 | Piret Elm                | Lisatud eeldus andmejälgija kasutamiseks
| 1.4      | 02.05.2017 | Piret Elm                | Lisatud peatükk andmejälgija teenuse avaldamine riigiportaalis
| 1.5      | 08.03.2018 | Vitali Stupin            | Lisatud näidis päringu ja vastuse eristamiseks

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sissejuhatus](#sissejuhatus)
  * [Andmejälgija tarkvara paigaldamine](#andmej%C3%A4lgija-tarkvara-paigaldamine)
    * [Eeldus andmejälgija tarkvara paigaldamiseks](#eeldus-andmejälgija-tarkvara-paigaldamiseks)
    * [Üldised nõuded](#%C3%9Cldised-n%C3%B5uded)
    * [Rakenduse paigaldamine X\-tee turvaserverisse](#rakenduse-paigaldamine-x-tee-turvaserverisse)
    * [Rakenduse paigaldamine Java rakendusserverisse](#rakenduse-paigaldamine-java-rakendusserverisse)
      * [Andmebaasi skeemi tekitamine](#andmebaasi-skeemi-tekitamine)
    * [MISP2 X\-forms faili paigaldamine](#misp2-x-forms-faili-paigaldamine)
    * [Andmejälgija teenuse avaldamine riigiportaalis](#andmejälgija-teenuse-avaldamine-riigiportaalis)
      * [Andmejälgija teenuse testimine riigiportaali partnerite arenduskeskkonnas www.stage.eesti.ee](#andmejälgija-teenuse-testimine-riigiportaali-partnerite-arenduskeskkonnas-wwwstageeestiee)
      * [Andmejälgija teenuse avaldamine riigiportaali toodangukeskkonnas](#andmejälgija-teenuse-avaldamine-riigiportaali-toodangukeskkonnas)
      * [Kasutajatugi, hooldustööd ja katkestused](#kasutajatugi-hooldustööd-ja-katkestused)
      * [Katkestused teenuse töös](#katkestused-teenuse-töös)
      * [Teenuse tööaeg ja katkestuste kestus](#teenuse-tööaeg-ja-katkestuste-kestus)
    * [Rakenduse eemaldamine](#rakenduse-eemaldamine)
      * [X\-tee turvaserverisse paigaldatud rakenduse komponentide eemaldamine](#x-tee-turvaserverisse-paigaldatud-rakenduse-komponentide-eemaldamine)
    * [Java rakendusserverisse paigaldatud rakenduse komponentide eemaldamine](#java-rakendusserverisse-paigaldatud-rakenduse-komponentide-eemaldamine)
  * [Häälestamine](#h%C3%A4%C3%A4lestamine)
    * [Andmesalvestaja komponendi häälestamine](#andmesalvestaja-komponendi-h%C3%A4%C3%A4lestamine)
    * [Eraldusfiltri komponendi filtrite kirjeldamine](#eraldusfiltri-komponendi-filtrite-kirjeldamine)
      * [Nimeruumide kirjeldamine](#nimeruumide-kirjeldamine)
      * [Välistuste kirjeldamine](#v%C3%A4listuste-kirjeldamine)
      * [Vaikeväärtuste kirjeldamine](#vaikev%C3%A4%C3%A4rtuste-kirjeldamine)
      * [Filtrite kirjeldamine](#filtrite-kirjeldamine)
      * [Näidis](#n%C3%A4idis)
    * [Esitamise testrakenduse häälestamine](#esitamise-testrakenduse-h%C3%A4%C3%A4lestamine)
    * [Autentimise häälestamine](#autentimise-h%C3%A4%C3%A4lestamine)


## Sissejuhatus

Antud dokument on [rakendusjuhendi](Rakendusjuhend.md) üks osa ning annab ammendavad ja samm-sammulised juhised andmejälgija tarkvara kõigi komponentide paigaldamiseks, konfigureerimiseks, eemaldamiseks. See on vormistatud eraldiseisva dokumendina hoidmaks rakendusjuhendi põhidokumendi mahtu madalal ja läbi selle tagades dokumentide parema loetavuse.

## Andmejälgija tarkvara paigaldamine

### Eeldus andmejälgija tarkvara paigaldamiseks

Andmejälgija tarkvara on arendatud X-tee versioon 6 turvaserveri jaoks, st eelnevalt peab olema paigaldatud versioon 6 turvaserver, omatakse allkirjastamisseadet ja kasutatakse usaldusteenuse pakkujaid, kirjeldatud on alamsüsteemid läbi mille saab teenuseid kasutada. Vt X-tee kasutamise juhend https://moodle.ria.ee/mod/page/view.php?id=288.

### Üldised nõuded

Tarkvara on võimalik paigaldada järgmistel võimalikel viisidel:

* X-tee turvaserverisse
* Java rakendusserverisse

X-tee turvaserverisse paigaldamisel eeldatakse, et turvaserver töötab Ubuntu 14.04 LTS operatsioonisüsteemi käitavas serveris.

Java rakendusserverisse paigaldamisel on tagatud rakenduse töötamine Ubuntu 14.04 LTS operatsioonisüsteemi käitavas serveris selle koosseisu kuuluva Jetty 8 rakendusserveriga. Tarkvara on võimalik kasutada ka teistes ühilduvates Java rakendusserverites (näiteks Tomcat), kuid nende antud juhendis
nende kasutamise üksikasju ei kajastata.

### Rakenduse paigaldamine X-tee turvaserverisse

X-tee turvaserverisse paigaldamisel tuleb kasutada viimaseid DEB paigalduspakette, mis on kättesaadavad lingilt https://github.com/e-gov/AJ/releases.

Paigaldaja peab otsustama, millised andmejälgija komponendid ta turvaserverisse paigaldab. Valitud komponentide DEB failid on vaja laadida alla ning paigutada turvaserveri mingisse kataloogi.
 
Tarkvarakomponendi paigaldamiseks tuleb avada turvaserveris käsurida, liikuda kataloogi, kuhu on tarkvara alla laetud, ning anda käsk:

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

Komponendi paigaldamiseks tuleb esmalt allalaetud ZIP fail lahti pakkida. ZIP fail tekitab lahti pakkides vastava komponendi nimega alamkataloogi. Selles
alamkataloogis leiduv "war" laiendiga fail on komponendi Java rakendus ning tuleb paigaldada rakendusserveri "webapps" kataloogi. Jetty 8 korral
tuleb selleks see fail kopeerida kataloogi "/var/lib/jetty8/webapps". Lisaks leidub samas ka fail "dumonitor.properties". See fail tuleb kopeerida kataloogi "/usr/share/jetty8/resources". Kõikidel andmejälgija komponentidel on see konfiguratsioonifail täpselt ühe ja sama sisuga. Kui serverisse paigaldatakse
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
Need tuleb andmebaasimootorit jooksutavas arvutis kasutaja "postgres" õigustes käivitada järgmiselt 
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
peale andmesalvestaja komponendi käivitamist lingilt "{andmesalvestajaUrl}/dumonitor-xforms.xml", kus {andmesalvestajaUrl} tähistab konfiguratsiooni elemendi "Andmesalvestaja URL" väärtust.

Antud fail tuleb paigaldada MISP2 tarkvarasse vastavalt selle tarkvara tootja poolt antud juhistele.

MISP2 rakenduses on vaja ära näidata ka andmekogu WSDL. See on kättesaadav lingilt "{andmesalvestajaUrl}/dumonitor.wsdl".

### Andmejälgija teenuse avaldamine riigiportaalis

**Eeldused**:

- paigaldatud on v6 turvaserver
- paigaldatud ja häälestatud on andmejälgija komponendid vastavalt tarkvara paigaldamise juhendile: https://github.com/e-gov/AJ/blob/master/doc/Paigaldamine.md).

Andmejälgijat paigaldades tuleb seadistused valida selliselt, et andmejälgija ei jälgiks päringuid, mis on tehtud niisuguste politseiliste menetlustoimingute osana, mis peavad jääma varjatuks: https://github.com/e-gov/AJ/blob/master/doc/Rakendusjuhend.md#milliseid-andme-edastamisi-ja--kasutamisi-logida-ja-milliseid-mitte.

Päringute logimise keelamiseks saab tüüplahenduse juures kasutada jälgimisfiltri konfiguratsiooni osaks olevat nn blacklist'i: täpsemalt paigaldamisjuhendi peatükis "Välistuste kirjeldamine" https://github.com/e-gov/AJ/blob/master/doc/Paigaldamine.md#v%C3%A4listuste-kirjeldamine.

Küsimuste korral võtke ühendust RIA kasutajatoega aadressil help@ria.ee (teema: andmejälgija paigaldamine).

X-tee v6 erinevates keskkondades olemas olevate asutuste teenuste kohta saab infot siit: http://x-road.eu/allmethods/

Soovitavalt tuleks sama alamsüsteemi nimetust kasutada kõikides X-tee keskkondades.

#### Andmejälgija teenuse testimine riigiportaali partnerite arenduskeskkonnas www.stage.eesti.ee

- Alamsüsteemi loomine ja registreerimine (vajadusel)
  * Andmejälgija teenust võib osutada mõne olemasoleva alamsüsteemi kaudu.
  * Kui teenusepakkuja otsustab andmejälgija teenuse pakkumiseks luua eraldi alamsüsteemi, tuleb see oma X-tee testkeskkonna turvaserveri kaudu registreerimisele esitada.
  * X-tee keskus rahuldab alamsüsteemi taotluse üldjuhul paari tööpäeva jooksul. Turvaserveri kasutajaliideses annab sellest märku alamsüsteemi juures olev märge 'Registered'.
- Andmejälgija teenuse avamine RIA-le
  * Teenusepakkuja peab oma X-tee v6 testkeskkonna turvaserveris avama RIA-le andmejälgija teenuse `findUsage`:
    + testkeskkonnas alamsüsteemile: `ee-test : GOV : 70006317 : riigiportaal-citizen`.
- Teenuse avaldamine ja testimine www.stage.eesti.ee keskkonnas.
  * Teenusepakkuja edastab RIA kasutajatoele (help@ria.ee) taotluse ja lepingu andmejälgija kasutamiseks
    + Teenusepakkuja täidab andmejälgija teenuse registreerimise taotluse (https://github.com/e-gov/AJ/blob/master/doc/Andmej%C3%A4lgija%20liitumistaotlus%20p%C3%B5hi.docx) ja lepingu (https://github.com/e-gov/AJ/blob/master/doc/Andmej%C3%A4lgija%20liitumisleping%20p%C3%B5hi.docx).
    + Taotluse ja lepingu peab allkirjastama asutuse allkirjaõiguslik isik.
    + Taotlus ja leping tuleb edastada RIA kasutajatoe aadressile help@ria.ee. 
  * Teenusepakkuja saadab RIA kasutajatoele (help@ria.ee) testija(te) IP-aadress(id), et saaksime tekitada ligipääsu stage riigiportaali. 
  * Teenusepakkuja lisab testandmed Smart-ID testisikukoodile 30303039914.
  * Riigiportaali teenusehaldur lisab seejärel www.stage.eesti.ee keskkonnas teenuse vormi, testib selle toimimist ning annab teenusepakkujale teada, kui see võib asuda ka ise testima.

#### Andmejälgija teenuse avaldamine riigiportaali toodangukeskkonnas

- Alamsüsteemi loomine ja registreerimine (vajadusel); teenuse info lisamine x-tee iseteeninduses (x-tee.ee)
  * Andmejälgija teenust võib osutada mõne olemasoleva alamsüsteemi kaudu.
  * Kui teenusepakkuja otsustab andmejälgija teenuse pakkumiseks luua eraldi alamsüsteemi, tuleb see oma X-tee toodangukeskkonna turvaserveri kaudu registreerimisele esitada (https://x-tee.ee/docs/live/xroad/ug-ss_x-road_7_security_server_user_guide.html#42-adding-a-security-server-client).
    + X-tee keskus rahuldab alamsüsteemi taotluse üldjuhul paari tööpäeva jooksul, sellest antakse teada RIHAs alamsüsteemi juures märgitud kontaktisikule.
  * Teenusepakkuja lisab X-tee iseteeninduses (x-tee.ee) alamsüsteemile teenuse info
- Andmejälgija teenuse avamine RIA-le.
  * Teenusepakkuja avab oma X-tee v6 toodangukeskkonna turvaserveris andmejälgija teenuse `findUsage` RIA alamsüsteemile: `EE : GOV : 70006317 : riigiportaal-citizen`.
- Kui teenusepakkuja on arendustega lõpule jõudnud ja soovib toodangukeskkonda liikuda oma andmejälgijaga, siis edastab selle soovi RIA kasutajatoele (help@ria.ee)
  * Riigiportaali teenusehaldur lisab seejärel www.eesti.ee keskkonnas teenuse vormi ja annab teenusepakkujale teada, kui see on lisatud.

#### Kasutajatugi, hooldustööd ja katkestused

- Teenusele osutab esmast kasutajatuge RIA. Kui kasutajatugi ei saa kasutajat kohe aidata, suunab ta küsimuse edasi teenuseomanikule. 
- Kõikide RIA kasutajatoelt teenuse omanikule suunatud lõppkasutajate pöördumiste lahendused tuleb edastada ka RIA kasutajatoele. See tähendab, et kasutajale e-posti teel vastates tuleb edastada koopia RIA kasutajatoele (help@ria.ee) koos viitega pöördumise ID-numbrile.
- RIA kasutajatoelt saadetud e-kirjadele tuleb edastada automaatvastus, mis sisaldab hiliseimat vastamistähtaega.

#### Katkestused teenuse töös

- Teenusega seotud hooldustöödest ja planeeritud katkestustest peab teenusepakkuja RIA kasutajatuge teavitama (lisaks RIHA kaudu edastatavale teatele teenuse kasutajatele) vähemalt 48 tundi enne katkestuse toimumist (nädalavahetusel ja/või esmaspäeval toimuvatest katkestustest tuleb teavitada hiljemalt reedel kell 10.00).
- Katkestuse info peab olema selge, lõppkasutajale arusaadav ja sobilik riigiportaalis avaldamiseks.
- Teenusega seotud planeerimata katkestustest peab teenusepakkuja RIA kasutajatuge aadressil help@ria.ee teavitama niipea kui võimalik, märkides võimalusel ära katkestuse eeldatava lõppaja.
- RIA kasutajatugi teavitab teenusepakkujat riigiportaali üldistest hooldustöödest ja katkestustest teenusepakkuja edastatud e-posti listiaadressil vähemalt 48 tundi enne nende toimumist.

#### Teenuse tööaeg ja katkestuste kestus

- Teenuse tööaeg ja kasutajatoe aeg peab olema vähemalt E–R kl 9–17.
- Teenuse tööajal planeeritud katkestuste maksimaalne kogukestus aastas võib olla kuni 24 tundi.
- Teenuse ühe planeeritud katkestuse maksimaalne kestus võib olla kuni kaks tundi.
- Soovituslikult võiks planeeritud katkestusi teenuse tööajal olla ühes kuus kaks.
- Teenuse tööajal planeerimata katkestuste kogukestus aastas võib olla kuni 24 tundi.
- Teenuse ühekordse planeerimata katkestuse kestus võib olla kuni 12 tundi.

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
* Failis "/etc/xroad/jetty/jetty-public.xml" muudeti elemendi "//Set[@name='extractWars']" väärtus "false" pealt "true" peale. Vajadusel tuleb see tagasi väärtustada.
* Kui paigaldati ka andmesalvestaja komponent, siis on serveris töötavasse PostgreSQL andmebaasimootorisse tekitatud juurde andmebaas nimega "dumonitor" ning kasutajatunnused nimedega "dumonitor" ja "dumonitor_app". Need tuleb vajaduse möödudes eemaldada.

Muudatuste jõustamiseks tuleb teha restart ka "jetty" ja "nginx" teenustele.

Andmebaasi eemaldamine andmebaasimootorist toimub andmebaasi administreerimise juhendis ettenähtud viisil.

### Java rakendusserverisse paigaldatud rakenduse komponentide eemaldamine

Komponentide paigaldamise käigus tekitatud failid tuleb eemaldada. Sõltuvalt rakendusserveri konfiguratsioonist võib olla vajalik ka rakendusserverile
restardi tegemine.

## Häälestamine

Kõigi komponentide üldine häälestamine käib ühise konfiguratsioonifaili "dumonitor.properties" muutmise teel. Faili asukoht sõltub komponendi paigaldamise viisist, vt eespool toodud paigaldamise juhiseid, faili asukohta märgib seal konfiguratsiooni element "Konfiguratsioonifaili asukoht". Faili näol on tegemist standardse Java properties-failiga, kus sümbol '#' tähendab kommentaari algust ning igal real on kirjas parameetrid kujul "{param}={väärtus}", kus {param} tähistab parameetri nime ja {väärtus} tähistab parameetrile omistatavat väärtust. Parameetri nime ja väärtust eraldab omavahel sümbol '=', selle sümboli ümber olevaid tühikuid ignoreeritakse.

Lisaks on eraldusfiltri komponendil olemas ka eraldi konfiguratsioonifail "dumonitor-filter.xml", mille abil kirjeldatakse päringute sisu baasilt logimist juhtivad filtrid.

### Andmesalvestaja komponendi häälestamine

Andmesalvestaja konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Andmesalvestaja X-tee teenuse nimeruum. Kui on vaja kasutada vaikeväärtusest erinevat XML nimeruumi.
* Andmebaasi ühenduse JNDI nimi. Juhul, kui soovitakse andmebaasi ühendus kirjeldada JNDI nime kaudu (vt täpsemalt https://wiki.eclipse.org/Jetty/Feature/JNDI). 
* Andmebaasi ühendusstring. Juhul, kui soovitakse andmebaasi ühendus luua vahetult konfiguratsioonifailis kasutajatunnuse ja parooli ära näitamisega.


Andmesalvestaja tööd juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                               | Vaikeväärtus                        | Väärtustamine                                  |
|------------------------------------------|-------------------------------------|------------------------------------------------|
| dumonitor.storage.xroad.producerns       | http://dumonitor.x-road.eu/producer | Andmesalvestaja X-tee teenuse nimeruum |
| dumonitor.storage.database.jndi          |                                     | Andmebaasi ühenduse JNDI nimi, täita juhul, kui soovitakse andmebaasiühendust JNDI kaudu |
| dumonitor.storage.database.connectstring |                                     | Andmebaasi ühendusstring, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli ära näitamisega |
| dumonitor.storage.database.user          |                                     | Andmebaasi kasutaja tunnus, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli ära näitamisega |
| dumonitor.storage.database.password      |                                     | Andmebaasi kasutaja parool, täita juhul, kui soovitakse andmebaasiühendust vahetult kasutajatunnuse ja parooli ära näitamisega |

Juhul, kui konfiguratsioonifailis näidatakse ära andmebaasi kasutaja parool, on vaja hoolitseda selle faili salajasuse eest - selleks tuleb faili lugemisõigus jätta alles ainult Jetty rakendusserveri kasutajatunnusele.

Peale andmesalvestaja häälestamist ja käivitamist tuleb andmesalvestaja X-tee teenus ära kirjeldada ka X-tee turvaserveris. Selleks on kõige lihtsam lisada X-tee turvaserveri konfiguratsiooni juurde täiendav WSDL, näidates WSDL faili asukohaks 
"{andmesalvestajaUrl}/dumonitor.wsdl", kus {andmesalvestajaUrl} tähistab konfiguratsiooni elementi "Andmesalvestaja URL". Seejärel tuleb turvaserveris ära muuta selle WSDL all teenuse "findUsage" URL, muutes algses URLis serveri nime "TURVASERVER" ümber selle serveri nimeks,
kus andmesalvestaja teenus paikneb (lisades vajadusel ka pordi numbri ja/või muutes protokolli "http" pealt "https" peale).

### Eraldusfiltri komponendi häälestamine

Eraldusfiltri konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Eraldusfiltri filtrite kirjelduse faili asukoht. Asukoht tuleb esitada komponendi poolt kasutatava classpath suhtes. Kui see pole paigaldajale
täpselt teada, siis sisuliselt tuleb esitada faili asukoht komponendi konfiguratsioonifaili suhtes.
* Turvaserveri URL. URL, mille kaudu tehakse andmekogu poolt pöördumised teiste X-tee liikmete teenuste poole.
* Andmekogu URL. URL, mille kaudu pääseb ligi andmekogu teenustele.
* Eraldusfiltri paralleelsete lõimede arv. Kui see arv on 0, siis haldab eraldusfilter ise dünaamiliselt thread'ide arvu - tekitab neid vastavalt koormusele juurde või võtab vähemaks. Kui see arv erineb nullist, siis käivitatakse näidatud arv threade päringute töötlemiseks. Antud parameeter reguleerib päringute filtris töötlemist, mitte HTTP päringute vastuvõtmist ja edastamist. Viimast reguleeritakse rakendusserveri vastavate parameetrite abil.
* Eraldusfiltri sõnumite järjekorra suurus. Maksimaalne sõnumite arv, mis võib olla sõnumite lõimede poolt töötlemise ootel.
Kui ootel sõnumite arv läheb sellest numbrist suuremaks, siis jätab eraldusfilter seda arvu ületavad sõnumid filtrites rakendamata (kuid vahendab need endistviisi edasi teisele osapoolele) ning edastab rakendusserveri logisse vastava veateate.
* Must nimekiri. X-tee osapoolte koodid, kellelt saadud ja kellele edastatud sõnumeid läbi filtri töötluse ei lasta (ja jäävad sedasi logimata).
* Eraldusfiltrite kirjeldused.

Eraldusfiltri tööd juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                                 | Vaikeväärtus         | Väärtustamine 
|--------------------------------------------|----------------------|----------------------------------------------------|
| dumonitor.filter.configuration.file        | dumonitor-filter.xml | Eraldusfiltri filtrikirjelduste faili asukoht  |
| dumonitor.filter.turvaserver.url           |                      | Turvaserveri URL                                   |
| dumonitor.filter.andmekogu.url             |                      | Andmekogu URL                       |
| dumonitor.filter.logger.rest.url           |                      | Andmesalvestaja komponendi REST logimisteenuse URL. Saadakse andmesalvestaja komponendi URLile sufiksi "/rest" liitmisega. |
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

Rakendus logib oma tegevust [SLF4J](http://www.slf4j.org/) abil ning selle all kasutatakse Apache Log4J 2 raamistikku. Logimise häälestamine tuleb teostada vastavalt Log4J raamistiku tootja poolt antud juhistele (vt http://logging.apache.org/log4j/2.x/manual/configuration.html). Rakenduses on sisemiselt kirjeldatud konfiguratsioonifail "log4j2.xml", millega on häälestatud INFO taseme teadete logimine konsooli logijasse, st rakendusserveri logifaili.

Et X-tee turvaserveri ja andmekogu vaheline liiklus toimuks läbi eraldusfiltri, on vaja veel ka:

* Näidata X-tee turvaserveris andmekogu URLina eraldusfiltri URL, millele on lisatud sufiks "/producer". Eraldusfiltri X-tee turvaserverisse paigaldamise korral on see kujul "http://{turvaserver}:4080/dumonitor-filter/producer".
* Näidata andmekogu adapterserveris X-tee turvaserveri URLina eraldusfiltri URL, millele on lisatud sufiks "/xroad". Eraldusfiltri X-tee turvaserverisse paigaldamise korral on see kujul "http://{turvaserver}:4080/dumonitor-filter/xroad".

#### Eraldusfiltri komponendi filtrite kirjeldamine

Eraldusfiltri komponent kasutab oma töös vahendatavate päringute logimisel spetsiaalset XML-faili "dumonitor-filter.xml" (faili täpne asukoht ning nimi määratakse
kindlaks eraldusfiltri komponendi häälestusparameetri "dumonitor.filter.configuration.file" abil). Faili ülesehitust kirjeldab XML Schema fail [dumonitor.xsd](spetsifikatsioonid/dumonitor.xsd). Filtrite kirjeldamine nõuab head arusaama X-tee SOAP sõnumite ülesehituse nüanssidest ning kursis olekut XPath v1.0 standardiga. Failis tuleb filtrid kirjeldada järgmiselt.

Faili juurelemendiks peab olema element nimega "filterConfiguration". See element ning ka kõik teised selle elemendi all asuvad filtrifaili elemendid peavad kasutama XML nimeruumi "http://x-road.eu/xsd/dumonitor.xsd".

Filtrite kirjeldamisel peab arvestama, et eraldusfiltri komponent rakendab samu filtreid kõigile teda läbivatele sõnumitele, nii X-tee poolt andmekogu poole liikuvatele päringutele, kui ka andmekogu poolt tagastatavatele päringute vastustele. Kui soovitakse logida kas ainult päringut või ainult vastust, siis tuleb ka XPath avaldis koostada selliselt, mis rakenduks ainult soovitavas suunas liikuvale sõnumile (kontrollides näiteks SOAP sõnumi "Body" elemendi all asuvat elemendi nime).

##### Nimeruumide kirjeldamine

Kõik filtrifaili XPath avaldistes kasutatavad XML nimeruumid tuleb kirjeldada elemendi "namespaces" all. Iga nimeruum tuleb selle all näidata eraldi elemendiga "namespace", millel on kaks alamelementi:

* element "prefix", mille väärtuseks on XPath avaldises kasutatav nimeruumi prefiks
* element "uri", mille väärtuseks on XML nimeruumi URI

Nimeruumide prefiksid ei pea kokku langema filtrist läbi liikuvates sõnumites kasutatavate nimeruumide prefiksitega. XPath avaldistes toimub kontroll
nimeruumide endi võrdlemise baasilt, otsides avaldises esitatud prefiksile antud elemendi all kirjeldatud nimeruumi URI.

##### Välistuste kirjeldamine

Filtrifailis saab eraldi sektsioonis ära näidata XPath avaldised, millele vastavad sõnumid jäetakse igal juhul logimata ka juhul,
kui sellistele sõnumitele leidub positiivne filtrikirjeldus. 

Avaldistes saab kasutada spetsiaalset funktsiooni "inBlacklist", millele saab näidata parameetrina XPath avaldise. Avaldisega leitud elemendi
väärtust võrreldakse nn. musta nimekirja väärtustega (eraldusfiltri konfiguratsioonifaili parameetriga "dumonitor.blacklist") ning kui nende hulgast
leidub väärtus, mis langeb sellega kokku, siis seda sõnumit ei logita.

NB! Funktsiooni "inBlacklist" tuleb tingimata kasutada koos nimeruumile "http://x-road.eu/xsd/dumonitor.xsd" viitava prefiksiga ning see prefiks tuleb kirjeldada ka elemendi "namespaces" all. Vt näidet allpool. 

Eraldusfiltri komponendil on vaikimisi elemendi "exclusions" sisuks allpool toodud näites näha olev sisu. Kui kasutaja poolt koostatud failis elementi "exclusions" ei leidu, siis kasutatakse vaikeväärtust, vastasel korral kasutatakse kasutaja poolt koostatud failis näidatut.

Faili [dumonitor.properties](../filter/etc/dumonitor.properties) konfiguratsiooni element "dumonitor.blacklist" peab sisaldama väärtuseid 70000591, 70008747, 70005938, 70000349, 70000898 (Kaitsepolitseiamet, Politsei- ja Piirivalveamet, Teabeamet, Maksu- ja Tolliamet, Justiitsministeerium). Ehk:
```
dumonitor.blacklist=70000591, 70008747, 70005938, 70000349, 70000898
```

##### Vaikeväärtuste kirjeldamine

Globaalselt üle kõikide filtrite saab kirjelda ära logitava info vaikeväärtused. Neid väärtusi kasutatakse logi vastavate väljade väärtustamisel
juhul, kui filtrikirjelduses pole selle välja väärtust eraldi toodud. Sellised vaikeväärtused näidatakse elemendi "defaults" all. Seal tuleb esitada
iga logi välja jaoks element, mille nimi langeb kokku selle logiväljaga, ning väärtuseks tuleb esitada XPath avaldis, mille leitakse 
kontrollitavast sõnumist väljale väärtus.

Eraldusfiltri komponendil on vaikimisi elemendi "defaults" sisuks allpool toodud näites näha olev sisu. Kui kasutaja poolt koostatud failis
elementi "defaults" ei leidu, siis kasutatakse vaikeväärtust, vastasel korral kasutatakse kasutaja poolt koostatud failis näidatut.

##### Filtrite kirjeldamine

Vahendatavatele X-tee sõnumitele rakendatavad filtrid kirjeldatakse elemendi "filters" sees. Iga konkreetne filtrikirjeldus näidatakse elemendi "filter" abil. Elemendi all peab olema toodud kaks alamelementi:

* Element "xpath" peab sisaldama XPath avaldist, mille filter teisendab tõeväärtuseks. Kui selle avaldise rakendamine kontrollitavale sõnumile
annab tõese väärtuse, siis sõnum kuulub logimisele ja rohkem filtreid enam ei kontrollita. Kui avaldise rakendamisel tõest väärtust ei saada, siis kontrollitakse filtrite nimekirjast järgmist filtrit. Kui mitte ühegi filtri korral tõest väärtust ei saadud, siis sõnumit ei logita.
* Elemendi "loggableFields" abil näidatakse ära logitavate väljade väärtused. Iga logitav väli tuleb esitada samanimelise elemendina ning selle
elemendi väärtuseks peab olema XPath avaldis, mille abil leitakse vastavale väljale väärtus.

Logitavad väljad saavad olla järgmised:

* personcode - Isikukood, kelle isikuinfot antud päringuga edastati (peab sisaldama riigi prefiksit, näiteks Eesti korral 'EE')
* action - Isikuandmete tegevusele omistatud inimloetav nimi
* sender - Isikuandmeid saatva osapoole inimloetav nimi
* receiver - Isikuandmeid vastuvõtva osapoole inimloetav nimi
* restrictions - Kui väärtuseks on 'A', siis on logikirje nähtav andmesalvestaja vastava X-tee teenuse kaudu, vastasel korral mitte
* sendercode - Isikuandmeid saatava osapoole X-tee kood
* receivercode - Isikuandmeid vastuvõtva osapoole X-tee kood
* actioncode - Isikuandmete tegevusele omistatud sisemine masintöödeldav nimi
* xroadrequestid - Isikuandmeid edastava päringu X-tee ID
* xroadservice - Isikuandmeid edastava päringu X-tee teenuse nimi
* usercode - Isikuandmeid küsinud osapoole päringut sooritanud ametniku isikukood (peab sisaldama riigi prefiksit, näiteks Eesti korral 'EE')

Kui mingi väli neist jäetakse filtri kirjelduses esitamata, siis kasutatakse vaikeväärtuste elemendi "defaults" all toodud avaldist. Kui
ka seal pole seda välja esitatud, kasutatakse filtri komponendi sisemist vaikeväärtust. Kui ka seda pole esitatud, siis logitakse see
väli tühja väärtusega.

##### Näidis

Allpool on toodud näidis ühest võimalikust filtri konfiguratsiooni failist.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<filterConfiguration xmlns="http://x-road.eu/xsd/dumonitor.xsd"
	xmlns:du="http://x-road.eu/xsd/dumonitor.xsd"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <namespaces>
        <namespace>
            <prefix>du</prefix>
            <uri>http://x-road.eu/xsd/dumonitor.xsd</uri>
        </namespace>
        <namespace>
            <prefix>SOAP-ENV</prefix>
            <uri>http://schemas.xmlsoap.org/soap/envelope/</uri>
        </namespace>
        <namespace>
            <prefix>xrd</prefix>
            <uri>http://x-road.eu/xsd/xroad.xsd</uri>
        </namespace>
        <namespace>
            <prefix>id</prefix>
            <uri>http://x-road.eu/xsd/identifiers</uri>
        </namespace>
    </namespaces>
    <exclusions>
        <exclusion>du:inBlacklist(/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:memberCode)</exclusion>
        <exclusion>du:inBlacklist(/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:client/id:memberCode)</exclusion>
    </exclusions>
    <defaults>
        <sender>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:memberCode</sender>
        <receiver>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:client/id:memberCode</receiver>
        <sendercode>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:memberCode</sendercode>
        <receivercode>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:client/id:memberCode</receivercode>
        <xroadrequestid>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:id</xroadrequestid>
        <xroadservice>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:serviceCode</xroadservice>
        <usercode>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:userId</usercode>
    </defaults>
    <filters>
		<filter>
			<xpath>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:serviceCode = 'replaceWithServiceCode' and /SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:serviceVersion = 'replaceWithServiceVersion'</xpath>
			<loggableFields>
				<personcode>/SOAP-ENV:Envelope/SOAP-ENV:Body/pr:replaceWithServiceCodeResponse/replaceWithXPathToPersonCode</personcode>
				<action>'Human readable action name'</action>
				<restrictions>'A'</restrictions>
				<actioncode>'technicalActionCode'</actioncode>
			</loggableFields>
		</filter>
    </filters>
</filterConfiguration>
```

Filtri kirjeldamises peab arvestama, et väljal `personcode` on vaja isikukood esitada riigi prefiksiga. Kui päringus
on aga isikukoodid ilma riigi prefiksita, siis tuleb riigi prefiks lisada XPath avaldise abil näiteks järgmiselt (eelmise
näite baasilt):

```xml
<personcode>concat('EE',/SOAP-ENV:Envelope/SOAP-ENV:Body/pr:replaceWithServiceCodeResponse/replaceWithXPathToPersonCode)</personcode>
```

Samal ajal tuleks tähele panna, et eraldusfilter otsib vastavusi nii päringus kui ka vastuses. Ning juhul kui `personcode`
välja otsimiseks kasutatud xpath avaldis leiab väärtuse ainult vastuses, siis päringu puhul `concat` liidab 'EE' tühja
väärtusega ning tulemusena saab 'EE', mis omakorda on salvestatud andmesalvestajasse.

Antud probleemi on võimalik lahendada määrates `<xpath>` elemendis, et antud filter kehtib vaid päringu või vastuse puhul.
Näiteks järgmine `<xpath>` väärtus on tõene vaid päringute puhul:

```xml
<xpath>/SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:serviceCode = 'replaceWithServiceCode'
	and /SOAP-ENV:Envelope/SOAP-ENV:Header/xrd:service/id:serviceVersion = 'replaceWithServiceVersion'
	and /SOAP-ENV:Envelope/SOAP-ENV:Body/pr:replaceWithServiceCodeResponse
</xpath>
```

### Esitamise testrakenduse häälestamine

Esitamise testrakenduse konfigureerimiseks on vaja täiendavalt kindlaks määrata järgmised konfiguratsiooni elemendid:

* Turvaserveri URL. URL, mille kaudu teeb esitamise testrakendus pöördumisi X-tee andmekogude poole.
* Esitamise testrakenduse X-tee kliendi parameetrid (X-tee instants, X-tee liikme klass, X-tee liikme kood, X-tee liikme alamsüsteemi kood) 
* Esitamise testrakenduse poolt kasutatavate andmekogude parameetrid (X-tee instants, X-tee liikme klass, X-tee liikme kood, X-tee liikme alamsüsteemi kood, teenuse kood, teenuse versioon, andmekogu nimi)

Esitamise testrakendust juhitakse järgmiste "dumonitor.properties" failis olevate parameetrite abil:

| Parameeter                | Vaikeväärtus      | Väärtustamine    | 
|---------------------------|-------------------|------------------|
| dumonitor.query.xroad.url |                   | Turvaserveri URL |



Ülejäänud konfiguratsiooni elementide baasilt tuleb tekitada rakendusserveris, kuhu esitamise testrakendus sai paigaldatud, juurrakenduse juurkataloogi fail nimega "producers.js" ning mille sisus on muutuja "producers" kirjeldus järgmise ülesehitusega:

```js
var producers = {
"Andmekogu1": 
"<soapenv:Header>"+
"     <xro:protocolVersion>4.0</xro:protocolVersion>"+
"     <xro:id>{id}</xro:id>"+
"     <xro:userId>{userId}</xro:userId>"+
"     <xro:service iden:objectType=\"SERVICE\">"+
"        <iden:xRoadInstance>CI</iden:xRoadInstance>"+
"        <iden:memberClass>GOV</iden:memberClass>"+
"        <iden:memberCode>20000001</iden:memberCode>"+
"        <iden:subsystemCode>AJ</iden:subsystemCode>"+
"        <iden:serviceCode>findUsage</iden:serviceCode>"+
"        <iden:serviceVersion>v1</iden:serviceVersion>"+
"     </xro:service>"+
"     <xro:client iden:objectType=\"SUBSYSTEM\">"+
"        <iden:xRoadInstance>CI</iden:xRoadInstance>"+
"        <iden:memberClass>GOV</iden:memberClass>"+
"        <iden:memberCode>10000001</iden:memberCode>"+
"        <iden:subsystemCode>Center</iden:subsystemCode>"+
"     </xro:client>"+
"</soapenv:Header>",
"Andmekogu2": 
"<soapenv:Header>"+
"     <xro:protocolVersion>4.0</xro:protocolVersion>"+
"     <xro:id>{id}</xro:id>"+
"     <xro:userId>{userId}</xro:userId>"+
"     <xro:service iden:objectType=\"SERVICE\">"+
"        <iden:xRoadInstance>CI</iden:xRoadInstance>"+
"        <iden:memberClass>GOV</iden:memberClass>"+
"        <iden:memberCode>20000001</iden:memberCode>"+
"        <iden:subsystemCode>AJ</iden:subsystemCode>"+
"        <iden:serviceCode>findUsage</iden:serviceCode>"+
"        <iden:serviceVersion>v1</iden:serviceVersion>"+
"     </xro:service>"+
"     <xro:client iden:objectType=\"SUBSYSTEM\">"+
"        <iden:xRoadInstance>CI</iden:xRoadInstance>"+
"        <iden:memberClass>GOV</iden:memberClass>"+
"        <iden:memberCode>10000001</iden:memberCode>"+
"        <iden:subsystemCode>Center</iden:subsystemCode>"+
"     </xro:client>"+
"</soapenv:Header>",
}
```

Tegemist on JavaScript süntaksiga hash-muutuja kirjeldamisega, kus võtmeväljana tuleb näidata andmekogu inimloetav nimi ning võtmeväljale vastava väärtusena tuleb
näidata täpne X-tee v6 päiselemendi ülesehitus (kasutades näites näha olevaid nimeruumi prefikseid). Päisväljad tuleb täita vastavalt pärija X-tee seadetele ning 
andmekogu X-tee seadetele. Päises tuleb esitada X-tee päisväljade "id" ja "userId" väärtused vastavalt stringidena "{id}" ja "{userId"} (tarkvara asendab need
päringut tehes tegelikega). Elemente võib esitada üks kuni mitu (antud näites on toodud kahe andmekogu kirjeldused).

Kui tarkvara paigaldati töötama Ubuntu 14.04 LTS kaasatuleva Jetty 8 rakendusserverisse, siis tuleb fail "producers.js" asetada kataloogi "/var/lib/jetty8/webapps/root/", muude rakendusserverite korral tuleb konsulteerida vastava rakendusserveri juhenditega.

### Autentimise häälestamine

Kõigi andmejälgija moodulitega on eeldatud, et kasutaja autentimine lahendatakse rakendusserveri tasemel. 

Jetty rakendusserveri korral on Basic autentimise sisselülitamiseks vaja teha järgmist:

Kirjeldada Jetty konfiguratsioonifailis "etc/jetty.xml" (siin ja edaspidi antud punktis on failide asukohad antud Jetty kodukataloogi suhtes) elemendi "Configure" sees autentimise teenus:

```xml
<Call name="addBean">
    <Arg>
        <New class="org.eclipse.jetty.security.HashLoginService">
            <Set name="name">DUMonitor</Set>
            <Set name="config"><SystemProperty name="jetty.home" default="."/>/etc/realm.properties</Set>
            <Set name="refreshInterval">0</Set>
        </New>
    </Arg>
</Call>
```

Tekitada fail "etc/realm.properties". Faili sisus esitatakse kasutajatunnused ja paroolid kujul 
"{username}: {password}, {rolename}". Näiteks siis alljärgneva sisuga:

```
ajkasutaja: ajparool, admin
```

Lisada faili "etc/webdefault.xml" elemendi "web-app" sisse autentimise määrang kujul:

```xml
<security-constraint>
    <web-resource-collection>
      <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
      <role-name>admin</role-name>
    </auth-constraint>
</security-constraint>
<login-config>
    <auth-method>BASIC</auth-method>
    <realm-name>DUMonitor</realm-name>
</login-config>
```

Jetty rakendusserver toetab ka ID-kaardiga autentimist, selleks tuleb järgida juhiseid lingilt https://wiki.eclipse.org/Jetty/Howto/Configure_SSL.

Analoogsed võimalused on ka teistel rakendusserveritel (näiteks Tomcat). Instruktsioonid on toodud vastava rakendusserveri dokumentatsioonis.
