package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Country extends  BaseEntity<Integer>  {

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String name;

    @NotNull
    @SafeHtml
    @Size(min = 3, max = 3)
    @Column(length = 3, nullable = false)
    private String iso3;

    @NotNull
    @SafeHtml
    @Size(min = 2, max = 2)
    @Column(length = 2, nullable = false)
    private String iso2;

    @NotNull
    private long iso3digit;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String currency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getIso3digit() {
        return iso3digit;
    }

    public void setIso3digit(long iso3digit) {
        this.iso3digit = iso3digit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (getId() != country.getId()) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        if (iso2 != null ? !iso2.equals(country.iso2) : country.iso2 != null) return false;
        if (iso3 != null ? !iso3.equals(country.iso3) : country.iso3 != null) return false;
        if (iso3digit != country.iso3digit) return false;
        return  (currency != null ? !currency.equals(country.currency) : country.currency != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iso2 != null ? iso2.hashCode() : 0);
        result = 31 * result + (iso3 != null ? iso3.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (int) (iso3digit ^ (iso3digit >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", iso 2 letter code='" + iso2 + '\'' +
                ", iso 3 letter code='" + iso3 + '\'' +
                ", iso 3 digit code='" + iso3digit + '\'' +
                ", currency name='" + currency + '\'' +
                '}';
    }
}
