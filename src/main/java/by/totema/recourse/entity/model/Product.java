package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product extends  BaseEntity<Integer>  {

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
    private long code;

    @NotNull
    private long typeKey;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String name;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String size;

    @NotNull
    @SafeHtml
    @Size(min = 1, max = 45)
    @Column(length = 45, nullable = false)
    private String color;
    @NotNull
    @Min(-1)
    @Max(1)
    private int status;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(long typeKey) {
        this.typeKey = typeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
        if (!(o instanceof Product)) return false;

        Product product= (Product) o;

        if (getId() != product.getId()) return false;
        if (size != null ? !size.equals(product.size) : product.size != null) return false;
        if (color != null ? !color.equals(product.color) : product.color != null) return false;
        return name != null ? !name.equals(product.name) : product.name != null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product type{" +
                "id=" + getId() +
                ", size='" + size + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
