package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.exception.ServiceException;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Order> add(OrderDto dto) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Order> update(OrderDto dto, Integer id) throws ServiceException {
        return null;
    }
}
