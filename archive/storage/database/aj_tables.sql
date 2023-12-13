--- create sequences

create sequence ajlog_id_seq start with 1000;

--- create tables

create table ajlog (
  id integer primary key default nextval('ajlog_id_seq'), 
  personcode varchar(13), -- Selle isiku isikukood, kelle andmeid toodeldi.
                          -- Kui isikukood on olemas, peab ta algama riigi prefiksiga EE.  
  logtime timestamp default now() not null, -- Isikuandmete kasutusteabe syndmuse aeg (kuupaev ja kellaaeg) 
                             -- sekundi tapsusega. Salvestatakse logikirje kirjutamise aeg. 
  action varchar(100) not null, -- Menetluse/tegevuse inimmoistetav nimi. 
                                -- Tuletatakse kas X-tee paringu nimest ja/voi 
		                -- on andmetootleja poolt seatav.
  sender varchar(100), -- Nii asutuse kui andmekogu koond-inimmoistetav nimi / viide, 
                       -- kellelt andmed saadi. Kohustuslik juhul, kui 
		       -- logikirje vastab andmete saabumisele X-tee kaudu.
  receiver varchar(100), -- Asutuse, vajadusel tapsustava taiendusega, inimmoistetav 
                         -- nimi / viide, kellele andmeid edastatakse. 
			 -- Kohustuslik juhul, kui logikirje vastab andmete saatmisele 
			 -- X-tee kaudu. Voib sisaldada klikitavat URL-i.
  restrictions char(1),  -- Maarab, kas tegemist on piiratud nahtavusega kirjega.
                         -- "A" - avalik, piirang puudub
                         -- "P" - piiratud, rakendub eelpool kirjeldatud piirang 
  sendercode varchar(10), -- Asutuse voi andmekogu registrikood/sisekasutuse nimi, 
                          -- kellelt andmed saadi. Kohustuslik juhul, kui logikirje
			  -- vastab andmete saabumisele X-tee kaudu.
  receivercode varchar(10), -- Asutuse v�i andmekogu registrikood/sisekasutuse nimi, 
                            -- kellele andmeid edastatakse. Kohustuslik juhul, kui 
			    --- logikirje vastab andmete valjasaatmisele X-tee kaudu.
  actioncode varchar(50), -- Menetluse/tegevuse sisekasutuseks ettenahtud nimi. 
                          -- Voib olla X-tee paringu nimi, andmeteenuse voi andmekogu nimi vms.
  xroadrequestid varchar(50), -- X-tee paringu identifikaator.
  xroadservice varchar(50), -- Isikuandmete kasutuse paringu teenuse nimetus.
  usercode varchar(13) -- X-tee kaudu andmeid parinud isiku v�i asutusesisese tootleva
                       -- isiku isikukood. Vaartuse esinemisel peab algama riigi prefiksiga "EE".
);

--- create indexes

-- create index ajlog_personcode_idx on ajlog (personcode);
-- create index ajlog_logtime_idx on ajlog (logtime);

create index ajlog_personcode_logtime_idx on ajlog (personcode, logtime);

