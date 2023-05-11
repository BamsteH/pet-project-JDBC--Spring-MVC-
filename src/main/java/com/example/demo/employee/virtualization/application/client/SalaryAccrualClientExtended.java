package com.example.demo.employee.virtualization.application.client;

import java.io.IOException;

public class SalaryAccrualClientExtended implements SalaryAccrualClient{

  private final String http_client;

  public SalaryAccrualClientExtended(String http_client) {
    this.http_client = http_client;
  }

  @Override
  public void close() throws IOException {
  }

  @Override
  public FopClient getFopClient() {
    return new FopClientApi(http_client);
  }

  @Override
  public DirectClient getDirectClient() {
    return new DirectClientApi(http_client);
  }

  @Override
  public HistoryAccrualClient getHistoryAccrualClient() {
    return new HistoryAccrualClientApi(http_client);
  }

}
