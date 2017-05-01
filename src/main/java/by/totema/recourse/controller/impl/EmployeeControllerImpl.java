package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.EmployeeController;
import by.totema.recourse.controller.exception.AccessDeniedException;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.controller.exception.MethodNotAllowedException;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.entity.dto.PasswordChanging;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.entity.support.UserRoleEnumConverter;
import by.totema.recourse.service.EmployeeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class EmployeeControllerImpl extends AbstractCrudController<Employee, Integer> implements EmployeeController {

    private static final Logger logger = getLogger(EmployeeControllerImpl.class);
    private EmployeeService employeeService;
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    public EmployeeControllerImpl(EmployeeService employeeService, DefaultTokenServices defaultTokenServices) {
        super(employeeService, logger);
        this.employeeService = employeeService;
        this.defaultTokenServices = defaultTokenServices;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Employee.Role.class,
                new UserRoleEnumConverter());
    }

    @Override
    public <S extends Employee> S create(@Valid @RequestBody S entity, @Auth EmployeeAuthDetails authDetails) {
        throw new MethodNotAllowedException(HttpMethod.POST);
    }

    @Override
    public void delete(Integer integer, @Auth EmployeeAuthDetails authDetails) {
        throw new MethodNotAllowedException(HttpMethod.DELETE);
    }

    @Override
    public Employee update(@Valid @RequestBody Employee entity, @PathVariable("id") Integer id, @Auth EmployeeAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToRead);
        return wrapServiceCall(logger, () -> {
            Optional<Employee> callResult = employeeService.update(entity, id, authDetails);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<Employee> getEmployeesByRole(@RequestParam("role") Employee.Role role, Pageable pageable, @Auth EmployeeAuthDetails authDetails) {
        if (!authDetails.isAdmin()) {
            throw new AccessDeniedException();
        }
        return wrapServiceCall(logger, () -> employeeService.findByRole(role, pageable));
    }

    @Override
    public Iterable<Employee> getAll(@Auth EmployeeAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToRead);
        return super.getAll(authDetails);
    }

    @Override
    public void logout(OAuth2Authentication principal) {
        OAuth2AccessToken accessToken = defaultTokenServices.getAccessToken(principal);
        defaultTokenServices.revokeToken(accessToken.getValue());
    }

    @Override
    public void changePassword(@RequestBody PasswordChanging passwordChanging, @Auth EmployeeAuthDetails userAuthDetails) throws ControllerException {
        wrapServiceCall(logger, () -> employeeService.changePassword(userAuthDetails.getId(), passwordChanging));
    }

    @Override
    public Employee getMyInfo(@Auth EmployeeAuthDetails authDetails) {
        return wrapServiceCall(logger, () -> {
                    Optional<Employee> callResult = employeeService.findById(authDetails.getId());
                    return callResult.orElseThrow(NotFoundException::new);
                }
        );
    }

    @Override
    protected boolean hasAuthorityToEdit(Employee entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }

    @Override
    protected boolean hasAuthorityToRead(Employee entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin() || (entity != null && authDetails.getId().equals(entity.getId()));
    }
}
