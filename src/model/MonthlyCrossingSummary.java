package model;

import java.util.Date;
import java.util.Objects;

public class MonthlyCrossingSummary {
    private String border;
    private Date date;
    private String measure;
    private int sum;
    private double runningAvg;

    public MonthlyCrossingSummary(String border, Date date, String measure) {
        this.border = border;
        this.date = date;
        this.measure = measure;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public void addToSum(int val){
        this.sum+=val;
    }

    public double getRunningAvg() {
        return runningAvg;
    }

    public void setRunningAvg(double runningAvg) {
        this.runningAvg = runningAvg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyCrossingSummary that = (MonthlyCrossingSummary) o;
        return Objects.equals(border, that.border) &&
                Objects.equals(date, that.date) &&
                Objects.equals(measure, that.measure);
    }

    @Override
    public int hashCode() {

        return Objects.hash(border, date, measure);
    }

    @Override
    public String toString() {
        return
                 border  +
                ", " + date +
                ", " + measure +
                ", " + sum +
                ", " + runningAvg ;

    }
}
