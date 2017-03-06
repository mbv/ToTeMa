package by.totema.entity;

/**
 * Created by User on 05.03.2017.
 */
public class Office {
    private long id;
    private String address;
    private String country;
    private String city;
    private String fax;
    private String phone;
    private String postalCode;
    private long yearSalary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

        Office office = (Office) o;

        if (id != office.id) return false;
        if (address != null ? !address.equals(office.address) : office.address != null) return false;
        if (city != null ? !city.equals(office.city) : office.city != null) return false;
        if (fax != null ? !fax.equals(office.fax) : office.fax != null) return false;
        if (phone != null ? !phone.equals(office.phone) : office.phone != null) return false;
        return  (postalCode != null ? !postalCode.equals(office.postalCode) : office.postalCode != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
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
                "id=" + id +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", postal code='" + postalCode + '\'' +
                '}';
    }
}
