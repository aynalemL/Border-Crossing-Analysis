Structure of the program:
------------------------------------------------------------------------------------------------
The MainApplication class is the main entry class to the program. It expects two arguments: input 
file and output file. Input file is mandatory; it throws exception if one is not specified. 

The MainApplication class has two servant classes: BorderCrossingAnalyzer and BorderCrossingReportWriter.
The BorderCrosssingAnalyzer takes a path to the input file. It then create a stream of lines (using java 8)
and read one line at a time into a BorderCrossingInfo object. This approach is used so that the program 
does not read entire data to memory at once, which would end up causing memory issue if the input file is huge in size.
The BorderCrossingAnalyzer calculates the sum of values for each distinct combination of border, measure,
and month. It does so by using a hashcode generated from combination of border, measure and date;
then a key-value pair is inserted to the map where the key is the hashcode and the value is the sum of
values for all row in the input file with the same border, measure and month (i.e the same hashcode). 
Every time a new line is read, it checkes if the hashcode generated from it is in the map. If it is in the map
the value from this line is just added to the existing sum and the line is discarded. Else a new entry is 
created in the map. Each line of code is consumed this way and discarded so that memory is conserved. 
Once all the lines are consumed, the map of monthly border crossing summary by border, measure and month 
is returned to the MainApplication class.  

The summary map is now passed on to the BorderCrossingReportWriter for evaluating running average by border 
and measure and write it the output file.  For each border and measure list of MonthlyCrossingSummary is
sorted in ascending order so that running average can be calculated. Then each element of the sorted list 
with running average is written to the output file. 

Run instructions
------------------
I am not able to create the run.sh because when I try to use the test link it fails with the permission error. would you help me create the run.sh file? 

Main class: src/report/MainApplication.java
1st Arguments: input file (./input/Border_Crossing_Entry_Data.csv)
2nd Argument: out put file (./output/report.csv)


-----------------------------------NOTES below--------------------------------------------------
NB: The example on github sets running average by border and measure to zero for the first month.
I think that is an error In my code, however, I set the running average of the first month to the
same value as the sum (value) for that month.
