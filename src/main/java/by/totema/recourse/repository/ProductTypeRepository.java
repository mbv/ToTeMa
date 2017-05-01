package by.totema.recourse.repository;

import by.totema.recourse.entity.model.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends PagingAndSortingRepository<ProductType, Integer> {

}
