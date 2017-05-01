package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.sql.Timestamp;

public class EmployeeSupplier implements EntityIntegerPKSupplier<Employee> {
    @Override
    public Employee getValidEntityWithoutId() {
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setUsername("test");
        employee.setRole(Employee.Role.SALESMAN);
        employee.setContractTill(new Timestamp(1508457600));
        employee.setHireDate(new Timestamp(1462406400));

        return employee;
    }

    @Override
    public Employee getInvalidEntity() {
        return new Employee();
    }

    public Employee getWithRole(Employee.Role role) {
        Employee result = getValidEntityWithId();
        result.setRole(role);
        return result;
    }
}
