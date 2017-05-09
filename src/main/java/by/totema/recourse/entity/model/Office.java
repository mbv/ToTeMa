package by.totema.recourse.entity.model;

import by.totema.recourse.entity.dto.OrderReportDto;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "`office`")
@SqlResultSetMapping(
        name = "OfficeOrderMapping",
        classes = @ConstructorResult(
                targetClass = OrderReportDto.class,
                columns = {
                        @ColumnResult(name = "office", type = String.class),
                        @ColumnResult(name = "country", type = String.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "totalPrice", type = Double.class),
                        @ColumnResult(name = "totalCost", type = Double.class),
                        @ColumnResult(name = "totalGrossMargin", type = Double.class),
                        @ColumnResult(name = "currency", type = String.class)
                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Office.getOrderReport",
                query = "SELECT \n" +
                        "CONCAT(OF.CITY, ', ', OF.ADDRESS) AS office,\n" +
                        "C.NAME AS country,\n" +
                        "SUM(O.QUANTITY) AS quantity,\n" +
                        "TRUNCATE(SUM(O.PRICE*CR.CONVERSION_TO_LOCAL/100), 2) AS totalPrice,\n" +
                        "TRUNCATE(SUM(O.COST*CR.CONVERSION_TO_LOCAL/100), 2) AS totalCost,\n" +
                        "TRUNCATE(SUM(O.GROSS_MARGIN*CR.CONVERSION_TO_LOCAL/100), 2) AS totalGrossMargin,\n" +
                        "C.CURRENCY_NAME AS currency\n" +
                        "FROM `totema`.office OF \n" +
                        "LEFT JOIN `totema`.ORDER O \n" +
                        "ON OF.ID = O.OFFICE_KEY\n" +
                        "LEFT JOIN `totema`.country C\n" +
                        "ON OF.COUNTRY_KEY = C.ID\n" +
                        "LEFT JOIN `totema`.conversion_rate CR\n" +
                        "ON OF.COUNTRY_KEY = CR.COUNTRY_KEY\n" +
                        "AND O.DATE_KEY = CR.PERIOD_KEY\n" +
                        "GROUP BY OF.ID, C.ID;",
                resultSetMapping = "OfficeOrderMapping"

        ),

})
public class Office extends BaseEntity<Integer> {
    @NotNull
    @SafeHtml
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String address;

    @NotNull
    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "countryKey")
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
                Objects.equals(country, office.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, city, fax, phone, postalCode, country);
    }
}
