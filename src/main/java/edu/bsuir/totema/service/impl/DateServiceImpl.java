package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.DateDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Date;
import edu.bsuir.totema.service.DateService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.util.ValidationUtil;

import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class DateServiceImpl implements DateService {

    private DateDAO getDateDAO() {
        return DAOFactory.getDAOFactory().getDateDAO();
    }

    @Override
    public List<Date> getAll() throws ServiceException {
        return tryCallDAO(() -> getDateDAO().selectWithLimit(0, 10));
    }

    @Override
    public Date getById(long id) throws ServiceException {
        return tryCallDAO(() -> getDateDAO().selectById(id));
    }

    @Override
    public ValidationResult validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        //TODO: need validation for timestamp
        ValidationUtil.validateLong(attributes, "year", errors);
        ValidationUtil.validateLong(attributes, "quarter", errors);
        ValidationUtil.validateLong(attributes, "month", errors);
        ValidationUtil.validateLong(attributes, "week", errors);
        ValidationUtil.validateLong(attributes, "day", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "monthName", errors);
        //ValidationUtil.validateInt(attributes, "status", errors);

        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public Date add(HashMap<String, String> attributes) throws ServiceException {
        Date date = entitySetAttribute(new Date(), attributes);
        return tryCallDAO(() -> getDateDAO().insert(date));
    }

    @Override
    public Date update(long id, HashMap<String, String> attributes) throws ServiceException {
        Date date = getById(id);
        if (date != null) {
            entitySetAttribute(date, attributes);
            tryCallDAO(() -> getDateDAO().update(date));
        }
        return date;
    }

    @Override
    public Date entitySetAttribute(Date entity, HashMap<String, String> attributes) throws ServiceException {

        if (attributes.containsKey("month")) {
            entity.setMonthName(attributes.get("month"));
        }
        if (attributes.containsKey("year")) {
            entity.setYear(Long.parseLong(attributes.get("year")));
        }
        if (attributes.containsKey("quarter")) {
            entity.setQuarter(Long.parseLong(attributes.get("quarter")));
        }
        if (attributes.containsKey("month")) {
            entity.setMonth(Long.parseLong(attributes.get("month")));
        }
        if (attributes.containsKey("week")) {
            entity.setWeek(Long.parseLong(attributes.get("week")));
        }
        if (attributes.containsKey("day")) {
            entity.setDay(Long.parseLong(attributes.get("day")));
        }
        //TODO: need parse for timestamp
        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getDateDAO().delete(id));
        return true;
    }
}
