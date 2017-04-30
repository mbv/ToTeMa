package by.totema.recourse.controller;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.entity.dto.PasswordChanging;
import by.totema.recourse.entity.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/employees")
public interface EmployeeController extends CrudController<Employee, Integer> {

    @GetMapping(value = "", params = "role")
    List<Employee> getEmployeesByRole(@RequestParam("role") Employee.Role role, Pageable pageable, @Auth EmployeeAuthDetails authDetails);

    @GetMapping("/me")
    Employee getMyInfo(@Auth EmployeeAuthDetails authDetails);

    @PostMapping("/logout")
    void logout(OAuth2Authentication principal) throws ControllerException;

    @PostMapping("/password/change")
    void changePassword(@RequestBody PasswordChanging passwordChanging, @Auth EmployeeAuthDetails employeeAuthDetails) throws ControllerException;
}
