@echo off
setlocal

gradlew && docker/restart tomcat && docker/logs tomcat
