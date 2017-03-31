package edu.bsuir.totema.service.factory;

import edu.bsuir.totema.service.*;
import edu.bsuir.totema.service.impl.*;

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
    private ConvertionRateService convertionRateService;
    private CountryService countryService;
    private DateService dateService;
    private EmployeeService employeeService;
    private OfficeService officeService;
    private OrderService orderService;
    private ProductService productService;
    private ProductListService productListService;
    private ProductTypeService productTypeService;

    private ServiceFactory() {
        convertionRateService = new ConvertionRateServiceImpl();
        countryService = new CountryServiceImpl();
        dateService = new DateServiceImpl();
        employeeService = new EmployeeServiceImpl();
        officeService = new OfficeServiceImpl();
        orderService = new OrderServiceImpl();
        productService = new ProductServiceImpl();
        productListService = new ProductListServiceImpl();
        productTypeService = new ProductTypeServiceImpl();
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

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ConvertionRateService getConvertionRateService() {
        return convertionRateService;
    }

    public void setConvertionRateService(ConvertionRateService convertionRateService) {
        this.convertionRateService = convertionRateService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public DateService getDateService() {
        return dateService;
    }

    public void setDateService(DateService dateService) {
        this.dateService = dateService;
    }

    public OfficeService getOfficeService() {
        return officeService;
    }

    public void setOfficeService(OfficeService officeService) {
        this.officeService = officeService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductListService getProductListService() {
        return productListService;
    }

    public void setProductListService(ProductListService productListService) {
        this.productListService = productListService;
    }

    public ProductTypeService getProductTypeService() {
        return productTypeService;
    }

    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }
}
