package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.OrderController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.ProductListService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OrderSupplier;
import org.mockito.Mockito;

public class OrderControllerTest extends CrudControllerTest<Order, Integer> {
    private OrderController orderController;
    private OrderService orderService;
    private OrderSupplier orderSupplier;
    private ProductListService productListService;

    public OrderControllerTest() {
        orderService = Mockito.mock(OrderService.class);
        productListService = Mockito.mock(ProductListService.class);
        orderController = new OrderControllerImpl(orderService, productListService);
        orderSupplier = new OrderSupplier();
    }

    @Override
    protected CrudController<Order, Integer> getController() {
        return orderController;
    }

    @Override
    protected CrudService<Order, Integer> getService() {
        return orderService;
    }

    @Override
    protected String getEntityName() {
        return "orders";
    }

    @Override
    protected EntitySupplier<Order, Integer> getEntitySupplier() {
        return orderSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(Order entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}