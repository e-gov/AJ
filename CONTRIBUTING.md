###Arenduskorraldus

####Tarkvara publitseerimine
Tarkvara publitseeritakse [reliisidena](https://github.com/e-gov/AJ/releases).

####Versioneerimine
Kasutatakse [semantilist versioneerimist](http://semver.org/).

####Kaastöö
Kaastöö on oodatud, nii koodi kui ka dokumentatsiooni osas, vastavalt allolevatele põhimõtetele.

####Harud
Harudena kasutatakse `master` ja `develop`. Vajadusel luuakse täiendavaid harusid.

####Vearaportid ja täiendusettepanekud
- Vearaportid ja täiendusettepanekud palume lisada [Issues](https://github.com/e-gov/AJ/issues) rubriiki.
- Vearaporteid ja ettepanekuid on õigus esitada igaühel.
- Igaühel on õigus vearaporteid ja täiendusettepanekuid kommenteerida.
- [Issues](https://github.com/e-gov/AJ/issues) rubriik toimib AJ arenduse _product backlog_-ina.

####Koodihoidla
- Koodihoidlas https://github.com/e-gov/AJ hoitakse: 1) tarkvara lähtekoodi; 2) dokumentatsiooni; 3) paigalduspakette (rubriigis [reliisid](https://github.com/e-gov/AJ/releases)).
- Koodihoidlat dubleeritakse RIA BitBucket koodihoidlas.

####Koodi täiendamine ja edasiarendamine
- Koodi täiendused palun ainult `develop` haru kaudu. `develop` harust esitada tõmbenõue (_pull request_) `master` harusse.
- Arendajatel, kellel pole `develop` harule kirjutamisõigust, palun teha arendus eraldi harus ja esitada tõmbenõue `develop` haru vastu.
- Iga kooditäiendusega tehakse uus reliis.
- Iga koodi täiendus peegeldatakse varundamise eesmärgil RIA BitBucket koodihoidlasse.
- Koodi täiendamisega koos täiendatakse ka dokumentatsiooni, sh nii kasutusjuhendeid kui ka spetsifikatsiooni.

####Dokumentatsiooni täiendamine ja edasiarendamine
- Dokumentatsiooni parendused, mis ei ole seotud koodi muutmisega, ei tekita uut versiooni.
- Dokumentatsiooni parendusi võib teha `master` harus.

arhitekt Priit Parmakson, Riigi Infosüsteemi Amet
