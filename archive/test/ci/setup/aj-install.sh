#!/bin/bash
# CI keskkonna serverite ühine häälestusskript
#
# Käivitada root kasutaja õigustes!

# Eemaldame JRE 7 (kuna nõuetes on, et JRE6 peab olema supporditud):
apt-get autoremove openjdk-7-jre-headless
# Paigaldame JRE 6 ja jetty 8:
apt-get install openjdk-6-jdk jetty8

# Muudame jetty8 konfiguratsiooni:
#    Et toimuks jetty käivitamine serveri ülesbuutimisel:
perl -pi -e 's/^\s*#?\s*NO_START\s*=.*$/NO_START=0/' /etc/default/jetty8
#    Et jetty oleks kasutatav väliste võrguinterfeiside kaudu:
perl -pi -e 's/^\s*#?\s*JETTY_HOST\s*=.*$/JETTY_HOST=0.0.0.0/' /etc/default/jetty8
#    Et jetty kasutaks java v6:
perl -pi -e 's{^\s*#?\s*JAVA_HOME\s*=.*$}{JAVA_HOME=/usr/lib/jvm/java-6-openjdk-amd64}' /etc/default/jetty8

# Jetty hot deploy sisselülitamine:
cat <<EOF > /etc/jetty8/jetty-webapps.xml
<Configure id="Server" class="org.eclipse.jetty.server.Server">
    <Call name="addBean">
      <Arg>
        <New id="DeploymentManager" class="org.eclipse.jetty.deploy.DeploymentManager">
          <Set name="contexts">
            <Ref id="Contexts" />
          </Set>
          <Call name="setContextAttribute">
            <Arg>org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern</Arg>
            <Arg>.*/jsp-api-[^/]*\.jar$|.*/jsp-[^/]*\.jar$</Arg>
          </Call>
        </New>
      </Arg>
    </Call>
    <Ref id="DeploymentManager">
          <Call id="webappprovider" name="addAppProvider">
            <Arg>
              <New class="org.eclipse.jetty.deploy.providers.WebAppProvider">
                <Set name="monitoredDirName">/usr/share/jetty8/webapps</Set>
                <Set name="defaultsDescriptor"><Property name="jetty.home" default="."/>/etc/webdefault.xml</Set>
                <Set name="scanInterval">1</Set>
                <Set name="contextXmlDir">/usr/share/jetty8/contexts</Set>
                <Set name="extractWars">true</Set>
              </New>
            </Arg>
          </Call>
    </Ref>
</Configure>
EOF
perl -pi -e 's{^\s*/etc/jetty8/jetty-shared-webapps.xml\s*$}{/etc/jetty8/jetty-webapps.xml}' /etc/jetty8/jetty.conf

# Tagame webapps kataloogile juurdepääsu "jetty" kasutajagrupi poolt:
chgrp jetty /var/lib/jetty8/webapps /usr/share/jetty8/resources
chmod g+rwx /var/lib/jetty8/webapps /usr/share/jetty8/resources

# Paneme jetty käima:
service jetty8 start
