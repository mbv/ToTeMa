package by.totema.recourse.service;

import by.totema.recourse.entity.model.Date;
import by.totema.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface DateService {
    Optional<Date> getOrCreate(String dateString) throws ServiceException;
}
