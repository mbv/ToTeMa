package edu.bsuir.totema.service;

import edu.bsuir.totema.entity.Entity;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;

import java.util.HashMap;
import java.util.List;

public interface Service<T extends Entity> {
    List<T> getAll() throws ServiceException;
    T getById(long id) throws ServiceException;
    ValidationResult validate(HashMap<String, String> attributes);
    T add(HashMap<String, String> attributes) throws ServiceException;
    T update(long id, HashMap<String, String> attributes) throws ServiceException;
    T entitySetAttribute(T entity, HashMap<String, String> attributes) throws ServiceException;
    boolean delete(long id) throws ServiceException;
}
