package com.example.demo.department.dto.response;

import com.example.demo.employee.dto.response.EmployeeResponse;
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
