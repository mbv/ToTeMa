package edu.bsuir.totema.entity;
import com.google.gson.annotations.Expose;
import static edu.bsuir.totema.util.NullUtil.nullableEquals;
import static edu.bsuir.totema.util.NullUtil.nullableHashCode;

public class Office extends Entity {
    /**
     * Represents that this Employee entity has status Active.
     * Default value
     */
    public static final int STATUS_ACTIVE = 1;
    /**
     * Represents that this Employee entity has status Banned.
     * It means that this user cannot bet and create lots
     */
    public static final int STATUS_BANNED = 0;
    /**
     * Represents that this Employee entity is deleted.
     * Setting this status equals to deleting this user from the app.
     */
    public static final int STATUS_DELETED = -1;
    @Expose
    private String username;
    private String passwordHash;
    @Expose
    private String address;
    @Expose
    private long countryKey;
    @Expose
    private String city;
    @Expose
    private String fax;
    @Expose
    private String phone;
    @Expose
    private String postalCode;
    @Expose
    private long yearSalary;
    @Expose
    private int status;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCountryKey() {
        return countryKey;
    }

    public void setYearSalary(int yearSalary) {
        this.yearSalary = yearSalary;
    }

    public long getYearSalary() {
        return yearSalary;
    }

    public void setCountryKey(long countryKey) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;

        Office office = (Office) o;

        if (getId() != office.getId()) return false;
        if (address != null ? !address.equals(office.address) : office.address != null) return false;
        if (city != null ? !city.equals(office.city) : office.city != null) return false;
        if (fax != null ? !fax.equals(office.fax) : office.fax != null) return false;
        if (phone != null ? !phone.equals(office.phone) : office.phone != null) return false;
        return  (postalCode != null ? !postalCode.equals(office.postalCode) : office.postalCode != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (city != null ? fax.hashCode() : 0);
        result = 31 * result + (city != null ? phone.hashCode() : 0);
        result = 31 * result + (city != null ? postalCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + getId() +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", postal code='" + postalCode + '\'' +
                '}';
    }
}
