
-- tables

grant all privileges on ajlog to dumonitor;
grant select, insert, update, delete on ajlog to dumonitor_app;

-- sequences

grant all privileges on ajlog_id_seq to dumonitor;
grant usage, select, update on ajlog_id_seq to dumonitor_app;
