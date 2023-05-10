package com.example.demo.employee.virtualization.application.client.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Accrual {

  private String id;
  private String recipientCardNumber;
  private BigDecimal amount;

}
