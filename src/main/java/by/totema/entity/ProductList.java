package by.totema.entity;

/**
 * Created by User on 05.03.2017.
 */
public class ProductList {
    private long id;
    private long quantity;
    private long unitCost;
    private long unitPrice;
    private long grossMargin;
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList)) return false;

        ProductList productList= (ProductList) o;

        if (id != productList.id) return false;
        if (unitPrice != productList.unitPrice)  return false;
        if (unitCost != productList.unitCost)  return false;
        if (quantity != productList.quantity)  return false;
        if (product != null ? !product.equals(productList.product) : productList.product != null) return false;
        return  (grossMargin != productList.grossMargin);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (unitCost ^ (unitCost >>> 32));
        result = 31 * result + (int) (unitPrice ^ (unitPrice >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Product type{" +
                "id=" + id +
                ", product='" + product.getName() + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", unitCost='" + unitCost + '\'' +
                ", grossMargin='" + grossMargin + '\'' +
                '}';
    }


}
