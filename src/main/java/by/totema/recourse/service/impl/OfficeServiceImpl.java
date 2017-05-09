package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Office;
import by.totema.recourse.repository.OfficeRepository;
import by.totema.recourse.service.OfficeService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class OfficeServiceImpl extends AbstractCrudService<Office, Integer> implements OfficeService {
    private OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        super(officeRepository);
        this.officeRepository = officeRepository;
    }

    @Override
    protected String getEntityName() {
        return "office";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }

    @Override
    public Optional<List<OrderReportDto>> getOrderReport() {
        return wrapJPACallToOptional(() -> officeRepository.getOrderReport());
    }
}
