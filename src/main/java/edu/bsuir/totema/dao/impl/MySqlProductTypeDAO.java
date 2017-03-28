package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.ProductTypeDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.ProductType;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlProductTypeDAO extends AbstractBaseDAO<ProductType> implements ProductTypeDAO {

    private static final Logger logger = Logger.getLogger(MySqlProductTypeDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `PRODUCT_TYPE`.`ID`, `PRODUCT_TYPE`.`USERNAME`, `PRODUCT_TYPE`.`PASSWORD_HASH`, " +
            " `PRODUCT_TYPE`.`GENDER`,  `PRODUCT_TYPE`.`AGE`,  `PRODUCT_TYPE`.`SEASON`,  `PRODUCT_TYPE`.`TYPE`, `PRODUCT_TYPE`.`STATUS` FROM `PRODUCT_TYPE` WHERE  `PRODUCT_TYPE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `PRODUCT_TYPE`.`ID`, `PRODUCT_TYPE`.`USERNAME`, `PRODUCT_TYPE`.`PASSWORD_HASH`, " +
            " `PRODUCT_TYPE`.`GENDER`,  `PRODUCT_TYPE`.`AGE`,  `PRODUCT_TYPE`.`SEASON`,  `PRODUCT_TYPE`.`TYPE`, `PRODUCT_TYPE`.`STATUS` FROM `PRODUCT_TYPE` WHERE " +
            " `PRODUCT_TYPE`.`STATUS` != -1 ORDER BY `PRODUCT_TYPE`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `PRODUCT_TYPE` (`PRODUCT_TYPE`.`USERNAME`, `PRODUCT_TYPE`.`PASSWORD_HASH`, " +
            " `PRODUCT_TYPE`.`GENDER`, `PRODUCT_TYPE`.`AGE`, `PRODUCT_TYPE`.`SEASON`, `PRODUCT_TYPE`.`TYPE`, `PRODUCT_TYPE`.`STATUS`)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `PRODUCT_TYPE` SET `PRODUCT_TYPE`.`USERNAME` = ?, `PRODUCT_TYPE`.`PASSWORD_HASH` = ?, `PRODUCT_TYPE`.`GENDER` = ?, " +
            "`PRODUCT_TYPE`.`AGE` = ?," +
            " `PRODUCT_TYPE`.`SEASON` = ?, `PRODUCT_TYPE`.`TYPE` = ?, " +
            "`PRODUCT_TYPE`.`STATUS` = ? WHERE (`PRODUCT_TYPE`.`ID` = ?) AND (`PRODUCT_TYPE`.`STATUS` != -1);";
    private static final String QUERY_DELETE = "UPDATE `PRODUCT_TYPE` SET `PRODUCT_TYPE`.`STATUS` = -1 WHERE (`PRODUCT_TYPE`.`ID` = ?) AND (`PRODUCT_TYPE`.`STATUS` != -1);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`PRODUCT_TYPE`.`ID`) FROM `PRODUCT_TYPE` WHERE (`PRODUCT_TYPE`.`STATUS` != -1) ;";

    @Override
    public ProductType insert(ProductType entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setString(3, entity.getGender());
            statement.setString(4, entity.getAge());
            statement.setString(5, entity.getSeason());
            statement.setString(6, entity.getType());
            statement.setInt(7, entity.getStatus());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    long insertedId = keys.getLong(1);
                    return executeSelectById(connection, QUERY_SELECT_BY_ID, insertedId);
                } else {
                    throw new DAOException("Generated keys set is empty");
                }
            }
        }));
    }

    @Override
    public ProductType selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(ProductType entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setString(3, entity.getGender());
            statement.setString(4, entity.getAge());
            statement.setString(5, entity.getSeason());
            statement.setString(6, entity.getType());
            statement.setInt(7, entity.getStatus());
            statement.setLong(8, entity.getId());
            statement.executeUpdate();

            /*Employee reselectedEntity = executeSelectById(connection, QUERY_SELECT_BY_ID, updatedEntity.getId());
            updatedEntity.setModifiedAt(reselectedEntity.getModifiedAt());*/
        }));
    }

    @Override
    public void delete(long id) throws DAOException {
        executeDelete(QUERY_DELETE, id);
    }

    @Override
    public List<ProductType> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<ProductType> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
        return null;
    }

    @Override
    public int selectCount() throws DAOException {
        return tryCallJDBC(QUERY_SELECT_COUNT, statement -> {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("Cannot select question count, empty result set.");
                    throw new DAOException("Cannot select question count");
                }
            }
        });
    }

    @Override
    public int selectLikeCount(String likeQuery) throws DAOException {
        return 0;
    }

    @Override
    ProductType parseResultSet(ResultSet resultSet) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(resultSet.getLong(1));
        productType.setUsername(resultSet.getString(2));
        productType.setPasswordHash(resultSet.getString(3));
        productType.setGender(resultSet.getString(4));
        productType.setAge(resultSet.getString(5));
        productType.setSeason(resultSet.getString(6));
        productType.setType(resultSet.getString(7));
        productType.setStatus(resultSet.getInt(8));
        return productType;
    }
}
