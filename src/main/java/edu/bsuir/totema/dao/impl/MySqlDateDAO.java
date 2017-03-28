package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.DateDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Date;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlDateDAO extends AbstractBaseDAO<Date> implements DateDAO {

    private static final Logger logger = Logger.getLogger(MySqlDateDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `DATE`.`ID`, `DATE`.`USERNAME`, `DATE`.`PASSWORD_HASH`, `DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, " +
            "`DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY` FROM `DATE` WHERE `DATE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `DATE`.`ID`,`DATE`.`USERNAME`, `DATE`.`PASSWORD_HASH`, `DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, " +
            "`DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY`, FROM `DATE`  ORDER BY `DATE`.`TIME_STAMP` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `DATE`(`DATE`.`USERNAME`, `DATE`.`PASSWORD_HASH`,`DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, `DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `DATE` SET `DATE`.`USERNAME`=?, `DATE`.`PASSWORD_HASH`=?, `DATE`.`TIME_STAMP`=?,`DATE`.`YEAR`=?,`DATE`.`QUARTER`=?,`DATE`.`MONTH_NAME`=?,`DATE`.`MONTH_INT`=?,`DATE`.`WEEK`=?,`DATE`.`DAY`=?" +
            " WHERE (`DATE`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `DATE` WHERE (`DATE`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`DATE`.`ID`) FROM `DATE`;";

    @Override
    public Date insert(Date entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setTime(3, entity.getTime());
            statement.setLong(4, entity.getYear());
            statement.setLong(5, entity.getQuarter());
            statement.setString(6, entity.getMonthName());
            statement.setLong(7, entity.getMonth());
            statement.setLong(8, entity.getWeek());
            statement.setLong(9, entity.getDay());

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
    public Date selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(Date entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setTime(3, entity.getTime());
            statement.setLong(4, entity.getYear());
            statement.setLong(5, entity.getQuarter());
            statement.setString(6, entity.getMonthName());
            statement.setLong(7, entity.getMonth());
            statement.setLong(8, entity.getWeek());
            statement.setLong(9, entity.getDay());

            statement.setLong(10, entity.getId());
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
    public List<Date> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Date> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    Date parseResultSet(ResultSet resultSet) throws SQLException {
        Date date = new Date();
        date.setId(resultSet.getLong(1));
        date.setUsername(resultSet.getString(2));
        date.setPasswordHash(resultSet.getString(3));
        date.setTime(resultSet.getTime(4));
        date.setYear(resultSet.getLong(5));
        date.setQuarter(resultSet.getLong(6));
        date.setMonthName(resultSet.getString(7));
        date.setMonth(resultSet.getLong(8));
        date.setWeek(resultSet.getLong(9));
        date.setDay(resultSet.getLong(10));
        return date;
    }
}
