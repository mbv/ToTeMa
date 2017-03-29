package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.ProductDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Product;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlProductDAO extends AbstractBaseDAO<Product> implements ProductDAO {

    private static final Logger logger = Logger.getLogger(MySqlProductDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `PRODUCT`.`ID`, " +
            " `PRODUCT`.`CODE`, `PRODUCT`.`TYPE_KEY`, `PRODUCT`.`NAME`, `PRODUCT`.`SIZE`, `PRODUCT`.`COLOR`, `PRODUCT`.`STATUS` FROM `PRODUCT` WHERE  `PRODUCT`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `PRODUCT`.`ID`, " +
            " `PRODUCT`.`CODE`, `PRODUCT`.`TYPE_KEY`, `PRODUCT`.`NAME`, `PRODUCT`.`SIZE`, `PRODUCT`.`COLOR`, `PRODUCT`.`STATUS` FROM `PRODUCT` WHERE  " +
            " `PRODUCT`.`STATUS` != -1 ORDER BY `PRODUCT`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `PRODUCT` (" +
            " `PRODUCT`.`CODE`, `PRODUCT`.`TYPE_KEY`, `PRODUCT`.`NAME`, `PRODUCT`.`SIZE`, `PRODUCT`.`COLOR`, `PRODUCT`.`STATUS`)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `PRODUCT` SET `PRODUCT`.`CODE` = ?, " +
            "`PRODUCT`.`TYPE_KEY` = ?," +
            " `PRODUCT`.`NAME` = ?, `PRODUCT`.`SIZE` = ?, " +
            "`PRODUCT`.`COLOR` = ? ,`PRODUCT`.`STATUS` = ? WHERE (`PRODUCT`.`ID` = ?) AND (`PRODUCT`.`STATUS` != -1);";
    private static final String QUERY_DELETE = "UPDATE `PRODUCT` SET `PRODUCT`.`STATUS` = -1 WHERE (`PRODUCT`.`ID` = ?) AND (`PRODUCT`.`STATUS` != -1);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`PRODUCT`.`ID`) FROM `PRODUCT` WHERE (`PRODUCT`.`STATUS` != -1) ;";

    @Override
    public Product insert(Product entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setLong(1, entity.getCode());
            statement.setLong(2, entity.getTypeKey());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getSize());
            statement.setString(5, entity.getColor());
            statement.setLong(6, entity.getStatus());
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
    public Product selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(Product entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setLong(1, entity.getCode());
            statement.setLong(2, entity.getTypeKey());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getSize());
            statement.setString(5, entity.getColor());
            statement.setLong(6, entity.getStatus());
            statement.setLong(7, entity.getId());
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
    public List<Product> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Product> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    Product parseResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong(1));
        product.setCode(resultSet.getLong(2));
        product.setTypeKey(resultSet.getLong(3));
        product.setName(resultSet.getString(4));
        product.setSize(resultSet.getString(5));
        product.setColor(resultSet.getString(6));
        product.setStatus(resultSet.getInt(7));
        return product;
    }
}
