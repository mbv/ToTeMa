package edu.bsuir.totema.dao.factory.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.factory.DAOFactory;
import edu.bsuir.totema.dao.impl.MySqlEmployeeDAO;

/**
 * Concrete DAO factory for MySQL database
 */
public class MySqlDAOFactory extends DAOFactory {

    private static final MySqlDAOFactory instance = new MySqlDAOFactory();
    private EmployeeDAO employeeDAO;


    private MySqlDAOFactory() {
        employeeDAO = new MySqlEmployeeDAO();
    }

    public static MySqlDAOFactory getInstance() {
        return instance;
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }
}
