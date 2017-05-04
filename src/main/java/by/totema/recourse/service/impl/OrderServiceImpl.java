package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Date;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.DateRepository;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.exception.ServiceException;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class OrderServiceImpl extends AbstractCrudService<Order, Integer> implements OrderService {
    private OrderRepository orderRepository;
    private DateRepository dateRepository;

    public OrderServiceImpl(OrderRepository orderRepository, DateRepository dateRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.dateRepository = dateRepository;
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
        return null;
    }

    private void mapDtoToEntity(Order order, OrderDto dto) {
        order.setQuantity(dto.getQuantity());
        order.setCost(dto.getCost());
        order.setPrice(dto.getPrice());
        order.setGrossMargin(dto.getGrossMargin());
        order.setEmployee(dto.getEmployee());
        order.setOffice(dto.getOffice());

        order.setDate(processDate(dto));

    }

    private Date processDate(OrderDto dto) throws ServiceException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dto.getDate());
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            Optional<Integer> dateId = wrapJPACallToOptional(() -> dateRepository.getOrCreate(timestamp));
            if (!dateId.isPresent()) {
                throw new ServiceException("Bad fsdnfsdnklfs");
            } else {
                Optional<Date> date = wrapJPACallToOptional(() -> dateRepository.findOne(dateId.get()));
                if (!date.isPresent()) {
                    throw new ServiceException("Bad id");
                } else {
                    return date.get();
                }
            }
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
    }
}
