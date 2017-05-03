package by.totema.recourse.configuration;

import by.totema.recourse.repository.*;
import by.totema.recourse.service.*;
import by.totema.recourse.service.impl.*;
import by.totema.recourse.validation.validator.PasswordChangingValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {

    @Bean
    public ConversionRateService conversionRateService(ConversionRateRepository conversionRateRepository) {
        return new ConversionRateServiceImpl(conversionRateRepository);
    }

    @Bean
    public CountryService countryService(CountryRepository countryRepository) {
        return new CountryServiceImpl(countryRepository);
    }

    @Bean
    public EmployeeService userService(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder,
            TokenStore tokenStore,
            ConsumerTokenServices consumerTokenServices,
            PasswordChangingValidator passwordChangingValidator) {
        return new EmployeeServiceImpl(
                employeeRepository,
                passwordEncoder,
                tokenStore,
                consumerTokenServices,
                passwordChangingValidator);
    }

    @Bean
    public OfficeService officeService(OfficeRepository officeRepository) {
        return new OfficeServiceImpl(officeRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public ProductListService productListService(ProductListRepository productListRepository) {
        return new ProductListServiceImpl(productListRepository);
    }

    @Bean
    public ProductTypeService productTypeService(ProductTypeRepository productTypeRepository){
        return new ProductTypeServiceImpl(productTypeRepository);
    }



}
