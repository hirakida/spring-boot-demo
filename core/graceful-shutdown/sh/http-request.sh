#!/bin/sh

while true
do
  curl http://localhost:8080/
  if [ $? -gt 0 ]; then
    exit
  fi
  printf "\n"
done
