### Andmejälgija turvaanalüüs

X-tee andmejälgija analüüs ja disain

Versioon 1.0.2, 20.03.2017

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ ja FocusIT OÜ

![](../img/EL_Regionaalarengu_Fond_horisontaalne.jpg "Euroopa Liit | Euroopa Regionaalarengu Fond | Eesti tuleviku heaks")

#### 1	Dokumendi ajalugu

Tabel 1 - Dokumendi muudatuste ajalugu

Versioon	Kuupäev	Autor	Märkused
- 1.0	11.12.2105	Janek Part, Valdo Praust	Esimene versioon
- 1.0.1 31.05.2016 Priit Parmakson Teisendus Markdowni
- 1.0.2 20.03.2017 Vitali Stupin Parandatud Markdowni vead, Lisatud EL logo

#### 2	Kokkuvõte
Käesoleva projektiga disainitakse ja spetsifitseeritakse isikuandmete kasutamise jälgimise tarkvaraline tüüplahendus (andmejälgija). Andmejälgija on mõeldud kasutamiseks andmekogu omanikule, kes seadusest tulenevalt peab kodanikule jagama informatsiooni tema andmete edastamise, salvestamise, muutmise ja kustutamise kohta (vt punkti 7.1). 

Antud dokument keskendub turvaeesmärkidele, mida andmekogu omanik peab jälgima, et andmejälgijasse salvestatavat infot hoitaks ja käideldaks turvaliselt. Metoodiliselt on analüüsi aluseks võetud ISKE versioon 7.00. 

Dokument on suunatud andmekogu omanikule, et aidata tal paigaldada andmejälgija kõiki ISKE nõudeid jälgides ning koostada infoturbe meetmete ja ohtude nimekiri. 

Turvalisuse osas soovitame andmekogu omanikul pöörata eeskätt tähelepanu punktis 10 Usaldusalad käsitletud teemadele ning tagada organisatoorsete ja/või tehniliste meetmetega, et võimalikud riskid saaksid maandatud.

#### 3	Sisukord

1.	Dokumendi ajalugu
2.	Kokkuvõte
3.	Sisukord
4.	Tabelid ja joonised
5.	Sissejuhatus
6.	Turvatavad infovarad
6.1.	Infovarade täiendamine
7.	Turvaeesmärgid
7.1.	Õiguslikud nõuded
7.2.	Lepingulised nõuded
7.3.	Tagajärgede kaalukuse nõuded
7.4.	Turvaeesmärgid terviklusele (T), konfidentsiaalsusele (S) ja käideldavusele (K)
8.	Ohtude (rünnete) klassifikatsioon
8.1.	Asjakohaste ohtude klassifikatsioon „Turvaserveri sees olev proxy“ ja „Turvaserverist eraldiseisev proxy“ lahenduse korral
9.	Turvaeeldused
9.1.	Asjakohaste turvameetmete ülevaade „Turvaserveri sees olev proxy“ ja „Turvaserverist eraldiseisev proxy“ lahenduse korral
10.	Usaldusalad
11.	Lahenduse turvaelemendid
12.	Protokollide puhul – turvaomadused
13.	Seosed ülalnimetatud elementide vahel
14.	Seotud dokumendid
14.1.	Alamdokumendid
14.2	Viited dokumentidele

#### 4	Tabelid ja joonised

- Tabel 1 - Dokumendi muudatuste ajalugu
- Tabel 2 - Andmejälgija infovarad

#### 5	Sissejuhatus

Antud dokument kuulub riigihanke “X-tee andmejälgija analüüs ja disain” (viitenumber 164646) tulemitena üleantavate dokumentide koosseisu. Turvaanalüüsi koostamisel on arvesse võetud järgmiseid dokumente:
•	andmejälgija tehniline kontseptsioon;
•	eraldusfiltri disainilahendus; 
•	andmesalvestaja disainilahendus;
•	andmejälgija õiguslik analüüs.

Eeldus. Antud analüüsi eelduseks on võetud olukord, kus iga andmekogu kohta on paigaldatud eraldi eraldusfilter ja andmesalvestaja. Eraldusfiltri ja andmesalvestaja võib paigaldada ka mitme andmekogu üleselt, kuid turvakaalutlustel soovitame sellist olukorda võimaluse korral vältida (kasutajate ja andmete eraldatus, andmete kuhjumine ja 5 printsiip ).

