package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.ConversionRateController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.ConversionRate;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.ConversionRateService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ConversionRateSupplier;
import org.mockito.Mockito;

public class ConversionRateControllerTest extends CrudControllerTest<ConversionRate, Integer> {
    private ConversionRateController conversionRateController;
    private ConversionRateService conversionRateService;
    private ConversionRateSupplier conversionRateSupplier;

    public ConversionRateControllerTest() {
        conversionRateService = Mockito.mock(ConversionRateService.class);
        conversionRateController = new ConversionRateControllerImpl(conversionRateService);
        conversionRateSupplier = new ConversionRateSupplier();
    }

    @Override
    protected CrudController<ConversionRate, Integer> getController() {
        return conversionRateController;
    }

    @Override
    protected CrudService<ConversionRate, Integer> getService() {
        return conversionRateService;
    }

    @Override
    protected String getEntityName() {
        return "conversion-rates";
    }

    @Override
    protected EntitySupplier<ConversionRate, Integer> getEntitySupplier() {
        return conversionRateSupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(ConversionRate entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}