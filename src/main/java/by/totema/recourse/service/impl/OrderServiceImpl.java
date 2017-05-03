package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.OrderService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

public class OrderServiceImpl extends AbstractCrudService<Order, Integer> implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    protected String getEntityName() {
        return "order";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
