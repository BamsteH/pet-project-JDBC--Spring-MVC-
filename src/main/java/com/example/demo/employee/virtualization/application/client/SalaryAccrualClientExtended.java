package com.example.demo.employee.virtualization.application.client;

import java.io.IOException;

public class SalaryAccrualClientExtended implements SalaryAccrualClient{

  @Override
  public void close() throws IOException {
  }

  @Override
  public FopClient getFopClient() {
    return null;
  }

  @Override
  public DirectClient getDirectClient() {
    return null;
  }

}
