package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "`date`")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getOrCreateDate",
                procedureName = "getOrCreateDate",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "inTimestamp", type = Timestamp.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outId", type = Integer.class)

                }),
})
public class Date extends BaseEntity<Integer> {

    @Past
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp timeStamp;

    @NotNull
    private int year;

    @NotNull
    @Min(1)
    @Max(4)
    private int quarter;

    @NotNull
    @Min(1)
    @Max(12)
    private int monthInt;

    @NotNull
    @Min(1)
    @Max(53)
    private int week;

    @NotNull
    @Min(1)
    @Max(31)
    private int day;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String monthName;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public void setMonthInt(int monthInt) {
        this.monthInt = monthInt;
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

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        if (!super.equals(o)) return false;

        Date date = (Date) o;

        return Objects.equals(timeStamp, date.timeStamp) &&
                Objects.equals(monthName, date.monthName) &&
                (year == date.year) &&
                (quarter == date.quarter) &&
                (monthInt == date.monthInt) &&
                (week == date.week) &&
                (day == date.day);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), timeStamp, year, quarter, monthInt, week, day, monthName);
    }
}
