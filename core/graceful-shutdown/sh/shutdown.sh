#!/bin/sh

pid=$(ps -e -o pid,command | grep graceful-shutdown | grep -v grep | awk '{ print $1 }')
kill $pid
