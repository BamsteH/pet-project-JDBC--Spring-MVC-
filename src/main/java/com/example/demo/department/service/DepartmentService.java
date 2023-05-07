package com.example.demo.department.service;

import com.example.demo.department.dto.request.DepartmentAddRequest;
import com.example.demo.department.dto.response.DepartmentResponse;
import com.example.demo.department.dto.request.DepartmentUpdateRequest;
import com.example.demo.application.exceptions.DomainException;
import com.example.demo.department.entity.Department;
import com.example.demo.department.repository.DepartmentRepository;
import com.example.demo.application.utils.transferObject.DepartmentTransferObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.application.utils.PaginationPointCalculator.getStartPointLimit;


@Service
public class DepartmentService {

  private final DepartmentRepository repository;

  public DepartmentService(DepartmentRepository repository) {
    this.repository = repository;
  }

  public DepartmentResponse getById(long id) {
    return this.repository
            .getById(id)
            .map(Assembler::toResponse)
            .orElseThrow(() -> new DomainException("department doesn't exist"));
  }

  public DepartmentResponse create(DepartmentAddRequest request) {
    return Assembler.toResponse(this.repository.create(Assembler.fromAddRequest(request)));
  }

  public DepartmentResponse update(DepartmentUpdateRequest request, Long id) {
    return Assembler.toResponse(this.repository
            .update(Assembler.fromUpdateRequest(request, id), id)
            .orElseThrow(() -> {
              throw new DomainException("can't update department");
            }));
  }

  public boolean delete(long id) {
    return this.repository.delete(id);
  }

  public List<DepartmentResponse> getAll(int page, int limit) {
    int startPoint = getStartPointLimit(page, limit);
    return this.repository
            .getAll(startPoint, startPoint + limit)
            .stream()
            .map(Assembler::toResponse)
            .collect(Collectors.toList());
  }

  private static class Assembler {

    private static DepartmentResponse toResponse(Department department) {
      return new DepartmentResponse(department.getName(), department.getId());
    }

    private static Department fromAddRequest(DepartmentAddRequest request) {
      Department department = new Department();
      department.setName(request.getName());
      return department;
    }

    private static Department fromUpdateRequest(DepartmentUpdateRequest request, Long id) {
      return new Department(id, request.getName());
    }


  }


}
