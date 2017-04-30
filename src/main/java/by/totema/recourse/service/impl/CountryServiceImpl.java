package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Country;
import by.totema.recourse.repository.CountryRepository;
import by.totema.recourse.service.CountryService;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

public class CountryServiceImpl extends AbstractCrudService<Country, Integer> implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    @Override
    protected String getEntityName() {
        return "country";
    }

    @Override
    protected List<Validator> getValidators() {
        return Collections.emptyList();
    }
}
