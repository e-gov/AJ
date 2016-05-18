#!/bin/sh
#
# Usage:
# archive-old-records.sh {date}
#
# {date} should be in format 'YYYY-MM-DD'
# Records are copied to STDOUT, redirect this to file if needed.
# Set PosgreSQL environment variables PGDATABASE, PGHOST, PGPORT, PGUSER if needed!
psql -c "copy (select * from ajlog where logtime < to_char('$1', 'YYYY-MM-DD')) to STDOUT"
