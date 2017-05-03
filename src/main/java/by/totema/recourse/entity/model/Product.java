package by.totema.recourse.entity.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product extends BaseEntity<Integer> {

    @NotNull
    private int code;

    @NotNull
    @ManyToOne(targetEntity = ProductType.class)
    @JoinColumn(name = "typeKey")
    private ProductType type;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        return Objects.equals(type, product.type) &&
                Objects.equals(name, product.name) &&
                Objects.equals(size, product.size) &&
                Objects.equals(color, product.color) &&
                (code == product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, type, name, size, color);
    }

}

