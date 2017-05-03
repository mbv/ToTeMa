package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductType;
import by.totema.recourse.repository.ProductTypeRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.ProductTypeService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductTypeSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class ProductTypeServiceTest extends CrudServiceTest<ProductType, Integer> {
    private ProductTypeService productTypeService;
    private ProductTypeRepository productTypeRepository;
    private ProductTypeSupplier productTypeSupplier;

    public ProductTypeServiceTest() {
        productTypeRepository = Mockito.mock(ProductTypeRepository.class);
        productTypeService = new ProductTypeServiceImpl(productTypeRepository);
        productTypeSupplier = new ProductTypeSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(productTypeRepository.findBySolutionId(any())).thenReturn(productTypeSupplier.getValidEntityWithId());

        Optional<Country> result = productTypeService.findBySolutionId(productTypeSupplier.getAnyId());

        verify(productTypeRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(productTypeRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = productTypeService.findBySolutionId(productTypeSupplier.getAnyId());

        verify(productTypeRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

    @Override
    protected CrudService<ProductType, Integer> getCrudService() {
        return productTypeService;
    }

    @Override
    protected PagingAndSortingRepository<ProductType, Integer> getCrudRepository() {
        return productTypeRepository;
    }

    @Override
    protected EntitySupplier<ProductType, Integer> getEntitySupplier() {
        return productTypeSupplier;
    }

    @Override
    protected void setupAllowedRoles(ProductType entity) { }

}
