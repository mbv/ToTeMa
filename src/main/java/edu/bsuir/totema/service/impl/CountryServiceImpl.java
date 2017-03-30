package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.CountryDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Country;
import edu.bsuir.totema.service.CountryService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class CountryServiceImpl implements CountryService {

    private CountryDAO getCountryDAO() {
        return DAOFactory.getDAOFactory().getCountryDAO();
    }

    @Override
    public List<Country> getAll() throws ServiceException {
        return tryCallDAO(() -> getCountryDAO().selectWithLimit(0, 10));
    }

    @Override
    public Country getById(long id) throws ServiceException {
        return tryCallDAO(() -> getCountryDAO().selectById(id));
    }

    @Override
    public ValidationResult validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateStringOnEmpty(attributes, "name", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "iso3", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "iso2", errors);
        ValidationUtil.validateLong(attributes, "iso3digit", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "currency", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public Country add(HashMap<String, String> attributes) throws ServiceException {
        Country country = entitySetAttribute(new Country(), attributes);
        return tryCallDAO(() -> getCountryDAO().insert(country));
    }

    @Override
    public Country update(long id, HashMap<String, String> attributes) throws ServiceException {
        Country country = getById(id);
        if (country != null) {
            entitySetAttribute(country, attributes);
            tryCallDAO(() -> getCountryDAO().update(country));
        }
        return country;
    }

    @Override
    public Country entitySetAttribute(Country entity, HashMap<String, String> attributes) throws ServiceException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (attributes.containsKey("name")) {
            entity.setName(attributes.get("name"));
        }
        if (attributes.containsKey("iso2")) {
            entity.setIso2(attributes.get("iso2"));
        }
        if (attributes.containsKey("iso3")) {
            entity.setIso3(attributes.get("iso3"));
        }
        if(attributes.containsKey("currency")) {
            entity.setCurrency(attributes.get("currency"));
        }
        if (attributes.containsKey("iso3digit")) {
            entity.setIso3digit(Long.parseLong(attributes.get("iso3digit")));
        }

        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getCountryDAO().delete(id));
        return true;
    }
}
