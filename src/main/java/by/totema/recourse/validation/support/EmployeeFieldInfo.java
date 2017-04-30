package by.totema.recourse.validation.support;

import by.totema.recourse.entity.model.BaseEntity;
import by.totema.recourse.entity.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class EmployeeFieldInfo<E extends BaseEntity<ID>, ID> {
    private final Function<E, Employee> employeeFieldGetter;
    private final String fieldName;
    private List<Employee.Role> allowedRoles;

    public EmployeeFieldInfo(Function<E, Employee> employeeFieldGetter, String fieldName, List<Employee.Role> allowedRoles) {
        this.employeeFieldGetter = employeeFieldGetter;
        this.fieldName = fieldName;
        this.allowedRoles = allowedRoles;
    }

    public EmployeeFieldInfo(Function<E, Employee> employeeFieldGetter, String fieldName, Employee.Role allowedRole) {
        this(employeeFieldGetter, fieldName, Collections.singletonList(allowedRole));
    }

    public Function<E, Employee> getEmployeeFieldGetter(){
        return employeeFieldGetter;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<Employee.Role> getAllowedRoles() {
        return allowedRoles;
    }
}
