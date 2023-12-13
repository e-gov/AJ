#!/bin/bash

CURDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# "postgres" user password:
password="postgres"

# createuser -S -D -R -P -e dumonitor # create user dumonitor, asks for password, enter aj22p
#PGPASSWORD=$password psql -U postgres postgresql://localhost -c "create role dumonitor with password 'aj22p'"
#PGHOST=localhost PGUSER=postgres PGPASSWORD=$password createdb -O dumonitor -E 'UTF8' -T template0 dumonitor # create database dumonitor

# should be run as a user postgres
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password psql dumonitor -f $CURDIR/aj_tables_drop.sql
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password psql dumonitor -f $CURDIR/aj_tables.sql
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password psql dumonitor -f $CURDIR/aj_privileges.sql
