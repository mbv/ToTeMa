package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Product;
import by.totema.recourse.repository.ProductRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.ProductService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class ProductServiceTest extends CrudServiceTest<Product, Integer> {
    private ProductService productService;
    private ProductRepository productRepository;
    private ProductSupplier productSupplier;

    public ProductServiceTest() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
        productSupplier = new ProductSupplier();
    }

    @Override
    protected CrudService<Product, Integer> getCrudService() {
        return productService;
    }

    @Override
    protected PagingAndSortingRepository<Product, Integer> getCrudRepository() {
        return productRepository;
    }

    @Override
    protected EntitySupplier<Product, Integer> getEntitySupplier() {
        return productSupplier;
    }

    @Override
    protected void setupAllowedRoles(Product entity) { }

}
