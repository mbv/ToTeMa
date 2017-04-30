package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.CountryController;
import by.totema.recourse.entity.model.Country;
import by.totema.recourse.service.CountryService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class CountryControllerImpl extends AbstractCrudController<Country, Integer> implements CountryController {

    private static final Logger logger = getLogger(CountryControllerImpl.class);
    private CountryService countryService;

    @Autowired
    public CountryControllerImpl(CountryService countryService) {
        super(countryService, logger);
        this.countryService = countryService;
    }

    @Override
    protected boolean hasAuthorityToEdit(Country entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
