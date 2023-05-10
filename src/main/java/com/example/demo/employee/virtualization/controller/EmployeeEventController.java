package com.example.demo.employee.virtualization.controller;

import com.example.demo.employee.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component("virtualEmployeeEventController")
public class EmployeeEventController {


  public EmployeeEventController(EmployeeService employeeEventService) {
  }



}
