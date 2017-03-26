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

    private static final Logger logger = Logger.getLogger(MySqlEmployeeDAO.class);
    private static final String QUERY_SELECT_BY_ID = "SELECT `EMPLOYEE`.`ID`, `EMPLOYEE`.`USERNAME`, `EMPLOYEE`.`PASSWORD_HASH`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO`, `EMPLOYEE`.`STATUS` FROM `EMPLOYEE` " +
            "WHERE `EMPLOYEE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `EMPLOYEE`.`ID`, `EMPLOYEE`.`USERNAME`, `EMPLOYEE`.`PASSWORD_HASH`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO`, `EMPLOYEE`.`STATUS` FROM `EMPLOYEE` " +
            "WHERE `EMPLOYEE`.`STATUS` != -1 ORDER BY `EMPLOYEE`.`NAME` LIMIT ?, ?;";
    private static final String QUERY_INSERT = "INSERT INTO `EMPLOYEE` (`EMPLOYEE`.`USERNAME`, `EMPLOYEE`.`PASSWORD_HASH`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO`, `EMPLOYEE`.`STATUS`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_UPDATE = "UPDATE `EMPLOYEE` SET `EMPLOYEE`.`USERNAME` = ?, `EMPLOYEE`.`PASSWORD_HASH` = ?, `EMPLOYEE`.`NAME` = ?, `EMPLOYEE`.`HIRE_DATE` = ?, `EMPLOYEE`.`OFFICE_KEY` = ?, `EMPLOYEE`.`TITLE` = ?, `EMPLOYEE`.`YEAR_SALARY` = ?, `EMPLOYEE`.`CONTRACT_TILL` = ?, `EMPLOYEE`.`REPORTS_TO` = ?, `EMPLOYEE`.`STATUS` = ? WHERE (`EMPLOYEE`.`ID` = ?) AND (`EMPLOYEE`.`STATUS` != -1);";
    private static final String QUERY_DELETE = "UPDATE `EMPLOYEE` SET `EMPLOYEE`.`STATUS` = -1 WHERE (`EMPLOYEE`.`ID` = ?) AND (`EMPLOYEE`.`STATUS` != -1);";
    private static final String QUERY_SELECT_COUNT = "SELECT COUNT(`EMPLOYEE`.`ID`) FROM `EMPLOYEE` WHERE (`EMPLOYEE`.`STATUS` != -1) ;";

    @Override
    public Employee insert(Employee entity) throws DAOException {
        return tryCallJDBC(QUERY_INSERT, ((connection, statement) -> {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPasswordHash());
            statement.setString(3, entity.getName());
            statement.setDate(4, entity.getHireDate());
            statement.setLong(5, entity.getOfficeKey());
            statement.setString(6, entity.getTitle());
            statement.setLong(7, entity.getYearSalary());
            statement.setDate(8, entity.getContractTill());
            statement.setLong(9, entity.getReportsTo());
            statement.setInt(10, entity.getStatus());
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
    public void update(Employee updatedEntity) throws DAOException {
        tryCallJDBC(QUERY_UPDATE, ((connection, statement) -> {
            statement.setString(1, updatedEntity.getUsername());
            statement.setString(2, updatedEntity.getPasswordHash());
            statement.setString(3, updatedEntity.getName());
            statement.setDate(4, updatedEntity.getHireDate());
            statement.setLong(5, updatedEntity.getOfficeKey());
            statement.setString(6, updatedEntity.getTitle());
            statement.setLong(7, updatedEntity.getYearSalary());
            statement.setDate(8, updatedEntity.getContractTill());
            statement.setLong(9, updatedEntity.getReportsTo());
            statement.setInt(10, updatedEntity.getStatus());

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
    Employee parseResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong(1));
        employee.setUsername(resultSet.getString(2));
        employee.setPasswordHash(resultSet.getString(3));
        employee.setName(resultSet.getString(4));
        employee.setHireDate(resultSet.getDate(5));
        employee.setOfficeKey(resultSet.getLong(6));
        employee.setTitle(resultSet.getString(7));
        employee.setYearSalary(resultSet.getLong(8));
        employee.setContractTill(resultSet.getDate(9));
        employee.setReportsTo(resultSet.getLong(10));
        employee.setStatus(resultSet.getInt(11));
        return employee;
    }
}
