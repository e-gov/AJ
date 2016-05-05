#!/bin/bash
#
# Kasutamine:
#    buildDeb.sh {projectName} {version}

projectName="$1"
version="$2"
if [ -z "$projectName" -o -z "$version" ]; then
	echo "Kasutamine: $0 {projectName} {version}"
	exit 1
fi

if [ ! -f "build/libs/$projectName.war" ]; then
	echo "WAR faili ei leitud: 'build/libs/$projectName.war'"
	exit 2
fi

# Uuendame debian-i kirjeldusfailides versiooninumbri info:
perl -pi -e 's{^('"$projectName"'\s+)\([^)]+\)(\s+unstable;)}{$1('"$version"'-1)$2}' etc/debian/changelog


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
rm -rf etc/debian/$projectName* etc/debian/files
exit 0
