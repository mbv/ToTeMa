package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.ProductListController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.ProductListService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductListSupplier;
import org.mockito.Mockito;

public class ProductListControllerTest extends CrudControllerTest<ProductList, Integer> {
    private ProductListController productListController;
    private ProductListService productListService;
    private ProductListSupplier productListSupplier;

    public ProductListControllerTest() {
        productListService = Mockito.mock(ProductListService.class);
        productListController = new ProductListControllerImpl(productListService);
        productListSupplier = new ProductListSupplier();
    }

    @Override
    protected CrudController<ProductList, Integer> getController() {
        return productListController;
    }

    @Override
    protected CrudService<ProductList, Integer> getService() {
        return productListService;
    }

    @Override
    protected String getEntityName() {
        return "product-lists";
    }

    @Override
    protected EntitySupplier<ProductList, Integer> getEntitySupplier() {
        return productListSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(ProductList entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}