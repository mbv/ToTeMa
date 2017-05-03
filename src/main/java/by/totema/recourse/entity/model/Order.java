package by.totema.recourse.entity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order extends BaseEntity<Integer> {

    @NotNull
    private int quantity;

    @NotNull
    private int cost;

    @NotNull
    private int price;

    @NotNull
    private int grossMargin;

    @NotNull
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employeeKey")
    private Employee employee;

    @NotNull
    @ManyToOne(targetEntity = Date.class)
    @JoinColumn(name = "dateKey")
    private Date date;

    @NotNull
    @ManyToOne(targetEntity = Office.class)
    @JoinColumn(name = "officeKey")
    private Office office;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(int grossMargin) {
        this.grossMargin = grossMargin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public void setDate(Date date) {
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
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        return Objects.equals(grossMargin, order.grossMargin) &&
                Objects.equals(employee, order.employee) &&
                Objects.equals(office, order.office) &&
                (quantity == order.quantity) &&
                (cost == order.cost) &&
                (price == order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, cost, price, grossMargin, employee, office);
    }

}
