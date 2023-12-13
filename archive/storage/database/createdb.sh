# dumonitor database and user creation

# should be run as a user postgres
# asks for password for dumonitor, use aj22p for a password
# creates database dumonitor

# createuser -S -D -R -P -e dumonitor # create user dumonitor, asks for password, enter aj22p
#psql -c "CREATE ROLE dumonitor PASSWORD 'md545a9c580a300097b2636906348056c60' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;"
psql -c "CREATE ROLE dumonitor PASSWORD 'aj22p' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;"
createdb -O dumonitor -E 'UTF8' -T template0 dumonitor # create database dumonitor

#psql dumonitor -c "CREATE ROLE dumonitor_app PASSWORD 'md545a9c580a300097b2636906348056c60' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;"
psql dumonitor -c "CREATE ROLE dumonitor_app PASSWORD 'aj22p' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;"
