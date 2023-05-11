package com.example.demo.employee.virtualization.application.client;

import java.math.BigDecimal;

public class DirectClientApi implements DirectClient, AutoCloseable {

  private final String httpClient;

  public DirectClientApi(String httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public void moneySent(String cardNumber, BigDecimal amount) {
    moneySentViaApi(cardNumber, amount, httpClient);
  }


  @Override
  public void close() throws Exception {
  }

  private void moneySentViaApi(String cardNumber, BigDecimal amount, String httpClient) {
    System.out.println(
            "Sending money via http client: " + httpClient
                    + " to card number: " + cardNumber
                    + " amount: " + amount
    );
  }
}
