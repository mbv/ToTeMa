package edu.bsuir.totema.service.factory;

import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static edu.bsuir.totema.command.resource.Constants.SERVICE_EMPLOYEE;
import static edu.bsuir.totema.command.resource.Constants.SERVICE_OFFICE;
import static edu.bsuir.totema.command.resource.Constants.SERVICE_PRODUCT;

/**
 * Provides access to service layer objects
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private static final ArrayList<String> services = new ArrayList<>();
    static {
        services.add(SERVICE_EMPLOYEE);
        services.add(SERVICE_OFFICE);
        services.add(SERVICE_PRODUCT);
    }
    private EmployeeService employeeService;

    private ServiceFactory() {
        employeeService = new EmployeeServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public static List<String> getServices() {
        return services;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
