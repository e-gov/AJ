#!/bin/bash

../gradlew build
cp build/libs/dumonitor-query-1.0-SNAPSHOT.war /opt/web/query/webapps/

