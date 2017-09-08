#! /bin/bash
if [ "$1" = "run" ]; then
    sh 1.sh &
    sh 2.sh &
    sh 3.sh &
    sh zhihu.sh &
fi
