package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.ProductTypeController;
import by.totema.recourse.entity.model.ProductType;
import by.totema.recourse.service.ProductTypeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class ProductTypeControllerImpl extends AbstractCrudController<ProductType, Integer> implements ProductTypeController {

    private static final Logger logger = getLogger(ProductTypeControllerImpl.class);
    private ProductTypeService productTypeService;

    @Autowired
    public ProductTypeControllerImpl(ProductTypeService productTypeService) {
        super(productTypeService, logger);
        this.productTypeService = productTypeService;
    }

    @Override
    protected boolean hasAuthorityToEdit(ProductType entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
