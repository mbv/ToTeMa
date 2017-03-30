package edu.bsuir.totema.service.impl;

import edu.bsuir.totema.dao.OrderDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.entity.Order;
import edu.bsuir.totema.service.OrderService;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.util.ValidationUtil;

import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.util.caller.DAOCaller.tryCallDAO;

public class OrderServiceImpl implements OrderService {

    private OrderDAO getOrderDAO() {
        return DAOFactory.getDAOFactory().getOrderDAO();
    }

    @Override
    public List<Order> getAll() throws ServiceException {
        return tryCallDAO(() -> getOrderDAO().selectWithLimit(0, 10));
    }

    @Override
    public Order getById(long id) throws ServiceException {
        return tryCallDAO(() -> getOrderDAO().selectById(id));
    }

    @Override
    public ResponseErrorInfo validate(HashMap<String, String> attributes) {
        HashMap<String, String> errors = new HashMap<>();

        ValidationUtil.validateLong(attributes, "officeKey", errors);
        ValidationUtil.validateLong(attributes, "employeeKey", errors);
        ValidationUtil.validateLong(attributes, "dateKey", errors);
        ValidationUtil.validateLong(attributes, "quantity", errors);
        ValidationUtil.validateLong(attributes, "cost", errors);
        ValidationUtil.validateLong(attributes, "price", errors);
        ValidationUtil.validateLong(attributes, "grossMargin", errors);


        return ValidationUtil.getValidationResult(errors);
    }



    @Override
    public Order add(HashMap<String, String> attributes) throws ServiceException {
        Order date = entitySetAttribute(new Order(), attributes);
        return tryCallDAO(() -> getOrderDAO().insert(date));
    }

    @Override
    public Order update(long id, HashMap<String, String> attributes) throws ServiceException {
        Order date = getById(id);
        if (date != null) {
            entitySetAttribute(date, attributes);
            tryCallDAO(() -> getOrderDAO().update(date));
        }
        return date;
    }

    @Override
    public Order entitySetAttribute(Order entity, HashMap<String, String> attributes) throws ServiceException {

        if (attributes.containsKey("officeKey")) {
            entity.setOfficeKey(Long.parseLong(attributes.get("officeKey") ));
        }
        if (attributes.containsKey("employeeKey")) {
            entity.setEmployeeKey(Long.parseLong(attributes.get("employeeKey")));
        }
        if (attributes.containsKey("dateKey")) {
            entity.setDateKey(Long.parseLong(attributes.get("dateKey")));
        }
        if (attributes.containsKey("quantity")) {
            entity.setQuantity(Long.parseLong(attributes.get("quantity")));
        }
        if (attributes.containsKey("cost")) {
            entity.setCost(Long.parseLong(attributes.get("cost")));
        }
        if (attributes.containsKey("price")) {
            entity.setPrice(Long.parseLong(attributes.get("price")));
        }
        if (attributes.containsKey("grossMargin")) {
            entity.setGrossMargin(Long.parseLong(attributes.get("grossMargin")));
        }

        return entity;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        tryCallDAO(() -> getOrderDAO().delete(id));
        return true;
    }
}
