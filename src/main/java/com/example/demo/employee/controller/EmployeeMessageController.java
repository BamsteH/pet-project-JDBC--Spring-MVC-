package com.example.demo.employee.controller;

import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMessageController {

  private final EmployeeService employeeService;

  public List<EmployeeResponse> getEmployeeByDepartmentId(long departmentId) {
    return this.employeeService.readEmployeeByDepartmentId(departmentId);
  }

}
