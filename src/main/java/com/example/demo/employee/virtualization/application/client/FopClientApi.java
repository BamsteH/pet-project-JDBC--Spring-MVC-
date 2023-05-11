package com.example.demo.employee.virtualization.application.client;

import java.math.BigDecimal;

public class FopClientApi implements FopClient, AutoCloseable {

  private final String httpClient;

  public FopClientApi(String httpClient) {
    this.httpClient = httpClient;
  }


  @Override
  public void moneySent(String fop, String cardNumber, BigDecimal amount) {
    moneySentViaApi(fop, cardNumber, amount, httpClient);
  }

  @Override
  public void close() throws Exception {

  }

  private void moneySentViaApi(String fop, String cardNumber, BigDecimal amount, String httpClient) {
    System.out.println(
            "Sending money via http client: " + httpClient
                    + " to card number: " + cardNumber
                    + " amount: " + amount
                    + " fop: " + fop
    );
  }

}
