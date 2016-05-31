## Andmejälgija õiguslik analüüs

X-tee andmejälgija analüüs ja disain

Versioon 1.0, 20.12.2015

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ ja FocusIT OÜ

####1	Dokumendi ajalugu

Versioon	Kuupäev	Autor	Märkused
- 0.9	07.12.2015	Urmas Kukk	Esimene versioon
- 1.0	14.12.2015	Ivo Mehide	Dokumendi kujunduse muudatused
- 1.0.1 31.05.2015 Priit Parmakson Teisendus Markdowni			

####2	Sissejuhatus
Analüüsis esitatakse vastused alljärgnevatele küsimustele:
1)	Kas väljatöötatud lahendus vastab kehtivale õigusele? Kas väljatöötatud tehnilise lahenduse kasutamine eeldab muudatusi kehtivates õigusaktides?
2)	Kas ja millises osas on vaja muuta andmejälgija kasutusele võtmisega seonduvalt andmekogude pidamise aluseks olevaid õigusakte, sh põhimäärusi?
3)	Kui kaua peaks säilitama andmesalvestajasse kogutavaid metaandmeid? (peatükk 6)

####3	Aluseks olevad õigusaktid
Käesoleva analüüsi aluseks on võetud alljärgnevad rahvusvahelise ja siseriikliku õiguse normid: 

####3.1	Rahvusvaheline õigus
####3.1.1	Euroopa Nõukogu Isikuandmete automatiseeritud töötlemisel isiku kaitse konventsioon (Nr 108)
Artikkel 8. Lisatagatised andmesubjektile

Isikule peab olema võimaldatud:
b. saada määratletud ajavahemike järel ning ilma liigse viivituseta või kuluta teavet, kas tema isikuandmeid säilitatakse automatiseeritud andmekogus ja saada neid andmeid talle mõistetavas vormis;

####3.1.2	Inimõiguste ja biomeditsiini konventsioon: inimõiguste ja inimväärikuse kaitse bioloogia ja arstiteaduse rakendamisel
Artikkel 10. Eraelu ja õigus saada teavet
2. Igaühel on õigus saada mis tahes teavet, mis tema tervise kohta on kogutud.

####3.1.3	Direktiiv 95/46/EU
Artikkel 12. Õigus tutvuda andmetega

Liikmesriigid tagavad, et igal andmesubjektil on õigus nõuda vastutavalt töötlejalt:

a) mõistliku aja tagant, ilma piiranguteta ja ilma liigsete viivituste ja kulutusteta:
- kinnitust selle kohta, kas isikut ennast käsitlevaid andmeid töödeldakse, ja teavet vähemalt töötlemise eesmärkide, asjaomaste andmete liikide ja nende vastuvõtjate või vastuvõtjate kategooriate kohta, kellele andmed avalikustatakse,
- arusaadaval kujul teavet töödeldavate andmete ja nende allika kohta,

####3.2	Siseriiklik õigus
####3.2.1	Isikuandmete kaitse seadus (IKS)
§ 19.  Andmesubjekti õigus saada teavet ja tema kohta käivaid isikuandmeid
 (1) Andmesubjekti soovil peab isikuandmete töötleja andmesubjektile teatavaks tegema:
 1) tema kohta käivad isikuandmed;
 2) isikuandmete töötlemise eesmärgid;
 3) isikuandmete koosseisu ja allikad;
 4) kolmandad isikud või nende kategooriad, kellele isikuandmete edastamine on lubatud;
 5) kolmandad isikud, kellele tema isikuandmeid on edastatud;
6) isikuandmete töötleja või tema esindaja nime ning isikuandmete töötleja aadressi ja muud kontaktandmed.
 (2) Andmesubjektil on õigus saada isikuandmete töötlejalt enda kohta käivaid isikuandmeid. Isikuandmed väljastatakse võimaluse korral andmesubjekti soovitud viisil. 

§ 25. Isikuandmete organisatsioonilised, füüsilised ja infotehnilised turvameetmed
 (2) Isikuandmete töötleja on isikuandmete töötlemisel kohustatud:
 3) ära hoidma isikuandmete omavolilist salvestamist, muutmist ja kustutamist ning tagama, et tagantjärele oleks võimalik kindlaks teha, millal, kelle poolt ja milliseid isikuandmeid salvestati, muudeti või kustutati või millal, kelle poolt ja millistele isikuandmetele andmetöötlussüsteemis juurdepääs saadi;
5) tagama andmete olemasolu isikuandmete edastamise kohta: millal, kellele ja millised isikuandmed edastati, samuti selliste andmete muutusteta säilimise. 

