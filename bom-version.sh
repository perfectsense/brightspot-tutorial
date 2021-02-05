#!/bin/sh

version=$1
artifact=$2

if [[ -z $version || -z $artifact ]]
then
    echo "USAGE: $0 version artifact"
    exit
fi

curl -s https://artifactory.psdops.com/psddev-releases/com/psddev/brightspot-bom/$version/brightspot-bom-$version.pom \
| xmllint --xpath "//*[local-name()='dependency']/*[local-name()='artifactId' and text()='$artifact']/../*[local-name()='version']/text()" -
echo
