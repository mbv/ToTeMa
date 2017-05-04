package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.ProductController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.Product;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.ProductService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ProductSupplier;
import org.mockito.Mockito;

public class ProductControllerTest extends CrudControllerTest<Product, Integer> {
    private ProductController productController;
    private ProductService productService;
    private ProductSupplier productSupplier;

    public ProductControllerTest() {
        productService = Mockito.mock(ProductService.class);
        productController = new ProductControllerImpl(productService);
        productSupplier = new ProductSupplier();
    }

    @Override
    protected CrudController<Product, Integer> getController() {
        return productController;
    }

    @Override
    protected CrudService<Product, Integer> getService() {
        return productService;
    }

    @Override
    protected String getEntityName() {
        return "products";
    }

    @Override
    protected EntitySupplier<Product, Integer> getEntitySupplier() {
        return productSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(Product entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}