package com.example.demo.employee.virtualization.application.client;

import java.io.Closeable;
import java.math.BigDecimal;

public interface SalaryAccrualClient extends Closeable {

  FopClient getFopClient();

  DirectClient getDirectClient();

}
