package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.ProductDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Product;
import edu.bsuir.totema.service.ProductService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.util.ValidationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class ProductServiceImpl implements ProductService {

    private ProductDAO getProductDAO() {
        return DAOFactory.getDAOFactory().getProductDAO();
    }

    @Override
    public List<Product> getAll() throws ServiceException {
        return tryCallDAO(() -> getProductDAO().selectWithLimit(0, 10));
    }

    @Override
    public Product getById(long id) throws ServiceException {
        return tryCallDAO(() -> getProductDAO().selectById(id));
    }

    @Override
    public ResponseErrorInfo validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateStringOnEmpty(attributes, "name", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "size", errors);
        ValidationUtil.validateLong(attributes, "typeKey", errors);
        ValidationUtil.validateStringOnEmpty(attributes, "color", errors);
        ValidationUtil.validateLong(attributes, "code", errors);
        //ValidationUtil.validateInt(attributes, "status", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public Product add(HashMap<String, String> attributes) throws ServiceException {
        Product product = entitySetAttribute(new Product(), attributes);
        product.setStatus(Product.STATUS_ACTIVE);
        return tryCallDAO(() -> getProductDAO().insert(product));
    }

    @Override
    public Product update(long id, HashMap<String, String> attributes) throws ServiceException {
        Product product = getById(id);
        if (product != null) {
            entitySetAttribute(product, attributes);
            tryCallDAO(() -> getProductDAO().update(product));
        }
        return product;
    }

    @Override
    public Product entitySetAttribute(Product entity, HashMap<String, String> attributes) throws ServiceException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (attributes.containsKey("name")) {
            entity.setName(attributes.get("name"));
        }
        if (attributes.containsKey("typeKey")) {
            entity.setTypeKey(Long.parseLong(attributes.get("typeKey")));
        }
        if (attributes.containsKey("size")) {
            entity.setSize(attributes.get("size"));
        }
        if (attributes.containsKey("color")) {
            entity.setColor(attributes.get("color"));
        }

        if (attributes.containsKey("code")) {
            entity.setCode(Long.parseLong(attributes.get("code")));
        }
        if (attributes.containsKey("status")) {
            entity.setStatus(Integer.parseInt(attributes.get("status")));
        }
        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getProductDAO().delete(id));
        return true;
    }
}
