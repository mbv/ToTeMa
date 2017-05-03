package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "`product_type`")
public class ProductType extends  BaseEntity<Integer>  {

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String gender;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String age;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String season;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String type;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductType)) return false;
        if (!super.equals(o)) return false;

        ProductType productType = (ProductType) o;

        return Objects.equals(gender, productType.gender) &&
                Objects.equals(age, productType.age) &&
                Objects.equals(season, productType.season) &&
                Objects.equals(type, productType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gender, age, season, type);
    }
}
