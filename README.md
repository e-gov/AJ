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

### Andmejälgija teenus ja protokoll

**Realiseerida Andmejälgija kasutusteabe esitamise protokollile vastav X-tee teenus iseseisvalt**

   RIA vaatest on oluline, et kõik Andmejälgijat rakendavad andmekogud teeks seda samadel alustel, st rakendaks enda teenuse realiseerimisel Andmejälgija protokolli. Lihtsalt öeldes peab olema Andmejälgija teenus ehitatud RIA poolt pakutud spetsifikatsiooni põhjal, et oleks võimalik erinevate andmekogude Andmejälgija teenused eesti.ee portaalis sarnaselt kuvada.

   Kuna andmekogud ja neid teenindavad infosüsteemid on väga erinevad, on praktikas reeglina mõistlik realiseerida Andmejälgija teenus iseseisvalt, vastavalt konkreetse süsteemi iseloomule ja nõuetele. Tihti on mõistlik aluseks võtta igas infosüsteemis olev logilahendus, mis talletab kõik isikuandmetega toimunud sündmused.

   X-teel avatava [teenuse findUsage spetsifikatsioon](https://github.com/e-gov/AJ/blob/master/doc/spetsifikatsioonid/Kasutusteabe_esitamise_protokoll.md)
   * Kasutusteabe esitamise protokoll (SOAP) - andmesalvesti rollis on mis tahes vastavaid andmeid sisaldav rätseplahendus
   * Eesti.ee REST päringu tugi tuleb tulevikus

### Kontakt ja täpsem info

Kõigi küsimuste puhul palume võtta ühendust Riigi Infosüsteemi Ameti kasutajatoega help@ria.ee
