# dumonitor privileges creation

# should be run as a user postgres
# postgres user and postgres database and tables should have been created before,  
# see createdb.sh and createschema.sh

psql dumonitor -f aj_privileges.sql