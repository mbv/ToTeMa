package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.OrderDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Order;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlOrderDAO extends AbstractBaseDAO<Order> implements OrderDAO {

    private static final Logger logger = Logger.getLogger(MySqlOrderDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `ORDER`.`ID`,  " +
            "`ORDER`.`DATE_KEY`, `ORDER`.`EMPLOYEE_KEY`, `ORDER`.`OFFICE_KEY`, `ORDER`.`QUANTITY`, `ORDER`.`PRICE`, ``ORDER`.COST`, `ORDER`.`GROSS_MARGIN` FROM `ORDER` WHERE  `ORDER`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `ORDER`.`ID`, " +
            "`ORDER`.`DATE_KEY`, `ORDER`.`EMPLOYEE_KEY`, `ORDER`.`OFFICE_KEY`, `ORDER`.`QUANTITY`, `ORDER`.`PRICE`, ``ORDER`.COST`, `ORDER`.`GROSS_MARGIN` FROM `ORDER` " +
            " ORDER BY `ORDER`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `ORDER` (`ORDER`.`DATE_KEY`," +
            " `ORDER`.`EMPLOYEE_KEY`, `ORDER`.`OFFICE_KEY`, `ORDER`.`QUANTITY`, `ORDER`.`PRICE`, `ORDER`.`COST`, `ORDER`.`GROSS_MARGIN`)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `ORDER` SET `ORDER`.`DATE_KEY` = ?, `ORDER`.`EMPLOYEE_KEY` = ?," +
            " `ORDER`.`OFFICE_KEY` = ?, `ORDER`.`QUANTITY` = ?, `ORDER`.`PRICE` = ?, `ORDER`.`COST` = ?, `ORDER`.`GROSS_MARGIN` = ?" +
            " WHERE (`ORDER`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `ORDER` WHERE (`ORDER`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`ORDER`.`ID`) FROM `ORDER` ;";

    @Override
    public Order insert(Order entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setLong(1, entity.getDateKey());
            statement.setLong(2, entity.getEmployeeKey());
            statement.setLong(3, entity.getOfficeKey());
            statement.setLong(4, entity.getQuantity());
            statement.setLong(5, entity.getPrice());
            statement.setLong(6, entity.getCost());
            statement.setLong(7, entity.getGrossMargin());
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
    public Order selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(Order entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setLong(1, entity.getDateKey());
            statement.setLong(2, entity.getEmployeeKey());
            statement.setLong(3, entity.getOfficeKey());
            statement.setLong(4, entity.getQuantity());
            statement.setLong(5, entity.getPrice());
            statement.setLong(6, entity.getCost());
            statement.setLong(7, entity.getGrossMargin());
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
    public List<Order> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Order> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    Order parseResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(1));
        order.setDateKey(resultSet.getLong(2));
        order.setEmployeeKey(resultSet.getLong(3));
        order.setOfficeKey(resultSet.getLong(4));
        order.setQuantity(resultSet.getLong(5));
        order.setPrice(resultSet.getLong(6));
        order.setCost(resultSet.getLong(7));
        order.setGrossMargin(resultSet.getLong(8));
        return order;
    }
}
