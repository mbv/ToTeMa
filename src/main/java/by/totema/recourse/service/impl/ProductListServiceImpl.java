package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.repository.ProductListRepository;
import by.totema.recourse.service.ProductListService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

public class ProductListServiceImpl extends AbstractCrudService<ProductList, Integer> implements ProductListService {
    private ProductListRepository productListRepository;

    public ProductListServiceImpl(ProductListRepository productListRepository) {
        super(productListRepository);
        this.productListRepository = productListRepository;
    }

    @Override
    protected String getEntityName() {
        return "product_list";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
