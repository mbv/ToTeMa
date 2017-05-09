package by.totema.recourse.repository;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Office;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends PagingAndSortingRepository<Office, Integer> {
    List<OrderReportDto> getOrderReport();
}
