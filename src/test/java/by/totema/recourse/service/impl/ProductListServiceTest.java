package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.repository.ProductListRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.ProductListService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OrderSupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductListSupplier;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductListServiceTest extends CrudServiceTest<ProductList, Integer> {
    private ProductListService productListService;
    private ProductListRepository productListRepository;
    private ProductListSupplier productListSupplier;
    private OrderRepository orderRepository;
    private OrderSupplier orderSupplier;

    public ProductListServiceTest() {
        productListRepository = Mockito.mock(ProductListRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        productListService = new ProductListServiceImpl(productListRepository, orderRepository);
        productListSupplier = new ProductListSupplier();
        orderSupplier = new OrderSupplier();
    }

    @Test
    public void findByExistingOrderIdTest() throws Exception {
        when(orderRepository.exists(any())).thenReturn(true);
        when(productListRepository.findByOrderKeyOrderByIdDesc(any(), any())).thenReturn(Lists.emptyList());

        Optional<List<ProductList>> result = productListService.findByOrderId(productListSupplier.getAnyId(), null);

        verify(orderRepository, times(1)).exists(any());
        verify(productListRepository, times(1)).findByOrderKeyOrderByIdDesc(any(), any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(orderRepository.exists(any())).thenReturn(false);

        Optional<List<ProductList>> result = productListService.findByOrderId(productListSupplier.getAnyId(), null);

        verify(orderRepository, times(1)).exists(any());
        verify(productListRepository, times(0)).findByOrderKeyOrderByIdDesc(any(), any());
        Assert.assertFalse(result.isPresent());
    }

    @Override
    protected CrudService<ProductList, Integer> getCrudService() {
        return productListService;
    }

    @Override
    protected PagingAndSortingRepository<ProductList, Integer> getCrudRepository() {
        return productListRepository;
    }

    @Override
    protected EntitySupplier<ProductList, Integer> getEntitySupplier() {
        return productListSupplier;
    }

    @Override
    protected void setupAllowedRoles(ProductList entity) { }

}
