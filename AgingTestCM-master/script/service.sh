#! /bin/bash
if [ "$1" = "run" ]; then
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 10 --ei interval 3
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 20 --ei interval 3
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 30 --ei interval 3
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 40 --ei interval 3
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 50 --ei interval 3
sleep 30m
adb shell am broadcast -a com.smartwear.TRANSFER_PARAS --ei allocation 0 --ei thread 60 --ei interval 3
fi
