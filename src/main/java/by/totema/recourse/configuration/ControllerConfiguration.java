package by.totema.recourse.configuration;


import by.totema.recourse.controller.*;
import by.totema.recourse.controller.impl.*;
import by.totema.recourse.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
public class ControllerConfiguration {

    @Bean
    public EmployeeController userController(EmployeeService employeeService, DefaultTokenServices defaultTokenServices) {
        return new EmployeeControllerImpl(employeeService, defaultTokenServices);
    }

}

