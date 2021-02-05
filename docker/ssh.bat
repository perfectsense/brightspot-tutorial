@echo off
setlocal

docker-compose exec %* bash
