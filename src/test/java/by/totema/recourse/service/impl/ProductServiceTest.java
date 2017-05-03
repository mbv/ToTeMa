package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Product;
import by.totema.recourse.repository.ProductRepository;
import by.totema.recourse.service.ProductService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class ProductServiceTest extends CrudServiceTest<Product, Integer> {
    private ProductService productService;
    private ProductRepository productRepository;
    private ProductSupplier countrySupplier;

    public ProductServiceTest() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
        countrySupplier = new ProductSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(productRepository.findBySolutionId(any())).thenReturn(countrySupplier.getValidEntityWithId());

        Optional<Country> result = productService.findBySolutionId(countrySupplier.getAnyId());

        verify(productRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(productRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = productService.findBySolutionId(countrySupplier.getAnyId());

        verify(productRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

    @Override
    protected CrudService<Product, Integer> getCrudService() {
        return productService;
    }

    @Override
    protected CrudRepository<Product, Integer> getCrudRepository() {
        return productRepository;
    }

    @Override
    protected EntitySupplier<Product, Integer> getEntitySupplier() {
        return countrySupplier;
    }

    @Override
    protected void setupAllowedRoles(Product entity) { }

}
