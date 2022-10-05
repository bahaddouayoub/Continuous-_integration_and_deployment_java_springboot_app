#!/usr/bin/env bash

export IMAGE_NAME=$1
docker-compose up --detach
echo "success"