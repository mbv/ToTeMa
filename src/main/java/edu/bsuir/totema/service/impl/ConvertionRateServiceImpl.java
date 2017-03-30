package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.ConvertionRateDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.ConvertionRate;
import edu.bsuir.totema.service.ConvertionRateService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class ConvertionRateServiceImpl implements ConvertionRateService {

    private ConvertionRateDAO getConvertionRateDAO() {
        return DAOFactory.getDAOFactory().getConvertionRateDAO();
    }

    @Override
    public List<ConvertionRate> getAll() throws ServiceException {
        return tryCallDAO(() -> getConvertionRateDAO().selectWithLimit(0, 10));
    }

    @Override
    public ConvertionRate getById(long id) throws ServiceException {
        return tryCallDAO(() -> getConvertionRateDAO().selectById(id));
    }

    @Override
    public ResponseErrorInfo validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateLong(attributes, "countryKey", errors);
        ValidationUtil.validateLong(attributes, "periodKey", errors);
        ValidationUtil.validateLong(attributes, "convertion", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public ConvertionRate add(HashMap<String, String> attributes) throws ServiceException {
        ConvertionRate convertionRate = entitySetAttribute(new ConvertionRate(), attributes);

        return tryCallDAO(() -> getConvertionRateDAO().insert(convertionRate));
    }

    @Override
    public ConvertionRate update(long id, HashMap<String, String> attributes) throws ServiceException {
        ConvertionRate convertionRate = getById(id);
        if (convertionRate != null) {
            entitySetAttribute(convertionRate, attributes);
            tryCallDAO(() -> getConvertionRateDAO().update(convertionRate));
        }
        return convertionRate;
    }

    @Override
    public ConvertionRate entitySetAttribute(ConvertionRate entity, HashMap<String, String> attributes) throws ServiceException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (attributes.containsKey("countryKey")) {
            entity.setCountryKey(Long.parseLong(attributes.get("countryKey")));
        }
        if (attributes.containsKey("periodKey")) {
            entity.setPeriodKey(Long.parseLong(attributes.get("periodKey")));
        }
        if (attributes.containsKey("convertion")) {
            entity.setConvertion(Long.parseLong(attributes.get("convertion")));
        }

        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getConvertionRateDAO().delete(id));
        return true;
    }
}
