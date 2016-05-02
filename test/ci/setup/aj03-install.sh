#!/bin/bash
#
# aj03.ci.kit serveri konfigureerimise skript
#
# Käivitada root kasutaja õigustes!

# Käivitame serverite ühise konfigureerimise:
. ./aj-install.sh

# Paigaldame PostgreSQL andmebaasi:
apt-get install postgresql
# Tekitame postrgres kasutajale parooli:
#    "postgres" user password:
password="postgres"
sudo -u postgres psql -c "alter user postgres password '$password';"

# Tekitame andmejälgija kasutaja ja andmebaasi:
PGPASSWORD=$password psql -U postgres postgresql://localhost -c "CREATE ROLE ajuser PASSWORD 'md545a9c580a300097b2636906348056c60' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;"
PGHOST=localhost PGUSER=postgres PGPASSWORD=$password createdb -O ajuser -E 'UTF8' -T template0 aj # create database aj

# Lubame üle võrgu MD5 autentimist:
perl -pi -e 's{^(\s*host\s+all\s+all\s+)127.0.0.1/32(\s+md5\s)}{$1 0.0.0.0/0 $2}' /etc/postgresql/9.3/main/pg_hba.conf
# Avame ühendused ka välistelt hostidelt:
perl -pi -e 's{^\s*\#?\s*listen_addresses\s*=\s*'localhost'}{listen_addresses = 'localhost'}' /etc/postgresql/9.3/main/postgresql.conf
# Teeme restart:
service postgresql restart
