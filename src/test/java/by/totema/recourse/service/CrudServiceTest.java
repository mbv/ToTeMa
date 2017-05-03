package by.totema.recourse.service;

import by.totema.recourse.entity.model.BaseEntity;
import by.totema.recourse.repository.EmployeeRepository;
import by.totema.recourse.service.exception.ServiceException;
import by.totema.recourse.supplier.entity.model.EntitySupplier;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static by.totema.recourse.util.TestUtil.newPage;
import static by.totema.recourse.util.Util.allItemsPage;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public abstract class CrudServiceTest<E extends BaseEntity<ID>, ID extends Serializable> {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Captor
    protected ArgumentCaptor<E> captor;

    protected EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
    }

    protected abstract CrudService<E, ID> getCrudService();

    protected abstract PagingAndSortingRepository<E, ID> getCrudRepository();

    protected abstract EntitySupplier<E, ID> getEntitySupplier();

    @Test
    public void findExistingEntityTest() throws Exception {
        E expectedEntity = getEntitySupplier().getValidEntityWithId();
        ID id = expectedEntity.getId();
        when(getCrudRepository().findOne(id)).thenReturn(expectedEntity);

        Optional<E> actualResult = getCrudService().findById(id);

        verify(getCrudRepository(), times(1)).findOne(id);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void findNotExistingEntityTest() throws Exception {
        ID id = getEntitySupplier().getAnyId();
        when(getCrudRepository().findOne(id)).thenReturn(null);

        Optional<E> entity = getCrudService().findById(id);

        verify(getCrudRepository(), times(1)).findOne(id);
        Assert.assertFalse(entity.isPresent());
    }

    @Test
    public void findAllEntitiesTest() throws Exception {
        when(getCrudRepository().findAll(allItemsPage())).thenReturn(newPage(getEntitySupplier().getValidEntityWithId()));
        List<E> list = Lists.newArrayList(getCrudService().findAll(allItemsPage()));

        verify(getCrudRepository(), times(1)).findAll(allItemsPage());
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void addValidEntityTest() throws Exception {
        E expectedEntity = getEntitySupplier().getValidEntityWithoutId();
        when(getCrudRepository().save(expectedEntity)).thenReturn(expectedEntity);
        setupAllowedRoles(expectedEntity);

        Optional<E> actualResult = getCrudService().add(expectedEntity);

        verify(getCrudRepository(), times(1)).save(expectedEntity);
        Assert.assertEquals(expectedEntity, actualResult.orElse(null));
    }

    @Test
    public void addEntityWithExistingIdTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithId();
        when(getCrudRepository().save(entity)).thenReturn(entity);
        setupAllowedRoles(entity);

        getCrudService().add(entity);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(entity);
        Assert.assertNull(captor.getValue().getId());
    }


    @Test
    public void addEntityExceptionTest() throws Exception {
        E invalidEntity = getEntitySupplier().getValidEntityWithoutId();
        when(getCrudRepository().save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));
        setupAllowedRoles(invalidEntity);

        thrown.expect(ServiceException.class);

        getCrudService().add(invalidEntity);
    }

    @Test
    public void updateEntityWithoutIdTest() throws Exception {
        E newEntity = getEntitySupplier().getValidEntityWithoutId();
        E databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        ID parameterId = getEntitySupplier().getAnyId();
        databaseEntity.setId(parameterId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<E> actualResult = getCrudService().update(newEntity, parameterId);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<E>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateEntityWithDifferentParameterIdTest() throws Exception {
        Pair<ID, ID> ids = getEntitySupplier().getDifferentIds();
        ID entityId = ids.getFirst();
        ID parameterId = ids.getSecond();
        E newEntity = getEntitySupplier().getValidEntityWithoutId();
        E databaseEntity = getEntitySupplier().getValidEntityWithoutId();
        databaseEntity.setId(parameterId);
        newEntity.setId(entityId);
        when(getCrudRepository().save(newEntity)).thenReturn(newEntity);
        when(getCrudRepository().exists(parameterId)).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(databaseEntity);
        setupAllowedRoles(newEntity);

        Optional<E> actualResult = getCrudService().update(newEntity, parameterId);

        verify(getCrudRepository()).save(captor.capture());
        verify(getCrudRepository(), times(1)).save(Matchers.<E>any());
        Assert.assertEquals(newEntity, actualResult.orElse(null));
        Assert.assertEquals(parameterId, captor.getValue().getId());
    }

    @Test
    public void updateNotExistingEntityTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithoutId();
        ID parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().exists(parameterId)).thenReturn(false);
        when(getCrudRepository().findOne(parameterId)).thenReturn(null);

        Optional<E> actualResult = getCrudService().update(entity, parameterId);

        verify(getCrudRepository(), times(0)).save(entity);
        Assert.assertFalse(actualResult.isPresent());
    }

    @Test
    public void updateEntityExceptionTest() throws Exception {
        E entity = getEntitySupplier().getValidEntityWithoutId();
        ID parameterId = getEntitySupplier().getAnyId();
        when(getCrudRepository().save(Matchers.<E>any())).thenThrow(new DataIntegrityViolationException(""));
        when(getCrudRepository().exists(any())).thenReturn(true);
        when(getCrudRepository().findOne(parameterId)).thenReturn(entity);
        setupAllowedRoles(entity);

        thrown.expect(ServiceException.class);

        getCrudService().update(entity, parameterId);

        verify(getCrudRepository(), times(1)).save(Matchers.<E>any());
    }

    @Test
    public void deleteExistingEntityTest() throws Exception {
        getCrudService().delete(getEntitySupplier().getAnyId());

        verify(getCrudRepository(), times(1)).delete(Matchers.<ID>any());
    }

    @Test
    public void deleteNotExistingEntityTest() throws Exception {
        doThrow(new EmptyResultDataAccessException(1)).when(getCrudRepository()).delete(Matchers.<ID>any());

        Optional<Boolean> actual = getCrudService().delete(getEntitySupplier().getAnyId());

        verify(getCrudRepository(), times(1)).delete(Matchers.<ID>any());
        Assert.assertFalse(actual.isPresent());
    }

    protected abstract void setupAllowedRoles(E entity);
}