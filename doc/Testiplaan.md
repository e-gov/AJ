# Testiplaan

**DUMonitor**

Versioon 1.0, 09.05.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

![EL struktuurifondid](img/EL_struktuuri-_ja_investeerimisfondid_horisontaalne.jpg)

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 09.05.2016 | Ivo Mehide | Esimene versioon

## Sisukord

  * [Sissejuhatus](#sissejuhatus)
  * [Skoop](#skoop)
  * [Osapooled](#osapooled)
  * [Testimiseks vajalik keskkond](#testimiseks-vajalik-keskkond)
  * [Loodavad tulemid](#loodavad-tulemid)
  * [Ajaplaan](#ajaplaan)


## Sissejuhatus

Käesolev dokument kirjeldab DUMonitor tarkvara testimise plaani. Plaanis leiab käsitlemist:

* Testitavad komponendid
* Testimise põhimõtted
* Osapooled
* Testimistegevuste tulemid

## Skoop

Testiplaan kirjeldab DUMonitor tarkvaraga läbi viidavad integratsiooni- ja koormustestid. Testiplaanis eeldatakse, et tarkvara ühiktestid on testimise hetkeks juba realiseeritud ning läbi viidud tarkvara arendajate poolt.

Automaatsete integratsioonitestidega testitakse järgmised tarkvara suhtlusliidesed:

* Filtri komponendi päringute vahendusliides
* Andmesalvestaja komponendi REST kasutusteabe logimise liides
* Andmesalvestaja komponendi REST päringuliides
* Andmesalvestaja komponendi X-tee päringuliides

Käsitsi läbi viidavate integratsioonitestidega testitakse järgmised tarkvara suhtlusliidesed:

* Andmesalvestaja komponendi sisekasutuse veebiliides
* Esitamise testrakenduse veebiliides
* Eesti.ee komponendi xforms liides

Koormustestidega testitakse järgmised tarkvara suhtlusliidesed:

* Filtri komponendi päringute vahendusliides
* Andmesalvestaja komponendi REST kasutusteabe logimise liides
* Andmesalvestaja komponendi REST päringuliides
* Andmesalvestaja komponendi X-tee päringuliides

## Osapooled

Testimistegevustes osalevad järgmised isikud:

* Täitja projektijuht - integratsiooni- ja koormustestide skriptide loomine, testimise läbiviimise korraldamine, testidokumentatsiooni koostamine
* Täitja tarkvaraarendajad - ühiktesteide arendamine ning läbiviimine
* Tellija testijuht - testiplaani ja testilugude kooskõlastamine

## Testimiseks vajalik keskkond

Testide läbiviimiseks kasutatakse Tellija juurde paigaldatud spetsiaalselt antud projekti jaoks loodud CI-keskkonda.

Liideste toimimise testimine toimub integratsioonitestide abil. Integratsioonitestide läbiviimiseks realiseeritakse vajalikud makettrakendused ning testiskriptid. Lisaks luuakse vajalikud virtuaalmasinad ning paigaldatakse neisse testimiseks vajalik tarkvara. Pidevintegratsioonikeskkonna kaudu toimub arendatud tarkvarakoodi atomaatne paigaldamine testimiseks kasutatavatesse virtuaalmasinatesse ning testid käivitatakse automaatselt vastavate skriptide abil.

Koormustestide läbiviimseks kasutatakse integratsioonitestide jaoks üles seatud keskkonda. Koormustestideks koostatakse vastavad testskriptid.

## Loodavad tulemid

Testimise tegevuste käigus luuakse järgmised tulemid:

* Testistrateegia dokument
* Testiplaan
* Testilood
* Testiskriptid
* Testandmed (kirjeldatuna testilugudes)
* Testimiseks vajalikud makettrakendused (test andmekogu makettrakendus)
* Testide paigaldamise, käivitamise ja täiendamise juhend
* Testiraport

## Ajaplaan

* Testistrateegia, testiplaan, testilood, testandmed, makettrakendused valmivad 25.04.2016
* Testide paigaldamise käivitamise ja täiendamise juhend luuakse, testid viiakse läbi ning testiraport koostatakse perioodil 25.04.2016 - 09.05.2016

