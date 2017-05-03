package by.totema.recourse.service;

import by.totema.recourse.entity.model.ProductList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductListService extends CrudService<ProductList, Integer> {
    Optional<List<ProductList>> findByOrderId(Integer id, Pageable pageable);
}
