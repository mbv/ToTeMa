package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.util.HashUtil;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO getEmployeeDAO() {
        return DAOFactory.getDAOFactory().getEmployeeDAO();
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        return tryCallDAO(() -> getEmployeeDAO().selectWithLimit(0, 10));
    }

    @Override
    public Employee getById(long id) throws ServiceException {
        return tryCallDAO(() -> getEmployeeDAO().selectById(id));
    }

    @Override
    public ValidationResult validate(HashMap<String, String> attributes) throws ServiceException {
        HashMap<String, String> errors = new HashMap<>();

        if (!attributes.containsKey("name") || attributes.get("name").trim().isEmpty()) {
            errors.put("name", "Attribute \"name\" is empty");
        }
        if (!attributes.containsKey("username") || attributes.get("name").trim().isEmpty()) {
            errors.put("username", "Attribute \"username\" is empty");
        }
        if (!attributes.containsKey("officeKey")) {
            errors.put("officeKey", "Attribute \"officeKey\" is empty");
        } else {
            try {
                Long.parseLong(attributes.get("officeKey"));
            } 
            catch (NumberFormatException e) {
                errors.put("officeKey", "Attribute \"officeKey\" is not valid long");
            }
        }


        if (!errors.isEmpty()) {
            ValidationResult validationResult = new ValidationResult();
            validationResult.setStatus("error");
            validationResult.setErrors(errors);

            return validationResult;
        }
        return null;
    }

    @Override
    public Employee add(HashMap<String, String> attributes) throws ServiceException {
        Employee employee = entitySetAttribute(new Employee(), attributes);

        return tryCallDAO(() -> getEmployeeDAO().insert(employee));
    }

    @Override
    public Employee entitySetAttribute(Employee entity, HashMap<String, String> attributes) {
        Employee employee = new Employee();
        if (attributes.containsKey("username")) {
            employee.setUsername(attributes.get("username"));
        }
        if (attributes.containsKey("password")) {
            String passwordHash = HashUtil.hashString(attributes.get("password"));
            employee.setPasswordHash(passwordHash);
        }
        if (attributes.containsKey("name")) {
            employee.setName(attributes.get("name"));
        }
        if (attributes.containsKey("hireDate")) {
            employee.setHireDate(Date.valueOf(attributes.get("hireDate")));
        }
        if (attributes.containsKey("officeKey")) {
            employee.setOfficeKey(Long.parseLong(attributes.get("officeKey")));
        }
        if (attributes.containsKey("title")) {
            employee.setTitle(attributes.get("title"));
        }
        if (attributes.containsKey("yearSalary")) {
            employee.setYearSalary(Long.parseLong(attributes.get("yearSalary")));
        }
        if (attributes.containsKey("contractTill")) {
            employee.setContractTill(Date.valueOf(attributes.get("contractTill")));
        }
        if (attributes.containsKey("reportsTo")) {
            employee.setReportsTo(Long.parseLong(attributes.get("reportsTo")));
        }
        if (attributes.containsKey("status")) {
            employee.setStatus(Integer.parseInt(attributes.get("status")));
        }
        return employee;
    }
}
