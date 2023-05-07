package com.example.demo.department.search;

import com.example.demo.department.dto.response.DepartmentExtendResponse;
import com.example.demo.department.dto.response.DepartmentResponse;
import com.example.demo.department.service.DepartmentService;

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
