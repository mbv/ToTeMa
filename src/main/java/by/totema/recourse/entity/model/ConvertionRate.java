package by.totema.recourse.entity.model;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "convertion_rate")
public class ConvertionRate extends BaseEntity<Integer> {

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
    private long convertion;

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

    public long getConvertion() {
        return convertion;
    }

    public void setConvertion(long convertion) {
        this.convertion = convertion;
    }

    public ConvertionRate(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConvertionRate)) return false;

        ConvertionRate convertionRate = (ConvertionRate) o;

        if (getId() != convertionRate.getId()) return false;
        if (!country.equals(convertionRate.getCountry())) return false;
        if (!period.equals(convertionRate.getPeriod())) return false;
        return  (convertion == convertionRate.convertion);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (country.getId() ^ (country.getId() >>> 32));
        result = 31 * result + (int) (period.getId() ^ (period.getId() >>> 32));
        result = 31 * result + (int) (convertion ^ (convertion >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Conversion rate{" +
                "id=" + getId() +
                ", Country='" + country.getName() + '\'' +
                ", Period key='" + period.getId() + '\'' +
                ", Convertion ='" + convertion + '\'' +
                '}';
    }
}
