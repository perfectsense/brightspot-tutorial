@echo off
setlocal

echo Removing existing Docker containers...
docker-compose down
docker volume prune -f
