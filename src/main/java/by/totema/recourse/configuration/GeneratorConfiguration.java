package by.totema.recourse.configuration;

import by.totema.recourse.document.impl.StudentProfilePdfGenerator;
import by.totema.recourse.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfiguration {

    @Bean
    public StudentProfilePdfGenerator studentProfilePdfGenerator(
            EmployeeRepository employeeRepository) {
        return new StudentProfilePdfGenerator(employeeRepository);
    }

}