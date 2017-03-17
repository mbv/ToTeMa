package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Employee;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlEmployeeDAO extends AbstractBaseDAO<Employee> implements EmployeeDAO {
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `EMPLOYEE`.`ID`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO` FROM `EMPLOYEE` " +
            "ORDER BY `EMPLOYEE`.`NAME` LIMIT ?, ?;";
///WHERE `EMPLOYEE`.`STATUS` != -1
    @Override
    public Employee insert(Employee entity) throws DAOException {
        return null;
    }

    @Override
    public Employee selectById(long id) throws DAOException {
        return null;
    }

    @Override
    public void update(Employee updatedEntity) throws DAOException {

    }

    @Override
    public void delete(long id) throws DAOException {

    }

    @Override
    public List<Employee> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Employee> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
        return null;
    }

    @Override
    public int selectCount() throws DAOException {
        return 0;
    }

    @Override
    public int selectLikeCount(String likeQuery) throws DAOException {
        return 0;
    }

    @Override
    Employee parseResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong(1));
        employee.setName(resultSet.getString(2));
        employee.setTitle(resultSet.getString(5));
        return employee;
    }
    /*private static final Logger logger = Logger.getLogger(MySqlEmployeeDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `user`.`id`, `user`.`email`, `user`.`username`, `user`.`pass_hash`, `user`.`role`, `user`.`created_at`, `user`.`modified_at`, `user`.`status`, `user`.`locale`, `user`.`image_url`, `user`.`website`, (SELECT AVG(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS averageMark, (SELECT COUNT(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS markCount FROM `user` " +
            "WHERE `id` = ?;";
    private static final String QUERY_SELECT_BY_USERNAME = "SELECT `user`.`id`, `user`.`email`, `user`.`username`, `user`.`pass_hash`, `user`.`role`, `user`.`created_at`, `user`.`modified_at`, `user`.`status`, `user`.`locale`, `user`.`image_url`, `user`.`website`, (SELECT AVG(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS averageMark, (SELECT COUNT(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS markCount FROM `user` " +
            "WHERE (`username` = ?);";
    private static final String QUERY_SELECT_BY_EMAIL = "SELECT `user`.`id`, `user`.`email`, `user`.`username`, `user`.`pass_hash`, `user`.`role`, `user`.`created_at`, `user`.`modified_at`, `user`.`status`, `user`.`locale`, `user`.`image_url`, `user`.`website`, (SELECT AVG(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS averageMark, (SELECT COUNT(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS markCount FROM `user` " +
            "WHERE (`email` = ?);";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `user`.`id`, `user`.`email`, `user`.`username`, `user`.`pass_hash`, `user`.`role`, `user`.`created_at`, `user`.`modified_at`, `user`.`status`, `user`.`locale`, `user`.`image_url`, `user`.`website`, (SELECT AVG(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS averageMark, (SELECT COUNT(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS markCount FROM `user` " +
            "WHERE `user`.`status` != -1 ORDER BY `user`.`username` LIMIT ?, ?;";
    private static final String QUERY_SELECT_LIKE_WITH_LIMIT = "SELECT `user`.`id`, `user`.`email`, `user`.`username`, `user`.`pass_hash`, `user`.`role`, `user`.`created_at`, `user`.`modified_at`, `user`.`status`, `user`.`locale`, `user`.`image_url`, `user`.`website`, (SELECT AVG(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS averageMark, (SELECT COUNT(`mark`.`value`) FROM `mark` INNER JOIN `answer` ON `mark`.`answer_id` = `answer`.`id` WHERE `answer`.`creator_id` = `user`.`id`) AS markCount FROM `user` " +
            "WHERE (`user`.`status` != -1) AND (LOWER(`username`) LIKE LOWER(?)) ORDER BY `user`.`username` LIMIT ?, ?;";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`user`.`id`) FROM `user` WHERE (`user`.`status` != -1) ;";
    private static final String QUERY_SELECT_LIKE_COUNT = "SELECT COUNT(`user`.`id`) FROM `user` WHERE (`user`.`status` != -1) AND (LOWER(`username`) LIKE LOWER(?));";
    private static final String QUERY_UPDATE = "UPDATE `user` SET `email` = ?, `username` = ?, `pass_hash` = ?, `status` = ?, `locale` = ?, `image_url` = ?, `website` = ? WHERE (`id` = ?) AND (`status` != -1);";
    private static final String QUERY_INSERT = "INSERT INTO `user` (`email`, `username`, `pass_hash`) VALUES (?, ?, ?)";
    private static final String QUERY_DELETE = "UPDATE `user` SET `status` = -1 WHERE (`id` = ?) AND (`status` != -1);";

    @Override
    public Employee insert(Employee entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassHash());
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
    public Employee selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
    }

    @Override
    public Employee selectByEmail(String email) throws DAOException {
        return selectWithStringParameter(QUERY_SELECT_BY_EMAIL, email);
    }

    @Override
    public List<Employee> selectWithLimit(int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_WITH_LIMIT, statement -> {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            return executeStatementAndParseResultSetToList(statement);
        });
    }

    @Override
    public List<Employee> selectLikeWithLimit(String likeQuery, int offset, int count) throws DAOException {
        return tryCallJDBC(QUERY_SELECT_LIKE_WITH_LIMIT, statement -> {
            String likePattern = createGlobalLikePattern(likeQuery);
            statement.setString(1, likePattern);
            statement.setInt(2, offset);
            statement.setInt(3, count);
            return executeStatementAndParseResultSetToList(statement);
        });
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
        return tryCallJDBC(QUERY_SELECT_LIKE_COUNT, statement -> {
            String likePattern = createGlobalLikePattern(likeQuery);
            statement.setString(1, likePattern);
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
    public void update(Employee updatedEntity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, updatedEntity.getEmail());
            statement.setString(2, updatedEntity.getUsername());
            statement.setString(3, updatedEntity.getPassHash());
            statement.setInt(4, updatedEntity.getStatus());
            statement.setString(5, updatedEntity.getLocale());
            statement.setString(6, updatedEntity.getImageUrl());
            statement.setString(7, updatedEntity.getWebsite());

            statement.setLong(8, updatedEntity.getId());
            statement.executeUpdate();

            Employee reselectedEntity = executeSelectById(connection, QUERY_SELECT_BY_ID, updatedEntity.getId());
            updatedEntity.setModifiedAt(reselectedEntity.getModifiedAt());
        }));
    }

    @Override
    public void delete(long id) throws DAOException {
        executeDelete(QUERY_DELETE, id);
    }

    private Employee selectWithStringParameter(String selectQuery, String parameter) throws DAOException {
        return tryCallJDBC(selectQuery, statement -> {
            statement.setString(1, parameter);
            return executeStatementAndParseResultSet(statement);
        });
    }

    @Override
    protected Employee parseResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong(1));
        /*employee.setEmail(resultSet.getString(2));
        employee.setUsername(resultSet.getString(3));
        employee.setPassHash(resultSet.getString(4));
        employee.setRole(resultSet.getInt(5));
        employee.setCreatedAt(resultSet.getTimestamp(6));
        employee.setModifiedAt(resultSet.getTimestamp(7));
        employee.setStatus(resultSet.getInt(8));
        employee.setLocale(resultSet.getString(9));
        employee.setImageUrl(resultSet.getString(10));
        employee.setWebsite(resultSet.getString(11));
        employee.setAverageMark(resultSet.getFloat(12));
        employee.setMarkCount(resultSet.getInt(13));
        return employee;
    }*/
}
