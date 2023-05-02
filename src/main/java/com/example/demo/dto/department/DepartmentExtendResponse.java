package com.example.demo.dto.department;

import com.example.demo.dto.employee.EmployeeResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentExtendResponse {

  private long id;
  private String name;
  private List<EmployeeResponse> employees;

}
