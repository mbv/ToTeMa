package by.totema.recourse.entity.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order")
public class Order extends  BaseEntity<Integer>  {

    @NotNull
    private long quantity;

    @NotNull
    private long cost;

    @NotNull
    private long price;

    @NotNull
    private long grossMargin;

    @NotNull
    @ManyToOne(targetEntity = Employee.class)
    private long employeeKey;

    @NotNull
    @ManyToOne(targetEntity = Date.class)
    private long dateKey;

    @NotNull
    @ManyToOne(targetEntity = Office.class)
    private long officeKey;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(long grossMargin) {
        this.grossMargin = grossMargin;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getEmployeeKey() {
        return employeeKey;
    }

    public void setEmployeeKey(long employeeKey) {
        this.employeeKey = employeeKey;
    }

    public long getDateKey() {
        return dateKey;
    }

    public void setDateKey(long dateKey) {
        this.dateKey = dateKey;
    }

    public long getOfficeKey() {
        return officeKey;
    }

    public void setOfficeKey(long officeKey) {
        this.officeKey = officeKey;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order= (Order) o;

        if (getId() != order.getId()) return false;
        if (price != order.price)  return false;
        if (cost != order.cost)  return false;
        if (quantity != order.quantity)  return false;
        if (employeeKey != order.employeeKey) return false;
        if (dateKey != order.dateKey) return false;
        if (officeKey != order.officeKey) return false;
        return  (grossMargin != order.grossMargin);
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Ordere{" +
                "id=" + getId() +
                ", employee key='" + employeeKey + '\'' +
                ", office key='" + officeKey + '\'' +
                ", date key='" + dateKey + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", cost='" + cost + '\'' +
                ", grossMargin='" + grossMargin + '\'' +
                '}';
    }
}
