# aj privileges creation

# should be run as a user postgres
# aj user and aj database and tables should have been created before,  
# see createdb.sh and createschema.sh

psql aj -f aj_privileges.sql