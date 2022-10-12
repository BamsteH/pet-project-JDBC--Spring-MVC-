package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {


    private final JdbcTemplate template;

    public EmployeeRepository(JdbcTemplate template) {
        this.template = template;
    }


    public void create(Employee employee) {
        String sql = "INSERT INTO Employees(empName, empActive, emp_dpID) " +
                "VALUES (?,?,?)";
        template.update(sql,
                employee.getEmpName(),
                employee.isEmpActive(),
                employee.getEmp_dpID());
    }

    public Employee getById(long id) throws SQLException, EmptyResultDataAccessException {
        String sql = "SELECT * FROM Employees WHERE empId=?";

        return template.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Employee.class), id);
    }

    public List<Employee> getByStartsWith(String string, int minLimit, int maxLimit) {
        string = string + "%";
        String sql = "SELECT * FROM Employees WHERE empName LIKE ?  LIMIT ?,?";
        return template.query(sql,
                BeanPropertyRowMapper.newInstance(Employee.class),
                string,
                minLimit,
                maxLimit);
    }


    public List<Employee> getAll(int minLimit, int maxLimit) {
        String sql = "SELECT * FROM Employees LIMIT ?,?";
        return template.query(sql,
                BeanPropertyRowMapper.newInstance(Employee.class),
                minLimit,
                maxLimit);
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE Employees SET empName=?, empActive=?, emp_DpID=? WHERE empId=?";
        template.update(sql,
                employee.getEmpName(),
                employee.isEmpActive(),
                employee.getEmp_dpID(),
                employee.getEmpId());
    }

    public boolean deleteEmployee(long id) {
        String sql = "delete from Employees where empId = ?";

        return this.template.update(sql, id) > 0;
    }
}
