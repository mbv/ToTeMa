package by.totema.recourse.repository;

import by.totema.recourse.entity.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    Employee findByUsername(String username);

    List<Employee> findByRole(Employee.Role role, Pageable pageable);

}
