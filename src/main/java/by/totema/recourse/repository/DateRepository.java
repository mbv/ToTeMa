package by.totema.recourse.repository;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Date;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DateRepository extends PagingAndSortingRepository<Date, Integer> {
    Date findByTimeStamp(Timestamp timestamp);

    @Procedure(name = "getOrCreateDate")
    Integer getOrCreate(@Param("inTimestamp") Timestamp inTimestamp);

    List<OrderReportDto> getOrderReport();
}
