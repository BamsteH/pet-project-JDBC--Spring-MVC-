package com.example.demo.employee.virtualization.application.client.util.concurrent;

import com.example.demo.employee.virtualization.application.client.model.Accrual;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public final class WrappingAccrualPromise implements PromiseAccrual {

  private final Promise<Accrual> delegate;

  public WrappingAccrualPromise(Promise<Accrual> delegate) {
    if (delegate == null) {
      throw new NullPointerException("delegate is null");
    }
    this.delegate = delegate;
  }

  @Override
  public Accrual claim() {
    return (Accrual) this.delegate.claim();
  }

  @Override
  public Promise<Accrual> done(Consumer<? super Accrual> var1) {
    return this.delegate.done(var1);
  }

  @Override
  public Promise<Accrual> fail(Consumer<Throwable> var1) {
    return this.delegate.fail(var1);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return this.delegate.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return this.delegate.isCancelled();
  }

  @Override
  public boolean isDone() {
    return this.delegate.isDone();
  }

  @Override
  public Accrual get() throws InterruptedException, ExecutionException {
    return (Accrual) this.delegate.get();
  }

  @Override
  public Accrual get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return (Accrual) this.delegate.get(timeout, unit);
  }
}
