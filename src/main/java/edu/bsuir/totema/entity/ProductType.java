package edu.bsuir.totema.entity;

public class ProductType extends Entity {
    private String sex;
    private String age;
    private String season;
    private String type;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductType)) return false;

        ProductType productType = (ProductType) o;

        if (getId() != productType.getId()) return false;
        if (age != null ? !age.equals(productType.age) : productType.age != null) return false;
        if (season != null ? !season.equals(productType.season) : productType.season != null) return false;
        return sex != null ? !sex.equals(productType.sex) : productType.sex != null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product type{" +
                "id=" + getId() +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
