package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.ConversionRate;
import by.totema.recourse.repository.ConversionRateRepository;
import by.totema.recourse.service.ConversionRateService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.ConversionRateSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class ConversionRateServiceTest extends CrudServiceTest<ConversionRate, Integer> {
    private ConversionRateService conversionRateService;
    private ConversionRateRepository conversionRateRepository;
    private ConversionRateSupplier conversionRateSupplier;

    public ConversionRateServiceTest() {
        conversionRateRepository = Mockito.mock(ConversionRateRepository.class);
        conversionRateService = new ConversionRateServiceImpl(conversionRateRepository);
        conversionRateSupplier = new ConversionRateSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(countryRepository.findBySolutionId(any())).thenReturn(countrySupplier.getValidEntityWithId());

        Optional<Country> result = countryService.findBySolutionId(countrySupplier.getAnyId());

        verify(countryRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(countryRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = countryService.findBySolutionId(countrySupplier.getAnyId());

        verify(countryRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

    @Override
    protected CrudService<ConversionRate, Integer> getCrudService() {
        return conversionRateService;
    }

    @Override
    protected CrudRepository<ConversionRate, Integer> getCrudRepository() {
        return conversionRateRepository;
    }

    @Override
    protected EntitySupplier<ConversionRate, Integer> getEntitySupplier() {
        return conversionRateSupplier;
    }

    @Override
    protected void setupAllowedRoles(ConversionRate entity) { }

}
