package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.ErrorMessage;
import by.totema.recourse.entity.dto.PasswordChanging;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import by.totema.recourse.service.EmployeeService;
import by.totema.recourse.service.exception.ServiceBadRequestException;
import by.totema.recourse.service.exception.ServiceException;
import by.totema.recourse.validation.validator.PasswordChangingValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public class EmployeeServiceImpl extends AbstractCrudService<Employee, Integer> implements EmployeeService {

    private static final Logger logger = getLogger(EmployeeServiceImpl.class);

    private final TokenStore tokenStore;
    private final ConsumerTokenServices consumerTokenServices;
    private EmployeeRepository employeeRepository;
    private PasswordChangingValidator passwordChangingValidator;
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PasswordEncoder passwordEncoder,
                               TokenStore tokenStore,
                               ConsumerTokenServices consumerTokenServices,
                               PasswordChangingValidator passwordChangingValidator) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.consumerTokenServices = consumerTokenServices;
        this.passwordChangingValidator = passwordChangingValidator;
    }

    @Override
    public List<Employee> findByRole(Employee.Role role, Pageable pageable) {
        return wrapJPACall(() -> employeeRepository.findByRole(role, pageable));
    }

    @Override
    public Optional<Employee> findByUsername(String username) throws ServiceException {
        return wrapJPACallToOptional(() -> employeeRepository.findByUsername(username));
    }

    @Override
    public Optional<Employee> update(Employee entity, Integer id) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    @Override
    public <S extends Employee> Optional<S> update(S newEmployee, Integer id, Employee performer) throws ServiceException {
        Optional<S> result;
        Integer performerId = performer.getId();
        performer = wrapJPACall(() -> employeeRepository.findOne(performerId));
        Optional<Employee> databaseUserOptional = wrapJPACallToOptional(() -> employeeRepository.findOne(id));
        if (databaseUserOptional.isPresent()) {
            Employee databaseEmployee = databaseUserOptional.get();
            restoreEmployeePermanentValues(databaseEmployee, newEmployee);
            if (databaseEmployee.getRole() != newEmployee.getRole()) {
                if (performer.getRole() == Employee.Role.ADMIN) {
                    if (!performer.getId().equals(databaseEmployee.getId())) {
                        handleRoleUpdating(databaseEmployee, newEmployee);
                    } else {
                        rejectRoleChanging("Admin can not downgrade himself");
                    }
                } else {
                    denyRoleChanging("Role changing is denied.");
                }
            }
            result = wrapJPACallToOptional(() -> employeeRepository.save(newEmployee));
        } else {
            result = Optional.empty();
        }
        return result;
    }

    private void restoreEmployeePermanentValues(Employee databaseEmployee, Employee newEmployee) {
        newEmployee.setId(databaseEmployee.getId());
        newEmployee.setPasswordHash(databaseEmployee.getPasswordHash());
    }

    private void handleRoleUpdating(Employee databaseEmployee, Employee newEmployee) {
        if (newEmployee.getRole() == Employee.Role.DISABLED) {
            disableUser(databaseEmployee, newEmployee);
        } else {
            switch (databaseEmployee.getRole()) {
               /* case TEACHER:
                    checkTeacherRoleUpdate(databaseEmployee);
                    break;
                case STUDENT:
                    checkStudentRoleUpdate(databaseEmployee);
                    break;*/
                case ADMIN:
                    break;
                case DISABLED:
                    checkUserEnabling(databaseEmployee, newEmployee.getRole());
                    break;
                default:
                    rejectRoleChanging("Unknown role");
            }
        }
        forceLogoutEmployee(databaseEmployee);
    }

    private void checkUserEnabling(Employee disabledEmployee, Employee.Role newRole) {
        switch (newRole) {
            /*case TEACHER:
                if (!disabledEmployee.getCourses().isEmpty()) {
                    rejectRoleChanging("Teacher can't be registered to courses.");
                }
                break;
            case STUDENT:
                List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeDesc(disabledEmployee.getId(), allItemsPage()));
                if (!lessons.isEmpty()) {
                    rejectRoleChanging("Teacher can't have any lessons.");
                }
                break;*/
            case ADMIN:
               /* lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeDesc(disabledEmployee.getId(), allItemsPage()));
                if (!lessons.isEmpty() || !disabledEmployee.getCourses().isEmpty()) {
                    rejectRoleChanging("Admin can't have any lessons or be registered to courses.");
                }*/
                break;
            default:
                rejectRoleChanging("Unknown role");
        }
    }

    private void disableUser(Employee databaseUser, Employee newUser) {
        switch (databaseUser.getRole()) {
          /*  case STUDENT:
                Set<Course> courses = databaseUser.getCourses();
                courses = courses.stream()
                        .filter(course -> course.getStatus() == Course.Status.FINISHED)
                        .collect(Collectors.toSet());
                newUser.setCourses(courses);
                break;
            case TEACHER:
                List<Lesson> lessons = wrapJPACall(() -> lessonRepository.findByTeacherIdOrderByStartTimeDesc(
                        newUser.getId(), allItemsPage()));
                if (lessons.stream().anyMatch(
                        lesson -> lesson.getStartTime().after(Timestamp.from(Instant.now())))) {
                    rejectRoleChanging("Teacher has lessons in the future.");
                }
                break;*/
        }
    }

    private void checkStudentRoleUpdate(Employee student) {
     /*  if (!student.getCourses().isEmpty()) {
            rejectRoleChanging("Student is registered to courses.");
        }*/
    }

    private void rejectRoleChanging(String message) {
        throw new ServiceBadRequestException(new ErrorMessage("role", message));
    }

    private void denyRoleChanging(String message) {
        throw new ServiceBadRequestException(new ErrorMessage("role", message));
    }

    private void forceLogoutEmployee(Employee employee) {
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName(
                "web_client",
                employee.getUsername());
        for (OAuth2AccessToken token : tokens) {
            consumerTokenServices.revokeToken(token.getValue());
        }
    }

    @Override
    public Optional<Boolean> changePassword(Integer employeeId, PasswordChanging passwordChanging) throws ServiceException {
        Optional<Boolean> result;
        validate(passwordChanging, "password changing");
        Optional<Employee> databaseEmployeeOptional = wrapJPACallToOptional(() -> employeeRepository.findOne(employeeId));
        if (databaseEmployeeOptional.isPresent()) {
            Employee databaseEmployee = databaseEmployeeOptional.get();
            String newPasswordHash = passwordEncoder.encode(passwordChanging.getPassword());
            databaseEmployee.setPasswordHash(newPasswordHash);
            wrapJPACall(() -> employeeRepository.save(databaseEmployee));
            result = Optional.of(true);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    protected String getEntityName() {
        return "employee";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.singletonList(passwordChangingValidator);
    }

}