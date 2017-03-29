package edu.bsuir.totema.dao.factory.impl;

import edu.bsuir.totema.dao.*;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.dao.impl.*;

/**
 * Concrete DAO factory for MySQL database
 */
public class MySqlDAOFactory extends DAOFactory {

    private static final MySqlDAOFactory instance = new MySqlDAOFactory();
    private EmployeeDAO employeeDAO;
    private ConvertionRateDAO convertionRateDAO;
    private CountryDAO countryDAO;
    private DateDAO dateDAO;
    private OfficeDAO officeDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private ProductListDAO productListDAO;
    private ProductTypeDAO productTypeDAO;


    private MySqlDAOFactory() {
        employeeDAO = new MySqlEmployeeDAO();
        convertionRateDAO = new MySqlConvertionRateDAO();
        countryDAO = new MySqlCountryDAO();
        dateDAO = new MySqlDateDAO();
        officeDAO = new MySqlOfficeDAO();
        orderDAO = new MySqlOrderDAO();
        productDAO = new MySqlProductDAO();
        productListDAO = new MySqlProductListDAO();
        productTypeDAO = new MySqlProductTypeDAO();
    }

    public static MySqlDAOFactory getInstance() {
        return instance;
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    @Override
    public ConvertionRateDAO getConvertionRateDAO() {
        return convertionRateDAO;
    }

    @Override
    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    @Override
    public DateDAO getDateDAO() {
        return dateDAO;
    }

    @Override
    public OfficeDAO getOfficeDAO() {
        return officeDAO;
    }

    @Override
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        return productDAO;
    }

    @Override
    public ProductListDAO getProductListDAO() {
        return productListDAO;
    }

    @Override
    public ProductTypeDAO getProductTypeDAO() {
        return productTypeDAO;
    }
}
