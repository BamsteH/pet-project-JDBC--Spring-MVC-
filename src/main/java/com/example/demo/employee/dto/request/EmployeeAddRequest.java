package com.example.demo.employee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddRequest {
  @NonNull
  private String employeeName;
  private boolean active;
  private long departmentId;

}
