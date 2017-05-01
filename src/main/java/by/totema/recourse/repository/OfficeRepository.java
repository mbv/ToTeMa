package by.totema.recourse.repository;

import by.totema.recourse.entity.model.Office;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends PagingAndSortingRepository<Office, Integer> {

}
