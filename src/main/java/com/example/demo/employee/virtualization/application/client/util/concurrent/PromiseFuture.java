package com.example.demo.employee.virtualization.application.client.util.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PromiseFuture<T> implements Future<T> {

  public static <K> PromiseFuture<K> create(Listener<K> listener, ExecutorService executorService) {
    return new PromiseFuture<>(listener, executorService);
  }

  private Listener<T> listener;
  private ExecutorService listenerExecutor;
  private ReadWriteLock readWriteLock;
  private volatile boolean isCancelled;
  private T object;
  private Exception exception;


  private PromiseFuture(Listener<T> listener, ExecutorService executorService) {
    this.readWriteLock = new ReentrantReadWriteLock();
    this.isCancelled = false;
    this.listener = listener;
    this.listenerExecutor = executorService == null ? Executors.newCachedThreadPool() : executorService;
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    readWriteLock.writeLock().lock();
    try {
      this.isCancelled = true;
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }

  @Override
  public boolean isCancelled() {
    return this.isCancelled;
  }

  @Override
  public boolean isDone() {
    readWriteLock.readLock().lock();
    try {
      return this.object != null;
    } finally {
      readWriteLock.readLock().unlock();
    }
  }

  @Override
  public T get() throws InterruptedException, ExecutionException {
    return this.object;
  }

  public void set(T object) {
    readWriteLock.writeLock().lock();
    try {
      if (this.exception != null) {
        return;
      }
      this.object = object;
      if (this.listenerExecutor == null) {
        this.listener.settableSet(object, null);
      } else {
        this.listenerExecutor.submit(new Runnable() {
          @Override
          public void run() {
            listener.settableSet(object, null);
          }
        });
      }
      this.listener.settableSet(object, null);
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }

  public void setError(Exception exception) {
    readWriteLock.writeLock().lock();
    try {
      if (this.exception != null) {
        return;
      }
      this.exception = exception;
      this.listener.settableSet(null, exception);
    } finally {
      readWriteLock.writeLock().unlock();
    }
  }

  @Override
  public T get(long timeout, TimeUnit unit) throws InterruptedException,
          ExecutionException,
          TimeoutException {
    return this.object;
  }

  public interface Listener<T> {
    void settableSet(T result, Exception e);
  }
}
