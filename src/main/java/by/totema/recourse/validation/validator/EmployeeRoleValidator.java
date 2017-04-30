package by.totema.recourse.validation.validator;

import by.totema.recourse.entity.model.BaseEntity;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import by.totema.recourse.validation.support.EmployeeFieldInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class EmployeeRoleValidator<E extends BaseEntity<ID>, ID extends Serializable> implements Validator {

    private List<EmployeeFieldInfo<E, ID>> allowedRoles;
    private EmployeeRepository employeeRepository;
    private CrudRepository<E, ID> entityRepository;

    public EmployeeRoleValidator(List<EmployeeFieldInfo<E, ID>> fieldsInfo, EmployeeRepository employeeRepository, CrudRepository<E, ID> entityRepository) {
        this.allowedRoles = fieldsInfo;
        this.employeeRepository = employeeRepository;
        this.entityRepository = entityRepository;
    }

    public EmployeeRoleValidator(EmployeeFieldInfo<E, ID> fieldInfo, EmployeeRepository employeeRepository, CrudRepository<E, ID> entityRepository) {
        this(Collections.singletonList(fieldInfo), employeeRepository, entityRepository);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseEntity.class.isAssignableFrom(aClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object o, Errors errors) {
        E newEntity = (E) o;
        E databaseEntity = null;
        if (newEntity.getId() != null) {
            databaseEntity = wrapJPACall(() -> entityRepository.findOne(newEntity.getId()));
        }
        for (EmployeeFieldInfo<E, ID> employeeFieldInfo : allowedRoles) {
            Integer employeeId = employeeFieldInfo.getEmployeeFieldGetter().apply(newEntity).getId();
            Employee databaseEmployee = wrapJPACall(() -> employeeRepository.findOne(employeeId));
            if (databaseEntity != null){
                Integer oldUserId = employeeFieldInfo.getEmployeeFieldGetter().apply(databaseEntity).getId();
                if (!oldUserId.equals(employeeId)){
                    checkUserUpdating(errors, employeeFieldInfo, databaseEmployee);
                }
            } else {
                checkUserUpdating(errors, employeeFieldInfo, databaseEmployee);
            }
        }
    }

    private void checkUserUpdating(Errors errors, EmployeeFieldInfo<E, ID> employeeFieldInfo, Employee databaseEmployee) {
        if (employeeFieldInfo.getAllowedRoles().stream().noneMatch(role -> role == databaseEmployee.getRole())) {
            String fieldName = employeeFieldInfo.getFieldName();
            errors.rejectValue(fieldName, fieldName, String.format("%s has invalid role", fieldName));
        }
    }
}
