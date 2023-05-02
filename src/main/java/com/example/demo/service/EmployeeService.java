package com.example.demo.service;

import com.example.demo.dto.employee.EmployeeAddRequest;
import com.example.demo.dto.employee.EmployeeResponse;
import com.example.demo.dto.employee.EmployeeUpdateRequest;
import com.example.demo.exceptions.DomainException;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.utils.PaginationPointCalculator.getStartPointLimit;
import static com.example.demo.utils.transferObject.EmployeeTransferObj.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository repository;

  public EmployeeResponse create(EmployeeAddRequest request) {
    return toEmployeeResponse(this.repository.create(fromEmployeeAddRequest(request)));
  }

  public EmployeeResponse readEmployeeById(long id) {
    return toEmployeeResponse(this.repository
            .getById(id)
            .orElseThrow(() -> new DomainException("Employee doesn't exist")));
  }

  public List<EmployeeResponse> readAllEmployee(int page, int limit) {
    int pointLimit = getStartPointLimit(page, limit);
    return toListEmployeeResponse(this.repository
            .getAll(pointLimit,
                    pointLimit + limit));
  }

  public List<EmployeeResponse> readByStartsWith(String startsWith, int page, int limit) {
    int pointLimit = getStartPointLimit(page, limit);
    return toListEmployeeResponse(this.repository
            .getByStartsWith(startsWith, pointLimit, pointLimit + limit));
  }

  public EmployeeResponse update(EmployeeUpdateRequest request, Long id) {
    return toEmployeeResponse(this.repository
            .update(fromEmployeeUpdateRequest(request), id)
            .orElseThrow(() -> new DomainException("Employee doesn't exist")));
  }

  public boolean delete(long id) {
    return this.repository.delete(id);
  }

  public List<EmployeeResponse> readEmployeeByDepartmentId(long departmentId) {
    return toListEmployeeResponse(this.repository.getByDepartmentId(departmentId));
  }

}
