package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Order;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class OrderSupplier implements EntityIntegerPKSupplier<Order> {
    private EmployeeSupplier employeeSupplier = new EmployeeSupplier();
    private OfficeSupplier officeSupplier = new OfficeSupplier();
    private DateSupplier dateSupplier = new DateSupplier();
    @Override
    public Order getValidEntityWithoutId() {
        Order order = new Order();

        order.setGrossMargin(250);
        order.setEmployee(employeeSupplier.getValidEntityWithoutId());
        order.setOffice(officeSupplier.getValidEntityWithoutId());
        order.setDate(dateSupplier.getValidEntityWithoutId());

        order.setQuantity(5);
        order.setCost(500);
        order.setPrice(750);

        return order;
    }

    @Override
    public Order getInvalidEntity() {
        return new Order();
    }
}
