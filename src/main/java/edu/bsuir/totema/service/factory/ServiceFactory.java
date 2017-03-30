package edu.bsuir.totema.service.factory;

import edu.bsuir.totema.service.ConvertionRateService;
import edu.bsuir.totema.service.EmployeeService;
import edu.bsuir.totema.service.impl.ConvertionRateServiceImpl;
import edu.bsuir.totema.service.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static edu.bsuir.totema.command.resource.Constants.*;

/**
 * Provides access to service layer objects
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private static final ArrayList<String> services = new ArrayList<>();
    static {
        services.add(SERVICE_CONVERTION_RATE);
        services.add(SERVICE_COUNTRY);
        services.add(SERVICE_DATE);
        services.add(SERVICE_EMPLOYEE);
        services.add(SERVICE_OFFICE);
        services.add(SERVICE_ORDER);
        services.add(SERVICE_PRODUCT);
        services.add(SERVICE_PRODUCT_LIST);
        services.add(SERVICE_PRODUCT_TYPE);
    }
    private ConvertionRateService convertionRate;
    private EmployeeService employeeService;

    private ServiceFactory() {
        convertionRate = new ConvertionRateServiceImpl();
        employeeService = new EmployeeServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }


    public ConvertionRateService getConvertionRate() {
        return convertionRate;
    }

    public void setConvertionRate(ConvertionRateService convertionRate) {
        this.convertionRate = convertionRate;
    }

    public static List<String> getServices() {
        return services;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
