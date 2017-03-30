package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.ProductTypeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.ProductType;
import edu.bsuir.totema.service.ProductTypeService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDAO getProductTypeDAO() {
        return DAOFactory.getDAOFactory().getProductTypeDAO();
    }

    @Override
    public List<ProductType> getAll() throws ServiceException {
        return tryCallDAO(() -> getProductTypeDAO().selectWithLimit(0, 10));
    }

    @Override
    public ProductType getById(long id) throws ServiceException {
        return tryCallDAO(() -> getProductTypeDAO().selectById(id));
    }

    @Override
    public ResponseErrorInfo validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateStringOnEmpty(attributes, "gender", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "age", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "season", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "type", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public ProductType add(HashMap<String, String> attributes) throws ServiceException {
        ProductType office = entitySetAttribute(new ProductType(), attributes);
        office.setStatus(ProductType.STATUS_ACTIVE);
        return tryCallDAO(() -> getProductTypeDAO().insert(office));
    }

    @Override
    public ProductType update(long id, HashMap<String, String> attributes) throws ServiceException {
        ProductType country = getById(id);
        if (country != null) {
            entitySetAttribute(country, attributes);
            tryCallDAO(() -> getProductTypeDAO().update(country));
        }
        return country;
    }

    @Override
    public ProductType entitySetAttribute(ProductType entity, HashMap<String, String> attributes) throws ServiceException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (attributes.containsKey("gender")) {
            entity.setGender(attributes.get("gender"));
        }
        if (attributes.containsKey("age")) {
            entity.setAge(attributes.get("age"));
        }
        if (attributes.containsKey("season")) {
            entity.setSeason(attributes.get("season"));
        }
        if(attributes.containsKey("type")) {
            entity.setType(attributes.get("type"));
        }

        if (attributes.containsKey("status")) {
            entity.setStatus(Integer.parseInt(attributes.get("status")));
        }

        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getProductTypeDAO().delete(id));
        return true;
    }
}