Vastavalt lähteülesandele käsitletakse turvaanalüüsis järgmisi elemente: turvatavad infovarad; turvaeesmärgid; ohtude (rünnete) klassifikatsioon; turvaeeldused; usaldusalad; lahenduse turvaelemendid; protokollide puhul – turvaomadused; seosed ülalnimetatud elementide vahel. 

Metoodikana on kasutatud turvaanalüüsis ISKE versioon 7.00 rakendusjuhendit ja ISKE kataloogi, sest selle etalonturbe süsteemi juurutamine on kohustuslik kõikides riiklikes andmekogudes. Samuti kõnetab antud metoodika kõiki andmekogude omanikke ning selle baasil turvateema käsitlemine vähendab oluliselt lisanduvate juurutustööde mahtu.

#### 6	Turvatavad infovarad

Analüüsi tulemusena otsustati realiseerida eraldusfilter proxy põhimõttel selliselt, et teda saaks paigutada nii turvaserveri sisse kui turvaserverist välja.

Andmekogu pidajal on ISKE kohaste infovarade nimekirja vaja täiendada kasutusele võetava riistvara, tarkvara ja võrguühenduste vaates nii, nagu seda nõuab ISKE juurutamise samm 1.

#### 6.1	Infovarade täiendamine

Vastavalt valitud lahendusele tuleb varade kaardistamise juures arvestada järgnevat:

Tabel 2 - Andmejälgija infovarad

Varad	| Turvaserverist eraldiseisev proxy	| Turvaserveri sees olev proxy
----|----|----
Riistvara	| Eraldi riistvara või virtualiseerimine	| Turvaserveri riistvara, varad ei muutu
Virtualiseerimine	| Kasutatakse vajadusel virtualiseerimist	| Varad ei muutu
Tarkvara	| Serveri Linux või MS platvorm -	Asjakohane MS või UNIX moodul - Andmebaasi platvorm	| Varad ei muutu
Andmebaas	| Väline andmebaas	| Turvaserveri baas, varad ei muutu
Rakendused	| -	Andmejälgija veebirakendus -	ID-kaardiga autentimine	| -	Andmejälgija veebirakendus -	ID-kaardiga autentimine
Võrguühendused	| Lisaühendused switchis. Täiendused/muudatused tulemüüris	| Kasutab infosüsteemiga suhtlemiseks samu porte, mis turvaserver.
Moodulid, millega tuleb arvestada kas täies mahus või osade ohtude meetmete mahus	| -	B 1.3. Hädaolukorraks valmisoleku kontseptsioon -	B 1.8 Turvaintsidentide käsitlus -	B 1.9 Riist- ja tarkvara haldus -	B 1.14 Turvapaikade ja muudatuste haldus -	B 3.101 Server -	B 3.304 Virtualiseerimine -	Asjakohane B3 IT-süsteemid kihi moodul(i )-	B 5.4 Veebiserver -	B 5.7 Andmebaasid -	B 5.21 Veebirakendused -	B 5.22 Logimine -	B 5.E2 ID-kaart/PKI	| -	B 1.3. Hädaolukorraks valmisoleku kontseptsioon -	B 1.8 Turvaintsidentide käsitlus -	B 1.9 Riist- ja tarkvara haldus -	B 1.14 Turvapaikade ja muudatuste haldus -	B 5.4 Veebiserver -	B 5.21 Veebirakendused -	B 5.22 Logimine -	B 5.E2 ID-kaart/PKI
 
#### 7	Turvaeesmärgid

Vastavalt ISKE juurutamise sammule 2 peab andmekogu omanik vajadusel täiendama turvaosaklassi analüüsi ja määramise dokumenti. Arvestama peab olemasolevate õiguslike nõuete täitmisega. 

#### 7.1	Õiguslikud nõuded
Antud punktis on välja toodud õiguslikud nõuded, mis on olulised turvaklassi määramise seisukohast. Andmejälgija kohta tervikuna on koostatud samuti õiguslik analüüs (vt dokumenti „Andmejälgija õiguslik analüüs“).

