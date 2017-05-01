package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "office")
public class Office extends BaseEntity<Integer> {
    @NotNull
    @SafeHtml
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String address;

    @NotNull
    private int countryKey;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String city;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String fax;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String phone;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String postalCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCountryKey() {
        return countryKey;
    }

    public void setCountryKey(int countryKey) {
        this.countryKey = countryKey;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        if (!super.equals(o)) return false;

        Office office = (Office) o;

        return Objects.equals(address, office.address) &&
                Objects.equals(city, office.city) &&
                Objects.equals(fax, office.fax) &&
                Objects.equals(phone, office.phone) &&
                Objects.equals(postalCode, office.postalCode) &&
                (countryKey == office.countryKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, city, fax, phone, postalCode);
    }

}
