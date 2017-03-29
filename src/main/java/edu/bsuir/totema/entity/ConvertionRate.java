package edu.bsuir.totema.entity;
import com.google.gson.annotations.Expose;
import static edu.bsuir.totema.util.NullUtil.nullableEquals;
import static edu.bsuir.totema.util.NullUtil.nullableHashCode;

public class ConvertionRate extends Entity {

    @Expose
    private long countryKey;
    @Expose
    private long periodKey;
    @Expose
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
