package by.totema.recourse.controller.impl;

import by.totema.recourse.controller.CountryController;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.CrudControllerTest;
import by.totema.recourse.entity.model.Country;
import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.service.CountryService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.CountrySupplier;
import org.mockito.Mockito;

public class CountryControllerTest extends CrudControllerTest<Country, Integer> {
    private CountryController countryController;
    private CountryService countryService;
    private CountrySupplier countrySupplier;

    public CountryControllerTest() {
        countryService = Mockito.mock(CountryService.class);
        countryController = new CountryControllerImpl(countryService);
        countrySupplier = new CountrySupplier();
    }

    @Override
    protected CrudController<Country, Integer> getController() {
        return countryController;
    }

    @Override
    protected CrudService<Country, Integer> getService() {
        return countryService;
    }

    @Override
    protected String getEntityName() {
        return "countries";
    }

    @Override
    protected EntitySupplier<Country, Integer> getEntitySupplier() {
        return countrySupplier;
    }

    @Override
    protected Employee prepareAuthorizedEmployee(Country entity, Employee validEmployeeWithId) {
        validEmployeeWithId.setRole(Employee.Role.ADMIN);
        return validEmployeeWithId;
    }
}