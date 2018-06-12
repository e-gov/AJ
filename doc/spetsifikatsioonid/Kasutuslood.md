# Andmejälgija kasutuslood

X-tee andmejälgija analüüs ja disain

Versioon 1.1, 13.06.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ ja FocusIT OÜ

![EL Regionaalarengu Fond](../img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

## 1. Dokumendi ajalugu

| Versioon | Kuupäev | Autor | Märkused |
| --- | --- | --- | --- |
| 1.0 | 20.12.2015 | Tanel Tammet | Esimene versioon |
| 1.1 | 13.06.2016 | Tanel Tammet | Uuendused ja täpsustused peale süsteemi valmimist 

## 2. Sisukord

  * [1\. Dokumendi ajalugu](#1-dokumendi-ajalugu)
  * [2\. Sisukord](#2-sisukord)
  * [3\. Sissejuhatus](#3-sissejuhatus)
  * [4\. Kasutuslood](#4-kasutuslood)
    * [4\.1\. eesti\.ee kaudu lähenev lõppkasutaja](#41-eestiee-kaudu-l%C3%A4henev-l%C3%B5ppkasutaja)
    * [4\.2\. Asutuse sisekontroll](#42-asutuse-sisekontroll)
    * [4\.3\. Asutuse IT\-administraator](#43-asutuse-it-administraator)
    * [4\.4\. Asutuse IT\-süsteemi arendaja](#44-asutuse-it-s%C3%BCsteemi-arendaja)
    * [4\.5\. eesti\.ee administraator](#45-eestiee-administraator)
  * [5\. Liidestuvate andmekogude ja huvipoolte kasutuslood: koondid intervjuudest](#5-liidestuvate-andmekogude-ja-huvipoolte-kasutuslood-koondid-intervjuudest)
    * [5\.1\. Andmekaitse inspektsioon](#51-andmekaitse-inspektsioon)
    * [5\.2\. E\-tervise sihtasutus](#52-e-tervise-sihtasutus)
    * [5\.3\. Haigekassa](#53-haigekassa)
    * [5\.4\. Töötukassa](#54-t%C3%B6%C3%B6tukassa)
    * [5\.5\. Sotsiaalministeerium](#55-sotsiaalministeerium)
    * [5\.6\. RIK](#56-rik)
    * [5\.7\. Elering](#57-elering)


## 3. Sissejuhatus

Käesolev dokument kajastab tarkvaralise lahenduse "Andmejälgija" planeeritud kasutuslugusid, mitte reaalselt kasutusele läinud andmejälgija kogemusi.

Süsteemis on mitu erinevat kasutajate tüüpi:

- eesti.ee kaudu lähenev lõppkasutaja
- asutuse sisekontroll, kes otsib logikirjeid andmejälgija andmebaasist veebiliidese kaudu (sisekontrollija rakenduse kaudu)
- asutuse IT-administraator, kes süsteemi üles seab, konfigureerib ja jälgib töökorda
- (valikuliselt) asutuse IT-süsteemi arendaja, kes kutsub välja andmesalvestaja API-t sisekasutuseks või realiseerib otse X-tee teenuse eesti.ee jaoks
- eesti.ee poolne administraator, kes lisab eesti.ee-sse andmejälgija üldlehe ja liidestab sellega erinevaid andmekogusid, kes selleks soovi avaldavad

Dokumendi lõpus on toodud ka andmejälgija analüüsi projektis piloteerinud asutustega läbiviidud intervjuude baasilt koostatud neile vajalikud kasutuslood.

## 4. Kasutuslood

### 4.1. eesti.ee kaudu lähenev lõppkasutaja

Süsteemi põhikasutaja on eesti.ee portaali sisenenud isik. Enamus portaali kasutajaid andmejälgijasüsteemi lehtedeni ei jõua / ei oska neid otsida / ei pea oluliseks. Kasutajad jõuavad süsteemi lehtedeni tüüpiliselt selliselt, et kuulevad taoliste lehtede olemasolust kas meediast, tuttavatelt või riigiasutusest.

Süsteemi lehtedel on eesti.ee-s kergesti kopeeritav URL, mida saab saata eelnimetatud kanalites kasutajatele. Süsteemi menüüde kaudu saab lehti leida nii sisse logituna kui mitte sisse logituna. Asukoht üldmenüüdes on "E-teenused/kodanikule/Riik ja kodanik" loetelus.

Peale andmejälgijasüsteemi lehtede leidmist on kasutusel veel üks kahest võimalusest:

(a) kasutaja näeb liitunud asutusi/andmekogusid ka ilma sisse logimata (parem variant)

(b) kasutajalt nõutakse sisselogimist ja siis näeb liitunud asutusi/andmekogusid

Nõutav variant otsustatakse RIA poolt koostöös eesti.ee-ga.

Andmejälgija süsteemi avalehel näeb kasutaja paarilauselist süsteemi kirjeldust ja loetelu liitunud asutustest/andmekogudest. Klikkides mõnele neist näeb ta paarilauselist selgitust: miks ja kuidas see andmekogu isikuandmeid kasutab, kuhu ta neid saadab ja kontaktinfot lisaselgituste küsimiseks. Samuti näeb ta nuppu oma andmesaatmiste/kasutuste otsinguks.

Nupule vajutades kuvatakse kasutajale isikuandmete kasutuse loetelu alates ajaliselt kõige hilisematest: millal/kuhu/mis teenuse kaudu on andmeid saadetud või millal/mille jaoks/kelle poolt on andmeid kasutatud. 
Lehe allosas kuvatakse navigeerimisnupud, mille abil ta saab liikuda järgmise lehekülje info peale ja uuesti tagasi.

Kasutaja saab seejärel liikuda lihtsasti andmejälgija avalehele.

Andmejälgija avalehelt peab olema võimalik saada infot, kuidas saab kodanik infot mitteliitunud andmekogudes toimunud isikuandmete töötlemise kohta. See info tuleb esitada kas eraldi veebilehega või paarilauselise selgitusega.

### 4.2. Asutuse sisekontroll

Asutuse sisekontrollija on ametnik, kellel on parasjagu ülesandeks kas:

1. Vastata isikute päringutele (telefonitsi, e-postiga, kirjaga) tema isikuandmete töötlemise kohta. Sellist küsimust esitatakse seni üldjuhul vaid väga väikese hulga asutuste kohta. Küll aga võib küsimuste hulk oluliselt kasvada peale andmejälgija süsteemi käivitamist ja laiemat tutvustamist.

2. Teostada pistelist sisekontrolli isikuandmete sisekasutuse kohta. Seda tehakse üldjuhul asutustes, kus andmejälgija on API kaudu integreeritud infosüsteemiga ning on võimalik tuvastada isikuandmete tava-kasutamist infosüsteemi sees.

Kummalgi juhul avab sisekontrolli veebilehitsejas andmejälgija sisekontrolli rakenduse: URL on pandud kas asutuse siseportaali või saadetud varem e-postiga.

Sisekontrollija saab üldjuhul andmejälgijale ligi vaid asutuse sisevõrgust.

Sisekontrollija peab ennast süsteemi jaoks autentima. Autentimise konkreetne viis sõltub süsteemi paigaldusvalikutest.

Autendituna näeb sisekontrollija vormi, kus on (a) viit kasutusjuhendile, (b) kus saab täita kõiki andmejälgija infovälju, ning (c) "otsi" nuppu. Viimasele vajutades kuvatakse lehekülgedeks jagatuna veergude kaupa sorteeritav loetelu isikuandmete töötlemistest. Igale kirjele saab vajutada, et näha kogu detailinfot.

Sisekontrollija kirjeid muuta, kustutada ega lisada ei saa.

### 4.3. Asutuse IT-administraator

Asutuse IT-administraatori ülesanded on:

1. andmejälgija süsteemi paigaldamine;
2. eesti.ee-le vajaliku info saatmine andmejälgija süsteemi liidestamiseks eesti.ee süsteemiga;
3. regulaarselt andmejälgija süsteemi töövõime monitoorimine, sh vealogide vaatamine;
4. jälgimine, et süsteemi andmebaas ei oleks liiga mahukas ega täidaks serveri ketast. Liigse mahukuse korral peab administraator vanad andmed arhiivifaili kopeerima ja andmebaasist kustutama;
5. andma andmejälgija sisekontrolli eest vastutavatele ametnikele (kui neid on) selgitusi andmejälgija süsteemi põhimõtete kohta;
6. anda vajadusel eesti.ee haldajale täiendavat infot asutuse/andmekogu isikuandmete töötlemise põhimõtete kohta.

### 4.4. Asutuse IT-süsteemi arendaja

Asutuse IT-süsteemi arendaja ülesanneteks on:

1. vajadusel andmejälgija süsteemi paigaldamine (juhul, kui asutuses on see IT-administraatorilt delegeeritud arendajale)
2. vajadusel asutuse infosüsteemile andmejälgija API liidese arendamine
3. vajadusel sisekontrolli eriliideste arendamine või muu spetsiifiline andmejälgijaga seotud muutmissoov

### 4.5. eesti.ee administraator

eesti.ee administraatoril on järgmised ülesanded:

1. andmejälgija üldlehtede paigaldamine süsteemi
2. liituda soovijatele täidetava liitumisvormi saatmine
3. liituda sooviva andmekogu liidestamine eesti.ee keskkonda
4. liidestatud andmekogu poolt esitatud jooksvatele küsimustele vastamine

## 5. Liidestuvate andmekogude ja huvipoolte kasutuslood: koondid intervjuudest

Esitame lühidalt intervjueeritavate asutuse põhipunktid andmejälgijaga seoses. Intervjuude detailid on esitatud eraldi failides. Antud kasutuslood pole nõutud otse ja vahetult realiseerida, vaid on realiseeritavad võimalikult suures mahus eelpool kirjeldatud kasutuslugude kaudu.

### 5.1. Andmekaitse inspektsioon

Andmekaitse inspektsioon on antud kontekstis huvitatud eeskätt sellest, et isikud saaksid võimalikult palju adekvaatset infot selle kohta, kuidas nende andmed andmekogudes liiguvad ning kus/miks neid kasutatakse.

Seejuures on huvipakkuvad nii konkreetsed ühe inimese andmete edasisaatmised ja ametniku poolt kasutamised (vaatamised, töötlemised) kui ka mass-saatmised ja kasutajaliideses loetlemised. Viimastel juhtudel tuleks võtta põhimõtteks, et:

* Isik saaks infot, et asutus X saadab vahel/regulaarselt suure hulga isikuandmeid asutusele Y ja Z, kuid ta ei pea nägema, kas tema enda andmed on mingi sellise hulga sees ja millal neid saadeti. Isikukoode ei pea sellise sündmuse juures logima.

* Kui aga eelmises punktis kirjeldatud isikuandmete kogus on väiksem, kui mingi N (vahemikus ca 2-50), siis tuleks logida iga saatmine iga isikukoodi korral eraldi.

Eesti.ee-s võiks kuvada infot eeskätt andmekogude kaupa ja kuvada seejuures lisainfot,  linke ja teisi asutusi, kellele andmeid edastatakse.

### 5.2. E-tervise sihtasutus

E-tervises on realiseeritud põhjalik ja läbimõteldud isikuandmete töötlemise logimine ning selle kuvamine patsiendiportaali sisseloginud isikutele. Teatud päringuid siiski ei logita.

E-tervise infosüsteemi kogemust saab võtta arvesse standardlahenduse koostamisel.

eesti.ee jaoks liides puudub, selle loomine oleks E-tervise jaoks pluss. Liides loodaks olemasoleva andmete liikumise logiandmebaasi pealt analoogiliselt juba olemasolevale süsteemile.

### 5.3. Haigekassa

Haigekassa teenuste ja neis tehtavate päringute arv on suur, koormusprobleemid on olulised.

Hetkel tehakse automaatlogimist nii X-tee teenuste sissetulevatelt sõnumitelt, vaadates nende päist ja keha, kui infosüsteemi siseselt, kasutajainterfeisi tegevuste peale. Logi salvestatakse andmebaasi. Logi uuritakse päringute (helistamine ja kirjutamine) alusel ametnike poolt ning see on küllalt sage tegevus.

Praegust logimissüsteemi saaks täiendada infosüsteemi siseselt.

Loodavas standardlahenduses aitaks eesti.ee teenuse komponent, kui see ehitada juba olemasoleva logiandmebaasi peale.

### 5.4. Töötukassa

Töötukassa informatsiooni kasutavad paljud muud süsteemid, näiteks STAR, ning paljude ametnike poolt. Reeglina tegu korraga ühe inimese andmete liigutamisega.

EI ole realiseeritud süstemaatilist isikuandmete kasutuse logi. Ei ole kogemust, et kodanikud uuriks oma isikuandmete kasutamist.

Ametnikud delegeeriks logimise ülesande ja problemaatika arendajale.

Võiks kasutada kas filterserveri põhist lahendust või infosüsteemi sisest lahendust: lisahinnangu saaks anda senine arendaja (Nortal). X-tee teenuseid realiseerib Nortali arendatud adapterserver.

### 5.5. Sotsiaalministeerium

Intervjuul kajastatud info STAR andmekogust. STAR on kohalike omavalitsuste töövahend erinevate sotsiaalteenuste osutamise toetamiseks. Keskkonda kasutatakse peamiselt veebipõhise interfeisi kaudu, olemas on ka mõned X-tee teenused

Põhimõtteliselt on X-tee teenuste kaudu võimalik küsida kahte infot: hooldaja hooldusi (kasutab Sotsiaalkindlustusamet) ning isikule toimetulekutoetuse määramist (kasutab Töötukassa). Mõlema info korral on realiseeritud üksikpäringud, kus sisendis isikukood (hoolduste korral hooldaja isikukood, toimetulekutoetuste korral isiku isikukood) ning väljundis soovitud andmed.

Hetkel on realiseerimisel masspäringute tegemise võimalus.

Eelistaksid eraldi filtri varianti, kuid siis on vaja kuidagi lahendada masspäringute logimise probleem. Nende keskkonda tundub arhitektuuriliselt kõige paremini sobivat andmejälgija sõltumatu ja eraldiseisva komponendina realiseerimine.

Andmejälgija jaoks on vaja igal juhul teostada täiendavad arendused info logimiseks, kuna masspäringutega kokkupandud failide sisu pole võimalik hiljem enam analüüsida.

### 5.6. RIK

Registrite suur valik: kõik justiitsministeeriumi 25 andmekogu X-tees (RIHAs) ja 80 registrit

on RIKi hallata.

KARi andmete poliitikas on kaks taset:

- kehtivad andmed (reeglina kuni 1 aasta peale täitmist)

- kustutatud ehk arhiveeritud andmed (julgeolekuorganid saavad vaadata)

Üldjuhul näed, et kes on sinu kohta andmeid pärinud ehk kes on sinu isikukoodiga andmeid mis teenusega vaadanud.

KAR koostab ja saadab vormi kaudu tulnud päringule vastuseks PDF dokumendi.

Sisemisi tegevusi logitakse, aga ei näidata välja.

Mitte-sisemised vaatamised tulevad üle X-tee (e-toimik, MISP, e-toimiku klientsüsteemid jms, kolmandad asutused üle X-tee).

### 5.7. Elering

2015 novembri algusest alustati uue infosüsteemi faasi käivitamisega: põhineb X-tee tehnoloogial ja võimaldab rakenduste paljusust. Igal müüjal saab olla oma rakendus, mis küsib Eleringilt API kaudu tarbimisandmeid.

Tahaks anda inimestele infot selle kohta, kellele on andmeid antud.

Pigem eelistataks rest API tasemel päringu tegemist andmete liikumise salvestamiseks.

Pigem ei tahaks kasutada andmejälgija filtri lahendust, vaid  after-the-fact logi parsimist. Igal logireal on isikukood.
