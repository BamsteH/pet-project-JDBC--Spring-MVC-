package com.example.demo.application.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

  T create(T t);

  Optional<T> getById(ID id);

  List<T> getAll();

  List<T> getAll(int minLimit, int maxLimit);

  Optional<T> update(T t, ID id);

  boolean delete(ID id);

}
