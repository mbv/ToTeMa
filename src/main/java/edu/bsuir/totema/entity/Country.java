package edu.bsuir.totema.entity;
import com.google.gson.annotations.Expose;
import static edu.bsuir.totema.util.NullUtil.nullableEquals;
import static edu.bsuir.totema.util.NullUtil.nullableHashCode;

public class Country extends Entity {
    @Expose
    private String username;
    private String passwordHash;
    @Expose
    private String name;
    @Expose
    private String iso3;
    @Expose
    private String iso2;
    @Expose
    private long iso3digit;
    @Expose
    private String currency;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

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
