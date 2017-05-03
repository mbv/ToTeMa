package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.ProductController;
import by.totema.recourse.entity.model.Product;
import by.totema.recourse.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class ProductControllerImpl extends AbstractCrudController<Product, Integer> implements ProductController {

    private static final Logger logger = getLogger(ProductControllerImpl.class);
    private ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        super(productService, logger);
        this.productService = productService;
    }

    @Override
    protected boolean hasAuthorityToEdit(Product entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
