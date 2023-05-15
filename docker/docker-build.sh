#!/bin/sh

# Setting versions
VERSION='1.0.0'

cd ..
./gradlew clean build -x test

ROOT_PATH=`pwd`
echo $ROOT_PATH

echo 'api Docker image build..'
cd $ROOT_PATH/api && docker build -t api:$VERSION .
echo 'api Docker image build.. Done'

echo 'consumer Docker image build..'
cd $ROOT_PATH/consumer && docker build -t consumer:$VERSION .
echo 'consumer Docker image build.. Done'

echo 'css Docker image build..'
cd $ROOT_PATH/css && docker build -t css:$VERSION .
echo 'css Docker image build.. Done'

echo 'nginx Docker image build..'
cd $ROOT_PATH/nginx && docker build -t nginx:$VERSION .
echo 'nginx Docker image build.. Done'