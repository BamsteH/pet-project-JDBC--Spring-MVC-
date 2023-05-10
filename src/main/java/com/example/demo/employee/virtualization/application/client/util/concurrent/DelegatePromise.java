package com.example.demo.employee.virtualization.application.client.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class DelegatePromise<T> implements Promise<T> {

  private final Promise<T> delegate;

  public DelegatePromise(Promise<T> delegate) {
    this.delegate = delegate;
  }

  @Override
  public T claim() {
    try {
      return delegate.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Promise<T> done(Consumer<? super T> var1) {
    return delegate.done(var1);
  }

  @Override
  public Promise<T> fail(Consumer<Throwable> var1) {
    return delegate.fail(var1);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return delegate.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return delegate.isCancelled();
  }

  @Override
  public boolean isDone() {
    return delegate.isDone();
  }

  @Override
  public T get() throws InterruptedException, ExecutionException {
    return delegate.get();
  }

  @Override
  public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return delegate.get(timeout, unit);
  }
}
