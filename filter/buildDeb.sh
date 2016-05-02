#!/bin/bash
#
# Kasutamine:
#    buildDeb.sh {projectName}

projectName="$1"
if [ -z "$projectName" ]; then
	echo "Kasutamine: $0 {projectName}"
	exit 1
fi

if [ ! -f "build/libs/$projectName.war" ]; then
	echo "WAR faili ei leitud: 'build/libs/$projectName.war'"
	exit 2
fi

(cd etc ; debuild --no-tgz-check -us -uc || exit $?)

if [ ! -f $projectName*.deb ]; then
	echo "DEB paketi tekitamine ebaõnnestus"
	exit 3
fi

if [ ! -d "build/deb" ]; then
	mkdir -p build/deb
fi

# Kopeerime deb paketi:
mv -f $projectName*.deb build/deb/

# Kustutame üleliigsed failid:
rm -f $projectName*.build $projectName*.changes $projectName*.dsc $projectName*.tar.gz
rm -f etc/debian/$projectName* etc/debian/files
exit 0
