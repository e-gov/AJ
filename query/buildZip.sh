#!/bin/bash
#
# Kasutamine:
#    buildZip.sh {projectName} {version}

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

if [ ! -d "build/zip/$projectName-$version" ]; then
	mkdir -p "build/zip/$projectName-$version" || exit 3
fi

# Kopeerime vajalikud failid:
cp -f "build/libs/$projectName.war" etc/dumonitor*.* "build/zip/$projectName-$version" || exit 4

# Tekitame ZIP:
(cd build/zip; zip -r "$projectName-$version.zip" "$projectName-$version" || exit 5)

# Kustutame üleliigsed failid:
rm -rf "build/zip/$projectName-$version" || exit 6

exit 0
