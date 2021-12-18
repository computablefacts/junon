# Junon

![Maven Central](https://img.shields.io/maven-central/v/com.computablefacts/junon)
[![Build Status](https://travis-ci.com/computablefacts/junon.svg?branch=master)](https://travis-ci.com/computablefacts/junon)
[![codecov](https://codecov.io/gh/computablefacts/junon/branch/master/graph/badge.svg)](https://codecov.io/gh/computablefacts/junon)

Junon is a data transfer object for Java compatible with our platform API.

## Adding Junon to your build

Junon's Maven group ID is `com.computablefacts` and its artifact ID is `junon`.

To add a dependency on Junon using Maven, use the following:

```xml
<dependency>
  <groupId>com.computablefacts</groupId>
  <artifactId>junon</artifactId>
  <version>0.x</version>
</dependency>
```

## Snapshots 

Snapshots of Junon built from the `master` branch are available through Sonatype 
using the following dependency:

```xml
<dependency>
  <groupId>com.computablefacts</groupId>
  <artifactId>junon</artifactId>
  <version>0.x-SNAPSHOT</version>
</dependency>
```

In order to be able to download snapshots from Sonatype add the following profile 
to your project `pom.xml`:

```xml
 <profiles>
    <profile>
        <id>allow-snapshots</id>
        <activation><activeByDefault>true</activeByDefault></activation>
        <repositories>
            <repository>
                <id>snapshots-repo</id>
                <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
                <releases><enabled>false</enabled></releases>
                <snapshots><enabled>true</enabled></snapshots>
            </repository>
        </repositories>
    </profile>
</profiles>
```

## Publishing a new version

Deploy a release to Maven Central with these commands:

```bash
$ git tag <version_number>
$ git push origin <version_number>
```

To update and publish the next SNAPSHOT version, just change and push the version:

```bash
$ mvn versions:set -DnewVersion=<version_number>-SNAPSHOT
$ git commit -am "Update to version <version_number>-SNAPSHOT"
$ git push origin master
```
