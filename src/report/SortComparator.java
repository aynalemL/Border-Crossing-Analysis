package report;

import model.MonthlyCrossingSummary;

import java.util.Comparator;

public class SortComparator implements Comparator<MonthlyCrossingSummary> {


    @Override
    public int compare(MonthlyCrossingSummary obj1, MonthlyCrossingSummary obj2) {
        int compareByDate = obj2.getDate().compareTo(obj1.getDate());
        int compareByVal = obj2.getSum()>obj1.getSum()?1:obj2.getSum()<obj1.getSum()?-1:0;
        int compareByMeasure = obj2.getMeasure().compareTo(obj1.getMeasure());
        int compareByBorder = obj2.getBorder().compareTo(obj1.getBorder());

        if(compareByDate == 0){
            if(compareByVal == 0){
                if(compareByMeasure == 0){
                    return compareByBorder;
                }else {
                    return compareByMeasure;
                }
            }else{
                return compareByVal;
            }
        }else{
            return compareByDate;
        }

    }

}



