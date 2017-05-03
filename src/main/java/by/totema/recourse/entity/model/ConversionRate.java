package by.totema.recourse.entity.model;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "conversion_rate")
public class ConversionRate extends BaseEntity<Integer> {

    @NotNull
    @Id
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "countryKey")
    private Country country;
    @Id
    @NotNull
    @ManyToOne(targetEntity = Date.class)
    @JoinColumn(name = "periodKey")
    private Date period;
    @NotNull
    private int conversionToLocal;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public int getConversionToLocal() {
        return conversionToLocal;
    }

    public void setConversionToLocal(int conversionToLocal) {
        this.conversionToLocal = conversionToLocal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversionRate)) return false;
        if (!super.equals(o)) return false;

        ConversionRate conversionRate = (ConversionRate) o;

        return Objects.equals(country, conversionRate.country) &&
                Objects.equals(period, conversionRate.period) &&
                (conversionToLocal == conversionRate.conversionToLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, period, conversionToLocal);
    }

}
