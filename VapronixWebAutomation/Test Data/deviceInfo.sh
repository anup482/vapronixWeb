#!/bin/sh
output1=$(adb devices | grep -vE '^List' | awk '{print $1}' )
echo $output1
exit 0

