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
    private static final String QUERY_SELECT_BY_ID = "SELECT `DATE`.`ID`, `DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, " +
            "`DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY` FROM `DATE` WHERE `DATE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `DATE`.`ID`, `DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, " +
            "`DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY`, FROM `DATE`  ORDER BY `DATE`.`TIME_STAMP` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `DATE`(`DATE`.`TIME_STAMP`, `DATE`.`YEAR`, `DATE`.`QUARTER`, `DATE`.`MONTH_NAME`, `DATE`.`MONTH_INT`, `DATE`.`WEEK`, `DATE`.`DAY`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `DATE` SET `DATE`.`TIME_STAMP`=?,`DATE`.`YEAR`=?,`DATE`.`QUARTER`=?,`DATE`.`MONTH_NAME`=?,`DATE`.`MONTH_INT`=?,`DATE`.`WEEK`=?,`DATE`.`DAY`=?" +
            " WHERE (`DATE`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `DATE` WHERE (`DATE`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`DATE`.`ID`) FROM `DATE`;";

    @Override
    public Date insert(Date entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setTime(1, entity.getTime());
            statement.setLong(2, entity.getYear());
            statement.setLong(3, entity.getQuarter());
            statement.setString(4, entity.getMonthName());
            statement.setLong(5, entity.getMonth());
            statement.setLong(6, entity.getWeek());
            statement.setLong(7, entity.getDay());

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
            statement.setTime(1, entity.getTime());
            statement.setLong(2, entity.getYear());
            statement.setLong(3, entity.getQuarter());
            statement.setString(4, entity.getMonthName());
            statement.setLong(5, entity.getMonth());
            statement.setLong(6, entity.getWeek());
            statement.setLong(7, entity.getDay());

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
        date.setTime(resultSet.getTime(2));
        date.setYear(resultSet.getLong(3));
        date.setQuarter(resultSet.getLong(4));
        date.setMonthName(resultSet.getString(5));
        date.setMonth(resultSet.getLong(6));
        date.setWeek(resultSet.getLong(7));
        date.setDay(resultSet.getLong(8));
        return date;
    }
}
