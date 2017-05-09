package by.totema.recourse.repository;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {
    List<OrderReportDto> getOrderReport();
}
