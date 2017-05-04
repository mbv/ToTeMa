package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Order;
import by.totema.recourse.repository.DateRepository;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OrderSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class OrderServiceTest extends CrudServiceTest<Order, Integer> {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private DateRepository dateRepository;
    private OrderSupplier orderSupplier;

    public OrderServiceTest() {
        orderRepository = Mockito.mock(OrderRepository.class);
        dateRepository = Mockito.mock(DateRepository.class);
        orderService = new OrderServiceImpl(orderRepository, dateRepository);
        orderSupplier = new OrderSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(orderRepository.findBySolutionId(any())).thenReturn(orderSupplier.getValidEntityWithId());

        Optional<Country> result = orderService.findBySolutionId(orderSupplier.getAnyId());

        verify(orderRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(orderRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = orderService.findBySolutionId(orderSupplier.getAnyId());

        verify(orderRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

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
