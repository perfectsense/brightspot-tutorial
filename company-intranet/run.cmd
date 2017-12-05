﻿@ECHO OFF

CALL :configure
CALL :start

EXIT /B

:configure
ECHO "Configuring Tomcat..."
IF NOT EXIST ".tomcat" mkdir .tomcat
POWERSHELL -Command "(gc ..\etc\tomcat-context.xml) -replace '<project-dir>', '<project-dir>\' | Out-File -encoding ASCII .tomcat\tomcat-context.xml
POWERSHELL -Command "(gc .tomcat\tomcat-context.xml) -replace '<project-dir>', "$pwd" | Out-File -encoding ASCII .tomcat\tomcat-context.xml
POWERSHELL -Command "(gc ..\etc\tomcat-storage.xml) -replace '<project-dir>', '<project-dir>\' | Out-File -encoding ASCII .tomcat\tomcat-storage.xml
POWERSHELL -Command "(gc .tomcat\tomcat-storage.xml) -replace '<project-dir>', "$pwd" | Out-File -encoding ASCII .tomcat\tomcat-storage.xml
COPY ..\etc\tomcat-logging.properties .tomcat\tomcat-logging.properties

ECHO "Initializing Storage Directory..."
IF NOT EXIST ".storage" mkdir .storage
EXIT /B 0

:start
ECHO "Starting Application..."
CALL ..\mvnw.cmd -P run clean package cargo:run
EXIT /B 0
