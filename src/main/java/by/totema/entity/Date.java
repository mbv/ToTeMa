package by.totema.entity;

import java.sql.Time;

/**
 * Created by User on 05.03.2017.
 */
public class Date {
    private long id;
    private Time time;
    private int year;
    private int quarter;
    private int month;
    private int week;
    private int day;
    private String monthName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public long getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
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

        return (id != date.id);
}
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", quarter='" + quarter + '\'' +
                ", month='" + month + '\'' +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