####4	Väljatöötatava lahenduse vastavus õigusnormidele.
Andmejälgija eesmärgiks on luua võimalus nii andmesubjektidele kui andmetöötlejatele keskkond IKS §25 lg2 pp3 ning 5 rakendamiseks ja §19 lg1 ja 2 õiguse realiseerimiseks.

Seega on Andmejälgija eesmärk pakkuda andmesubjektile lihtsat võimalust talle IKS §19 antud õiguste kasutamiseks ja samas anda talle lihtne võimalus kontrollimiseks iseseisvalt, kas andmetöötleja järgib IKS §25 lg 2 p-de 3 ja 5 nõudeid.  

Andmejälgija funktsionaalsuse osas soovitakse pakkuda:
-	isikuandmete töötlemise logimist andmejälgija oma andmebaasi;
-	X-tee teenuse kaudu kodanike päringutele vastamine eesti.ee veebilehe kaudu;
-	asutuse sisekasutuse otsingu-API ja otsingu veebiliidest.

Andmejälgija funktsionaalsus tervikuna ja osade kaupa  vastab IKS-i nõuetele ja seepärast ei ole siinkohal vajalik iga eelnevas lõigus toodud alamosist eraldi analüüsida.

####5	Piloteeritavate andmekogude pidamist reguleerivate õigusaktide vastavus isikuandmete kaitse seadusele.
####5.1	Sotsiaalteenuste ja –toetuste andmeregistri asutamine ja selle pidamise põhimäärus.

Andmesubjekti poolt tehtava päringu ja andmete väljastamise kohta on põhimääruses alljärgnev regulatsioon: 
§ 11. Andmete väljastamine
 (1) Andmeid, mille saamiseks on isikul õigus, väljastab vastutav või volitatud töötleja, kelle poole isik pöördub, kui vastutaval või volitatud töötlejal on vastavatele andmetele juurdepääsuõigus.
 (2) Andmeid väljastav vastutav või volitatud töötleja peab arvestust andmete väljastamise aja, väljastatud andmete koosseisu, andmesaaja, andmete väljastamise õigusliku aluse ja andmete väljastamise viisi üle.

Regulatsioon (küll väga üldsõnaline) vastab IKS-i nõuetele ja selle alusel saab andmeid väljastada ka Andmejälgija rakendumisel. Selguse huvides võiks põhimääruses olla viide isikuandmete kaitse seaduse kohaldamisel isikuandmete töötlemisel.

####5.2	Rahvastikuregister

Rahvastikuregistri toimimine on sätestatud rahvastikuregistri seadusega, milles isikuandmete kaitse ja teabe väljastamise on sätestatud alljärgnevalt:
§ 61.  Andmete kaitse nõuded
 (1) Rahvastikuregistri andmete kaitsmisel kohaldatakse isikuandmete kaitse seaduses ja avaliku teabe seaduses sätestatut, kui käesolevast seadusest ei tulene teisiti.

§ 68.  Isiku õigus saada teavet ja tema kohta rahvastikuregistrisse kantud andmeid
 (1) Täisealisel isikul on juurdepääsuõigus kõigi tema, tema alaealiste laste ja eestkostetavate kohta rahvastikuregistrisse kantud aktuaalsetele andmetele, samuti rahvastikuregistri arhiivi kantud andmetele ja andmete töötlemise andmetele, välja arvatud rahvastikuregistri tarkvarast tulenevatele andmetele ja andmete koodidele.
 (2) Isiku taotlusel teavitab volitatud töötleja isikut:
 1) tema kohta rahvastikuregistrisse kantud andmetest või nende puudumisest;
 2) tema kohta rahvastikuregistrisse kantud andmete töötlemise eesmärgist ja õiguslikest alustest;
 3) rahvastikuregistri andmete koosseisust, andmeandjatest ja rahvastikuregistri andmete alusdokumentidest;
 4) rahvastikuregistri andmetele juurdepääsuõigust omavatest asutustest ja isikutest;
 5) rahvastikuregistri andmete väljastamise korrast;
 6) rahvastikuregistri andmetele juurdepääsu piiramise korrast;
 7) rahvastikuregistri andmete kasutamisvõimalustest;
 8) rahvastikuregistri pidamise organisatsioonilisest struktuurist.

Regulatsioon vastab IKS-i nõuetele ja selle alusel saab andmeid väljastada ka Andmejälgija rakendumisel.

####5.3	Andmevahetusplatvorm

