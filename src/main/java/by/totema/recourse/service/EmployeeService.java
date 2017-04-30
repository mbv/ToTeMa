package by.totema.recourse.service;

import by.totema.recourse.entity.dto.PasswordChanging;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends CrudService<Employee, Integer> {

    List<Employee> findByRole(Employee.Role role, Pageable pageable);

    Optional<Employee> findByUsername(String username) throws ServiceException;

    <S extends Employee> Optional<S> update(S entity, Integer employeeId, Employee performer) throws ServiceException;

    Optional<Boolean> changePassword(Integer employeeId, PasswordChanging passwordChanging) throws ServiceException;
}
