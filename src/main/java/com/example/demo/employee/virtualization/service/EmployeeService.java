package com.example.demo.employee.virtualization.service;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

  void accrue(List<Long> userIds, BigDecimal amount);

}
