package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.exceptions.DomainException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository implements CrudRepository<Department, Long> {

    private final JdbcTemplate template;

    public DepartmentRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional
    public Department create(Department department) {
        String sql = "INSERT INTO Departments(dpName) VALUES (?)";
        template.update(sql,
                department.getName());
        sql = "SELECT " +
                "Departments.dpID AS id, " +
                "Departments.dpName AS name " +
                "FROM Departments WHERE dpID=LAST_INSERT_ID()";
        return template.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Department.class));
    }

    @Override
    public Optional<Department> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    public Optional<Department> getById(long id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM Departments WHERE dpID=?";
        try {
            return Optional.ofNullable(template.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Department.class), id));
        } catch (EmptyResultDataAccessException exception) {
            throw new DomainException("department doesn't exist");
        }
    }

    @Override
    public Optional<Department> update(Department department) {
        String sql = "UPDATE Departments SET dpName=? WHERE dpID=?";
        template.update(sql,
                department.getName(),
                department.getId());
        return getById(department.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from Departments where dpID = ?";
        return this.template.update(sql, id) > 0;
    }

    public List<Department> getAll(int minLimit, int maxLimit) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM Departments LIMIT ?,?";
        return template.query(sql,
                BeanPropertyRowMapper.newInstance(Department.class),
                minLimit,
                maxLimit);
    }
}