(a)	Isikuandmete kaitse seadus (IKS)
§ 19. Andmesubjekti õigus saada teavet ja tema kohta käivaid isikuandmeid
(1) Andmesubjekti soovil peab isikuandmete töötleja andmesubjektile teatavaks tegema:
1) tema kohta käivad isikuandmed;
2) isikuandmete töötlemise eesmärgid;
3) isikuandmete koosseisu ja allikad;
4) kolmandad isikud või nende kategooriad, kellele isikuandmete edastamine on lubatud;
5) kolmandad isikud, kellele tema isikuandmeid on edastatud;
6) isikuandmete töötleja või tema esindaja nime ning isikuandmete töötleja aadressi ja muud kontaktandmed.
(2) Andmesubjektil on õigus saada isikuandmete töötlejalt enda kohta käivaid isikuandmeid. Isikuandmed väljastatakse võimaluse korral andmesubjekti soovitud viisil. 

§ 20. Teabe ja isikuandmete saamise õiguse piirangud
(1) Andmesubjekti õigust saada teavet ja enda kohta käivaid isikuandmeid isikuandmete töötlemisel piiratakse, kui see võib;
3) takistada kuriteo tõkestamist või kurjategija tabamist;
4) raskendada kriminaalmenetluses tõe väljaselgitamist.

§ 25. Isikuandmete organisatsioonilised, füüsilised ja infotehnilised turvameetmed
(2) Isikuandmete töötleja on isikuandmete töötlemisel kohustatud:
3) ära hoidma isikuandmete omavolilist salvestamist, muutmist ja kustutamist ning tagama, et tagantjärele oleks võimalik kindlaks teha, millal, kelle poolt ja milliseid isikuandmeid salvestati, muudeti või kustutati või millal, kelle poolt ja millistele isikuandmetele andmetöötlussüsteemis juurdepääs saadi;
5) tagama andmete olemasolu isikuandmete edastamise kohta: millal, kellele ja millised isikuandmed edastati, samuti selliste andmete muutusteta säilimise. 

(b)	Logimine ja logide säilitamine
Lähtuda saab järgmistest õiguslikest nõuetest:
1)	Eesti teabevärava eesti.ee haldamise, teabe kättesaadavaks tegemise, arendamise ning kasutamise nõuded ja kord § 21 ;
2)	Infosüsteemide andmevahetuskiht § 19 ;
3)	AKI Andmekogude juhendi alapeatükk 3.5 .

#### 7.2	Lepingulised nõuded

Nõuded ei täiene eeldusel, et andmejälgijat ei hallata teenusepartneri poolt. Juhul kui andmejälgija haldamine antakse teenusepakkujale, tuleb täiendavalt arvestada ISKE tüüpmooduliga „B 1.11 Väljasttellimine (Outsourcing)“ ning täita seal esitatud nõudeid. 

#### 7.3	Tagajärgede kaalukuse nõuded

(a)	Õiguslikest nõuetest tulenevad tagajärjed, mis võivad tuua kaasa ettekirjutusi AKI poolt.

(b)	Võimalikud kohtukulud eraisiku poolt, kes ei saa õigustest tulenevalt ülevaadet tema isiku kohta tehtud päringutest.

(c)	Juhul kui andmejälgija andmete säilitamise nõuetest ei peeta kinni, võib see kaasa tuua kahjusid kolmandatele osapooltele ning sellega kahjustada andmekogu pidaja mainet (sh Eesti Riigi mainet).

(d)	Kuna Andmejälgija tegevusest sõltub ka X-tee turvaserveri töö, siis peab tagama, et Andmejälgija vastaks samale või kõrgemale käideldavuse nõuetele.

Punkt (a), (b) ja (c) R1 – kaasnevad väheolulised kahjud, turvaintsident (st info käideldavuse ja/või konfidentsiaalsuse ja/või tervikluse nõuete mittetäitmine) põhjustab tõenäoliselt märkimisväärseid takistusi asutuse funktsiooni täitmisele või märkimisväärseid rahalisi kaotusi.

Punkt (d) R2 - kaasnevad olulised kahjud, turvaintsident (st info käideldavuse ja/või konfidentsiaalsuse ja/või tervikluse nõuete mittetäitmine) põhjustab tõenäoliselt olulise takistuse asutuse funktsiooni täitmisele.

