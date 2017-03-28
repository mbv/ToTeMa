package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.util.HashUtil;
import edu.bsuir.totema.util.ValidationUtil;

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
    public ValidationResult validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateStringOnEmpty(attributes, "username", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "name", errors);
        ValidationUtil.validateDate(attributes, "hireDate", errors);
        ValidationUtil.validateLong(attributes, "officeKey", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "title", errors);
        ValidationUtil.validateDate(attributes, "contractTill", errors);
        ValidationUtil.validateLong(attributes, "reportsTo", errors);
        //ValidationUtil.validateInt(attributes, "status", errors);

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
        employee.setStatus(Employee.STATUS_ACTIVE);
        return tryCallDAO(() -> getEmployeeDAO().insert(employee));
    }

    @Override
    public Employee update(long id, HashMap<String, String> attributes) throws ServiceException {
        Employee employee = getById(id);
        if (employee != null) {
            entitySetAttribute(employee, attributes);
            tryCallDAO(() -> getEmployeeDAO().update(employee));
        }
        return employee;
    }

    @Override
    public Employee entitySetAttribute(Employee entity, HashMap<String, String> attributes) {
        if (attributes.containsKey("username")) {
            entity.setUsername(attributes.get("username"));
        }
        if (attributes.containsKey("password")) {
            String passwordHash = HashUtil.hashString(attributes.get("password"));
            entity.setPasswordHash(passwordHash);
        }
        if (attributes.containsKey("name")) {
            entity.setName(attributes.get("name"));
        }
        if (attributes.containsKey("hireDate")) {
            entity.setHireDate(Date.valueOf(attributes.get("hireDate")));
        }
        if (attributes.containsKey("officeKey")) {
            entity.setOfficeKey(Long.parseLong(attributes.get("officeKey")));
        }
        if (attributes.containsKey("title")) {
            entity.setTitle(attributes.get("title"));
        }
        if (attributes.containsKey("yearSalary")) {
            entity.setYearSalary(Long.parseLong(attributes.get("yearSalary")));
        }
        if (attributes.containsKey("contractTill")) {
            entity.setContractTill(Date.valueOf(attributes.get("contractTill")));
        }
        if (attributes.containsKey("reportsTo")) {
            entity.setReportsTo(Long.parseLong(attributes.get("reportsTo")));
        }
        if (attributes.containsKey("status")) {
            entity.setStatus(Integer.parseInt(attributes.get("status")));
        }
        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getEmployeeDAO().delete(id));
        return true;
    }
}
