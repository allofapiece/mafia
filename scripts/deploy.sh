#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/andrey-key-belarus.pem \
    target/mafia-1.0-SNAPSHOT.jar \
    ec2-user@ec2-3-134-96-85.us-east-2.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i ~/.ssh/andrey-key-belarus.pem ec2-user@ec2-3-134-96-85.us-east-2.compute.amazonaws.com << EOF
pgrep java | xargs kill -9
nohup java -jar mafia-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'