#### 7.4	Turvaeesmärgid terviklusele (T), konfidentsiaalsusele (S) ja käideldavusele (K)

Õiguslikest ja tagajärgede kaalukuse nõuetest lähtuvalt peab Andmejälgija turvaosaklass vastama järgmistele tingimustele:

T2 – info allikas, selle muutmise ja hävitamise fakt peavad olema tuvastatavad; vajalikud on perioodilised info õigsuse, täielikkuse ja ajakohasuse kontrollid;

S1 - info asutusesiseseks kasutamiseks: juurdepääs teabele on lubatav juurdepääsu taotleva isiku õigustatud huvi korral;	 

Märkus: Tavaolukorras piisab S1 turvosaklassist. Andmekogu omanik peaks andmejälgija vaates analüüsima, kas delikaatsete isikuandmete töötlemisel tuleks määrata teabe konfidentsiaalsuse turvaosaklassiks vähemalt S2 (vihjed delikaatsetele andmetele).	
S2 – salajane info: info kasutamine lubatud ainult teatud kindlatele kasutajate gruppidele, juurdepääs teabele on lubatav juurdepääsu taotleva isiku õigustatud huvi korral.

K2 – käideldavus – suurem või võrdne kui 99% ja väiksem kui 99,9% aastas ning maksimaalne lubatud ühekordse katkestuse pikkus teenuse töö ajal kuni 4 tundi (st ühekordse katkestuse pikkus võib olla vahemikus väiksem või võrdne 4 tunniga ja suurem kui 1 tund).

Vastavalt leitud turvaosaklassidele tuleb andmejälgija turbeastmeks võtta M tase. 

#### 8	Ohtude (rünnete) klassifikatsioon

Vastavalt ISKE juurutamise sammule 10 kontrollitakse tegelikku turvaolukorda ohtude kataloogi G baasil. Vaadeldes punktis „6.1 Infovarade täiendamine“ mainitud mooduleid, muutuvad asjakohaseks all mainitud ohud.

#### 8.1	Asjakohaste ohtude klassifikatsioon „Turvaserveri sees olev proxy“ ja „Turvaserverist eraldiseisev proxy“ lahenduse korral

Täielik ja asjakohane ohtude nimekiri on ära toodud lisas 1. Siin punktis on kokku võetud olulisemad ohud, mida peab turvaserveri juurutamisel silmas pidama.

Suurimaks ohuks tuleb lugeda asutuse sisemiste protsesside puudulikkust, millisel juhul ei rakendata piisavalt kontrolle, tagamaks andmejälgija turvalist seadistamist ja kasutamise ülevaadet. 

(a)	Andmejälgijaga seotud turvaintsidendid. Tuleb arvestada, et andmejälgijaga seotud turvaintsidendid on spetsiifilised. Seetõttu tuleb tagada, et intsidentide haldus hõlmaks andmejälgija puhul nii kasutajate käitumist kui ka administraatorite tööd. Erilist tähelepanu tuleb pöörata juhtumitele, kus keegi modifitseerib kuritahtlikult andmejälgija konfiguratsiooni ja seetõttu lekib tundlikku infot kolmandatele osapooltele. Andmejälgija kaudu on võimalik tuvastada muuhulgas andmekogu volitamata kasutamist – ka selliseid juhtumeid tuleb käsitleda intsidentidena. 

i.	Seotud oht (ISKE ohtude kataloogis, vt lisa): G 2.62 Turvaintsidentide puudulik käsitlus.

(b)	Puudulik või vähene kontroll. Punkt hõlmab mitmeid ohufaktoreid ning siin toome ära vaid olulisema. Kriitilisim oht on andmejälgija konfiguratsioonifaili volitamata muutmine, mis mõjutab otseselt kogutavate andmete (logide) hulka või nende edasist edastamist/käitlemist. Kaks kõige ohtlikumat rünnet on järgnevad. Esiteks terviklusrünne, kus kellegi andmete vaatamisi kuritahtlikult ei salvestata/registreerita (andmesubjekti kohta tehtud päringud koos päringusubjektidega jäetakse volitamatult logidest (jälgimise alt) välja). Teiseks ohtlikuks ründeks on selline konfidentsiaalsusrünne, kus riigi jõustruktuuride päringud koos päringusubjektidega ja/või muude päringu parameetritega edastatakse kellelegi lubamatult.		

