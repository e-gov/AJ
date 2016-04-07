#!/bin/bash

../gradlew build
cp build/libs/dumonitor-storage-1.0-SNAPSHOT.war /opt/web/storage/webapps/

