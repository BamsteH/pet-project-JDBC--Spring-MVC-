package com.example.demo.remote;

import com.example.demo.dto.employee.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
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
