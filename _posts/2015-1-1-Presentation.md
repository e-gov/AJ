---
layout: page
title: Andmejälgija / Personal Data Monitor
---

<img src='{{ site.url }}/img/Extractor.svg'>

- pakub X-tee sõnumite edastamise teenust / X-tee sõnumiprotokoll (SOAP)
- vajab teenust, kuhu X-tee sõnum edastada / X-tee sõnumiprotokoll (SOAP)
- vajab teenust, kuhu isikuandmete kasutamise metaandmed salvestada / AS salvestusprotokoll
- vajab konfifaili eraldusreeglitega / EF konfivorming

--- 

<img src='{{ site.url }}/img/Logger.svg'>

- pakub andmete salvestamise teenust / AS salvestusprotokoll
- pakub andmete väljastuse teenust X-teel / X-tee sõnumiprotokoll (SOAP)
- pakub andmete väljastuse teenust REST API vormis / AS REST andmeväljastusprotokoll

---

<img src='{{ site.url }}/img/Verifier.svg'>

- vajab teenust, kust andmed võtta  / AS REST andmeväljastusprotokoll
- pakub andmete inimesele kuvamise  teenust / HTML veebirakendus
- vajab konfifaili, milles paroolid / paroolifail

---

<img src='{{ site.url }}/img/Presenter.svg'>

- pakub inimesele andmete kuvamise teenust / HTML veebirakendus
- vajab X-teel teenuseid, kust andmeid  saada / X-tee sõnumivahetusprotokoll
- vajab konfifaili X-tee teenuste nimedega  / Esitleja konfivorming



