package by.totema.recourse.entity.model;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "convertion_rate")
public class ConvertionRate extends BaseEntity<Integer> {

    @NotNull
    @Id
    @ManyToOne(targetEntity = Country.class)
    private long countryKey;
    @Id
    @NotNull
    @ManyToOne(targetEntity = Date.class)
    private long periodKey;
    @NotNull
    private long convertion;

    public long getCountryKey() {
        return countryKey;
    }

    public void setCountryKey(long countryKey) {
        this.countryKey = countryKey;
    }

    public long getPeriodKey() {
        return periodKey;
    }

    public void setPeriodKey(long periodKey) {
        this.periodKey = periodKey;
    }

    public long getConvertion() {
        return convertion;
    }

    public void setConvertion(long convertion) {
        this.convertion = convertion;
    }

    public ConvertionRate(){}

    public ConvertionRate(ConvertionRate convertionRate){
        countryKey = convertionRate.countryKey;
        periodKey = convertionRate.periodKey;
        convertion = convertionRate.convertion;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConvertionRate)) return false;

        ConvertionRate convertionRate = (ConvertionRate) o;

        if (getId() != convertionRate.getId()) return false;
        if (countryKey != convertionRate.countryKey) return false;
        if (periodKey != convertionRate.periodKey) return false;
        return  (convertion == convertionRate.convertion);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (countryKey ^ (countryKey >>> 32));
        result = 31 * result + (int) (periodKey ^ (periodKey >>> 32));
        result = 31 * result + (int) (convertion ^ (convertion >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Conversion rate{" +
                "id=" + getId() +
                ", Country key='" + countryKey + '\'' +
                ", Period key='" + periodKey + '\'' +
                ", Convertion ='" + convertion + '\'' +
                '}';
    }
}
