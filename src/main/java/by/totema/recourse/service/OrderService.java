package by.totema.recourse.service;

import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface OrderService extends CrudService<Order, Integer> {
    Optional<Order> add(OrderDto dto) throws ServiceException;

    Optional<Order> update(OrderDto dto, Integer id) throws ServiceException;
}
