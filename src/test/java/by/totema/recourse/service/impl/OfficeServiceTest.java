package by.totema.recourse.service.impl;

import by.totema.recourse.entity.model.Office;
import by.totema.recourse.repository.OfficeRepository;
import by.totema.recourse.service.CrudService;
import by.totema.recourse.service.CrudServiceTest;
import by.totema.recourse.service.OfficeService;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import by.totema.recourse.supplier.entity.model.impl.OfficeSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.PagingAndSortingRepository;

public class OfficeServiceTest extends CrudServiceTest<Office, Integer> {
    private OfficeService officeService;
    private OfficeRepository officeRepository;
    private OfficeSupplier officeSupplier;

    public OfficeServiceTest() {
        officeRepository = Mockito.mock(OfficeRepository.class);
        officeService = new OfficeServiceImpl(officeRepository);
        officeSupplier = new OfficeSupplier();
    }

    @Override
    protected CrudService<Office, Integer> getCrudService() {
        return officeService;
    }

    @Override
    protected PagingAndSortingRepository<Office, Integer> getCrudRepository() {
        return officeRepository;
    }

    @Override
    protected EntitySupplier<Office, Integer> getEntitySupplier() {
        return officeSupplier;
    }

    @Override
    protected void setupAllowedRoles(Office entity) { }

}
