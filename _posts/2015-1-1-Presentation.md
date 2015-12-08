---
layout: page
title: Andmejälgija / Personal Data Monitor
---

Personal Data Monitor is a set of 4 [microservice](https://en.wikipedia.org/wiki/Microservices)-style small applications that, when combined with each other and attached to X-Road can offer a citizen the comprehensive view of how his or her personal data has been used in governmental databases. Each component is designed to be "dumb" - that is to be simple in function.

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



