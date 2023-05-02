package com.example.demo.search.department;

import com.example.demo.dto.department.DepartmentExtendResponse;
import com.example.demo.dto.employee.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.remote.EmployeeMessageController;
import com.example.demo.utils.transferObject.EmployeeTransferObj;

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
