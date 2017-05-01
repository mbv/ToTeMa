package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductType;
import by.totema.recourse.repository.ProductTypeRepository;
import by.totema.recourse.service.ProductTypeService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

public class ProductTypeServiceImpl extends AbstractCrudService<ProductType, Integer> implements ProductTypeService {
    private ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        super(productTypeRepository);
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    protected String getEntityName() {
        return "productType";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
