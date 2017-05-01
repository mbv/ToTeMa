package by.totema.recourse.configuration;


import by.totema.recourse.controller.CountryController;
import by.totema.recourse.controller.EmployeeController;
import by.totema.recourse.controller.OfficeController;
import by.totema.recourse.controller.ProductTypeController;
import by.totema.recourse.controller.exception.WhiteLabelErrorPageController;
import by.totema.recourse.controller.impl.CountryControllerImpl;
import by.totema.recourse.controller.impl.EmployeeControllerImpl;
import by.totema.recourse.controller.impl.OfficeControllerImpl;
import by.totema.recourse.controller.impl.ProductTypeControllerImpl;
import by.totema.recourse.service.CountryService;
import by.totema.recourse.service.EmployeeService;
import by.totema.recourse.service.OfficeService;
import by.totema.recourse.service.ProductTypeService;
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
    public ProductTypeController productTypeController(ProductTypeService productTypeService) {
        return new ProductTypeControllerImpl(productTypeService);
    }


}

