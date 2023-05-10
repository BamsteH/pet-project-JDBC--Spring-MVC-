package com.example.demo.employee.virtualization.application.client;

import java.math.BigDecimal;

public interface DirectClient {

  void moneySent( String cardNumber, BigDecimal amount);

}
