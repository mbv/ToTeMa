package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.EmployeeService;
import by.totema.recourse.service.exception.ServiceException;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.EmployeeSupplier;
import by.totema.recourse.validation.validator.PasswordChangingValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EmployeeServiceTest extends CrudServiceTest<Employee, Integer> {


    private EmployeeService employeeService;

    private EmployeeRepository employeeRepository;

    private TokenStore tokenStore;

    private ConsumerTokenServices consumerTokenServices;

    private PasswordEncoder passwordEncoder;

    private EmployeeSupplier employeeSupplier;

    public EmployeeServiceTest() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        tokenStore = Mockito.mock(TokenStore.class);
        consumerTokenServices = Mockito.mock(ConsumerTokenServices.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);

        PasswordChangingValidator passwordChangingValidator = Mockito.mock(PasswordChangingValidator.class);
        employeeService = new EmployeeServiceImpl(employeeRepository,
                passwordEncoder,
                tokenStore,
                consumerTokenServices,
                passwordChangingValidator);
        employeeSupplier = new EmployeeSupplier();
    }

    @Test
    public void findByExistingEmailTest() throws Exception {
        Employee employee = employeeSupplier.getValidEntityWithId();
        when(employeeRepository.findByUsername(employee.getUsername())).thenReturn(employee);

        Optional<Employee> result = employeeService.findByUsername(employee.getUsername());

        verify(employeeRepository, times(1)).findByUsername(employee.getUsername());
        Assert.assertEquals(employee, result.orElse(null));
    }

    @Test
    public void findByNotExistingEmailTest() throws Exception {
        Employee employee = employeeSupplier.getValidEntityWithId();
        when(employeeRepository.findByUsername(employee.getUsername())).thenReturn(null);

        Optional<Employee> result = employeeService.findByUsername(employee.getUsername());

        verify(employeeRepository, times(1)).findByUsername(employee.getUsername());
        Assert.assertFalse(result.isPresent());
    }

    @Test
    @Override
    public void updateEntityWithoutIdTest() throws Exception {
        Employee newEntity = getEntitySupplier().getValidEntityWithoutId();
        Employee databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        databaseEntity.setId(parameterId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<Employee> actualResult = employeeService.update(newEntity, parameterId, employeeSupplier.getWithRole(Employee.Role.ADMIN));

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<Employee>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    @Override
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<Integer, Integer> ids = getEntitySupplier().getDifferentIds();
        Integer entityId = ids.getFirst();
        Integer parameterId = ids.getSecond();
        Employee newEntity = getEntitySupplier().getValidEntityWithoutId();
        Employee databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        databaseEntity.setId(parameterId);
        newEntity.setId(entityId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<Employee> actualResult = employeeService.update(newEntity, parameterId, employeeSupplier.getWithRole(Employee.Role.ADMIN));

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<Employee>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    @Override
    public void updateNotExistingEntityTest() throws Exception {
        Employee entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().exists(parameterId)).thenReturn(false);
        when(getCrudRepository().findOne(parameterId)).thenReturn(null);

        Optional<Employee> actualResult = employeeService.update(entity, parameterId, employeeSupplier.getWithRole(Employee.Role.ADMIN));

        verify(getCrudRepository(), times(0)).save(entity);
        Assert.assertFalse(actualResult.isPresent());
    }

    @Test
    @Override
    public void updateEntityExceptionTest() throws Exception {
        Employee entity = getEntitySupplier().getValidEntityWithoutId();
        Integer parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(Matchers.<Employee>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(entity);
        setupAllowedRoles(entity);

        thrown.expect(ServiceException.class);

        employeeService.update(entity, parameterId, employeeSupplier.getWithRole(Employee.Role.ADMIN));

        verify(getCrudRepository(), times(1)).save(Matchers.<Employee>any());
    }

    @Override
    protected CrudService<Employee, Integer> getCrudService() {
        return employeeService;
    }

    @Override
    protected CrudRepository<Employee, Integer> getCrudRepository() {
        return employeeRepository;
    }

    @Override
    protected EntitySupplier<Employee, Integer> getEntitySupplier() {
        return employeeSupplier;
    }

    @Override
    protected void setupAllowedRoles(Employee entity) { }
}

