package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Country;
import by.totema.recourse.repository.CountryRepository;
import by.totema.recourse.service.CountryService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.CountrySupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class CountryServiceTest extends CrudServiceTest<Country, Integer> {
    private CountryService countryService;
    private CountryRepository countryRepository;
    private CountrySupplier countrySupplier;

    public CountryServiceTest() {
        countryRepository = Mockito.mock(CountryRepository.class);
        countryService = new CountryServiceImpl(countryRepository);
        countrySupplier = new CountrySupplier();
    }

    @Override
    protected CrudService<Country, Integer> getCrudService() {
        return countryService;
    }

    @Override
    protected PagingAndSortingRepository<Country, Integer> getCrudRepository() {
        return countryRepository;
    }

    @Override
    protected EntitySupplier<Country, Integer> getEntitySupplier() {
        return countrySupplier;
    }

    @Override
    protected void setupAllowedRoles(Country entity) { }

}
