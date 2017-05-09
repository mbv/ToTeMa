package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Product;
import by.totema.recourse.repository.ProductRepository;
import by.totema.recourse.service.ProductService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class ProductServiceImpl extends AbstractCrudService<Product, Integer> implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    protected String getEntityName() {
        return "product";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

    @Override
    public Optional<List<OrderReportDto>> getOrderReport() {
        return wrapJPACallToOptional(() -> productRepository.getOrderReport());
    }

    @Override
    public Optional<List<OrderReportDto>> getDetailedOrderReport() {
        return wrapJPACallToOptional(() -> productRepository.getDetailedOrderReport());
    }
}
