package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class BorderCrossingInfo {
    private String portName;
    private String state;
    private int portCode;
    private String border;
    private Date date;
    private String measure;
    private int value;
    private GeoCoordinate location;

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPortCode() {
        return portCode;
    }

    public void setPortCode(int portCode) {
        this.portCode = portCode;
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

    public void setDate(Date date) {this.date = date;}

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GeoCoordinate getLocation() {
        return location;
    }

    public void setLocation(GeoCoordinate location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorderCrossingInfo that = (BorderCrossingInfo) o;
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
        return "BorderCrossingInfo{" +
                "portName='" + portName + '\'' +
                ", state='" + state + '\'' +
                ", portCode=" + portCode +
                ", border='" + border + '\'' +
                ", date=" + date +
                ", measure='" + measure + '\'' +
                ", value=" + value +
                ", location={lat=" + location.getLat() + " lon=" + location.getLon()+
    "}";
    }
}
