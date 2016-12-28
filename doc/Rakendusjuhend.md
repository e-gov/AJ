Tarkvara rakendusjuhend
=======================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL Regionaalarengu Fond](img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor                    | Märkused
|----------|------------|--------------------------|----------------------------------------------
| 1.0      | 09.05.2016 | Tanel Tammet, Ivo Mehide | Esimene versioon
| 1.1      | 28.12.2016 | Piret Elm, Vitali Stupin | Logimise välistuste täpsustamine

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sihtrühm](#sihtr%C3%BChm)
  * [Sissejuhatus](#sissejuhatus)
  * [Andmejälgija ülevaade](#andmej%C3%A4lgija-%C3%BClevaade)
  * [Milliseid andme-edastamisi ja -kasutamisi logida ja milliseid mitte](#milliseid-andme-edastamisi-ja--kasutamisi-logida-ja-milliseid-mitte)
  * [Variandid andmejälgija kasutuselevõtuks](#variandid-andmej%C3%A4lgija-kasutuselev%C3%B5tuks)
  * [Andmejälgija kasutuselevõtmise protsess](#andmej%C3%A4lgija-kasutuselev%C3%B5tmise-protsess)
  * [Tarkvara paigaldamine](#tarkvara-paigaldamine)
  * [Tarkvara ehitamine ja kohandamine](#tarkvara-ehitamine-ja-kohandamine)
  * [Andmejälgija haldustoimingud](#andmej%C3%A4lgija-haldustoimingud)  
  * [Infoturbe küsimused](#infoturbe-k%C3%BCsimused)
  
## Sihtrühm

Käesoleva juhendi sihtrühmaks on andmekogude omanikud, haldajad ning arendajad, kes
on huvitatud andmejälgija tarkvara kasutamisest oma tarbeks.

## Sissejuhatus

Isikuandmete kaitse seadus nõuab, et andmekogu haldaja peab vastama mistahes isiku arupärimisele
selle kohta, kellele tema isikuandmeid on edastatud. Samuti peab olema võimalik tagantjärele teada
saada, kelle poolt ja millal isikuandmeid töödeldi.

Andmejälgija on Riigi Infosüsteemi Ameti poolt tellitud, 2016 kevadel valminud vabavaraline
tarkvarasüsteem,  mille eesmärk on pakkuda andmekogude haldajatele paindlikud standardkomponendid 
eeltoodud nõuete täitmiseks: eeskätt isikuandmete töötlemise faktide logimiseks ning nende
faktide esitamiseks eesti.ee portaali kaudu.

## Andmejälgija ülevaade

Andmejälgija põhieesmärk on lõppkokkuvõttes pakkuda kõigile isikutele eesti.ee kaudu teenust,
kust isik saab asutuste kaupa vaadata, millal, kuhu ja miks on tema isikuandmeid edastatud.
Andmejälgija teine eesmärk on pakkuda andmekogule mugavad vahendid isikuandmete edastamise
ja töötlemise jälgimiseks asutusesiseselt.

Andmejälgija paigaldatakse ja konfigureeritakse iga andmekogu jaoks eraldi, mingit riiklikku keskset 
andmebaasi isikuandmete liikumise jaoks andmejälgija ette ei näe ega paku.

Andmejälgija ei ole üks monoliitne tarkvarasüsteem, vaid koosneb mitmest sõltumatust, kergesti
muudetavast komponendist, mida kasutades saab iga andmekogu enda jaoks võimalikult sobival viisil
oma andmejälgija süsteemi paigaldada või ehitada. 

Andmejälgija põhiosad on järgmised:

* Eraldusfilter - X-tee liikluse jälgija, mis tuvastab isikuandmete edasisaatmised X-tee kaudu.
* Andmesalvestaja - Isikuandmete liikluse logiandmebaas koos REST-liidesega, mida saab mistahes tarkvarast välja kutsuda.
* Sisekontrollija rakendus - Isikuandmete liikluse logiandmebaasi veebiliides asutuse sisekasutuseks: [vaata ekraanipilti](img/screenshot_andmesalvestaja.png)
* Kodaniku vaatamisrakendus - X-tee liides kodanike päringutele vastamiseks http://eesti.ee kaudu. Konkreetne ekraanipilt hetkel puudub, kuid vaata [testrakenduse ekraanipilti](img/screenshot_testrakendus.png).

Andmejälgija on realiseeritud Javas (võib töötada alates Java versioonist 1.6), andmebaasiks on Postgresql ja põhimõtteliselt võib teda installeerida nii otse X-tee turvaserverisse kui mõnda olemasolevasse serverisse koos teiste süsteemidega, või hoopis eraldi serverisse.

## Milliseid andme-edastamisi ja -kasutamisi logida ja milliseid mitte

Isikuandmete kaitse seadus sisaldab kahte punkti, mille lahendamise hõlbustamiseks andmejälgija
lahendus on loodud:

* Andmesubjekti soovil peab isikuandmete töötleja andmesubjektile teatavaks tegema kolmandad isikud, kellele tema isikuandmeid on edastatud. (IKS § 19 lg 1 p 5);

* Isikuandmete töötleja on isikuandmete töötlemisel kohustatud tagama, et tagantjärele oleks võimalik kindlaks teha, millal, kelle poolt ja milliseid isikuandmeid salvestati, muudeti või kustutati või millal, kelle poolt ja millistele isikuandmetele andmetöötlussüsteemis juurdepääs saadi. (IKS § 25 lg 2 p 3)

Sarnased nõuded tulenevad ka euroopa direktiividest:

*	Isikule peab olema võimaldatud: b. saada määratletud ajavahemike järel ning ilma liigse viivituseta või kuluta teavet, kas tema isikuandmeid säilitatakse automatiseeritud andmekogus ja saada neid andmeid talle mõistetavas vormis" (Euroopa Nõukogu Isikuandmete automatiseeritud töötlemisel isiku kaitse konventsioon, artikkel 8 "Lisatagatised andmesubjektile")

* Liikmesriigid tagavad, et igal andmesubjektil on õigus nõuda vastutavalt töötlejalt:
a) mõistliku aja tagant, ilma piiranguteta ja ilma liigsete viivituste ja kulutusteta:
- kinnitust selle kohta, kas isikut ennast käsitlevaid andmeid töödeldakse, ja teavet vähemalt töötlemise eesmärkide, asjaomaste andmete liikide ja nende vastuvõtjate või vastuvõtjate kategooriate kohta, kellele andmed avalikustatakse,
- arusaadaval kujul teavet töödeldavate andmete ja nende allika kohta," (Direktiiv 95/46/EU, artikkel 12)

Isikuandmetena käsitleb seadus mistahes andmeid isiku kohta - kaasa arvatud tema nimi ja sünniaeg - mitte ainult
tundlikke isikuandmeid.

Reaalsuses edastatakse isikuandmeid väga erinevatel viisidel. Näiteks: üksiku isiku kohta käiv teenus X-teel, 
sadu inimesi väljastav otsipäring X-teel, regulaarne mass-päring kümnete tuhandete isikute kohta
nende andmete ajutiseks puhverdamiseks, isikuandmeid sisaldavate PDF failide edasisaatmine,
isikuandmeid sisaldava andmebaasi kokkupakitud dumbi kopeerimine ftp kaudu jne.

Samuti toimub isikuandmete töötlemine väga mitmekesisel moel, muuhulgas on ka andmetele ligipääsu mõiste mitmeti arusaadav: 
see võib tähendada üksiku inimese andmete vaatamist, sadade inimeste loendi kiiret ülevaatust, tehnilisi ligipääsuõigusi jne.

Lihtsaid ja üheseid reegleid nimetatud seaduspunktide täitmiseks ei ole võimalik koostada: oluline on
lähtuda seaduse mõttest ning arvestada konkreetse andmekogu või infosüsteemi eripäradega.

Kõige üldisem põhimõte, millest lähtuda, on konkreetsele isikule teadliku tähelepanu pööramise või spetsiaalselt tema
andmete edasisaatmise talletamisvajadus, samal ajal kui inimese andmete liikumine või töötlemine nö taustandmetes, puhverdamiseks
vms ei nõua alati isikupõhist talletamist, küll aga edastamis/töötlemis-põhimõtete tutvustamist kõigile huvitatutele.

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

Isikuandmete kaitse seadus sisaldab teabe ja isikuandmete saamise õiguse piiranguid (IKS § 20 lg 1-4). Andmesubjekti õigust saada teavet ja enda kohta käivaid isikuandmeid isikuandmete töötlemisel piiratakse, kui see võib: 1) kahjustada teise isiku õigusi ja vabadusi; 2) ohustada lapse põlvnemise saladuse kaitset; 3) takistada kuriteo tõkestamist või kurjategija tabamist; 4) raskendada kriminaalmenetluses tõe väljaselgitamist.

Seetõttu tuleb andmejälgijat paigaldades seadistada selliselt, et andmejälgija ei jälgiks päringuid, mille toimumine on selliste politseiliste menetlustoimingute osa, mis peavad jääma varjatuks. Sellised päringud tuleb jälgimata jätta. Andmejälgija standardlahendus ei võimalda sellist filtrit, mis varjab üksnes käimasolevas kriminaalmenetluses (st üksnes kuritegude uurimisel) tehtud päringuid ning jälitustegevuses tehtud päringuid. Kui  Politsei- ja Piirivalveameti päring tehakse mingis muus menetluses (nt väärteomenetluses) või üldse menetluse väliselt, peab kodanik tegema tavapärase päringu jälitustegevuse õigusega seotud asutusse.

Jälitustegevuse õigusega asutused on üles loetletud Kriminaalmenetluse seadustiku ja teiste seaduste muutmise seaduses § 126<sup>2</sup> [https://www.riigiteataja.ee/akt/121032011002](https://www.riigiteataja.ee/akt/121032011002): Politsei- ja Piirivalveamet (70008747); Kaitsepolitseiamet (70000591); Maksu- ja Tolliamet (70000349); Justiitsministeeriumi (70000898) vanglate osakond ja vangla. Julgeolekuasutuste seaduses § 5. on toodud julgeolekuasutusena ka Teabeamet (70005938) [https://www.riigiteataja.ee/akt/117122015039#para28](https://www.riigiteataja.ee/akt/117122015039#para28). Asutuse valdkondlikus regulatsioonis võib olla täiendavaid osapooli, kelle andmekasutus ei tohi olla kodanikule ilmutatud. Abistava variandina osade päringute logimise keelamiseks saab tüüplahenduse juures kasutada jälgimisfiltri konfiguratsiooni osaks olevat nn blacklisti: vaata täpsemalt paigaldamisjuhendi peatükki "[Välistuste kirjeldamine](Paigaldamine.md#välistuste-kirjeldamine)" või välistused tuleb kirjeldada kodanikule andmete esitamise teenuses, mis on teenuse pakkuja realiseerida.

## Variandid andmejälgija kasutuselevõtuks

Andmejälgija on loodud olema võimalikult paindlik, tagamaks erinevate andmekogude spetsiifika jaoks
võimalikult mugavat ja lihtsat kasutuselevõtu viisi. Samuti on andmejälgija realiseeritud selliselt,
et teda on võimalik paigaldada ka otse X-tee turvaserverisse, segamata viimase tööd.

Andmejälgija andmebaas on alati seotud üheainsa andmekoguga: juba turvakaalutlustel ei tohi lubada
olukorda, kus üks andmejälgija salvestab andmeid erinevatest andmekogudest. 

Standardvariandina töötab andmejälgija selliselt, et tema eraldusfiltri komponent on infosüsteemi ja X-tee turvaserveri
vahel nö proxy-režiimis: infosüsteem edastab oma X-tee päringuid otse eraldusfiltri komponendile, mis siis 
omakorda edastab neid turvaserverile. Kui seos andmekogu ja turvaserveriga ei ole üksühene, ei tekita
see probleeme:

* Sama režiimi on võimalik kasutada ka juhul, kui üks X-tee turvaserver teenindab korraga mitut andmekogu: 
siis küll ei saa paigaldada andmejälgijat otse turvaserverisse.
* Samuti on lubatav olukord, kus üks andmekogu kasutab mitut turvaserverit: eraldusfiltri komponendid
tuleb sel juhul paigaldada iga turvaserveri ja andmekogu vahele.

Järgnevas esitame põhilised otsusekohad andmejälgija tehnilise kasutuselevõtu variantide jaoks.

Esimene otsusekoht tuleneb küsimusest, kas andmekogus on juba realiseeritud isikuandmete edastamise ja 
kasutamise logimine. 

* Kui jah, siis ei ole tingimata vaja teha muud, kui võtta andmesalvestaja
X-tee liidese alamkomponent, asendada selle lähtekoodis andmebaasiühendus ja SQL-päring olemasoleva logibaasi ja
temale vastava päringuga, kompileerida ja paigaldada komponent ning luua X-tee turvaserverisse vastav
teenus eesti.ee jaoks.

* Kui ei, siis kõige otsesemaks viisiks andmejälgijat kasutusele võtta on installeerida ja konfigureerida kõik tema komponendid:
sel juhul asub süsteem jälgima oma konfiguratsioonis seatud väljaminevaid sõnumeid X-teel, leiab sealt 
konfiguratsioonis antud teel asuva isikukoodi ja salvestab selle oma andmebaasi. Andmebaasiga on omakorda 
seotud X-tee teenus, mis vastab eesti.ee kaudu tulnud isikupõhistele päringutele. Lisaks sisaldab andmejälgija
sisekontrollija rakendust - asutusesiseseks kasutamiseks mõeldud lihtsat veebirakendust sellestsamast andmebaasist päringute tegemiseks.
Kõik need komponendid on paigaldatavad ilma arendustööta, küll aga nõuavad nad konfigureerimis- ja paigaldustöid
ja oskust seadistada X-tee turvaserverit, nõudes seega infosüsteemi haldava spetsialisti panustust.

Teine otsusekoht tuleb küsimusest, kas isikuandmete edastamine on üheselt seotud konkreetsete X-tee päringutega,
kus liiguvad korraga ainult ühe inimese andmed ja tema isikukoodi saab X-tee päringust leida, või ei ole X-tee
päringutest üheselt võimalik isikuid leida.

* Kui isikuandmeid edastatakse X-teel ja selliselt, et seal liiguvad korraga ainult ühe inimese andmed ja 
tema isikukoodi saab X-tee päringust leida, tasub paigaldada ja konfigureerida andmejälgija eraldusfiltri komponent 
X-tee liikluse jälgimiseks. See nõuab konfigureerimistöid, kuid mitte arendustöid.

* Kui X-tee lihtne jälgimine ei ole võimalik, saab andmejälgijat kasutada selliselt, et ehitada andmekogu infosüsteemi
lihtsad HTTP(S) põhised REST-päringud, millega isikuandmete edastamise või töötlemise fakt saadetakse otse
andmejälgija andmesalvestaja komponendi andmebaasi. See nõuab arendustöid.

Mõistlik võib olla ka stsenaarium, kus kasutatakse nii eraldusfiltri komponenti valitud X-tee teenuste
jälgimiseks, kui REST teenuseid otse infosüsteemist logikirjete salvestamiseks andmejälgija baasi.

Kolmas otsusekoht on küsimus, kas kasutada andmejälgija oma andmebaasi (Postgresql) või asutuses juba
olemasolevat muud andmebaasi, ning millisesse serverisse andmejälgija paigutada:

* Üks võimalus on paigaldada andmejälgija valitud komponendid otse samasse serverisse, kus töötab X-tee turvaserver,
kasutades seejuures (uue X-tee versiooni korral) otse X-tee poolt kasutatavat Postgresql andmebaasi.

* Teine võimalus on paigaldada andmejälgija komponendid muudesse serveritesse: nende ressursinõudlus on väike
ja nad ei vaja tingimata omaette serverit.

* Kolmas võimalus on mitte paigaldada eraldi andmebaasi-serverit andmejälgija jaoks, vaid kasutada mõnda andmekogu 
infosüsteemis olevat andmebaasi. Eriti lihtne (ei vaja arendustööd) on see juhul, 
kui andmebaas on realiseeritud Postgresql-l. Vastasel korral võib tekkida vajadus teha väikeses mahus
arendust, muutmaks ära andmejälgija komponentides olevad SQL-päringud ja kompileerimaks andmejälgija uuesti.

Neljas otsusekoht on küsimus, kas ja kuidas paigaldada ja konfigureerida sisekontrollija rakendus - asutuse sisekasutuseks 
ettenähtud veebirakendus, mis võimaldab mugavalt otsida andmejälgija andmebaasis olevaid kirjeid:

* Kui asutuses on juba olemas oma isikuandmete liikumise/kasutamise logisüsteem koos mugava võimalusega
sealt otsinguid teha, ei ole mainitud komponenti mõtet installeerida.
* Kui taolist süsteemi ei ole, on komponendi installeerimine tõenäoliselt mõttekas. Sel juhul on tingimata
vaja konfigureerida selle komponendi veebiserver selliselt, et rakenduse API-dele ei oleks välist ligipääsu
ning asutusesiselt pääseks ligi ainult piiratud hulk töötajaid.


## Andmejälgija kasutuselevõtmise protsess

Eelmisest punktist lähtuvalt on kasutuselevõtu protsess erinevate andmekogude jaoks küllalt erinev.
Sellegipoolest toome välja üldjuhul vajalikud põhietapid koos nende umbkaudse ajahinnanguga:

1.	Leppida asutusesiseselt kokku konkreetne meeskond, kes juurutuse läbi viib, koos esialgse ajakavaga. 
Kindlasti on vaja, et meeskonnas oleks:
  * Põhimõtete otsustaja: millise teenuse/kasutuse külge andmejälgija liidestada jms.
  *	IT süsteemide haldaja, kes mh konfigureerib X-tee liideseid ja haldab infosüsteeme, mille külge andmejälgija liidestub.
Soovitav oleks, et meeskonnas on ka:
  * Arendaja, kes vajadusel liidestatavale infosüsteemile liidestusi ja täiendusi ehitab.

2.	Otsustada, milliste teenuste, millise olemasoleva tarkvara või milliste olemasolevate logibaaside külge andmejälgijat liidestada;  samuti, millised andmejälgija komponendid kasutusele võtta. Töö võiks võtta mõnest tunnist kuni ca ühe päevani. Siin etapis on vaja 
  * Aru saada andmejälgija põhimõtetest ja võimalustest.
  * Arutada variante teenuse/infosüsteemi haldaja ja/või arendajaga.

3.	Otsustada, millistesse olemasolevatesse serveritesse süsteemi komponendid paigutada, või kas hankida uus server. Töö võiks võtta mõne tunni ja eeskätt on otsustajaks asutuse IT süsteemide haldaja, kes peab selleks hetkeks olema kursis andmejälgija põhimõtete ja eelneva punkt 2 otsusega.

4.	Installeerida andmejälgija valitud komponendid.  Töö võiks võtta mõne tunni kuni ca ühe päeva ja realiseerijaks on asutuse IT süsteemide haldaja, keda võib vajadusel toetada arendaja.

5.	Konfigureerida andmejälgija valitud komponendid tööle ja testida nad läbi. Töö võiks võtta mõne tunni ja realiseerijaks asutuse IT süsteemide haldaja, keda võib vajadusel toetada arendaja.

6.	Valikuliselt: konfigureerida asutuse X-tee liidest, kui see osutub valitud liidestusviisi jaoks vajalikuks. Töö võiks võtta mõne tunni ja realiseerijaks isik, kes on seni asutuse X-tee liideseid konfigureerinud.

7.	Valikuliselt: realiseerida eriliidesed oma infosüsteemides andmejälgija külge, kui etapis kaks on nii otsustatud. Andmejälgija ei vaja otseselt eriliideseid, seega see etapp üldse  ja tema maht sõltub täielikult konkreetse asutuse vajadustest ja otsustest. Siin on tarvilik asutuse IT arendaja töö. Kui neid töid teha, võiks mahuks planeerida ca ühe tööpäeva.

8.	Valikuliselt: teha andmejälgija standardkomponentides muutusi ja täiendusi,  kui etapis kaks on nii otsustatud. Jällegi, andmejälgija ei vaja otseselt muutusi ja täiendusi – kuigi ta on realiseeritud selliselt, et seda oleks lihtne teha - seega see etapp üldse  ja tema maht sõltub täielikult konkreetse asutuse vajadustest ja otsustest. Siin on tarvilik asutuse IT arendaja töö. Kui neid töid teha, võiks mahuks planeerida ühe või paar tööpäeva.

9.	Töötav süsteem tervikuna läbi testida. Spetsiaalseid formaalseid testsüsteeme ei ole siin vaja kasutada, tuleks aga lihtsalt tuvastada, et ettenähtud funktsionaalsus töötab ja ei paista ette-ennustamatuid kõrvalefekte. Siin võiks osa võtta asutuse IT põhimõtete otsustaja ja IT süsteemide haldaja, mahuks planeerida mõned tunnid kuni päeva.

10.	Leppida asutusesiseselt kokku andmejälgija haldamise töökorraldus: milline IT haldaja kontrollib tema töökorda, teeb tagavarakoopiaid, jälgib, et ketas ei saaks täis jne.


## Tarkvara paigaldamine

Andmejälgija komponentide paigaldamise, eemaldamise ja konfigureerimise tegevused on kirjeldatud eraldi dokumendis 
[Tarkvara paigaldamise juhend](Paigaldamine.md)

# Tarkvara ehitamine ja kohandamine

Andmejälgija komponentide lätekoodist kompileerimise ja ehitamise tegevused on kirjeldatud eraldi dokumendis 
[Tarkvara ehitusjuhend](Ehitusjuhend.md)

Andmejälgija komponentide kohandamise ehk lähtekoodi muutmise ja edasiarendamise võimalused on kirjeldatud eraldi dokumendis 
[Tarkvara kohandamise juhend](Kohandamine.md)

## Andmejälgija haldustoimingud

Paigaldatud ja konfigureeritud andmejälgija ei vaja palju haldustoiminguid. Siiski tuleb regulaarselt teha harilikke
tegevusi:

* Kontrollida, et andmejälgija andmebaas ei hakka lõpuni täitma serveri kettaruumi.
* Kontrollida, et andmejälgija regulaarselt andmeid ka salvestab ning eesti.ee-st on salvestatud andmed kättesaadavad.
* Kontrollida, et andmesalvestaja liidesed ei ole serverite konfiguratsioonide muutmise käigus asutuseväliselt kättesaadavaks tehtud.
* Uute isikuandmeid edastavate X-tee teenuste ehitamise või muude uute isikuandmete töötlemisvõimaluste ehitamise korral
lisada ka need andmejälgijasse.
* Perioodiliselt kustutada aegunud logiridu.

### Aegunud andmejälgija logiridade kustutamine

Aegunud andmejälgija logiread tuleb kustutada andmebaasist SQL käsu abil. Kõige lihtsamal juhul saab ridu kustutada käsuga:

```sql
delete from ajlog where logtime < to_date('{kuupaev}', 'YYYY-MM-DD');
```

Seal {kuupaev} tuleb asendada kuupäevaga kujul AAAA-KK-PP.

Andmesalvestaja komponendi andmebaasiskriptide hulgas leidub ka kaks käsurea skripti vanade kirjete haldamiseks PostgreSQL andmebaasi korral.

* Skript archive-old-records.sh väljastab vanad kirjed stdout-i. Vajdusel tuleb suunata skripti väljund faili.
* Skript delete-old-records.sh kustutab andmebaasist vanad kirjed.

Mõlemad skriptid vajavad parameetrina eelpool toodud kujul kuupäeva. Andmebaas ja autentimisinfo tuleb skriptidele
ette anda PostgreSQL standardsete keskkonnamuutujate PGDATABASE, PGHOST, PGPORT, PGUSER, PGPASSWORD abil.

## Infoturbe küsimused

Üldise põhimõttena andmejälgija paigaldamisel tuleks lähtuda sellest, et andmejälgija on põhiandmekogu osa, 
mitte eraldiseisev süsteem. Seega ei nõua ta ka omaette ISKE analüüsi, omaette X-tee süsteeme jne. 

Sellest tulenevalt ei saa aga üks andmejälgija teenindada korraga mitut andmekogu.

Andmejälgija paigaldamisel tuleb seda teha selliselt, et andmesalvestaja komponendi erinevad teenused:
* andmete salvestamise REST liides
* sisekontrollija rakenduse päringu REST liides
* X-tee turvaserveri SOAP liides
ei oleks ligipääsetavad asutusest/andmekogust väljapool.

Sisekontrollija rakenduse paigaldamise korral tuleb server seadistada selliselt, et ligipääsud REST teenusele
oleks võimalikud ainult selleks konkreetse õiguse saanud töötajatele. Rakendus ise selleks mingeid
vahendeid ei paku, eeldades ligipääsu piiramist veebiserveri standardvahenditega: 
vaata täpsemalt paigaldamisjuhendi peatükki "Autentimise häälestamine".

Rõhutame, et palju olulisem, kui veebirakendusele endale ligipääsu piiramine, on eelpool mainitud
andmesalvestaja komponendi teenustele ligipääsu piiramine.
