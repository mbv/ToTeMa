package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ConversionRate;
import by.totema.recourse.repository.ConversionRateRepository;
import by.totema.recourse.service.ConversionRateService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

public class ConversionRateServiceImpl extends AbstractCrudService<ConversionRate, Integer> implements ConversionRateService {
    private ConversionRateRepository conversionRateRepository;

    public ConversionRateServiceImpl(ConversionRateRepository conversionRateRepository) {
        super(conversionRateRepository);
        this.conversionRateRepository = conversionRateRepository;
    }

    @Override
    protected String getEntityName() {
        return "conversion_rate";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
