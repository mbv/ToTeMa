package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.sql.Time;

public class Date extends  BaseEntity<Integer>  {

    @Past
    @Column(columnDefinition = "TIMESTAMP")
    private Time time;

    @NotNull
    private long year;

    @NotNull
    @Min(1)
    @Max(4)
    private long quarter;

    @NotNull
    @Min(1)
    @Max(12)
    private long month;

    @NotNull
    @Min(1)
    @Max(53)
    private long week;

    @NotNull
    @Min(1)
    @Max(31)
    private long day;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
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

    public void setMonthName(String monthName) {
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
