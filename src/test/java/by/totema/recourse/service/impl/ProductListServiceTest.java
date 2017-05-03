package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.repository.ProductListRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.ProductListService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductListSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class ProductListServiceTest extends CrudServiceTest<ProductList, Integer> {
    private ProductListService productListService;
    private ProductListRepository productListRepository;
    private ProductListSupplier productListSupplier;
    private OrderRepository orderRepository;

    public ProductListServiceTest() {
        productListRepository = Mockito.mock(ProductListRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        productListService = new ProductListServiceImpl(productListRepository, orderRepository);
        productListSupplier = new ProductListSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(productListRepository.findBySolutionId(any())).thenReturn(productListSupplier.getValidEntityWithId());

        Optional<Country> result = productListService.findBySolutionId(productListSupplier.getAnyId());

        verify(productListRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(productListRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = productListService.findBySolutionId(productListSupplier.getAnyId());

        verify(productListRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

    @Override
    protected CrudService<ProductList, Integer> getCrudService() {
        return productListService;
    }

    @Override
    protected CrudRepository<ProductList, Integer> getCrudRepository() {
        return productListRepository;
    }

    @Override
    protected EntitySupplier<ProductList, Integer> getEntitySupplier() {
        return productListSupplier;
    }

    @Override
    protected void setupAllowedRoles(ProductList entity) { }

}
