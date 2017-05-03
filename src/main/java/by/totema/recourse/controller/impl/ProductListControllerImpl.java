package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.ProductListController;
import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.service.ProductListService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class ProductListControllerImpl extends AbstractCrudController<ProductList, Integer> implements ProductListController {

    private static final Logger logger = getLogger(ProductListControllerImpl.class);
    private ProductListService productListService;

    @Autowired
    public ProductListControllerImpl(ProductListService productListService) {
        super(productListService, logger);
        this.productListService = productListService;
    }

    @Override
    protected boolean hasAuthorityToEdit(ProductList entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
