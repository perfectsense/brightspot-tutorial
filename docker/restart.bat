@echo off
setlocal

docker-compose stop %*
docker-compose up -d %*
