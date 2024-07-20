#!/bin/bash

aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/magalu-connect_localstack/helloWorld" --value "Hello World Parameter Store" --type String
