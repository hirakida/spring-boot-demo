#!/bin/sh

pid=$(ps -e -o pid,command | grep graceful-shutdown-demo | grep -v grep | awk '{ print $1 }')
kill $pid
