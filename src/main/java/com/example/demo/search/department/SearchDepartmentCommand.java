package com.example.demo.search.department;

import com.example.demo.dto.department.DepartmentExtendResponse;
import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.service.DepartmentService;

import java.util.function.Function;

class SearchDepartmentCommand implements Function<Long, DepartmentExtendResponse> {

  private final DepartmentService departmentService;

  SearchDepartmentCommand(DepartmentService departmentService) {
  this.departmentService = departmentService;
  }


  @Override
  public DepartmentExtendResponse apply(Long id) {
    DepartmentResponse response = departmentService.getById(id);

    DepartmentExtendResponse model = new DepartmentExtendResponse();
    model.setId(response.getId());
    model.setName(response.getName());

    return model;
  }
}
