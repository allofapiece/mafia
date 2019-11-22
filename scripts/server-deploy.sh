#!/usr/bin/env bash

echo 'Restart server...'

pgrep java | xargs kill -9
git pull
mvn clean package
nohup java -jar -Dspring.profiles.active=local mafia-1.0-SNAPSHOT.jar > log.txt &

echo 'Bye'