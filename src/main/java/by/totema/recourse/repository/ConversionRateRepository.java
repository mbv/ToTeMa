package by.totema.recourse.repository;

import by.totema.recourse.entity.model.ConversionRate;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRateRepository extends PagingAndSortingRepository<ConversionRate, Integer> {

}
