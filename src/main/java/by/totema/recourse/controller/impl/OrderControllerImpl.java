package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.OrderController;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class OrderControllerImpl extends AbstractCrudController<Order, Integer> implements OrderController {

    private static final Logger logger = getLogger(OrderControllerImpl.class);
    private OrderService orderService;

    @Autowired
    public OrderControllerImpl(OrderService orderService) {
        super(orderService, logger);
        this.orderService = orderService;
    }

    @Override
    protected boolean hasAuthorityToEdit(Order entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
