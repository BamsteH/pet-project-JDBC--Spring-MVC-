package com.example.demo.employee.virtualization.service;

import com.example.demo.employee.virtualization.application.client.SalaryAccrualClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("virtualEmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

  private final SalaryAccrualClient salaryAccrualClient;

  public EmployeeServiceImpl(SalaryAccrualClient salaryAccrualClient) {
    this.salaryAccrualClient = salaryAccrualClient;
  }


  @Override
  public void accrue(List<Long> userIds, BigDecimal amount) {
    userIds.forEach(userId -> salaryAccrualClient
            .getDirectClient()
            .moneySent(userId.toString(), amount)
    );
  }

}
