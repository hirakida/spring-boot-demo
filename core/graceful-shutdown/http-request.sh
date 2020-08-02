#!/bin/sh

while true
do
  wget -q -O - http://localhost:8080/
  printf "\n"
done
