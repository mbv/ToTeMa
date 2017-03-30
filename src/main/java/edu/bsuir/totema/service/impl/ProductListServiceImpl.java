package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.ProductListDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.ProductList;
import edu.bsuir.totema.service.ProductListService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.util.ValidationUtil;

import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class ProductListServiceImpl implements ProductListService {

    private ProductListDAO getProductListDAO() {
        return DAOFactory.getDAOFactory().getProductListDAO();
    }

    @Override
    public List<ProductList> getAll() throws ServiceException {
        return tryCallDAO(() -> getProductListDAO().selectWithLimit(0, 10));
    }

    @Override
    public ProductList getById(long id) throws ServiceException {
        return tryCallDAO(() -> getProductListDAO().selectById(id));
    }

    @Override
    public ValidationResult validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();


        ValidationUtil.validateLong(attributes, "productKey", errors);
        ValidationUtil.validateLong(attributes, "orderKey", errors);
        ValidationUtil.validateLong(attributes, "quantity", errors);
        ValidationUtil.validateLong(attributes, "unitCost", errors);
        ValidationUtil.validateLong(attributes, "unitPrice", errors);
        ValidationUtil.validateLong(attributes, "grossMargin", errors);

        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public ProductList add(HashMap<String, String> attributes) throws ServiceException {
        ProductList date = entitySetAttribute(new ProductList(), attributes);
        return tryCallDAO(() -> getProductListDAO().insert(date));
    }

    @Override
    public ProductList update(long id, HashMap<String, String> attributes) throws ServiceException {
        ProductList date = getById(id);
        if (date != null) {
            entitySetAttribute(date, attributes);
            tryCallDAO(() -> getProductListDAO().update(date));
        }
        return date;
    }

    @Override
    public ProductList entitySetAttribute(ProductList entity, HashMap<String, String> attributes) throws ServiceException {

        if (attributes.containsKey("productKey")) {
            entity.setProductKey(Long.parseLong(attributes.get("productKey") ));
        }
        if (attributes.containsKey("orderKey")) {
            entity.setOrderKey(Long.parseLong(attributes.get("orderKey")));
        }
        if (attributes.containsKey("quantity")) {
            entity.setQuantity(Long.parseLong(attributes.get("quantity")));
        }
        if (attributes.containsKey("unitCost")) {
            entity.setUnitCost(Long.parseLong(attributes.get("unitCost")));
        }
        if (attributes.containsKey("unitPrice")) {
            entity.setUnitPrice(Long.parseLong(attributes.get("unitPrice")));
        }
        if (attributes.containsKey("grossMargin")) {
            entity.setGrossMargin(Long.parseLong(attributes.get("grossMargin")));
        }
        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getProductListDAO().delete(id));
        return true;
    }
}
