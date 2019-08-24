#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Belw is an example of what might be found in this file if your program was written in Python 3.7
# python3.7 ./src/border_analytics.py ./input/Border_Crossing_Entry_data.csv ./output/report.csv
pwd
java -classpath /home/ubuntu/cc-grader/20A/apps/aynalemL_github/target/production/*  report.MainApplication $/home/ubuntu/cc-grader/20A/apps/aynalemL_github/input/Border_Crossing_Entry_Data.csv /home/ubuntu/cc-grader/20A/apps/aynalemL_github/report.csv
 
