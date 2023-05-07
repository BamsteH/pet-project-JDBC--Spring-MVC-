package com.example.demo.application.utils.transferObject;

import com.example.demo.department.dto.request.DepartmentAddRequest;
import com.example.demo.department.dto.response.DepartmentResponse;
import com.example.demo.department.dto.request.DepartmentUpdateRequest;
import com.example.demo.department.entity.Department;

@Deprecated
public class DepartmentTransferObject {

  public static Department fromAddRequest(DepartmentAddRequest request) {
    Department department = new Department();
    department.setName(request.getName());
    return department;
  }

  public static DepartmentResponse toResponse(Department department) {
    return new DepartmentResponse(department.getName(), department.getId());
  }

  public static Department fromUpdateRequest(DepartmentUpdateRequest request, Long id) {
    return new Department(id, request.getName());
  }

}
