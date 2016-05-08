Testiraport
===========

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

  * [Dokumendi ajalugu](#dokumendi-ajalugu)
  * [Sisukord](#sisukord)
  * [Sissejuhatus](#sissejuhatus)
  * [Testide tulemused](#testide-tulemused)
    * [Automaatsed testid](#automaatsed-testid)
    * [Käsitsi läbi viidud testid](#k%C3%A4sitsi-l%C3%A4bi-viidud-testid)
    * [Koormustestid](#koormustestid)
  * [Erisused](#erisused)
  * [Soovitused](#soovitused)

## Sissejuhatus

Käesolev dokument sisaldab DUMonitor tarkvara testide läbiviimise tulemuste kokkuvõtet. 
Testide läbiviimise aluseks olid [testiplaani](Testiplaan.md) ja [testilugude](Testilood.md) dokumendid.

## Testide tulemused

Testimine viidi läbi Ivo Mehide poolt ajavahemikul 08.05.2016 kell 22:55 kuni 14:00.

Testide tulemused on toodud allpool.

### Automaatsed testid

Automaatsed testid käivitati tellija sidusarenduskeskkonna Jenkins CI tarkaras kirjeldatud projekti "AJ-build" tegevuse "build" sooritamise abil.

| Testiloo nr | Testiloo nimetus                                                                                        | Testimise aeg    | Testi tulemus | Kommentaarid |
|-------------|---------------------------------------------------------------------------------------------------------|------------------|---------------|--------------|
| 1           | Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - korrektne päring          | 08.05.2016 22:57 | OK            |              |
| 2           | Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - korrektne MTOM päring     | 08.05.2016 22:59 | OK            |              |
| 3           | Filtri komponent: X-teelt saabunud päringu vastuvõtt ja vastuse tagastamine - vigane päring             | 08.05.2016 22:58 | OK            |              |
| 4           | Filtri komponent: päringu ja vastuse logimine                                                           | 08.05.2016 22:59 | OK            |              |
| 5           | Andmesalvestaja komponent: URL kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine          | 08.05.2016 23:00 | OK            |              |
| 6           | Andmesalvestaja komponent: REST liidese kaudu kasutusteabe logimise sõnumi vastuvõtmine ja salvestamine | 08.05.2016 23:00 | OK            |              |
| 7           | Andmesalvestaja komponent: REST liidese kaudu logist otsimine ja tulemuse tagastamine                   | 08.05.2016 22:57 | OK            |              |
| 8           | Andmesalvestaja komponent: X-tee liidese kaudu logist otsimine ja tulemuse tagastamine                  | 08.05.2016 22:58 | OK            |              |

### Käsitsi läbi viidud testid

Käsitsi läbi viidavad testid teostati tellija sidusarenduskeskkonnas vastava testiloo poolt ettenähtud viisil.

| Testiloo nr | Testiloo nimetus                                                                              | Testimise aeg    | Testi tulemus | Kommentaarid |
|-------------|-----------------------------------------------------------------------------------------------|------------------|---------------|--------------|
|  9          | Andmesalvestaja komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine         | 08.05.2016 23:00 | OK            |              |
| 10          | Esitamise testrakenduse komponent: veebiliidese kaudu logist otsimine ja tulemuse tagastamine | 08.05.2016 23:02 | OK            |              |
| 11          | Eesti.ee komponent: MISP2 veebiliidese kaudu logist otsimine ja tulemuse tagastamine          | 08.05.2016 23:05 | OK            | Testitud isikukoodi 37012062719 baasilt, selleks muudetud andmebaasis vastaval kirjel isikukoodi väärtus ümber. |

### Koormustestid

Koormustestid käivitati tellija sidusarenduskeskkonna Jenkins CI tarkaras iga koormustesti jaoks kirjeldatud vastava projekti tegevuse "build" sooritamise abil.

| Testiloo nr | Testiloo nimetus                                            | Testimise aeg    | Testi tulemus | Kommentaarid |
|-------------|-------------------------------------------------------------|------------------|---------------|--------------|
| 12          | Filtri komponendi koormustest                               | 08.05.2016 23:07 | OK            | Tulemused: filterLoadRequest avg 625ms, testakLoadRequest avg 647ms, vahe seega -22ms. |
| 13          | Andmesalvestaja komponendi REST logimisliidese koormustest  | 08.05.2016 13:00 | OK            | Tulemus: avg 9ms |
| 14          | Andmesalvestaja komponendi REST päringuliidese koormustest  | 08.05.2016 13:00 | OK            | Tulemus: avg 14ms |
| 15          | Andmesalvestaja komponendi X-tee päringuliidese koormustest | 08.05.2016 13:00 | OK            | Tulemus: avg 42ms |

## Erisused

Filtri komponendi koormustesti tulemused on liiga palju mõjutatud keskkonnast. Testkeskkonnad on kõik virtuaalmasinates ning seetõttu pole nende stabiilsus piisavalt tagatud,
et testida 1ms täpsusega päringute täitmise aega. Tulemusena kõigub ka 500 päringu korral aeg nii palju, et keskmise baasilt arvutades on läbi filtri tehtud päringud andmekogu pihta 22ms kiiremad, kui otse tehtud päringud.

## Soovitused

Koormustestide õigete tulemuste saamiseks oleks vaja testid läbi viia virtuaalmasinate asemel päris masinatega ning lisaks on vaja tagada nende vahel testimise hetkel võrguühendus, kus ei toimu muud võrguliiklust.

