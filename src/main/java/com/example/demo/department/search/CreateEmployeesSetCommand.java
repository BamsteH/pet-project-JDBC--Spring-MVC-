package com.example.demo.department.search;

import com.example.demo.department.dto.response.DepartmentExtendResponse;
import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.controller.EmployeeMessageController;

import java.util.List;
import java.util.function.UnaryOperator;

class CreateEmployeesSetCommand implements UnaryOperator<DepartmentExtendResponse> {

  private final EmployeeMessageController employeeMessageController;

  CreateEmployeesSetCommand(
          EmployeeMessageController employeeMessageController
  ) {
    this.employeeMessageController = employeeMessageController;
  }


  @Override
  public DepartmentExtendResponse apply(DepartmentExtendResponse response) {
    long departmentId = response.getId();
    List<EmployeeResponse> employees =
            employeeMessageController.getEmployeeByDepartmentId(departmentId);
    response.setEmployees(employees);

    return response;
  }
}
