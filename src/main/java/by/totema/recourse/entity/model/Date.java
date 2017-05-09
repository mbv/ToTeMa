package by.totema.recourse.entity.model;

import by.totema.recourse.entity.dto.OrderReportDto;
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
@SqlResultSetMapping(
        name = "YearOrderMapping",
        classes = @ConstructorResult(
                targetClass = OrderReportDto.class,
                columns = {
                        @ColumnResult(name = "year", type = Integer.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "totalPrice", type = Double.class),
                        @ColumnResult(name = "totalCost", type = Double.class),
                        @ColumnResult(name = "totalGrossMargin", type = Double.class),
                        @ColumnResult(name = "currency", type = String.class)
                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Date.getOrderReport",
                query = "SELECT \n" +
                        "D.YEAR AS year,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.date D\n" +
                        "LEFT JOIN `totema`.ORDER O \n" +
                        "ON D.ID = O.DATE_KEY\n" +
                        "LEFT JOIN `totema`.office OF \n" +
                        "ON O.OFFICE_KEY = OF.ID\n" +
                        "LEFT JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "LEFT JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "GROUP BY D.YEAR, C.ID\n" +
                        "ORDER BY D.YEAR;",
                resultSetMapping = "YearOrderMapping"

        ),

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