i.	Seotud ohud (ISKE ohtude kataloogis, vt lisa): G 5.20 Administraatoriõiguste väärkasutus; G 3.11 Sendmaili väär konfiguratsioon; G 5.2 Andmete või tarkvara manipuleerimine; G 5.71 Tundliku informatsiooni konfidentsiaalsuse kadu ja G 5.85 Tundliku informatsiooni tervikluse kadu.

#### 9	Turvaeeldused

Vastavalt ISKE juurutamise sammule 7 koostatakse turbeastmest lähtudes turbehalduse meetmete loetelu. Vaadeldes punktis „Infovarade täiendamine“ nimetatud mooduleid, muutuvad all mainitud turvameetmed asjakohaseks.

#### 9.1	Asjakohaste turvameetmete ülevaade „Turvaserveri sees olev proxy“ ja „Turvaserverist eraldiseisev proxy“ lahenduse korral

Täielik ja asjakohane turvameetmete nimekiri on ära toodud lisas 1. Alljärgnevas on kokku võetud olulisemad turvameetmed, mida peab turvaserveri juurutamisel silmas pidama.

Antud proxy lahenduste korral sõltub turvaserveri töö (ühendus X-teega) andmejälgijast ning asutusel on oluline arvestada võimalike hädaolukordadega. Samuti tuleb tähelepanu pöörata intsidentidele ja nende järeltöötlusele, et parandada kontrolliprotsesse. Oluline on eeskätt tuvastada volitamata muudatused andmejälgija konfiguratsioonis. Lisaks tuleb tagada  veebiliidese turvalisus. 

(a)	Hädaolukord. Kuna andmejälgijast hakkab sõltuma ka turvaserveri töö, siis tuleb olemasolevat hädaolukorra plaani täiendada andmejälgija vaates. 

i.	Seotud meede: M 6.118 (L) Hädaolukorra meetmete kontroll ja käigushoidmine

(b)	Turvaintsidendid. Turvaintsidentide suuniste koostamise/täiendamise ja turvaintsidentide avastamise ning käsitlemise juures tuleb arvestada ka andmejälgija spetsiifikat. Kaudselt võib andmejälgijat võtta kui logisüsteemi ning peamine rõhk tuleb panna andmejälgija konfiguratsioonifaili volitamata muutmise avastamisele – selle korral peaks peaadministraator (või peakasutaja) saama vastava meili, milles on vana konfiguratsioonifaili räsi ja uue, muudetud konfiguratsioonifaili räsi. Tuleb arvestada juhtumitega, kus keegi modifitseerib kuritahtlikult andmejälgija konfiguratsiooni. Samuti tuleb arvestada ka sisemisi intsidente, kus asutuse töötajad kasutavad andmekogu andmeid vääriti ja kasutavad saadud infot oma hüvedest lähtuvalt või uudishimu rahuldamiseks.

i.	Seotud meede: M 6.121 (L) Suuniste väljatöötamine turvaintsidentide käsitlemiseks ja M 6.67 (M) Turvaintsidentide avastamismeetmete rakendamine.

(c)	Krüpteerimine. Andmejälgija vaates peaks kogu veebipõhine kaughaldus ja andmete vaatamine toimuma üle TLSile toetuva https-protokolli. Administraatorid peaksid vältima oma välja antud „katkiste “ sertifikaatide kasutamist (valede harjumuste õpetamine tavakasutajatele). HTTP protokolli võib kasutada vaid juhul, kui andmejälgija veebiliidesele on ligipääs ainult asutuse sisevõrgust. Kuna andmejälgija on väga tihedalt seotud turvaserveriga, siis tuleb arvestada ka turvaserveri võimaluste ja nõuetega, selleks vaata lähemalt „Turvaserveri kasutusjuhend v 2.3“ punkti 9 . 	

Teise punktina võib vaadelda ka andmebaasi krüpteerimist, kuid turvaosaklassist tulenevalt ei ole otsest nõuet, et andmejälgija baas peaks olema krüpteeritud. Andmekogu omanik võib antud lisaturvalisuse meetme soovi korral rakendada. Soovitame juhinduda turvaserverile kehtestatud nõuetest.

