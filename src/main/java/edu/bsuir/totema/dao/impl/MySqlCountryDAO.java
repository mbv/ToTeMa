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
    private static final String QUERY_SELECT_BY_ID = "SELECT `COUTNRY`.`ID`, `COUTNRY`.`USERNAME`, `COUTNRY`.`PASSWORD_HASH`, " +
            "`COUTNRY`.`NAME`, `COUTNRY`.`ISO_THREE_LETTER_CODE`, `COUTNRY`.`ISO_TWO_LETTER_CODE`, `COUTNRY`.`ISO_THREE_DIGIT_CODE`, `COUTNRY`.`CURRENCY_NAME` FROM `COUNTRY` WHERE  `COUTNRY`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `COUTNRY`.`ID`, `COUTNRY`.`USERNAME`, `COUTNRY`.`PASSWORD_HASH`, " +
            "`COUTNRY`.`NAME`, `COUTNRY`.`ISO_THREE_LETTER_CODE`, `COUTNRY`.`ISO_TWO_LETTER_CODE`, `COUTNRY`.`ISO_THREE_DIGIT_CODE`, `COUTNRY`.`CURRENCY_NAME` FROM `COUNTRY` " +
            " ORDER BY `COUTNRY`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `COUTNRY` (`COUTNRY`.`USERNAME`, `COUTNRY`.`PASSWORD_HASH`, `COUTNRY`.`NAME`," +
            " `COUTNRY`.`ISO_THREE_LETTER_CODE`, `COUTNRY`.`ISO_TWO_LETTER_CODE`, `COUTNRY`.`ISO_THREE_DIGIT_CODE`, `COUTNRY`.`CURRENCY_NAME`)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `COUTNRY` SET `COUTNRY`.`USERNAME` = ?, `COUTNRY`.`PASSWORD_HASH` = ?, `COUTNRY`.`NAME` = ?, `COUTNRY`.`ISO_THREE_LETTER_CODE` = ?," +
            " `COUTNRY`.`ISO_TWO_LETTER_CODE` = ?, `COUTNRY`.`ISO_THREE_DIGIT_CODE` = ?, `COUTNRY`.`CURRENCY_NAME` = ?" +
            " WHERE (`COUTNRY`.`ID` = ?);";
    private static final String QUERY_DELETE = "DELETE FROM `COUTNRY` WHERE (`COUTNRY`.`ID` = ?);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`COUTNRY`.`ID`) FROM `COUTNRY` ;";

    @Override
    public Country insert(Country entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getIso3());
            statement.setString(5, entity.getIso2());
            statement.setLong(6, entity.getIso3digit());
            statement.setString(7, entity.getCurrency());
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
    public void update(Country updatedEntity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, updatedEntity.getUsername());
            statement.setString(2, updatedEntity.getPasswordHash());
            statement.setString(3, updatedEntity.getName());
            statement.setString(4, updatedEntity.getIso3());
            statement.setString(5, updatedEntity.getIso2());
            statement.setLong(6, updatedEntity.getIso3digit());
            statement.setString(7, updatedEntity.getCurrency());
            statement.setLong(8, updatedEntity.getId());
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
        country.setUsername(resultSet.getString(2));
        country.setPasswordHash(resultSet.getString(3));
        country.setName(resultSet.getString(4));
        country.setIso3(resultSet.getString(5));
        country.setIso2(resultSet.getString(6));
        country.setIso3digit(resultSet.getLong(7));
        country.setCurrency(resultSet.getString(8));
        return country;
    }
}
