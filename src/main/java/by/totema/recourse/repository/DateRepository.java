package by.totema.recourse.repository;

import by.totema.recourse.entity.model.Date;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends PagingAndSortingRepository<Date, Integer> {

}
