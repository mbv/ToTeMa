package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.ProductTypeController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.ProductType;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.ProductTypeService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductTypeSupplier;
import org.mockito.Mockito;

public class ProductTypeControllerTest extends CrudControllerTest<ProductType, Integer> {
    private ProductTypeController productTypeController;
    private ProductTypeService productTypeService;
    private ProductTypeSupplier productTypeSupplier;

    public ProductTypeControllerTest() {
        productTypeService = Mockito.mock(ProductTypeService.class);
        productTypeController = new ProductTypeControllerImpl(productTypeService);
        productTypeSupplier = new ProductTypeSupplier();
    }

    @Override
    protected CrudController<ProductType, Integer> getController() {
        return productTypeController;
    }

    @Override
    protected CrudService<ProductType, Integer> getService() {
        return productTypeService;
    }

    @Override
    protected String getEntityName() {
        return "product-types";
    }

    @Override
    protected EntitySupplier<ProductType, Integer> getEntitySupplier() {
        return productTypeSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(ProductType entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}