package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.OfficeController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.Office;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.OfficeService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OfficeSupplier;
import org.mockito.Mockito;

public class OfficeControllerTest extends CrudControllerTest<Office, Integer> {
    private OfficeController officeController;
    private OfficeService officeService;
    private OfficeSupplier officeSupplier;

    public OfficeControllerTest() {
        officeService = Mockito.mock(OfficeService.class);
        officeController = new OfficeControllerImpl(officeService);
        officeSupplier = new OfficeSupplier();
    }

    @Override
    protected CrudController<Office, Integer> getController() {
        return officeController;
    }

    @Override
    protected CrudService<Office, Integer> getService() {
        return officeService;
    }

    @Override
    protected String getEntityName() {
        return "offices";
    }

    @Override
    protected EntitySupplier<Office, Integer> getEntitySupplier() {
        return officeSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(Office entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}