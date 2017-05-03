package by.totema.recourse.entity.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "`product_list`")
public class ProductList extends  BaseEntity<Integer>  {

    @NotNull
    private int quantity;

    @NotNull
    private int unitCost;

    @NotNull
    private int unitPrice;

    @NotNull
    private int grossMargin;

    @NotNull
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "productKey")
    private Product product;

    @NotNull(message = "Order ID is not specified")
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer orderKey;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public int getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(int grossMargin) {
        this.grossMargin = grossMargin;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList)) return false;
        if (!super.equals(o)) return false;

        ProductList productList = (ProductList) o;

        return Objects.equals(product, productList.product) &&
                Objects.equals(orderKey, productList.orderKey) &&
                (quantity == productList.quantity) &&
                (unitCost == productList.unitCost) &&
                (unitPrice == productList.unitPrice) &&
                (grossMargin == productList.grossMargin);

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, unitCost, unitPrice, grossMargin, product, orderKey);
    }

}
