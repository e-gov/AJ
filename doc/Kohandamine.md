Tarkvara kohandamisjuhend
=========================

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](img/EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide, Tanel Tammet | Esimene versioon

## Sisukord

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sihtrühm](#sihtr%C3%BChm)
  * [Sissejuhatus](#sissejuhatus)
  * [Andmejälgija komponendid ja nende lähtekoodi asukoht](#andmej%C3%A4lgija-komponendid-ja-nende-l%C3%A4htekoodi-asukoht)
  * [Eraldusfilter](#eraldusfilter)
  * [Andmesalvestaja](#andmesalvestaja)
  * [Sisekasutuse veebiliides](#sisekasutuse-veebiliides)
  * [eesti.ee](#eesti.ee)
  * [Testrakendus](#testrakendus)


## Sihtrühm

Kohandamisjuhendi sihtrühmaks on:

* Andmejälgija tarkvara kasutav andmekogu omanik, kes soovib seda tarkvara oma tarbeks kohandada ja edasi arendada

## Sissejuhatus

Tarkvara lähtekood on kättesaadav avalikus GitHub repositooriumis https://github.com/e-gov/AJ.

Käesoleva juhendi eesmärgiks on selgitada lähtekoodi struktuuri ja anda näpunäiteid tarkvara kohandamiseks ja edasiarendamiseks,
Tarkvara paigaldamine ja konfigureerimine on selgitatud eraldi paigaldamisjuhendis ning kompileerimine ja ehitamine ehitusjuhendis,
seega neid punkte siin juhendis me ei käsitle ning eeldame, et nende juhenditega on juba tutvutud.

Pea kogu andmejälgija tarkvara on kirjutatud Javas, võimaldades kasutada Java versioone alates 1.6st. 

Komponendid on struktureeritud selliselt, et neid saaks käivitada Java rakendusserveris.
Standardvariandina on rakendusserverina kasutatud Jetty serverit, kuid süsteem sobib ka Tomcati
ja teiste sarnaste rakendusserverite jaoks.

Veebirakenduste funktsionaalsus on kirjutatud javascriptis, andmebaasipärigud SQL-s, eraldusfilter kasutab ka xpath avaldisi.

Operatsioonisüsteemina eeldatakse Linuxit, arendus ja testimine on toimunud Ubuntu 14 ja 15 versioonidel.

Andmejälgija ehitamiseks on kasutatud gradle ehitustööriistu.

## Andmejälgija komponendid ja nende lähtekoodi asukoht

Siin juhendis jagame andmejälgija järgmisteks osadeks:

* Eraldusfilter, mis jälgib x-tee turvaserveri sõnumeid ja salvestab isikuandmete liikumise andmesalvestajasse.
* Andmesalvestaja sisaldab andmebaasi ja temaga seotud teenuseid ehk API-sid: salvestamise REST teenus,
sisekasutuseks otsimise REST teenus, avalikuks eesti.ee kasutamiseks ettenähtud x-tee SOAP otsingute teenus.
* Sisekasutuse veebiliides andmesalvestajast kirjete otsimiseks.
* eesti.ee jaoks ettenähtud xforms teenuse komponendid
* Testrakendus, mis sarnaneb eesti.ee rakendusele ja ei ole mõeldud reaalseks kasutuseks.

Järgmistes punktides anname näpunäiteid nende komponentide ehituse kohta, et hõlbustada nende lähtekoodi kohandamist
ja edasiarendamist.

## Eraldusfilter

Lähtekood asub ülemise taseme kataloogis filter.

## Andmesalvestaja

Lähtekood asub ülemise taseme kataloogis storage.

* Andmebaasi loomise failid on kataloogis storage/database. Rakenduse töötamise ajal neid ei vajata. 
* Kõigi API-de ehk teenuste lähtekood on kataloogis storage/src/main/java/ee/degeetia/dumonitor/storage/
* API-de konfiguratsioonifailid on kataloogis storage/src/main/resources/
* Sisekasutuse veebiliides on kataloogis storage/src/main/webapp, mh on vajalikud ka seal all olevad css ja js kataloogid.

Andmebaasi struktuur koosneb ühestainsast tabelist, mis on koos kommentaaridega toodud failis storage/database/aj_tables.sql

Andmesalvestaja põhiosa moodustavadki API-d andmebaasi kirjutamiseks ja sealt lugemiseks. API-d on üksteisest
sõltumatud, kuid osa lähtekoodi on neil kõigil ühine. Kõik API-d on ette nähtud töötamiseks Java rakendusserveris
servlettidena üle http(s)-i ning realiseerivad seega servleti doGet ja doPost meetodeid.

API-de ühised lähtekoodifailid on:

* Util.java : seda kasutavad kõik API-d; sisaldab konfifailide lugemise, logikäivitamise, 
andmebaasiühenduse loomise, päringusisendite parsimise, veatrükkide ja XML töötluse utiliite. 
* Context.java : sisaldab globaalsetest muutujatest koosnevat objekti. Kõik globaalmuutujad on siin.
* Strs.java : sisaldab XML ümbrike template Xroad.java jaoks.

Util.java kasutab konfiguratsiooni lugemiseks neid kahte andmejälgija teistes kataloogides
realiseeritud klasse:
* ee.degeetia.dumonitor.common.config.Property;
* ee.degeetia.dumonitor.common.config.PropertyLoader;

Konkreetseid API-sid realiseerivad järgmised failid:

* Store.java : realiseerib andmesalvestamise REST teenust.
* Query.java : realiseerib sisekasutuse veebilehe jaoks otsingu REST teenust.
* Xroad.java : realiseerib eesti.ee jaoks x-tee SOAP otsingupäringu uue x-tee versiooni jaoks.
* Heartbeat.java : realiseerib üldise metainfo andmise x-tee jaoks, ei ole vajalik API-de tööks.

Store, Query ja Xroad failid avavad andmebaasiühenduse Util failis asuva funktsiooni abil,
kuid sisaldavad igaüks ise konkreetseid SQL päringuid jdbc kaudu. Andmebaasiühenduse parameetrid
on toodud ühises konfiguratsioonifailis, mis loetakse sisse Util.java kaudu.

Andmesalvestamise API (Store.java) põhimõtted:

* Päringu- ja vastuseväljad on samad, mis andmebaasi tabeliväljad.
* Salvestada saab nii cgi-formaadis parameetritega GET päringuga a la
http://baasurl/store?action=miski
kus ainult action parameeter on kohustuslik ja võib anda ka kõiki teisi baasivälju ka,
peale id ja logtime, mis võetakse automaatselt.
* Kui json-encoded postiga a la 
{"action":"miski",....}
Kui postitada jsonit, peab http Content-type sisaldama teksti "json"
* Päringuvastus on lihtsalt json kujul
{"ok": 1}
või vea korral
{"errcode":10, "errmessage":"something ..."}

Päringu-api (Query.java) põhimõtted:

* Päringu- ja vastuseväljad on samad, mis andmebaasi tabeliväljad.
* Pärida saab nii cgi-formaadis parameetritega GET päringuga a la
http://baasurl/query?action=sisu&callback=foo
kus võib kõik parameetrid ära jätta, samas võib kasutada kõiki välju (otsitakse sisalduvust, v.a.
personcode, mida otsitakse täpselt) pluss from_date, to_date, offset, limit, callback
kus callback paneb vastusele ümber javascripti funktsioonikutsumise.
* Kui json-encoded postiga a la 
{"action":"miski",....}
Kui postitada jsonit, peab http Content-type sisaldama teksti "json"
* Päringuvastus on lihtsalt json vastuseridadega, kus igaüks on andmebaasiväljadest
võtmetega võti/väärtus paare sisaldav objekt.

X-tee api (Xroad.java) põhimõtted:

* Sisend on uue versiooni x-tee SOAP ümbrik
* Sisendis arvestatakse ainult isikukoodi, vastusridade algus-offsetti ja soovitud vastusridade maksimaalarvu
* Sisendi isikukood võetakse SOAP päise väljast
```xml
<xrd:userId>EE3....</xrd:userId>
```
* Sisendi keha on kujul
```xml
   <soapenv:Body>
     <prod:findUsage>        
        <offset>0</offset>
        <limit>1000</limit>
     </prod:findUsage>
  </soapenv:Body>
```  
ja sellest arvestatakse ainult offset ja limit parameetreid, mis võib ka ära jätta (vaikimisi 0 ja 1000).
* Tulemuse päis on identne sisendi päisega, vastavalt uue x-tee põhimõttele.
* Tulemuse keha on kujul
```xml
<soapenv:Body>
     <findUsageResponse xmlns="http://dumonitor.x-road.eu/producer">
        <!--Zero or more repetitions:-->
        <usage>
           <logtime>?</logtime>
           <action>?</action>
           <receiver>?</receiver>
        </usage>
     </findUsageResponse>
  </soapenv:Body>
</soapenv:Envelope>
``` 

## Sisekasutuse veebiliides

## eesti.ee

## Testrakendus




