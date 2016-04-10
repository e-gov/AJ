#!/bin/bash

CURDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# "postgres" user password:
password="postgres"

# createuser -S -D -R -P -e ajuser # create user ajuser, asks for password, enter aj22p
#PGPASSWORD=$password psql -U postgres postgresql://localhost -c "create role ajuser with password 'aj22p'"
#PGHOST=localhost PGUSER=postgres PGPASSWORD=$password createdb -O ajuser -E 'UTF8' -T template0 aj # create database aj

# should be run as a user postgres
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password psql aj -f $CURDIR/aj_tables.sql
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password psql aj -f $CURDIR/aj_privileges.sql
