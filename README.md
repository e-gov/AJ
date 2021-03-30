![EL Regionaalarengu Fond](doc/img/EL_Regionaalarengu_Fond_horisontaalne.jpg)

Andmejälgija / Personal Data Usage Monitor
=====================

### Andmejälgija üldine kirjeldus

Andmejälgija on inimesele suunatud teenus eesti.ee-s, mille eesmärgiks on tagada isikuandmete töötluse läbipaistvus avalikus sektoris. Andmejälgija toetub iga andmekogu võimekusele enda sees toimuvat andmetöötlust logide kujul talletada, et seda hiljem inimesele ehk andmesubjektile eesti.ee-s oleva teenuse kaudu kuvada. 

Arhitektuurselt on tegemist täielikult hajusa süsteemiga, st inimesele kuvatav info pärineb otse Andmejälgija teenuse realiseerinud andmekogust. Eesti.ee teeb kasutaja soovil päringu igasse Andmejälgija teenusesse ning kuvab päringuvastuse ilma salvestamata välja.

### Andmejälgijas kajastuv info

Andmejälgija peab inimesele kuvama infot nii andmekogus lokaalselt toimuvast andmetöötlusest (ametnike-töötajate tegevused isikuandmetega) kui ka ülevaadet sellest, kui andmeid on edastatud mõnele kolmandale osapoolele (üle X-tee teisele riigiasutusele, ettevõttele jm). 

Soovituslik on Andmejälgija funktsionaalsuse peale mõelda juba infosüsteemi projekteerides, arvestades vajadusega süsteemis toimuv andmetöötlus tulevikus inimeste jaoks läbipaistvaks ja arusaadavaks muuta. 

### Andmejälgija teenus ja protokoll

Andmejälgija teenuse ehitamiseks on 2 meetodit

1. Soovitatav: Realiseerida Andmejälgija kasutusteabe esitamise protokollile vastav X-tee teenus

   RIA vaatest on oluline, et kõik Andmejälgijat rakendavad andmekogud teeks seda samadel alustel, st rakendaks enda teenuse realiseerimisel Andmejälgija protokolli. Lihtsalt öeldes peab olema Andmejälgija teenus ehitatud RIA poolt pakutud spetsifikatsiooni põhjal, et oleks võimalik erinevate andmekogude Andmejälgija teenused eesti.ee portaalis sarnaselt kuvada.

   Kuna andmekogud ja neid teenindavad infosüsteemid on väga erinevad, on praktikas reeglina mõistlik realiseerida Andmejälgija teenus iseseisvalt, vastavalt konkreetse süsteemi iseloomule ja nõuetele. Tihti on mõistlik aluseks võtta igas infosüsteemis olev logilahendus, mis talletab kõik isikuandmetega toimunud sündmused.

   X-teel avatava teenuse findUsage spetsifikatsioon
   * Kasutusteabe esitamise protokoll (SOAP) - kirjutatud andmesalvesti seiskohast
   * Kasutusteabe esitamise protokoll (REST) - eesti.ee tugi 2021 II+ kvartal

2. Kasutada RIA poolt loodud standardkomponente (AS-IS põhimõttel, aktiivset arendust hetkel ei toimu)

   RIA poolt 2016. aastal loodud standardkomponendid on võimelised eristama andmekogust väljuvaid X-tee päringute vastuseid, mis sisaldavad inimese isikukoodi, kattes ära osa Andmejälgija teenuse jaoks vajalikust funktsionaalsusest. Siiski tuleb seda vaadelda kui ainult osa ühes andmekogus toimuvast andmetöötlusest. Andmekogude puhul, kus andmevahetus üle X-tee on minimaalne, kuid isikuandmeid töödeldakse lokaalselt, jätaks ainult standardkomponentide kasutamine andmesubjektile väära mulje vähesest andmetöötlusest.

   Kuna lokaalne andmetöötlus ning üle X-tee toimuv andmetöötlus on tihti praktikas läbipõimunud ning kõigi logide põhjal Andmejälgija funktsionaalsuse loomine hõlmab endas ka X-tee päringuid, on standardkomponentide kasutus jäänud väikseks. Sellepärast pakub RIA hetkel neid komponente AS-IS põhimõttel ning aktiivselt arendusse vahendeid ei suuna.

### Kontakt ja täpsem info

Kõigi küsimuste puhul palume võtta ühendust Andmejälgija tootejuhiga Riigi Infosüsteemi Ametis:
Sander Randorg sander.randorg@ria.ee