i.	Seotud meede: M 5.66z (M) TLS-i/SSL-i kasutamine.

(d)	Veebiliidese turve ja penetratsioonitest. Andmejälgija veebiliidese korral võiks läbi viia penetratsioonitesti OWASP Top 10 metoodika põhjal. Antud turvalisuse test tuleks tellida standardsele andmejälgija veebiliidesele ning aruanne testist tuleks lisada andmejälgija juurutuse dokumentide hulka. Juhul kui asutus loob oma veebiliidese, tuleb OWASP Top 10 penetratsioonitest läbi viia asutusel endal.

i.	Seotud meede: M 5.150 Penetratsioonitestide läbiviimine.

#### 10	Usaldusalad

Andmejälgija töö saab tõhusalt toimida vaid sujuvas koostöös IT ja äripoole vahel. Seega peab äripool selgelt välja ütlema nõuded, mida on vaja järgida. Koostöös IT-ga tuleb need nõuded ka rakendada ning tagada nende üle kontroll. 

(a)	 Konfiguratsiooni kontrollitud muudatused – andmejälgija konfiguratsiooniga pannakse paika nii ligipääsud andmejälgija andmetele kui ka andmete salvestamise ulatus. Konfiguratsiooniga saab seadistada, et teatud asutuste päringuid ei salvestata. Oluline on see jõustruktuuride vaates, kes teevad näiteks taustauuringuid kriminaaluurimise käigus. Sellise info lekkimine võib tuua kaasa pöördumatut kahju. Seega tuleb tagada, et konfiguratsiooni muudatused ja sisu oleks alati tuvastatud ning kooskõlastatud. Vt ka 11 (b).

(b)	Ligipääsud andmejälgijale – Oluline on mainida, et asutus saab nüüd parema kontrolli andmekogu päringute üle ja on võimeline tuvastama sisemisi andmete väärkasutusi. Tuleb tagada, et ligipääsude andmisega andmejälgijale ei tekitataks rollikonflikte, kus andmekogust päringute tegija saab ka kontrollida iseenda tegevusi või tellida/teha muudatusi konfiguratsioonis. 
Loobudes sissejuhatuses mainitud eeldusest, kus ühele andmekogule vastab üks eraldusfilter ja andmejälgija, peab andmekogu omanik tagama, et võõrastele kasutajatele (ka sisemistele töötajatele) ei tekiks ligipääsu mitteasjakohastele andmejälgija andmetele. Täieliku ligipääsu võib anda vaid sisekontrolli töötajatele, kellel on kohutus kontrollida ja tuvastada andmete väärkasutusi.

(c)	Andmejälgija andmete säilitamine – andmejälgija andmete säilitamise tähtaja arvestamisel tuleb kaaluda üleval mainitud seadusi ja juhendeid. Samuti soovitame arvestada võimalike tagajärgedega kaalukusega kui selgub, et andmejälgija andmete lekkimise tõttu võib tekkida arvestav kahju. Arvestada tuleb väärtegude ja kriminaalkaristuste aegumise tähtaegadega.

i.	Näide, kus avalikuks tulevad jõustruktuuri päringud ning näiteks kriminaaluurimine saab seetõttu takistatud.

ii.	Näide, kus asutuse töötaja saab oma huvides teha (ja peita) päringuid andmekogust ning seda infot omades saada majandusliku kasu (nt väljapressimised).

Andmejälgija andmeid võib säilitada ka arhiveerimise teel. Arhiveerimise juures tuleb arvestada vastava ISKE mooduliga.

#### 11	Lahenduse turvaelemendid

All on ära toodud peamised turvaelemendid, mis peaksid õige kasutamise korral tagama andmejälgija turvalise töö ja kasutamise.

(a)	Minimaalne installatsioon – eraldiseisva installatsiooni korral tuleb jälgida minimaalse installatsiooni nõuet.

(b)	Konfiguratsioonifaili räsi – soovitame kasutada konfiguratsiooni muudatuste tuvastamiseks räsimist, räsiahelaid ja räside muutmise korral (räsiahelate katkemise korral) käivituvat automaatset teavitusprotsessi. Alternatiivina võib kasutada ka neljasilma printsiipi.

