#!/bin/bash

# usage: ./uitest.sh run
#        ./uitest.sh init
PROJ_PATH=$HOME/Developtools/AgingTestCM-master
SDK_PATH=$HOME/Developtools/adt-bundle-linux-x86_64-20140702/sdk
ANDROID_PATH=$SDK_PATH/tools
PROJ_NAME="AgingTestCM"


####    run test   ####
if [ "$1" = "run" ]; then
    $ANDROID_PATH/android create uitest-project -n $PROJ_NAME -t 1 -p $PROJ_PATH
    cd $PROJ_PATH
    ant build
    adb push $PROJ_PATH/bin/${PROJ_NAME}.jar /data/local/tmp/
    adb shell uiautomator runtest ${PROJ_NAME}.jar
fi
####    end test   ####

####    init test   ####
UITEST_PHONE_PATH=/sdcard/uitest
UITEST_HOST_PATH=../uitest
if [ "$1" = "init" ]; then
    adb shell rm -rf $UITEST_PHONE_PATH
    adb push $UITEST_HOST_PATH $UITEST_PHONE_PATH
fi
####    end init    ####

