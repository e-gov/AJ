# aj database and user creation

# should be run as a user postgres
# asks for password for ajuser, use aj22p for a password
# creates database aj

createuser -S -D -R -P -e ajuser # create user ajuser, asks for password, enter aj22p
createdb -O ajuser -E 'UTF8' -T template0 aj # create database aj

