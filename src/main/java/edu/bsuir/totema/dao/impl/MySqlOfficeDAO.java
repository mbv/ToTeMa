package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.OfficeDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Office;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlOfficeDAO extends AbstractBaseDAO<Office> implements OfficeDAO {

    private static final Logger logger = Logger.getLogger(MySqlOfficeDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `OFFICE`.`ID`, `OFFICE`.`USERNAME`, `OFFICE`.`PASSWORD_HASH`, " +
            "`OFFICE`.`COUNTRY_KEY`, `OFFICE`.`CITY`, `OFFICE`.`ADDRESS`, `OFFICE`.`FAX`, `OFFICE`.`PHONE`, `OFFICE`.`POSTAL_CODE`, `OFFICE`.`STATUS` FROM `OFFICE` WHERE  `OFFICE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `OFFICE`.`ID`, `OFFICE`.`USERNAME`, `OFFICE`.`PASSWORD_HASH`, " +
            "`OFFICE`.`COUNTRY_KEY`, `OFFICE`.`CITY`, `OFFICE`.`ADDRESS`, `OFFICE`.`FAX`, `OFFICE`.`PHONE`, `OFFICE`.`POSTAL_CODE`, `OFFICE`.`STATUS` FROM `OFFICE` WHERE " +
            " `OFFICE`.`STATUS` != -1 ORDER BY `OFFICE`.`ID` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `DATE` (`DATE`.`USERNAME`, `DATE`.`PASSWORD_HASH`, `DATE`.`NAME`, `DATE`.`COUNTRY_KEY`, `DATE`.`CITY`, " +
            "`DATE`.`ADDRESS`, `DATE`.`FAX`, `DATE`.`PHONE`, `DATE`.`POSTAL_CODE`, `OFFICE`.`STATUS`)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `OFFICE` SET `OFFICE`.`USERNAME` = ?, `OFFICE`.`PASSWORD_HASH` = ?, `OFFICE`.`COUNTRY_KEY` = ?, `OFFICE`.`CITY` = ?," +
            " `OFFICE`.`ADDRESS` = ?, `OFFICE`.`FAX` = ?, `OFFICE`.`PHONE` = ?, `OFFICE`.`POSTAL_CODE` = ?, " +
            "`OFFICE`.`STATUS` = ? WHERE (`OFFICE`.`ID` = ?) AND (`OFFICE`.`STATUS` != -1);";
    private static final String QUERY_DELETE = "UPDATE `OFFICE` SET `OFFICE`.`STATUS` = -1 WHERE (`OFFICE`.`ID` = ?) AND (`OFFICE`.`STATUS` != -1);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`OFFICE`.`ID`) FROM `OFFICE` WHERE (`OFFICE`.`STATUS` != -1) ;";

    @Override
    public Office insert(Office entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setLong(3, entity.getCountryKey());
            statement.setString(4, entity.getCity());
            statement.setString(5, entity.getAddress());
            statement.setString(6, entity.getFax());
            statement.setString(7, entity.getPhone());
            statement.setString(8, entity.getPostalCode());
            statement.setInt(9, entity.getStatus());
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
    public Office selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public void update(Office entity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setLong(3, entity.getCountryKey());
            statement.setString(4, entity.getCity());
            statement.setString(5, entity.getAddress());
            statement.setString(6, entity.getFax());
            statement.setString(7, entity.getPhone());
            statement.setString(8, entity.getPostalCode());
            statement.setInt(9, entity.getStatus());

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
    public List<Office> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Office> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
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
    Office parseResultSet(ResultSet resultSet) throws SQLException {
        Office office = new Office();
        office.setId(resultSet.getLong(1));
        office.setUsername(resultSet.getString(2));
        office.setPasswordHash(resultSet.getString(3));
        office.setCountryKey(resultSet.getLong(4));
        office.setCity(resultSet.getString(5));
        office.setAddress(resultSet.getString(6));
        office.setFax(resultSet.getString(7));
        office.setPhone(resultSet.getString(8));
        office.setPostalCode(resultSet.getString(9));
        office.setStatus(resultSet.getInt(10));
        return office;
    }
}
