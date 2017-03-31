package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.CountryDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Country;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlCountryDAO extends AbstractBaseDAO<Country> implements CountryDAO {

    private static final Logger logger = Logger.getLogger(MySqlCountryDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `COUNTRY`.`ID`, " +
            "`COUNTRY`.`NAME`, `COUNTRY`.`ISO_THREE_LETTER_CODE`, `COUNTRY`.`ISO_TWO_LETTER_CODE`, `COUNTRY`.`ISO_THREE_DIGIT_CODE`, `COUNTRY`.`CURRENCY_NAME` FROM `COUNTRY` WHERE  `COUNTRY`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `COUNTRY`.`ID`, " +
            "`COUNTRY`.`NAME`, `COUNTRY`.`ISO_THREE_LETTER_CODE`, `COUNTRY`.`ISO_TWO_LETTER_CODE`, `COUNTRY`.`ISO_THREE_DIGIT_CODE`, `COUNTRY`.`CURRENCY_NAME` FROM `COUNTRY` " +
            " ORDER BY `COUNTRY`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `COUNTRY` (`COUNTRY`.`NAME`," +
            " `COUNTRY`.`ISO_THREE_LETTER_CODE`, `COUNTRY`.`ISO_TWO_LETTER_CODE`, `COUNTRY`.`ISO_THREE_DIGIT_CODE`, `COUNTRY`.`CURRENCY_NAME`)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `COUNTRY` SET `COUNTRY`.`NAME` = ?, `COUNTRY`.`ISO_THREE_LETTER_CODE` = ?," +
            " `COUNTRY`.`ISO_TWO_LETTER_CODE` = ?, `COUNTRY`.`ISO_THREE_DIGIT_CODE` = ?, `COUNTRY`.`CURRENCY_NAME` = ?" +
            " WHERE (`COUNTRY`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `COUNTRY` WHERE (`COUNTRY`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`COUNTRY`.`ID`) FROM `COUNTRY` ;";

    @Override
    public Country insert(Country entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getIso3());
            statement.setString(3, entity.getIso2());
            statement.setLong(4, entity.getIso3digit());
            statement.setString(5, entity.getCurrency());
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
    public Country selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(Country entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getIso3());
            statement.setString(3, entity.getIso2());
            statement.setLong(4, entity.getIso3digit());
            statement.setString(5, entity.getCurrency());
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
    public List<Country> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Country> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    Country parseResultSet(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getLong(1));
        country.setName(resultSet.getString(2));
        country.setIso3(resultSet.getString(3));
        country.setIso2(resultSet.getString(4));
        country.setIso3digit(resultSet.getLong(5));
        country.setCurrency(resultSet.getString(6));
        return country;
    }
}
