package com.example.demo.utils.transferObject;

import com.example.demo.dto.department.DepartmentAddRequest;
import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.dto.department.DepartmentUpdateRequest;
import com.example.demo.entity.Department;

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
