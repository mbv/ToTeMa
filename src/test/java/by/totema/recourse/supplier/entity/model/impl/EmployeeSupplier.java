package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.sql.Timestamp;

public class EmployeeSupplier implements EntityIntegerPKSupplier<Employee> {
    private OfficeSupplier officeSupplier = new OfficeSupplier();

    @Override
    public Employee getValidEntityWithoutId() {
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setUsername("test");
        employee.setTitle("Salesman");
        employee.setRole(Employee.Role.SALESMAN);
        employee.setContractTill(new Timestamp(1508457600));
        employee.setHireDate(new Timestamp(1462406400));
        employee.setOffice(officeSupplier.getValidEntityWithId());

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
