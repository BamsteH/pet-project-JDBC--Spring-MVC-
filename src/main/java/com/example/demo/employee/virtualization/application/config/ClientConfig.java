package com.example.demo.employee.virtualization.application.config;

import com.example.demo.employee.virtualization.application.client.SalaryAccrualClient;
import com.example.demo.employee.virtualization.application.client.SalaryAccrualClientExtended;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

  @Bean
  public SalaryAccrualClient salaryAccrualClient() {
    return new SalaryAccrualClientExtended("http://localhost:65000");
  }

}
