---
layout: page
title: Andmejälgija / Personal Data Monitor
---

Personal Data Monitor, *Andmejälgija* in Estonian, is a set of 4 [microservice](https://en.wikipedia.org/wiki/Microservices)-style  applications that, when combined with each other and attached to X-Road can offer the citizen the comprehensive view of how his or her personal data has been used by the government.

Each component is designed to be simple, "dumb". That means the component performs a single, clear function and communicates with its environment via clean APIs. Component requires only limited view and understanding of its environment. 

The power lies in how the components are connected to each other and the elements of X-Road.

---
**Extractor** (*Eraldusfilter* *et*) watches Information System's outbound traffic and filters out the messages where personal data is being sent out from the Information System. A log record is created for each such event.

Extractor is placed as a proxy between X-Road Security Server and governmental Information System.  

<img style='float:left; margin: 10px 50px;' src='{{ site.url }}/img/Extractor.svg'>

Extractor has four intefaces:

<img style='display: inline-block;' src='{{ site.url }}/img/ProvidesRIGHT.svg'> - provides a service of delivering X-Road message from Information System to Security Server

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



