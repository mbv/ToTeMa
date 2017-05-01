package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.OfficeController;
import by.totema.recourse.entity.model.Office;
import by.totema.recourse.service.OfficeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;


public class OfficeControllerImpl extends AbstractCrudController<Office, Integer> implements OfficeController {

    private static final Logger logger = getLogger(OfficeControllerImpl.class);
    private OfficeService officeService;

    @Autowired
    public OfficeControllerImpl(OfficeService officeService) {
        super(officeService, logger);
        this.officeService = officeService;
    }

    @Override
    protected boolean hasAuthorityToEdit(Office entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }
}
