#!/bin/sh
#
# Usage:
# archive-old-records.sh {date}
#
# {date} should be in format 'YYYY-MM-DD'
# Set PosgreSQL environment variables PGDATABASE, PGHOST, PGPORT, PGUSER if needed!
psql -c "delete from ajlog where logtime < to_char('$1', 'YYYY-MM-DD')"
