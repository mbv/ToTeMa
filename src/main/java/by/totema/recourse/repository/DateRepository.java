package by.totema.recourse.repository;

import by.totema.recourse.entity.model.Date;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface DateRepository extends PagingAndSortingRepository<Date, Integer> {
    Date findByTimeStamp(Timestamp timestamp);

    @Procedure(name = "getOrCreate")
    Integer getOrCreate(@Param("inTimestamp") Timestamp inTimestamp);
}
