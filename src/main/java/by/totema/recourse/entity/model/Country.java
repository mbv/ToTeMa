package by.totema.recourse.entity.model;

import by.totema.recourse.entity.dto.CountryOrderReportDto;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "`country`")
@SqlResultSetMapping(
        name = "CountryOrderMapping",
        classes = @ConstructorResult(
                targetClass = CountryOrderReportDto.class,
                columns = {
                        @ColumnResult(name = "country", type = String.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "totalPrice", type = Integer.class),
                        @ColumnResult(name = "totalCost", type = Integer.class),
                        @ColumnResult(name = "totalGrossMargin", type = Integer.class),
                        @ColumnResult(name = "currency", type = String.class)
                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Country.getOrderReport",
                query = "SELECT \n" +
                        "C.NAME AS country,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.ORDER O \n" +
                        "JOIN `totema`.office OF \n" +
                        "ON O.OFFICE_KEY = OF.ID\n" +
                        "JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "GROUP BY C.ID;",
                resultSetMapping = "CountryOrderMapping"

        ),

        })
public class Country extends BaseEntity<Integer> {

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String name;

    @NotNull
    @SafeHtml
    @Size(min = 3, max = 3)
    @Column(length = 3, nullable = false)
    private String isoThreeLetterCode;

    @NotNull
    @SafeHtml
    @Size(min = 2, max = 2)
    @Column(length = 2, nullable = false)
    private String isoTwoLetterCode;

    @NotNull
    private int isoThreeDigitCode;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String currencyName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoTwoLetterCode() {
        return isoTwoLetterCode;
    }

    public void setIsoTwoLetterCode(String isoTwoLetterCode) {
        this.isoTwoLetterCode = isoTwoLetterCode;
    }

    public String getIsoThreeLetterCode() {
        return isoThreeLetterCode;
    }

    public void setIsoThreeLetterCode(String isoThreeLetterCode) {
        this.isoThreeLetterCode = isoThreeLetterCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getIsoThreeDigitCode() {
        return isoThreeDigitCode;
    }

    public void setIsoThreeDigitCode(int isoThreeDigitCode) {
        this.isoThreeDigitCode = isoThreeDigitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        return Objects.equals(name, country.name) &&
                Objects.equals(isoTwoLetterCode, country.isoTwoLetterCode) &&
                Objects.equals(isoThreeLetterCode, country.isoThreeLetterCode) &&
                Objects.equals(currencyName, country.currencyName) &&
                (isoThreeDigitCode == country.isoThreeDigitCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, isoTwoLetterCode, name, isoThreeLetterCode, currencyName, isoThreeDigitCode);
    }
}
