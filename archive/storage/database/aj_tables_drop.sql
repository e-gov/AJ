
--- drop all indexes

drop index if exists ajlog_personcode_logtime_idx;

--- drop all tables

drop table if exists ajlog cascade;

--- drop all sequences

drop sequence if exists ajlog_id_seq;

