package edu.bsuir.totema.service.factory;

import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.impl.EmployeeServiceImpl;

/**
 * Provides access to service layer objects
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private EmployeeService employeeService;

    private ServiceFactory() {
        employeeService = new EmployeeServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
