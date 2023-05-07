package com.example.demo.employee.service;

import com.example.demo.application.exceptions.DomainException;
import com.example.demo.employee.dto.request.EmployeeAddRequest;
import com.example.demo.employee.dto.request.EmployeeUpdateRequest;
import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.entity.Employee;
import com.example.demo.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.application.utils.PaginationPointCalculator.getStartPointLimit;
import static com.example.demo.application.utils.transferObject.EmployeeTransferObj.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository repository;

  public EmployeeResponse create(EmployeeAddRequest request) {
    return Assembler.toResponse(
            this.repository.create(Assembler.fromAddRequest(request))
    );
  }

  public EmployeeResponse readEmployeeById(long id) {
    return Assembler.toResponse(this.repository
            .getById(id)
            .orElseThrow(() -> new DomainException("Employee doesn't exist")));
  }

  public List<EmployeeResponse> readAllEmployee(int page, int limit) {
    int pointLimit = getStartPointLimit(page, limit);
    return Assembler.toListEmployeeResponse(this.repository
            .getAll(pointLimit,
                    pointLimit + limit));
  }

  public List<EmployeeResponse> readByStartsWith(String startsWith, int page, int limit) {
    int pointLimit = getStartPointLimit(page, limit);
    return Assembler.toListEmployeeResponse(this.repository
            .getByStartsWith(startsWith, pointLimit, pointLimit + limit));
  }

  public EmployeeResponse update(EmployeeUpdateRequest request, Long id) {
    return Assembler.toResponse(this.repository
            .update(Assembler.fromUpdateRequest(request), id)
            .orElseThrow(() -> new DomainException("Employee doesn't exist")));
  }

  public boolean delete(long id) {
    return this.repository.delete(id);
  }

  public List<EmployeeResponse> readEmployeeByDepartmentId(long departmentId) {
    return Assembler.toListEmployeeResponse(
            this.repository.getByDepartmentId(departmentId)
    );
  }

  private static class Assembler {

    private static EmployeeResponse toResponse(Employee employee) {
      return new EmployeeResponse(
              employee.getName(),
              employee.getId(),
              employee.isActive(),
              employee.getDepartmentName()
      );
    }

    private static Employee fromAddRequest(EmployeeAddRequest request) {
      return new Employee(
              request.getEmployeeName(),
              request.isActive(),
              request.getDepartmentId()
      );
    }

    private static Employee fromUpdateRequest(EmployeeUpdateRequest request) {
      return new Employee(
              request.getName(),
              request.isActive(),
              request.getDepartmentId()
      );
    }

    private static List<EmployeeResponse> toListEmployeeResponse(List<Employee> employees) {
      return employees.stream()
              .map(Assembler::toResponse)
              .collect(Collectors.toList());
    }


  }


}
