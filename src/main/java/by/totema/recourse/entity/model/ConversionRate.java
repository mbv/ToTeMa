package by.totema.recourse.entity.model;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    public ConversionRate(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversionRate)) return false;

        ConversionRate conversionRate = (ConversionRate) o;

        if (getId() != conversionRate.getId()) return false;
        if (!country.equals(conversionRate.getCountry())) return false;
        if (!period.equals(conversionRate.getPeriod())) return false;
        return  (conversionToLocal == conversionRate.conversionToLocal);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (country.getId() ^ (country.getId() >>> 32));
        result = 31 * result + (int) (period.getId() ^ (period.getId() >>> 32));
        result = 31 * result + (int) (conversionToLocal ^ (conversionToLocal >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Conversion rate{" +
                "id=" + getId() +
                ", Country='" + country.getName() + '\'' +
                ", Period key='" + period.getId() + '\'' +
                ", Convertion ='" + conversionToLocal + '\'' +
                '}';
    }
}
