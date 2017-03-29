package edu.bsuir.totema.entity;
import com.google.gson.annotations.Expose;
import static edu.bsuir.totema.util.NullUtil.nullableEquals;
import static edu.bsuir.totema.util.NullUtil.nullableHashCode;

import java.sql.Time;

public class Date extends Entity {

    @Expose
    private Time time;
    @Expose
    private long year;
    @Expose
    private long quarter;
    @Expose
    private long month;
    @Expose
    private long week;
    @Expose
    private long day;
    @Expose
    private String monthName;

    public long getWeek() {
        return week;
    }

    public void setWeek(long week) {
        this.week = week;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }


    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getQuarter() {
        return quarter;
    }

    public void setQuarter(long quarter) {
        this.quarter = quarter;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String name) {
        this.monthName = monthName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;

        Date date= (Date) o;

        return (getId() != date.getId());
}
    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + getId() +
                ", year='" + year + '\'' +
                ", quarter='" + quarter + '\'' +
                ", month='" + month + '\'' +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
