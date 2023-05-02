package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  private long id;
  private String name;
  private boolean active;
  private Long departmentId;
  private String departmentName;

  public Employee(String name, boolean active, Long departmentId) {
    this.name = name;
    this.active = active;
    this.departmentId = departmentId;
  }
}
