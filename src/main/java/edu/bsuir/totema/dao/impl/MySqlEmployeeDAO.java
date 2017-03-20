package edu.bsuir.totema.dao.impl;

import edu.bsuir.totema.dao.EmployeeDAO;
import edu.bsuir.totema.dao.exception.DAOException;
import edu.bsuir.totema.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static edu.bsuir.totema.util.caller.JDBCCaller.tryCallJDBC;

public class MySqlEmployeeDAO extends AbstractBaseDAO<Employee> implements EmployeeDAO {
    private static final String QUERY_SELECT_BY_ID = "SELECT `EMPLOYEE`.`ID`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO` FROM `EMPLOYEE` " +
            "WHERE `EMPLOYEE`.`ID` = ?;";
    private static final String QUERY_SELECT_WITH_LIMIT = "SELECT `EMPLOYEE`.`ID`, `EMPLOYEE`.`NAME`, `EMPLOYEE`.`HIRE_DATE`, `EMPLOYEE`.`OFFICE_KEY`, `EMPLOYEE`.`TITLE`, `EMPLOYEE`.`YEAR_SALARY`, `EMPLOYEE`.`CONTRACT_TILL`, `EMPLOYEE`.`REPORTS_TO` FROM `EMPLOYEE` " +
            "WHERE `EMPLOYEE`.`STATUS` != -1 ORDER BY `EMPLOYEE`.`NAME` LIMIT ?, ?;";
    @Override
    public Employee insert(Employee entity) throws DAOException {
        return null;
    }

    @Override
    public Employee selectById(long id) throws DAOException {
        return executeSelectById(QUERY_SELECT_BY_ID, id);
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
}
