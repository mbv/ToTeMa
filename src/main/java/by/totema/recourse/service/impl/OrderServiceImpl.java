package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.ErrorMessage;
import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Date;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.DateService;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.exception.ServiceException;
import by.totema.recourse.validation.exception.ServiceBadRequestException;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class OrderServiceImpl extends AbstractCrudService<Order, Integer> implements OrderService {
    private OrderRepository orderRepository;
    private DateService dateService;

    public OrderServiceImpl(OrderRepository orderRepository, DateService dateService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.dateService = dateService;
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
        Order order = new Order();
        order.setId(null);

        mapDtoToEntity(order, dto);

        validateEntity(order);
        return wrapJPACallToOptional(() -> orderRepository.save(order));
    }

    @Override
    public Optional<Order> update(OrderDto dto, Integer id) throws ServiceException {
        Optional<Order> result;
        Optional<Order> databaseOrderOptional = wrapJPACallToOptional(() -> orderRepository.findOne(id));
        if (databaseOrderOptional.isPresent()) {
            Order databaseOrder = databaseOrderOptional.get();
            mapDtoToEntity(databaseOrder, dto);

            result = wrapJPACallToOptional(() -> orderRepository.save(databaseOrder));
        } else {
            result = Optional.empty();
        }
        return result;
    }

    private void mapDtoToEntity(Order order, OrderDto dto) throws ServiceException {
        order.setQuantity(dto.getQuantity());
        order.setCost(dto.getCost());
        order.setPrice(dto.getPrice());
        order.setGrossMargin(dto.getGrossMargin());
        order.setEmployee(dto.getEmployee());
        order.setOffice(dto.getOffice());

        order.setDate(processDate(dto));
    }

    private Date processDate(OrderDto dto) throws ServiceException {
        Optional<Date> date = dateService.getOrCreate(dto.getDate());
        if (!date.isPresent()) {
            throw new ServiceBadRequestException(new ErrorMessage("date", "Bad date"));
        }
        return date.get();
    }
}
