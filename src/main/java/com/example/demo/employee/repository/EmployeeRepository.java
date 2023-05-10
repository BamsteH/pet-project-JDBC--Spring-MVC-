package com.example.demo.employee.repository;

import com.example.demo.employee.entity.Employee;
import com.example.demo.application.exceptions.DomainException;
import com.example.demo.application.repository.CrudRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository implements CrudRepository<Employee, Long> {


  private final JdbcTemplate template;

  public EmployeeRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Transactional
  public Employee create(Employee employee) {
    String sql = "INSERT INTO Employees(empName, empActive, emp_dpID) " +
            "VALUES (?,?,?);";
    try {
      template.update(sql,
              employee.getName(),
              employee.isActive(),
              employee.getDepartmentId()
      );
      return template.queryForObject("SELECT " +
                      "Employees.empId AS id," +
                      "Employees.empName AS name," +
                      "Employees.empActive AS active, " +
                      "Departments.dpName AS departmentName " +
                      "FROM Employees " +
                      "INNER JOIN " +
                      "Departments ON Employees.emp_dpID = Departments.dpID " +
                      "WHERE empId=last_insert_id()",
              BeanPropertyRowMapper.newInstance(Employee.class));
    } catch (DataAccessException exception) {
      throw new DomainException("Can't add an employee", exception);
    }
  }


  @Override
  public List<Employee> getAll() {
    String sql = "SELECT " +
            "Employees.empId AS id," +
            "Employees.empName AS name," +
            "Employees.empActive AS active, " +
            "Departments.dpName AS departmentName " +
            "FROM Employees " +
            "INNER JOIN " +
            "Departments ON Employees.emp_dpID = Departments.dpID ";
    return template.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
  }

  @Override
  public Optional<Employee> getById(Long id) {
    String sql = "SELECT " +
            "Employees.empId AS id," +
            "Employees.empName AS name," +
            "Employees.empActive AS active, " +
            "Departments.dpName AS departmentName " +
            "FROM Employees " +
            "INNER JOIN " +
            "Departments ON Employees.emp_dpID = Departments.dpID " +
            "WHERE Employees.empId=?";
    try {
      return Optional.ofNullable(template.queryForObject(sql,
              BeanPropertyRowMapper.newInstance(Employee.class), id));
    } catch (EmptyResultDataAccessException exception) {
      return Optional.empty();
    }
  }

  public List<Employee> getByStartsWith(String string, int minLimit, int maxLimit) {

    String sql = "SELECT " +
            "Employees.empId AS id," +
            "Employees.empName AS name," +
            "Employees.empActive AS active, " +
            "Departments.dpName AS departmentName " +
            "FROM Employees " +
            "INNER JOIN " +
            "Departments ON Employees.emp_dpID = Departments.dpID " +
            "WHERE Employees.empName LIKE ?  LIMIT ?,?";
    return template.query(sql,
            BeanPropertyRowMapper.newInstance(Employee.class),
            string + "%",
            minLimit,
            maxLimit);
  }


  @Override
  public List<Employee> getAll(int minLimit, int maxLimit) {
    String sql = "SELECT " +
            "Employees.empId AS id," +
            "Employees.empName AS name," +
            "Employees.empActive AS active, " +
            "Departments.dpName AS departmentName " +
            "FROM Employees " +
            "INNER JOIN " +
            "Departments ON Employees.emp_dpID = Departments.dpID " +
            "LIMIT ?,?";
    return template.query(sql,
            BeanPropertyRowMapper.newInstance(Employee.class),
            minLimit,
            maxLimit);
  }


  @Override
  public Optional<Employee> update(Employee employee, Long id) {
    String sql = "UPDATE Employees SET empName=?, " +
            "empActive=?," +
            "emp_DpID=? " +
            "WHERE empId=?";
    template.update(sql,
            employee.getName(),
            employee.isActive(),
            employee.getDepartmentId(),
            id);
    return getById(employee.getId());
  }

  @Override
  public boolean delete(Long id) {
    String sql = "DELETE FROM Employees WHERE empId = ?";
    return this.template.update(sql, id) > 0;
  }

  public List<Employee> getByDepartmentId(long departmentId) {
    String sql = "SELECT " +
            "Employees.empId AS id," +
            "Employees.empName AS name," +
            "Employees.empActive AS active, " +
            "Departments.dpName AS departmentName " +
            "FROM Employees " +
            "INNER JOIN " +
            "Departments ON Employees.emp_dpID = Departments.dpID " +
            "WHERE emp_dpID=?";

    return template.query(
            sql,
            BeanPropertyRowMapper.newInstance(Employee.class),
            departmentId
    );
  }
}
