package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Office;
import by.totema.recourse.repository.OfficeRepository;
import by.totema.recourse.service.OfficeService;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OfficeSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class OfficeServiceTest extends CrudServiceTest<Office, Integer> {
    private OfficeService officeService;
    private OfficeRepository officeRepository;
    private OfficeSupplier officeSupplier;

    public OfficeServiceTest() {
        officeRepository = Mockito.mock(OfficeRepository.class);
        officeService = new OfficeServiceImpl(officeRepository);
        officeSupplier = new OfficeSupplier();
    }
/*
    @Test
    public void findByExistingSolutionIdTest() throws Exception {
        when(officeRepository.findBySolutionId(any())).thenReturn(officeSupplier.getValidEntityWithId());

        Optional<Country> result = officeService.findBySolutionId(officeSupplier.getAnyId());

        verify(officeRepository, times(1)).findBySolutionId(any());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void findByNotExistingSolutionIdTest() throws Exception {
        when(officeRepository.findBySolutionId(any())).thenReturn(null);

        Optional<Country> result = officeService.findBySolutionId(officeSupplier.getAnyId());

        verify(officeRepository, times(1)).findBySolutionId(any());
        Assert.assertFalse(result.isPresent());
    }
*/

    @Override
    protected CrudService<Office, Integer> getCrudService() {
        return officeService;
    }

    @Override
    protected CrudRepository<Office, Integer> getCrudRepository() {
        return officeRepository;
    }

    @Override
    protected EntitySupplier<Office, Integer> getEntitySupplier() {
        return officeSupplier;
    }

    @Override
    protected void setupAllowedRoles(Office entity) { }

}
