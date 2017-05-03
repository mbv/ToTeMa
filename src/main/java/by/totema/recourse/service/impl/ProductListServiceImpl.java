package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.repository.OrderRepository;
import by.totema.recourse.repository.ProductListRepository;
import by.totema.recourse.service.ProductListService;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class ProductListServiceImpl extends AbstractCrudService<ProductList, Integer> implements ProductListService {
    private ProductListRepository productListRepository;
    private OrderRepository orderRepository;

    public ProductListServiceImpl(ProductListRepository productListRepository, OrderRepository orderRepository) {
        super(productListRepository);
        this.productListRepository = productListRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    protected String getEntityName() {
        return "product_list";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

    @Override
    public Optional<List<ProductList>> findByOrderId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (orderRepository.exists(id))
                ? productListRepository.findByOrderKeyOrderByIdDesc(id, pageable)
                : null
        );
    }
}
