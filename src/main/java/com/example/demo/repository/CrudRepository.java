package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.DomainException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T create(T t);

    Optional<T> getById(ID id);

    List<T> getAll();

    List<T> getAll(int minLimit, int maxLimit);

    Optional<T> update(T t);

    boolean delete(ID id);

}
