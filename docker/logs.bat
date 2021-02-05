@echo off
setlocal

docker-compose up -d %*
docker-compose logs -f --tail=1000 %*
