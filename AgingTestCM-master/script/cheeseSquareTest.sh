#! /bin/bash
if [ "$1" = "run" ]; then
    python test.py &
    python cheeseSquare.py
fi
