package edu.bsuir.totema.dao.pool;

import edu.bsuir.totema.dao.manager.DatabaseManager;
import edu.bsuir.totema.dao.pool.exception.PoolException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database connection pool of web application
 */
public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private BasicDataSource basicDataSource;


    private static final ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DatabaseManager.getProperty(DatabaseManager.DRIVER_NAME));
        basicDataSource.setUsername(DatabaseManager.getProperty(DatabaseManager.USER));
        basicDataSource.setPassword(DatabaseManager.getProperty(DatabaseManager.PASSWORD));
        basicDataSource.setUrl(DatabaseManager.getProperty(DatabaseManager.URL));

        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxOpenPreparedStatements(180);

        basicDataSource.addConnectionProperty(DatabaseManager.AUTORECONNECT, DatabaseManager.getProperty(DatabaseManager.AUTORECONNECT));
        basicDataSource.addConnectionProperty(DatabaseManager.ENCODING, DatabaseManager.getProperty(DatabaseManager.ENCODING));
        basicDataSource.addConnectionProperty(DatabaseManager.USE_UNICODE, DatabaseManager.getProperty(DatabaseManager.USE_UNICODE));

        logger.info("Connection pool created successfully");
    }

    /**
     * Provides instance of connection pool
     *
     * @return instance of connection pool
     */
    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws PoolException {
        try {
            return this.basicDataSource.getConnection();
        } catch (SQLException e) {
            logger.warn("SQLException when get connection from pool.");
            throw new PoolException(e);
        }
    }

}
