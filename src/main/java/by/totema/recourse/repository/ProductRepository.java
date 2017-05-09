package by.totema.recourse.repository;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    List<OrderReportDto> getOrderReport();
    List<OrderReportDto> getDetailedOrderReport();
}