Andmevahetusplatvorm on digitaalne keskkond, mille kaudu toimub elektriturul andmevahetus avatud tarnija vahetamiseks, mõõteandmete edastamiseks ning turuosalisele seadus kohustuste täitmiseks ja talle antud õiguste tagamiseks. 

Andmevahetusplatvormi kaudu toimuv andmete edastamine on reguleeritud võrgueeskirjaga (https://www.riigiteataja.ee/akt/12831412?leiaKehtiv), milles puudub igasugune regulatsioon isikuandmete töötlemiseks. 

Võrgueeskirja on vaja täiendada isikuandmete töötlemist puudutavate sätetega ka juhul kui Andmejälgija ei rakendu. Regulatsioon ei pea olema väga detailne. Piisab ka viitest selle, et isikuandmete töötlemisel kohaldatakse isikuandmete kaitse seadust. 

####5.4	Töötuskindlustuse andmekogu

Andmesubjekti poolt tehtava päringu ja andmete väljastamise kohta on Töötukindlustuse andmekogu põhimääruses alljärgnev regulatsioon:

§ 13. Andmete väljastamine
(1) Andmekogust väljastatakse andmeid:
3) isikule, kelle andmeid registris töödeldakse, või tema seaduslikule esindajale;
(5) Andmekogust väljastatakse andmeid vastavalt andmesaaja soovile ja tehnilistele võimalustele elektrooniliselt või paberkandjal.

Regulatsioon (samuti väga üldsõnaline) vastab IKS-i §19 lg1  nõuetele ja selle alusel saab andmeid väljastada ka Andmejälgija rakendumisel. Selguse huvides võiks ka selles põhimääruses olla viide isikuandmete kaitse seaduse kohaldamisel isikuandmete töötlemisel (väljastamisel).

####5.5	Ravikindlustuse andmekogu

Ravikindlustuse andmekogu pidamise põhimääruses on isikuandmete väljastamise kord kehtestatud alljärgnevalt:
VI Andmete väljastamise kord 
28. Andmekogust väljastatakse andmeid “Isikuandmete kaitse seaduse”, “Avaliku teabe seaduse” ja teiste õigusaktidega, samuti Euroopa Liidu õigusaktide või riikidevahelistes lepingutes ja nende rakenduslepingutes sätestatud tingimustel ja korras teise lepingupoole pädevale asutusele. 
29. Andmekogust väljastatakse andmeid “Ravikindlustuse seaduses” sätestatud alustel tagasinõude õiguse teostamisel.

Seega puudub Ravikindlustuse andmekogus regulatsioon, mis sätestab andmete väljastamist andmesubjektile. 
Ravikindlustuse andmekogu on vaja täiendada isikuandmete töötlemist puudutavate sätetega ka juhul kui Andmejälgija ei rakendu. Regulatsioon ei pea olema väga detailne. Piisab ka viitest selle, et isikuandmete töötlemisel kohaldatakse isikuandmete kaitse seadust.

####6	Metaandmete säilitamise tähtaeg
 Isikuandmete kaitse seadus ei sea tähtaegasid isikuandmete säilitamiseks. Ka Direktiiv 95/46/EÜ art.6 1.e) sätestab ainult, et isikuandmeid säilitatakse kujul, mis võimaldab andmesubjekti tuvastada ainult seni, kuni see on vajalik seoses andmete kogumise või hilisema töötlemise eesmärkidega.

Isikuandmete töötlemisel peaks lähtuma põhimõttest, et põhiõigusi (käesoleva kontekstis, siis eraelu ja isikuandmete kaitset) riivavaid andmeid peaks säilitama nii lühikest aega kui võimalik ja põhiõigusi kaitsvaid andmeid (käesoleva kontekstis, siis isikuandmete töötlemist näitavaid metaandmeid) nii pikka aega kui vajalik.

Metaandmete säilitamise ülemiseks ajaliseks piiriks võiks olla karistusseadustiku §1571 lõigetega 2 ja 4 ettenähtud süütegude aegumise tähtaeg, so 5 aastat.

####7	Kokkuvõte

Piloteeritavatest andmekogudest Ravikindlustuse andmekogu pidamise põhimääruses ja Andmevahetuskihis puudub regulatsioon isikuandmete töötlemiseks. Seega vajavad nii Ravikindlustuse andmekogu pidamise põhimäärus kui ka võrgueeskiri kindlasti vastavat täiendamist. Teiste puhul võimaldab ka praegune redaktsioon Andmejälgijat rakendada.

Selguse huvides võiks siiski kõikide andmekogude regulatsioonides olla alljärgnev säte:
„Andmekogust väljastatakse isikuandmeid isikuandmete kaitse seaduses sätestatud tingimustel ja korras.“

