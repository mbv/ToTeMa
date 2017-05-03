package by.totema.recourse.entity.dto;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.entity.model.Office;

import javax.validation.constraints.NotNull;

public class OrderDto {

    private int id;

    @NotNull
    private int quantity;

    @NotNull
    private int cost;

    @NotNull
    private int price;

    @NotNull
    private int grossMargin;

    @NotNull
    private Employee employee;

    @NotNull
    private String date;

    @NotNull
    private Office office;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(int grossMargin) {
        this.grossMargin = grossMargin;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
