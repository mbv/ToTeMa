package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.OfficeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Office;
import edu.bsuir.totema.service.OfficeService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class OfficeServiceImpl implements OfficeService {

    private OfficeDAO getOfficeDAO() {
        return DAOFactory.getDAOFactory().getOfficeDAO();
    }

    @Override
    public List<Office> getAll() throws ServiceException {
        return tryCallDAO(() -> getOfficeDAO().selectWithLimit(0, 10));
    }

    @Override
    public Office getById(long id) throws ServiceException {
        return tryCallDAO(() -> getOfficeDAO().selectById(id));
    }

    @Override
    public ResponseErrorInfo validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateStringOnEmpty(attributes, "city", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "address", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "fax", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "phone", errors);
        ValidationUtil.validateLong(attributes, "countryKey", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "postalCode", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public Office add(HashMap<String, String> attributes) throws ServiceException {
        Office office = entitySetAttribute(new Office(), attributes);
        office.setStatus(Office.STATUS_ACTIVE);
        return tryCallDAO(() -> getOfficeDAO().insert(office));
    }

    @Override
    public Office update(long id, HashMap<String, String> attributes) throws ServiceException {
        Office country = getById(id);
        if (country != null) {
            entitySetAttribute(country, attributes);
            tryCallDAO(() -> getOfficeDAO().update(country));
        }
        return country;
    }

    @Override
    public Office entitySetAttribute(Office entity, HashMap<String, String> attributes) throws ServiceException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (attributes.containsKey("city")) {
            entity.setCity(attributes.get("city"));
        }
        if (attributes.containsKey("address")) {
            entity.setAddress(attributes.get("address"));
        }
        if (attributes.containsKey("phone")) {
            entity.setPhone(attributes.get("phone"));
        }
        if(attributes.containsKey("fax")) {
            entity.setFax(attributes.get("fax"));
        }
        if(attributes.containsKey("postalCode")) {
            entity.setPostalCode(attributes.get("postalCode"));
        }
        if (attributes.containsKey("countryKey")) {
            entity.setCountryKey(Long.parseLong(attributes.get("countryKey")));
        }
        if (attributes.containsKey("status")) {
            entity.setStatus(Integer.parseInt(attributes.get("status")));
        }

        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getOfficeDAO().delete(id));
        return true;
    }
}
