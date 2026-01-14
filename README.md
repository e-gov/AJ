![EL Regionaalarengu Fond](doc/img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

Andmejälgija / Data Tracker
=====================

### Andmejälgija üldine kirjeldus

Andmejälgija on inimesele suunatud teenus eesti.ee-s, mille eesmärgiks on tagada isikuandmete töötluse läbipaistvus avalikus sektoris. Andmejälgija toetub iga andmekogu võimekusele enda sees toimuvat andmetöötlust logide kujul talletada, et seda hiljem inimesele ehk andmesubjektile eesti.ee-s oleva teenuse kaudu kuvada. 

Arhitektuurselt on tegemist täielikult hajusa süsteemiga, st inimesele kuvatav info pärineb otse Andmejälgija teenuse realiseerinud andmekogust. Eesti.ee teeb kasutaja soovil päringu igasse Andmejälgija teenusesse ning kuvab päringuvastuse ilma salvestamata välja.

![AJ_3](img/aj_model.PNG)
### Andmejälgijas kajastuv info

Andmejälgija peab inimesele kuvama infot nii andmekogus lokaalselt toimuvast andmetöötlusest (ametnike-töötajate tegevused isikuandmetega) kui ka ülevaadet sellest, kui andmeid on edastatud mõnele kolmandale osapoolele (üle X-tee teisele riigiasutusele, ettevõttele jm). 

Soovituslik on Andmejälgija funktsionaalsuse peale mõelda juba infosüsteemi projekteerides, arvestades vajadusega süsteemis toimuv andmetöötlus tulevikus inimeste jaoks läbipaistvaks ja arusaadavaks muuta. 

### Andmejälgija funktsionaalsuse loomine infosüsteemile või andmekogule
Oluline on esmalt luua andmekogus toimuvat andmetöötlust kajastav logi. 

Andmetöötlusfaktid (logid) tuleb teha publitseeritavaks X-teel. Selleks on kasutusel standardiseeritud teenus findUsage, mis võimaldab kuvada logi eesti.ee sektsioonis Andmejälgija. X-tee turvaserveris tuleb registreerida asutuse alamsüsteemi pakutav teenus (findUsage), mis väljastab andmejälgija kasutusteabe esitamise protokollile vastavalt logid. X-teel avatava teenuse findUsage spetsifikatsioon on dokumendis "Kasutusteabe esitamise protokoll". Liidestus tuleb teha REST päringu põhjal.

X-tee turvaserveris on vaja ning teenus teha kättesaadavaks riigiportaalile, LIVE keskkonna tunnus EE/GOV/70006317/datatracker. Testkeskkonna tunnus EE-TEST/GOV/70006317/datatracker ja arenduskeskkonna tunnus on EE-DEV/GOV/70006317/datatracker.

Andmejälgida teenuse kasutusele võtmisega seotud juriidilise infoga saad tutvuda dokumendis "Rakendusjuhend".

###  Andmetöötluslepingu sõlmimine RIA ning andmejälgijat kasutava asutuse vahel

Leping sõlmitakse RIA ja andmejälgija teenust pakkuva asutuse vahel.

* Andmejälgijaga liitumislepingu vormi fail on "Andmejälgija liitumislepingu põhi".

###  Taotlus andmejälgija teenuse avamiseks riigiportaalis eesti.ee

Andmekogu omav asutus esitab eesti.ee-le taotluse teenuse avamiseks. Vajalik info:
* Teenuse nimi riigiportaalis (eesti, inglise ja vene keeles); kuvatakse kasutajale dropdown menüüs.
* X-tee alamsüsteemi nimetus, mille vahendusel andmejälgija teenust pakutakse.
* Teenuse sisulise omaniku kontaktandmed (nimi, ametikoht, e-post, asutus). Kontaktisik, kellega võetakse ühendust teenuse osutamist puudutavate üldiste küsimuste korral.
* Teenuse andmekogu spetsiifiline kirjeldus riigiportaalis avaldamiseks (eesti, inglise ja venekeelne variant). Kirjeldus andmekogu inimmõistetavast eesmärgist ning üldine kirjeldus selles toimuvast andmetöötlusest.

* Andmejälgijaga liitumistatoluse vormi fail on "Andmejälgija liitumistaotluse põhi".

## Liidestumise protsess

Asutustele, kes soovivad riigiportaali andmejälgijaga liidestuda:

1.  **Teenuse arendus:** Arendada `findUsage` teenus vastavalt [kasutusteabe esitamise protokollile](doc/spetsifikatsioonid/Kasutusteabe_esitamise_protokoll.md).
2.  **Liitumistaotlus:** Asutuse allkirjaõiguslikul isikul täita ja allkirjastada [andmejälgija liitumistaotlus](doc/Andmej%C3%A4lgija%20liitumistaotlus%20p%C3%B5hi.docx) ning saata see aadressile `klient@ria.ee` või edastada DHX kaudu.
3.  **Ligipääsude avamine:** Avada asutuse `findUsage` teenus X-teel järgmistele RIA alamsüsteemidele:
    * `ee-dev/GOV/70006317/datatracker`
    * `ee-test/GOV/70006317/datatracker`
    * `EE/GOV/70006317/datatracker`
    * *Märkus: Toodangu ligipääsu võib avada pärast lepingu sõlmimist, kuid enne päringu toodangusse minekut.*
4.  **Testandmed:** Genereerida `ee-test` X-teel olevale andmejälgija teenusele testkirjed kokkulepitud demo-isikukoodile (näiteks `60001017869` või `39901012239`), et RIA saaks veenduda teenuse toimimises.
5.  **Testimine:** RIA testib esmalt teenuse tehnilist toimimist. Eduka testi korral lisatakse päring riigiportaali *stage*-keskkonda, kuhu luuakse asutusele ligipääs IP *whitelist*'i alusel kontrollimiseks.
6.  **Leping:** Sõlmida RIA-ga [andmetöötlusleping](doc/Andmej%C3%A4lgija%20liitumisleping%20p%C3%B5hi.docx). Lepingu protsessiga võib alustada paralleelselt liitumistaotlusega (punkt 2).
7.  **Toodang:** Kui teenus on testitud, leping sõlmitud ja toodangu X-tee ligipääs avatud, lisatakse päring riigiportaali andmejälgija rakendusse (live).

### Kontakt ja täpsem info

Kõigi küsimuste puhul palume võtta ühendust Riigi Infosüsteemi Ameti kasutajatoega help@ria.ee.
