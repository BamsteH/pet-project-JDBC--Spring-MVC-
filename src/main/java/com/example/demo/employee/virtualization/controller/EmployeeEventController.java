package com.example.demo.employee.virtualization.controller;

import com.example.demo.employee.virtualization.dto.event.EmployeeAccrualSalaryEvent;
import com.example.demo.employee.virtualization.service.EmployeeService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("virtualEmployeeEventController")
public class EmployeeEventController {

  private final EmployeeService employeeEventService;

  public EmployeeEventController(EmployeeService employeeEventService) {
    this.employeeEventService = employeeEventService;
  }

  @EventListener
  public void handleEmployeeAccrualSalaryEvent(EmployeeAccrualSalaryEvent event) {
    List<Long> employeeIds = event.getEmployeeIds();
    BigDecimal amount = event.getAmount();

    employeeEventService.accrue(employeeIds, amount);
  }

}
