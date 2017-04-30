package by.totema.recourse.configuration.security;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.slf4j.LoggerFactory.getLogger;

public class JdbcEmployeeDetailsService implements UserDetailsService {

    private static final Logger logger = getLogger(JdbcEmployeeDetailsService.class);
    private static final String ENCODING = "UTF-8";
    private final EmployeeRepository employeeRepository;

    JdbcEmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            username = URLDecoder.decode(username, ENCODING);
        } catch (UnsupportedEncodingException e) {
            logger.debug("Wrong encoding passed for username '" + username + '\'');
            throw new UsernameNotFoundException("Wrong encoding");
        }
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            logger.debug("Username '" + username + "' not found");
            throw new UsernameNotFoundException("User '" + username + "' not found in database.");
        }
        return new EmployeeAuthDetails(employee);

    }

}
