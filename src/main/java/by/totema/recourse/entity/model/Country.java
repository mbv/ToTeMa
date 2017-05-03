package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "`country`")
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
