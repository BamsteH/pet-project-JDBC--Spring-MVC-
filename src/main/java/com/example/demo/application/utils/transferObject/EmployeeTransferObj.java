package com.example.demo.application.utils.transferObject;

import com.example.demo.employee.dto.request.EmployeeAddRequest;
import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.dto.request.EmployeeUpdateRequest;
import com.example.demo.employee.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class EmployeeTransferObj {

  public static EmployeeResponse toEmployeeResponse(Employee employee) {
    return new EmployeeResponse(employee.getName(),
            employee.getId(),
            employee.isActive(),
            employee.getDepartmentName());
  }


  public static List<EmployeeResponse> toListEmployeeResponse(List<Employee> employees) {
    return employees.stream().map(EmployeeTransferObj::toEmployeeResponse)
            .collect(Collectors.toList());
  }

  public static Employee fromEmployeeAddRequest(EmployeeAddRequest request) {
    return new Employee(request.getEmployeeName(),
            request.isActive(),
            request.getDepartmentId());
  }

  public static Employee fromEmployeeUpdateRequest(EmployeeUpdateRequest request) {
    return new Employee(request.getName(),
            request.isActive(),
            request.getDepartmentId());
  }

}
