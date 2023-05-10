package com.example.demo.employee.virtualization.application.client;

import java.math.BigDecimal;

public interface FopClient {

  void moneySent(String fop, String cardNumber, BigDecimal amount);

}
