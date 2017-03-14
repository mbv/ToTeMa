package edu.bsuir.totema.model;

import edu.bsuir.totema.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Model {
    private static final String SELECT_ALL_EMPLOYEES_QUERY
            = "SELECT `ID`, `NAME`, `TITLE`, `YEAR_SALARY` FROM `EMPLOYEE`;";
    private static Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    public List<Employee> selectAllUsers() throws ModelException {
        try(Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EMPLOYEES_QUERY);
            ResultSet resultSet = statement.executeQuery()) {
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setName(resultSet.getString(2));
                employee.setTitle(resultSet.getString(3));
                employee.setYearSalary(resultSet.getLong(4));

                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }

    private Connection createConnection() throws ModelException {
        try {
            Class.forName(DatabaseManager.getProperty(DatabaseManager.DRIVER_NAME));
            Properties properties = getDatabaseProperties();
            String url = DatabaseManager.getProperty(DatabaseManager.URL);
            return DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ModelException(e);
        }
    }

    private Properties getDatabaseProperties() {
        Properties properties = new Properties();
        properties.put(DatabaseManager.USER, DatabaseManager.getProperty(DatabaseManager.USER));
        properties.put(DatabaseManager.PASSWORD, DatabaseManager.getProperty(DatabaseManager.PASSWORD));
        properties.put(DatabaseManager.AUTORECONNECT, DatabaseManager.getProperty(DatabaseManager.AUTORECONNECT));
        properties.put(DatabaseManager.ENCODING, DatabaseManager.getProperty(DatabaseManager.ENCODING));
        properties.put(DatabaseManager.USE_UNICODE, DatabaseManager.getProperty(DatabaseManager.USE_UNICODE));
        return properties;
    }
}
