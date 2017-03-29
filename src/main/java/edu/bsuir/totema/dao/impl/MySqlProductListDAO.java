package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.ProductListDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.ProductList;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlProductListDAO extends AbstractBaseDAO<ProductList> implements ProductListDAO {

    private static final Logger logger = Logger.getLogger(MySqlProductListDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `PRODUCT_LIST`.`ID`, " +
            "  `PRODUCT_LIST`.`ORDER_KEY`, `PRODUCT_LIST`.`PRODUCT_KEY`, `PRODUCT_LIST`.`QUANTITY`, `PRODUCT_LIST`.`UNIT_COST`, `PRODUCT_LIST`.`UNIT_PRICE`, `PRODUCT_LIST`.`GROSS_MARGIN` WHERE  `PRODUCT_LIST`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `PRODUCT_LIST`.`ID`,  " +
            "  `PRODUCT_LIST`.`ORDER_KEY`, `PRODUCT_LIST`.`PRODUCT_KEY`, `PRODUCT_LIST`.`QUANTITY`, `PRODUCT_LIST`.`UNIT_COST`, `PRODUCT_LIST`.`UNIT_PRICE`, `PRODUCT_LIST`.`GROSS_MARGIN`" +
            "  ORDER BY `PRODUCT_LIST`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `PRODUCT_LIST` (" +
            "  `PRODUCT_LIST`.`ORDER_KEY`, `PRODUCT_LIST`.`PRODUCT_KEY`, `PRODUCT_LIST`.`QUANTITY`, `PRODUCT_LIST`.`UNIT_COST`, `PRODUCT_LIST`.`UNIT_PRICE`, `PRODUCT_LIST`.`GROSS_MARGIN`)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `PRODUCT_LIST` SET `PRODUCT_LIST`.`ORDER_KEY` = ?, " +
            "`PRODUCT_LIST`.`PRODUCT_KEY` = ?," +
            " `PRODUCT_LIST`.`QUANTITY` = ?, `PRODUCT_LIST`.`UNIT_COST` = ?, " +
            "`PRODUCT_LIST`.`UNIT_PRICE` = ?, `PRODUCT_LIST`.`GROSS_MARGIN`=?  WHERE (`PRODUCT_LIST`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELERE FROM `PRODUCT_LIST`  WHERE (`PRODUCT_LIST`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`PRODUCT_LIST`.`ID`) FROM `PRODUCT_LIST`;";

    @Override
    public ProductList insert(ProductList entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setLong(1, entity.getOrderKey());
            statement.setLong(2, entity.getProductKey());
            statement.setLong(3, entity.getQuantity());
            statement.setLong(4, entity.getUnitCost());
            statement.setLong(5, entity.getUnitPrice());
            statement.setLong(6, entity.getGrossMargin());
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
    public ProductList selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(ProductList entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setLong(1, entity.getOrderKey());
            statement.setLong(2, entity.getProductKey());
            statement.setLong(3, entity.getQuantity());
            statement.setLong(4, entity.getUnitCost());
            statement.setLong(5, entity.getUnitPrice());
            statement.setLong(6, entity.getGrossMargin());
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
    public List<ProductList> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<ProductList> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    ProductList parseResultSet(ResultSet resultSet) throws SQLException {
        ProductList productList = new ProductList();
        productList.setId(resultSet.getLong(1));
        productList.setOrderKey(resultSet.getLong(2));
        productList.setProductKey(resultSet.getLong(3));
        productList.setQuantity(resultSet.getLong(4));
        productList.setUnitCost(resultSet.getLong(5));
        productList.setUnitPrice(resultSet.getLong(6));
        productList.setGrossMargin(resultSet.getLong(7));
        return productList;
    }
}
