package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.ConvertionRateDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.ConvertionRate;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlConvertionRateDAO extends AbstractBaseDAO<ConvertionRate> implements ConvertionRateDAO {

    private static final Logger logger = Logger.getLogger(MySqlConvertionRateDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `CONVERSION_RATE`.`ID`, `CONVERSION_RATE`.`USERNAME`, `CONVERSION_RATE`.`PASSWORD_HASH`, " +
            "`CONVERSION_RATE`.`COUNTRY_KEY`, `CONVERSION_RATE`.`PERIOD_KEY`, `CONVERSION_RATE`.`CONVERSION_TO_LOCAL` FROM `CONVERSION_RATE` WHERE  `CONVERSION_RATE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `CONVERSION_RATE`.`ID`, `CONVERSION_RATE`.`USERNAME`, `CONVERSION_RATE`.`PASSWORD_HASH`, " +
            "`CONVERSION_RATE`.`COUNTRY_KEY`, `CONVERSION_RATE`.`PERIOD_KEY`, `CONVERSION_RATE`.`CONVERSION_TO_LOCAL` FROM `CONVERSION_RATE` WHERE " +
            " ORDER BY `CONVERSION_RATE`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `CONVERSION_RATE` (`CONVERSION_RATE`.`USERNAME`, `CONVERSION_RATE`.`PASSWORD_HASH`, `CONVERSION_RATE`.`COUNTRY_KEY`, " +
            "`CONVERSION_RATE`.`PERIOD_KEY`, `CONVERSION_RATE`.`CONVERSION_TO_LOCAL`)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `CONVERSION_RATE` SET `CONVERSION_RATE`.`USERNAME` = ?, `CONVERSION_RATE`.`PASSWORD_HASH` = ?, `CONVERSION_RATE`.`COUNTRY_KEY` = ?," +
            " `CONVERSION_RATE`.`PERIOD_KEY` = ?," +
            " `CONVERSION_RATE`.`CONVERTION_TO_LOCAL` = ? WHERE (`CONVERSION_RATE`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `CONVERSION_RATE` WHERE (`CONVERSION_RATE`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`CONVERSION_RATE`.`ID`) FROM `CONVERSION_RATE` ;";

    @Override
    public ConvertionRate insert(ConvertionRate entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setLong(3, entity.getCountryKey());
            statement.setLong(4, entity.getPeriodKey());
            statement.setLong(5, entity.getConvertion());
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
    public ConvertionRate selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(ConvertionRate updatedEntity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, updatedEntity.getUsername());
            statement.setString(2, updatedEntity.getPasswordHash());
            statement.setLong(3, updatedEntity.getCountryKey());
            statement.setLong(4, updatedEntity.getPeriodKey());
            statement.setLong(5, updatedEntity.getConvertion());
            statement.setLong(6, updatedEntity.getId());
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
    public List<ConvertionRate> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<ConvertionRate> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    ConvertionRate parseResultSet(ResultSet resultSet) throws SQLException {
        ConvertionRate convertionRate = new ConvertionRate();
        convertionRate.setId(resultSet.getLong(1));
        convertionRate.setUsername(resultSet.getString(2));
        convertionRate.setPasswordHash(resultSet.getString(3));
        convertionRate.setCountryKey(resultSet.getLong(4));
        convertionRate.setPeriodKey(resultSet.getLong(5));
        convertionRate.setConvertion(resultSet.getLong(6));
        return convertionRate;
    }
}
