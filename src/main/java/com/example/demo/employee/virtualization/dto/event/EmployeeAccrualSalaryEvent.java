package com.example.demo.employee.virtualization.dto.event;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class EmployeeAccrualSalaryEvent {

  private final List<Long> employeeIds;
  private final BigDecimal amount;

}
