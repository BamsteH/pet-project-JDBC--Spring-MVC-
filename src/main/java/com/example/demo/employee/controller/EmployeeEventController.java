package com.example.demo.employee.controller;

import com.example.demo.employee.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component("employeeEventController")
public class EmployeeEventController {

  private final EmployeeService employeeEventService;

  public EmployeeEventController(EmployeeService employeeEventService) {
    this.employeeEventService = employeeEventService;
  }

}
