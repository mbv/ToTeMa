package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductType extends  BaseEntity<Integer>  {

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
    @NotNull
    @Min(-1)
    @Max(1)
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductType)) return false;

        ProductType productType = (ProductType) o;

        if (getId() != productType.getId()) return false;
        if (age != null ? !age.equals(productType.age) : productType.age != null) return false;
        if (season != null ? !season.equals(productType.season) : productType.season != null) return false;
        return gender != null ? !gender.equals(productType.gender) : productType.gender != null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product type{" +
                "id=" + getId() +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
