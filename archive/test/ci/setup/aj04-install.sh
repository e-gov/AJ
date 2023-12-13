#!/bin/bash
#
# aj04.ci.kit serveri konfigureerimise skript
#
# Käivitada root kasutaja õigustes!

# Käivitame serverite ühise konfigureerimise:
. ./aj-install.sh

service jetty8 stop

# Paigaldame JRE 7, sest SOAPui vajab seda:
apt-get install openjdk-7-jre-headless

# Muudame Jetty8 kasutama JRE 7:
perl -pi -e 's{^\s*#?\s*JAVA_HOME\s*=.*$}{JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64}' /etc/default/jetty8

# Et õnnestuks Jenkinsi poolt sinna WSDL faile paigutada:
chgrp jetty /var/lib/jetty8/webapps/root
chmod g+rwx /var/lib/jetty8/webapps/root

# Paneme jetty käima:
service jetty8 start
