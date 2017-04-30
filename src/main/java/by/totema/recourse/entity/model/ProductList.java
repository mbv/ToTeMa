package by.totema.recourse.entity.model;


import javax.validation.constraints.NotNull;

public class ProductList extends  BaseEntity<Integer>  {

    @NotNull
    private long quantity;

    @NotNull
    private long unitCost;

    @NotNull
    private long unitPrice;

    @NotNull
    private long grossMargin;

    @NotNull
    private long productKey;

    @NotNull
    private long orderKey;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(long unitCost) {
        this.unitCost = unitCost;
    }

    public long getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(long grossMargin) {
        this.grossMargin = grossMargin;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getProductKey() {
        return productKey;
    }

    public void setProductKey(long productKey) {
        this.productKey = productKey;
    }

    public long getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(long orderKey) {
        this.orderKey = orderKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList)) return false;

        ProductList productList= (ProductList) o;

        if (getId() != productList.getId()) return false;
        if (unitPrice != productList.unitPrice)  return false;
        if (unitCost != productList.unitCost)  return false;
        if (quantity != productList.quantity)  return false;
        if (productKey != productList.productKey) return false;
        return  (grossMargin != productList.grossMargin);
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (unitCost ^ (unitCost >>> 32));
        result = 31 * result + (int) (unitPrice ^ (unitPrice >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Product type{" +
                "id=" + getId() +
                ", product key='" + productKey + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitCost='" + unitCost + '\'' +
                ", grossMargin='" + grossMargin + '\'' +
                '}';
    }


}
