package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.controller.EmployeeController;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.EmployeeService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.EmployeeSupplier;
import org.assertj.core.util.Lists;
import org.mockito.Mockito;

import java.util.Optional;

import static by.totema.recourse.util.Util.allItemsPage;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EmployeeControllerTest extends CrudControllerTest<Employee, Integer> {

    private EmployeeService employeeService;

    private EmployeeController employeeController;

    private EntitySupplier<Employee, Integer> entitySupplier;

    public EmployeeControllerTest() {
        employeeService = Mockito.mock(EmployeeService.class);
        employeeController = new EmployeeControllerImpl(employeeService, null);
        entitySupplier = new EmployeeSupplier();
    }

    @Override
    public void createValidEntityTest() throws Exception {
        postEntityAuthorized(getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void createInvalidEntityTest() throws Exception {
        postEntityAuthorized(getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void deleteExistingEntityTest() throws Exception {
        sendDelete(idRequest, employeeSupplier.getWithRole(Employee.Role.ADMIN), entitySupplier.getAnyId()).
                andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void deleteNotExistingEntityTest() throws Exception {
        sendDelete(idRequest, employeeSupplier.getWithRole(Employee.Role.ADMIN), entitySupplier.getAnyId()).
                andExpect(status().isMethodNotAllowed());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        when(employeeService.update(any(), any(), any())).thenReturn(Optional.empty());

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isNotFound());
    }

    @Override
    public void updateEntityValidDataTest() throws Exception {
        when(employeeService.update(any(), any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isOk());
    }

    @Override
    public void getAllEntitiesTest() throws Exception {
        when(getService().findAll(allItemsPage())).thenReturn(Lists.emptyList());
        Employee admin = employeeSupplier.getWithRole(Employee.Role.ADMIN);
        sendGet(generalRequest, admin)
                .andExpect(status().isOk());
    }

    @Override
    public void updateEntityInvalidDataTest() throws Exception {
        super.updateEntityInvalidDataTest();
    }

    @Override
    protected String getEntityName() {
        return "employees";
    }

    @Override
    protected CrudController<Employee, Integer> getController() {
        return employeeController;
    }

    @Override
    protected CrudService<Employee, Integer> getService() {
        return employeeService;
    }

    @Override
    protected EntitySupplier<Employee, Integer> getEntitySupplier() {
        return entitySupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(Employee entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}