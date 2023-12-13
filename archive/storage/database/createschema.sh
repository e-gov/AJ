# dumonitor database schema creation

# should be run as a user postgres
# aj user and aj database should have been created before, 
# see createdb.sh

psql dumonitor -f aj_tables.sql