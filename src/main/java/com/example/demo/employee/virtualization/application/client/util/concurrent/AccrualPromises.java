package com.example.demo.employee.virtualization.application.client.util.concurrent;

import com.example.demo.employee.virtualization.application.client.model.Accrual;

public final class AccrualPromises {

  private AccrualPromises() {
  }

  public static PromiseAccrual toAccrualPromise(Promise<Accrual> promise) {
    return new WrappingAccrualPromise(promise);
  }

}
