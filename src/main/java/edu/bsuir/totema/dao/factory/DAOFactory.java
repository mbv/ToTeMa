package edu.bsuir.totema.dao.factory;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.exception.DAOFactoryNotFoundException;
import edu.bsuir.totema.dao.factory.impl.MySqlDAOFactory;
import edu.bsuir.totema.dao.manager.DatabaseManager;
import org.apache.log4j.Logger;

/**
 * Provides basic access for DAO
 */
public abstract class DAOFactory {

    /**
     * DAO type for MySQL / MariaDB databases
     */
    private static final String MY_SQL = "MySQL";

    private static final String DATABASE_TYPE = DatabaseManager.getProperty(DatabaseManager.DATABASE_TYPE);
    private static final Logger logger = Logger.getLogger(DAOFactory.class);

    /**
     * Returns instance of concrete factory with for the specified type of database
     *
     * @return concrete factory instance
     */
    public static DAOFactory getDAOFactory() {
        DAOFactory result;
        switch (DATABASE_TYPE) {
            case MY_SQL:
                result = MySqlDAOFactory.getInstance();
                break;
            default:
                logger.fatal("DAO Factory with for type " + DATABASE_TYPE + " not found.");
                throw new DAOFactoryNotFoundException();
        }
        return result;
    }

    /**
     * Returns concrete implementation of EmployeeDAO for appropriate database
     *
     * @return concrete {@link EmployeeDAO}
     */
    public abstract EmployeeDAO getEmployeeDAO();


}