(c)	TLS-ile toetuv https-protokoll – soovitame veebiliideses kasutada TLS protokolli ja mitte SSL protokolli, sest SSL protokolli teatav nõrkus (nüüd juba ka SSL3 põlvkonnas) võimaldab teoreetilist murdmist Poodle-ründega. HTTP-d võib kasutada (kuid pole soovitav) vaid juhul kui päringud ei välju asutuse sisevõrgust. 

(d)	Logi ja logiandmete analüüs ning säilitamine – tuleb tagada, et igast süsteemis tehtud toimingust jääks maha ka jälg ning oleks tagatud selle jälje muutmatus (terviklus). Logiandmeid tuleb perioodiliselt ka analüüsida, kas automaatselt või inimes(t)e poolt. 	

Iga asutus peab hindama logide säilitamise ajalise ulatuse suurust ja logide säilitamisele tuleb kehtestada tähtajad vastavalt andmete iseloomule. 

(e)	ID-kaardiga autentimine – veebiliidesesse autentimine peab toimuma ID-kaardi abil. ID-kaardi kasutamine on paroolide hulga tõttu eelistatuim variant.

#### 12	Protokollide puhul – turvaomadused

TLSile toetuv https-protokoll – veebiliideses on ülimalt soovitatav kasutada TLS protokolli ja mitte SSL protokolli, sest SSL protokolli (SSL3 protokolli) teatav nõrkus  võimaldab teoreetilist murdmist. 

Protokollide juures vaata ka „Turvaserveri kasutusjuhend v 2.3“ punkti 9 .

#### 13	Seosed ülalnimetatud elementide vahel

Antud turvaanalüüsi koostamisel on järgitud ISKE versiooni 7.00 ja ISKE juurutamise samme. Järgitud on järgmisi samme:

SAMM 1: Infovarade inventuur – turvatavate infovarade väljaselgitamine.

SAMM 2: Andmekogude kaardistamine, andmekogudele turvaklassi ja turbeastme määramine – turvaeesmärkide sõnastamine õiguslikest, lepingutest ja tagajärgede kaalukusest lähtuvalt.

SAMM 4: Kõikide turvaklassiga infovarade vajaliku turbeastme määramine – turbeastme (L, M, H) määramine varadele vastavalt leitud turvaklassidele.

SAMM 6: Tüüpmoodulite spetsifitseerimine – koostatakse moodulite ja meetmete tabel vastavalt ISKE versioonile 7.0.

SAMM 7: Turvameetmete loetelu koostamine – turvaeelduste koostamine vastavalt valitud infovaradele ja sellest tulenevatest moodulitest lähtuvalt ning protokollide puhul turvaomadused, mis peavad olema täidetud vastavate seotud meetmetega.

SAMM 10: Tegeliku turvaolukorra kontroll – ohtude (rünnete) klassifikatsioon analüüsi koostamine. 

#### 14	Seotud dokumendid

Antud dokument kuulub riigihanke “X-tee andmejälgija analüüs ja disain” (viitenumber 164646) tulemitena üleantavate dokumentide koosseisu. Selle dokumendi koostamisel on arvesse võetud järgmiseid dokumente:
•	andmejälgija tehniline kontseptsioon;
•	eraldusfiltri disainilahendus; 
•	andmesalvestaja disainilahendus;
•	andmejälgija õiguslik analüüs.

#### 14.1	Alamdokumendid

Antud dokumendi lisad:

(a)	Lisa 1. Andmejälgija ISKE ohud ja meetmed – kirjeldab detailselt andmejälgijaga lisanduvate täiendavate ISKE-kohaste tüüpmoodulite ohte ja meetmeid, mis siin dokumendis leiavad vaid põgusat kajastust.

#### 14.2	Viited dokumentidele

(a)	Lisa 2. AKI „ANDMEKOGUDE JUHEND“ - http://www.aki.ee/sites/www.aki.ee/files/elfinder/article_files/Andmekogude%20juhend_1.pdf

(b)	Lisa 3. „Turvaserveri kasutusjuhend v 2.3“ - http://x-road.eu/docs/x-road_v6_security_server_user_guide.pdf.
