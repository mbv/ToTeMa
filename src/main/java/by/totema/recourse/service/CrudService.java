package by.totema.recourse.service;

import by.totema.recourse.entity.model.BaseEntity;
import by.totema.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface CrudService<E extends BaseEntity<ID>, ID> {

    Optional<E> findById(ID id) throws ServiceException;

    Iterable<E> findAll() throws ServiceException;

    <S extends E> Optional<S> add(S entity) throws ServiceException;

    Optional<E> update(E entity, ID id) throws ServiceException;

    Optional<Boolean> delete(ID id) throws ServiceException;
}
