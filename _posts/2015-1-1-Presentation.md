---
layout: page
title: Andmejälgija / Personal Data Monitor
---

Personal Data Monitor, *Andmejälgija* in Estonian, is a set of 4 [microservice](https://en.wikipedia.org/wiki/Microservices)-style  applications that, when combined with each other and attached to X-Road can offer the citizen the comprehensive view of how his or her personal data has been used by the government.

Each component is designed to be simple, "dumb". That means the component performs a single, clear function and communicates with its environment via clean APIs. Component requires only limited view and understanding of its environment. 

The power lies in how the components are connected to each other and the elements of X-Road.

---
**Extractor** (*Eraldusfilter* *et*) watches Information System's outbound traffic and filters out the messages where personal data is being sent out from the Information System. A personal data usage log record is created for each such event. The log record contains metadata about personal data usage: person's ID, date, name of the X-Road service, name of the message recipient agency and the purpose of the personal data use (if available). Personal data itself, however, is neither extracted from the message nor recorded. 

Extractor is placed as a proxy between X-Road Security Server and governmental Information System.  

<img style='float:left; margin: 10px 50px 140px 50px;' src='{{ site.url }}/img/Extractor.svg'>

Extractor has four interfaces:

(1) <img style='display: inline-block;' src='{{ site.url }}/img/ProvidesRIGHT.svg'> provides a service of delivering X-Road message from Information System to Security Server

(2) <img style='display: inline-block;' src='{{ site.url }}/img/RequiresLEFT.svg'> requires a service that accepts the X-Road message received from Information System and to be passed on

(3) <img style='display: inline-block;' src='{{ site.url }}/img/RequiresDOWN.svg'> requires a service to where to send the log record

(4) <img style='display: inline-block;' src='{{ site.url }}/img/RequiresDOWN.svg'> requires a configuration file with extraction rules.

Protocols: (1) and (2) conform to X-Road message protocol (which is built over SOAP/HTTP(S)); (3) conforms to Personal Data Usage Logger protocol; and (4) follows a special extraction rule format.

--- 
**Personal Data Usage Logger** (*Andmesalvestaja* *et*) stores the personal data usage log record in database. Contents of the database is made available for request by the person.  

<img style='float:left; margin: 10px 50px 140px 50px;'  src='{{ site.url }}/img/Logger.svg'>

Personal Data Usage Logger has four interfaces:

(1) <img style='display: inline-block;' src='{{ site.url }}/img/ProvidesUP.svg'> provides a service of storing personal data usage log records in database

(2) <img style='display: inline-block;' src='{{ site.url }}/img/ProvidesLEFT.svg'> serves log records over X-Road to the interested party - the person him- or herself 

(3) <img style='display: inline-block;' src='{{ site.url }}/img/ProvidesDOWN.svg'> serves log records to Information Systems audit personnel

(4) <img style='display: inline-block;' src='{{ site.url }}/img/RequiresRIGHT.svg'> requires a configuration file that sets storage parameters.

Protocols: (1) conforms to Personal Data Usage Logger protocol; (2) conforms to X-Road message protocol (which is built over SOAP/HTTP(S)); (3) is a RESTful API.

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



