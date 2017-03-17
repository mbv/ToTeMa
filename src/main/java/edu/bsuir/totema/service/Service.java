package edu.bsuir.totema.service;

import edu.bsuir.totema.entity.Entity;
import edu.bsuir.totema.service.exception.ServiceException;

import java.util.List;

public interface Service<T extends Entity> {
    List<T> getAll() throws ServiceException;
}
