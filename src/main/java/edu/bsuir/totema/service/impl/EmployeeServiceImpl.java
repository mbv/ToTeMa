package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.exception.ServiceException;

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
}
