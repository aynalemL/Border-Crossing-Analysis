#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Belw is an example of what might be found in this file if your program was written in Python 3.7
# python3.7 ./src/border_analytics.py ./input/Border_Crossing_Entry_data.csv ./output/report.csv
java -version
java -cp ./target/production/border-crossing-analysis-master/report/*  MainApplication ./insight_testsuite/tests/test_1/input/Border_Crossing_Entry_Data.csv ./insight_testsuite/tests/test_1/output/report.csv
 
