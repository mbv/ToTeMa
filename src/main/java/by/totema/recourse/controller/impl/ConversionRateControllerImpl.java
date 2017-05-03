package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.ConversionRateController;
import by.totema.recourse.entity.model.ConversionRate;
import by.totema.recourse.service.ConversionRateService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class ConversionRateControllerImpl extends AbstractCrudController<ConversionRate, Integer> implements ConversionRateController {

    private static final Logger logger = getLogger(ConversionRateControllerImpl.class);
    private ConversionRateService conversionRateService;

    @Autowired
    public ConversionRateControllerImpl(ConversionRateService conversionRateService) {
        super(conversionRateService, logger);
        this.conversionRateService = conversionRateService;
    }

    @Override
    protected boolean hasAuthorityToEdit(ConversionRate entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
