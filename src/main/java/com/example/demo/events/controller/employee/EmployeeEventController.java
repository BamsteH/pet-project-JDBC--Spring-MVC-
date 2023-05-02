package com.example.demo.events.controller.employee;

import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component("employeeEventController")
public class EmployeeEventController {

  private final EmployeeService employeeEventService;

  public EmployeeEventController(EmployeeService employeeEventService) {
    this.employeeEventService = employeeEventService;
  }

}
