# Testiplaan ja testilood

**DUMonitor**

Versioon 1.0, 17.04.2016

Tellija: Riigi Infosüsteemi Amet

Täitja: Degeetia OÜ, Mindstone OÜ

## Dokumendi ajalugu

| Versioon | Kuupäev    | Autor      | Märkused
|----------|------------|------------|----------------------------------------------
| 1.0      | 17.04.2016 | Ivo Mehide | Esimene versioon


## Sissejuhatus

Käesolev dokument kirjeldab DUMonitor tarkvara arendmise käigus läbiviidavaid testimistegevusi ja testide tulemuste mõõtmist.

## Testitav funktsionaalsus

Testimise käigus veendutakse järgmise funktsionaalsuse toimimises:

* Filtri komponent:
 * Päringu vahendamine andmekogusse ning vastuse vahendamine tagasi
 * Päringu ja päringu vastuse logimine
 * Andmejälgimise alustamise ja lõpetamise teadete edastamine
* Andmesalvestaja komponent:
 * Andmete lisamise päringu vastuvõtmine ja vastuvõetud andmete salvestamine
 * Andmete otsimine REST liidese kaudu
 * Andmete otsimine veebiliidese kaudu
 * Ajahorisondi info tagastamine X-tee liidese kaudu
 * Andmete otsimine X-tee liidese kaudu
* Esitamise testrakenduse komponent:
 * Andmete otsimine

## Testimisviisid

Testitava funktsionaalsuse kohta realiseeritakse ühiktestid, mille abil veendutakse funktsionaalsuse põhimõttelises toimimises. 
Ühiktestid käivitatakse automaatselt tarkvarakoodi kompileerimise käigus.

Liideste toimimise testimine toimub integratsioonitestide abil. Integratsioonitestide läbiviimiseks realiseeritakse vajalikud makettrakendused ning testiskriptid. Lisaks luuakse vajalikud virtuaalmasinad ning paigaldatakse neisse testimiseks vajalik tarkvara. Pidevintegratsioonikeskkonna kaudu toimub arendatud tarkvarakoodi atomaatne paigaldamine testimiseks kasutatavatesse virtuaalmasinatesse ning testid käivitatakse automaatselt vastavate skriptide abil.

Koormustestide läbiviimseks kasutatakse integratsioonitestide jaoks üles seatud keskkonda. Koormustestideks koostatakse vastavad testskriptid.

## Testilood

### Testilugude koond

#### Integratsioonitestid

* Filtri komponent:
 * Päringu vastuvõtmine
 * Päringu edastamine
 * Päringu logimine
 * Päringu vastuse vastuvõtmine
 * Päringu vastuse tagastamine
 * Päringu vastuse logimine
 * Andmejälgimise alustamise teate saatmine
 * Andmejälgimise lõpetamise teate saatmine
* Andmesalvestaja komponent:
 * REST liidese kaudu kasutusteabe andmete lisamine
 * REST liidese kaudu andmejälgimise alustamise teate lisamine
 * REST liidese kaudu andmejälgimise lõpetamise teate lisamine
 * X-tee liidese kaudu kasutusteabe andmete otsimine
 * X-tee liidese kaudu kasutusteabe andmete otsingutulemuse tagastamine
 * X-tee liidese kaudu ajahorisondi teenuse päringu vastuvõtmine
 * X-tee liidese kaudu ajahorisondi teenuse vastuse tagastamine
 * Sisekasutuse REST liidese kaudu andmete otsimine
 * Sisekasutuse REST liidese kaudu tulemuse tagastamine
 * Sisekasutuse veebiliidese kaudu andmete otsimine
 * Sisekasutuse veebiliidese kaudu tulemuse tagastamine
* Esitamise testrakenduse komponent:
 * Andmete otsimine
 * Tulemuse tagastamine

#### Koormustestid
 
* Filtri komponendi koormustest
* Andmesalvestaja REST liidese koormustest
* Andmesalvestaja X-tee liidese koormustest
* Andmesalvestaja veebiliidese koormustest

### Testilugude kirjeldused

#### Integratsioonitestid

##### Filtri komponent

###### Päringu vastuvõtmine

* Testiloo nimetus: Filtri komponendi poolt päringu vastuvõtmine
* Testiloo indeks: 1
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Päringu edastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Päringu logimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Päringu vastuse vastuvõtmine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Päringu vastuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Päringu vastuse logimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Andmejälgimise alustamise teate edastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Andmejälgimise lõpetamise teate edastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

##### Andmesalvestaja komponent

###### REST liidese kaudu kasutusteabe andmete lisamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### REST liidese kaudu andmejälgimise alustamise teate lisamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### REST liidese kaudu andmejälgimise lõpetamise teate lisamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### X-tee liidese kaudu kasutusteabe andmete otsimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### X-tee liidese kaudu kasutusteabe andmete otsingutulemuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### X-tee liidese kaudu ajahorisondi teenuse päringu vastuvõtmine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### X-tee liidese kaudu ajahorisondi teenuse vastuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Sisekasutuse REST liidese kaudu andmete otsimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Sisekasutuse REST liidese kaudu tulemuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Sisekasutuse veebiliidese kaudu andmete otsimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Sisekasutuse veebiliidese kaudu tulemuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

##### Esitamise testrakenduse komponent

###### Andmete otsimine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

###### Tulemuse tagastamine

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

#### Koormustestid
 
##### Filtri komponendi koormustest

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

##### Andmesalvestaja REST liidese koormustest

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

##### Andmesalvestaja X-tee liidese koormustest

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:

##### Andmesalvestaja veebiliidese koormustest

* Testiloo nimetus:
* Testiloo indeks:
* Viide kasutusloole:
* Lühikirjeldus: 
* Omadused, mida testitakse:
* Omadused, mida ei testita:
* Lähtetingimused:
* Oletatavad vead:
* Testi jada kirjeldus:
* Käivitamise ja läbiviimise skript:
* Katkestamise ja jätkamise tingimused testi käigus tekkinud vigade korral:
* Testi tulemuste hindamise kriteeriumid negatiivsel ja positiivsel juhul:
* Testi läbiviimise meetodi kirjeldus:
