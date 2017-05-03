package by.totema.recourse.configuration;


import by.totema.recourse.controller.*;
import by.totema.recourse.controller.exception.WhiteLabelErrorPageController;
import by.totema.recourse.controller.impl.*;
import by.totema.recourse.service.*;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
public class ControllerConfiguration {

    @Bean
    public ErrorController errorController() {
        return new WhiteLabelErrorPageController();
    }

    @Bean
    public ConversionRateController conversionRateController(ConversionRateService conversionRateService) {
        return new ConversionRateControllerImpl(conversionRateService);
    }
    @Bean
    public CountryController countryController(CountryService countryService) {
        return new CountryControllerImpl(countryService);
    }

    @Bean
    public EmployeeController userController(EmployeeService employeeService, DefaultTokenServices defaultTokenServices) {
        return new EmployeeControllerImpl(employeeService, defaultTokenServices);
    }

    @Bean
    public OfficeController officeController(OfficeService officeService) {
        return new OfficeControllerImpl(officeService);
    }

    @Bean
    public OrderController orderController(OrderService orderService, ProductListService productListService) {
        return new OrderControllerImpl(orderService, productListService);
    }

    @Bean
    public ProductController productController(ProductService productService) {
        return new ProductControllerImpl(productService);
    }

    @Bean
    public ProductListController productListController(ProductListService productListService) {
        return new ProductListControllerImpl(productListService);
    }

    @Bean
    public ProductTypeController productTypeController(ProductTypeService productTypeService) {
        return new ProductTypeControllerImpl(productTypeService);
    }


}

