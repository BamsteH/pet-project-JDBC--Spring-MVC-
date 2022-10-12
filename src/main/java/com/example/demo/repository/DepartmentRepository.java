package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate template;

    public DepartmentRepository(JdbcTemplate template) {
        this.template = template;
    }


    public void create(Department department) {
        String sql = "INSERT INTO Departments(dpName) VALUES (?)";
        template.update(sql,
                department.getDpName());
    }

    public Department getById(long id) {
        String sql = "SELECT * FROM Departments WHERE dpID=?";
        return template.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Department.class), id);
    }

    public void updateDepartment(Department department) {
        String sql = "UPDATE Departments SET dpName=? WHERE dpID=?";
        template.update(sql,
                department.getDpName(),
                department.getDpID());
    }

    public boolean deleteDepartment(long id) {
        String sql = "delete from Departments where dpID = ?";
        return this.template.update(sql, id) > 0;
    }

    public List<Department> getAll(int minLimit, int maxLimit) {
        String sql = "SELECT * FROM Departments LIMIT ?,?";
        return template.query(sql,
                BeanPropertyRowMapper.newInstance(Department.class),
                minLimit,
                maxLimit);
    }

}
