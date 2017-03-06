package by.totema.entity;

/**
 * Created by User on 05.03.2017.
 */
public class Order {
    private long id;
    private long quantity;
    private long cost;
    private long price;
    private long grossMargin;
    private Employee employee;
    private Date date;
    private Office office;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date employee) {
        this.date = date;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order= (Order) o;

        if (id != order.id) return false;
        if (price != order.price)  return false;
        if (cost != order.cost)  return false;
        if (quantity != order.quantity)  return false;
        if (employee != null ? !employee.equals(order.employee) : order.employee != null) return false;
        return  (grossMargin != order.grossMargin);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Ordere{" +
                "id=" + id +
                ", employee='" + employee.getName() + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", cost='" + cost + '\'' +
                ", grossMargin='" + grossMargin + '\'' +
                '}';
    }
}
