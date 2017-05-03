package by.totema.recourse.repository;

import by.totema.recourse.entity.model.ProductList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends PagingAndSortingRepository<ProductList, Integer> {

}
