package by.totema.recourse.repository;

import by.totema.recourse.entity.model.ProductList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends PagingAndSortingRepository<ProductList, Integer> {
    List<ProductList> findByOrderKeyOrderByIdDesc(Integer OrderKey, Pageable pageable);
}
