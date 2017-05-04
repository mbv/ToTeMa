package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.DateService;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OrderSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class OrderServiceTest extends CrudServiceTest<Order, Integer> {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private DateService dateService;
    private OrderSupplier orderSupplier;

    public OrderServiceTest() {
        orderRepository = Mockito.mock(OrderRepository.class);
        dateService = Mockito.mock(DateService.class);
        orderService = new OrderServiceImpl(orderRepository, dateService);
        orderSupplier = new OrderSupplier();
    }

    @Override
    protected CrudService<Order, Integer> getCrudService() {
        return orderService;
    }

    @Override
    protected PagingAndSortingRepository<Order, Integer> getCrudRepository() {
        return orderRepository;
    }

    @Override
    protected EntitySupplier<Order, Integer> getEntitySupplier() {
        return orderSupplier;
    }

    @Override
    protected void setupAllowedRoles(Order entity) { }

}
