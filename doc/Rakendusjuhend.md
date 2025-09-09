Tarkvara rakendusjuhend
=======================

**DUMonitor**

Versioon 1.3, 09.09.2025

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL Regionaalarengu Fond](img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                    | Märkused
|----------|------------|--------------------------|----------------------------------------------
| 1.0      | 09.05.2016 | Tanel Tammet, Ivo Mehide | Esimene versioon
| 1.1      | 19.01.2017 | Piret Elm, Vitali Stupin | Logimise välistuste täpsustamine
| 1.2      | 16.03.2017 | Piret Elm                | Logimise välistuste täpsustamine
| 1.3      | 09.09.2025 |  	 	                     | Dokumendi täiendamine

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sihtrühm](#sihtr%C3%BChm)
  * [Andmejälgija ülevaade](#andmej%C3%A4lgija-%C3%BClevaade)
  * [Milliseid andme-edastamisi ja -kasutamisi logida ja milliseid mitte](#milliseid-andme-edastamisi-ja--kasutamisi-logida-ja-milliseid-mitte)
  * [Andmejälgija kasutuselevõtt](#variandid-andmej%C3%A4lgija-kasutuselev%C3%B5tuks)
  * [Andmejälgija kasutuselevõtmise protsess](#andmej%C3%A4lgija-kasutuselev%C3%B5tmise-protsess)
  * [Tarkvara paigaldamine](#tarkvara-paigaldamine)
  * [Andmejälgija haldustoimingud](#andmej%C3%A4lgija-haldustoimingud)
  * [Aegunud andmejälgija logiridade kustutamine](#infoturbe-k%C3%BCsimused) 
  * [Infoturbe küsimused](#infoturbe-k%C3%BCsimused)
  
## Sihtrühm

Käesoleva juhendi sihtrühmaks on andmekogude omanikud, haldajad ning arendajad, kes on huvitatud andmejälgija tarkvara kasutamisest. 

## Andmejälgija ülevaade

Isikuandmete kaitse seadus nõuab, et andmekogu haldaja peab vastama mistahes isiku arupärimisele selle kohta, kellele tema isikuandmeid on edastatud. Samuti peab olema võimalik tagantjärele teada saada, kelle poolt ja millal isikuandmeid töödeldi.

Andmejälgija eesmärk on pakkuda kodanikule selget ülevaadet tema andmetega sooritatud toimingutest: millal, kuhu ja miks on tema isikuandmeid edastatud. Terviklik ülevaade kuvatakse riigiportaalis eesti.ee.

Andmejälgija teine eesmärk on pakkuda andmekogule mugavad vahendid isikuandmete edastamise ja töötlemise jälgimiseks asutusesiseselt.

Andmejälgija luuakse, paigaldatakse ja konfigureeritakse iga andmekogu jaoks eraldi, mingit riiklikku keskset andmebaasi isikuandmete liikumise jaoks ega keskset paigaldatud lahendust andmejälgija ette ei näe ega paku.

Andmejälgija on realiseeritud Javas (võib töötada alates Java versioonist 1.6), andmebaasiks on Postgresql ja põhimõtteliselt võib teda installeerida nii otse X-tee turvaserverisse kui mõnda olemasolevasse serverisse koos teiste süsteemidega, või hoopis eraldi serverisse. 

## Milliseid andme-edastamisi ja -kasutamisi logida ja milliseid mitte

Isikuandmete kaitse seadus sisaldab punkte, mille lahendamise hõlbustamiseks andmejälgija lahendus on loodud:
* Isikuandmete töötlemise eesmärgid; Kui kodanik küsib, tuleb ettevõttel vastata, mis eesmärgil tema andmeid töödeldi (IKS § 19 lg 1 p 2);
* Andmesubjekti soovil peab isikuandmete töötleja andmesubjektile teatavaks tegema kolmandad isikud, kellele tema isikuandmeid on edastatud. (IKS § 19 lg 1 p 5);
* Isikuandmete töötleja on isikuandmete töötlemisel kohustatud tagama, et tagantjärele oleks võimalik kindlaks teha, millal, kelle poolt ja milliseid isikuandmeid salvestati, muudeti või kustutati või millal, kelle poolt ja millistele isikuandmetele andmetöötlussüsteemis juurdepääs saadi. (IKS § 25 lg 2 p 3)

//**Euroopa GDPR isikuandmete seadustest punktid **

Isikuandmetena käsitleb seadus mistahes andmeid isiku kohta - kaasa arvatud tema nimi ja sünniaeg - mitte ainult
tundlikke isikuandmeid.

Reaalsuses edastatakse isikuandmeid väga erinevatel viisidel. Näiteks: üksiku isiku kohta käiv teenus X-teel, sadu inimesi väljastav otsipäring X-teel, regulaarne mass-päring kümnete tuhandete isikute kohta nende andmete ajutiseks puhverdamiseks, isikuandmeid sisaldavate PDF failide edasisaatmine, isikuandmeid sisaldava andmebaasi kokkupakitud dumbi kopeerimine ftp kaudu jne.

Samuti toimub isikuandmete töötlemine väga mitmekesisel moel, muuhulgas on ka andmetele ligipääsu mõiste mitmeti arusaadav: see võib tähendada üksiku inimese andmete vaatamist, sadade inimeste loendi kiiret ülevaatust, tehnilisi ligipääsuõigusi jne.

Lihtsaid ja üheseid reegleid nimetatud seaduspunktide täitmiseks ei ole võimalik koostada: oluline on lähtuda seaduse mõttest ning arvestada konkreetse andmekogu või infosüsteemi eripäradega.

Kõige üldisem põhimõte, millest lähtuda, on konkreetsele isikule teadliku tähelepanu pööramise või spetsiaalselt tema andmete edasisaatmise talletamisvajadus, samal ajal kui inimese andmete liikumine või töötlemine nö taustandmetes, puhverdamiseks vms ei nõua alati isikupõhist talletamist, küll aga edastamis/töötlemis-põhimõtete tutvustamist kõigile huvitatutele.

Konkreetsemad põhimõtted, millest esimese ülaltoodud seaduspunkti täitmisel lähtuda, on järgmised:

* Andmete saatmise või töötlemise fakti salvestamise juures ei ole vaja talletada konkreetset andmehulka, vaid eeskätt aeg, eesmärk, saaja.
* Üksiku isiku mistahes andmete edasisaatmine X-tee kaudu tuleks talletada ja muuta tema jaoks leitavaks.
* Andmete saamise fakte teisest infosüsteemist ei tule üldjuhul talletada, kuid see ei ole ka keelatud.
* Mõõduka hulga isikute (kuni ca sajani) andmete korraga edasisaatmine tuleks üldjuhul samuti talletada ja iga isiku jaoks leitavaks muuta.
* Mass-edasisaatmiste (sajad ja tuhanded) fakte ei ole üldjuhul vaja salvestada isikupõhiselt.
* Isikuandmete edastamise põhimõtted (milleks ja mis andmekogule) peaks iga andmekogu poolt olema kõigile leitavalt kirja pandud.

Teise ülaltoodud seadusepunkti jaoks tuleks lähtuda järgmisest:

* Konkreetsele isikule tähelepanupööramise või tema andmete sisestamine/muutmise fakt tuleks talletada seotuna seda toimingut tegeva isikuga.
* Pika isikute loendi (näiteks üle mitmekümne inimese) vaatamist vms ei tule üldjuhul talletada nende isikutega seotuna.
* Tehnilised ja administratiivsed ligipääsuvõimalused ja nende kehtimise perioodid tuleks talletada eraldi logisse/faili vms.

Isikuandmete kaitse seadus sisaldab teabe ja isikuandmete saamise õiguse piiranguid (IKS § 20 lg 1-4).

Andmesubjekti õigust saada teavet ja enda kohta käivaid isikuandmeid isikuandmete töötlemisel piiratakse. Seaduses sätestatud juhtudel võib vastutav töötleja käesoleva paragrahvi lõikes 1 nimetatud teabe andmesubjektile esitada hiljem, piirata selle esitamist või jätta selle esitamata, kui see võib (IKS § 24 lg 2): 

1)	takistada või kahjustada süüteo tõkestamist, avastamist või menetlemist või karistuse täideviimist;
2)	kahjustada teise isiku õigusi ja vabadusi;
3)	ohustada riigi julgeolekut;s
4)	ohustada avaliku korra kaitset;
5)	takistada ametlikku uurimist või menetlust. 

Seetõttu ei tohi kodanikele avaldada päringuid, mis on tehtud jälitustegevuse käigus ja teistel IKS § 24 toodud juhtudel, samuti konkreetse asutuse eriseadustest tulenevatest piirangutest. Juba andmejälgija tarkvara kasutusele võtmisel tuleb otsustada ja dokumenteerida milliste teenuste, millise olemasoleva tarkvara või milliste olemasolevate logibaaside külge andmejälgijat liidestada, milliseid andmeedastamisi ja -kasutamisi logida ja milliseid mitte. Millised on üldised ja millised asutuse eriseadusest tulenevad piirangud.
Jälitustegevuse õigusega asutused on loetletud kriminaalmenetluse seadustiku ja teiste seaduste muutmise seaduses § 1262 https://www.riigiteataja.ee/akt/121032011002:

* Politsei- ja Piirivalveamet (70008747);
* Kaitsepolitseiamet (70000591);
* Maksu- ja Tolliamet (70000349);
* Sõjaväepolitsei (70008641);
* Justiitsministeeriumi (70000898) vanglate osakond ja vangla;
* Julgeolekuasutuste seaduses § 5. on toodud julgeolekuasutusena ka Teabeamet (70005938) https://www.riigiteataja.ee/akt/117122015039#para28.

Asutuse valdkondlikus regulatsioonis võib olla täiendavaid osapooli, kelle andmekasutus ei tohi olla kodanikule näha.

Standardlahendus võimaldab piirata kodanikule näidatavat infot kahel moel:

* Eraldusfiltris asuv Blacklist – võimaldab välja lülitada asutuse kogu andmevahetuse asutuse registrikoodi alusel, nii et asutuse päringuid ei logita üldse.
* Eraldusfiltri täpsem seadistus – võimaldab välistada teatud päringute logimise teenuse parameetri või alamsüsteemi täpsusega. 

Sõltumata sellest, kumba moodust välistuse tagamiseks kasutatakse, tuleb piirangute seadistamise reeglid kokku leppida päringuid sooritava asutusega. Juhtumi- või kaasusepõhine filtreerimine ei ole standardlahendust kasutades võimalik. 

Kodanikule andmete väljastamise filtreerimist standardlahendus ei toeta. Filtreeritud päringute kohta on kodanikul õigus teha asutusse järelepärimine traditsioonilisel moel (näiteks teabenõudega).

## Andmejälgija kasutuselevõtt

Andmejälgija andmebaas on alati seotud üheainsa andmekoguga: juba turvakaalutlustel ei tohi lubadaolukorda, kus üks andmejälgija salvestab andmeid erinevatest andmekogudest. 

Andmejälgija andmebaas on alati seotud üheainsa andmekoguga: juba turvakaalutlustel ei tohi lubada olukorda, kus üks andmejälgija salvestab andmeid erinevatest andmekogudest.

Samuti andmejälgija kasutab X-tee protolli teenuse jaoks, mille kaudu kutsutakse küsitakse logid. See kus neid hoitakse, milline on teenus või adapter, mis findusaget implementeerib me ette ei defineeri.

## Andmejälgija kasutuselevõtmise protsess

Alljärgnevalt toome välja põhilised tähelepanekud, millega asutuselevõtmisega tuleks arvestada:

1.	Leppida asutusesiseselt kokku konkreetne meeskond, kes juurutuse läbi viib, koos esialgse ajakavaga. 
Soovituslikult võiks meeskond koosneda järgnevalt:
  * Põhimõtete otsustaja: millise teenuse/kasutuse külge andmejälgija arendada jms.
  *	IT süsteemide haldaja, kes mh konfigureerib X-tee liideseid ja haldab infosüsteeme, mille külge andmejälgija soovitakse arendada.
  * Arendaja, kes vajadusel infosüsteemile liidestusi ja täiendusi ehitab.

2.	Otsustada, milliste teenuste, millise olemasoleva tarkvara või milliste olemasolevate logibaaside külge andmejälgijat arendada. 
  * Aru saada andmejälgija põhimõtetest ja võimalustest.
  * Arutada variante teenuse/infosüsteemi haldaja ja/või arendajaga.

3.	Otsustada, millistesse olemasolevatesse serveritesse Andmejälgija lisda või vajdusel hankida uus server. 

4.	Valikuliselt: konfigureerida asutuse X-tee liidest, kui see osutub valitud liidestusviisi jaoks vajalikuks. 

5.	Valikuliselt: realiseerida eriliidesed oma infosüsteemides andmejälgija külge, kui etapis kaks on nii otsustatud. Andmejälgija ei vaja otseselt eriliideseid, seega see etapp üldse  ja tema maht sõltub täielikult konkreetse asutuse vajadustest ja otsustest.

6. Töötav süsteem tuleks tervikuna läbi testida. Testlugusid ega testimise juhendeid ei ole ette antud, vaid oluline on ja tuleks tuvastada, et andmejälgija on töökorras ja tõrkeid ei esine.  Spetsiaalseid formaalseid testsüsteeme ei ole siin vaja kasutada, tuleks aga lihtsalt tuvastada, et ettenähtud funktsionaalsus töötab ja ei paista ette-ennustamatuid kõrvalefekte. 

7.	Leppida asutusesiseselt kokku andmejälgija haldamise töökorraldus: milline IT haldaja kontrollib tema töökorda, teeb tagavarakoopiaid, jälgib, et ketas ei saaks täis jne.


## Tarkvara paigaldamine

Tarkvara vajalikud juhendid on kättesaadavad avalikus GitHub repositooriumis (https://github.com/sipsu1/AJ/blob/master/doc/spetsifikatsioonid/Kasutusteabe_esitamise_protokoll.md). 

## Andmejälgija haldustoimingud

Paigaldatud ja konfigureeritud andmejälgija ei vaja palju haldustoiminguid. Siiski tuleb regulaarselt teha harilikke tegevusi:

* Kontrollida, et andmejälgija andmebaas ei hakka lõpuni täitma serveri kettaruumi.
* Kontrollida, et andmejälgija regulaarselt andmeid ka salvestab ning eesti.ee-st on salvestatud andmed kättesaadavad.
* Uute isikuandmeid edastavate X-tee teenuste ehitamise või muude uute isikuandmete töötlemisvõimaluste ehitamise korral
lisada ka need andmejälgijasse.
* Perioodiliselt kustutada aegunud logiridu.

### Aegunud andmejälgija logiridade kustutamine

Infosüsteem peab logima ise andmeid ning aegunud logid eemaldama.

Kui asutusel on juba olemas logimise süsteem, siis saab luua sellele findusage päringu toe, kui seda pole, siis tuleb see üles ehitada nullist.

## Infoturbe küsimused

Üldise põhimõttena andmejälgija paigaldamisel tuleks lähtuda sellest, et andmejälgija on põhiandmekogu osa, mitte eraldiseisev süsteem. Seega ei nõua ta ka omaette ISKE analüüsi, omaette X-tee süsteeme jne. 

Üks andmejälgija ei saa teenindada korraga mitut andmekogu. Kõik andmejälgija päringud kasutavad x-tee pärinugid ja seetõttu on oluline x-tee ja turvaserverite olemasolu. Kui seda ei ole, siis tuleb esmalt endale hankida turvaserverid ja luua x-tee lahendused enda süsteemidesse. X-tee ja turvaserveri olemasolu on eelduseks andmejälgija kasutuselevõtuks.

X-tee turvaserveri ülesandeks on vahendada päringuid X-tee võrgu ja asutuse siseste X-teed kasutavate lahenduste või teenuste vahel.


