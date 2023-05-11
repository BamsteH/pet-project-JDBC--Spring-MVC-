package com.example.demo.employee.virtualization.application.client.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PromiseApi {

  private ExecutorService executorService;

  public PromiseApi() {
    this.executorService = Executors.newCachedThreadPool();
  }

  public <T> PromiseFuture<T> promise(Callable<T> task, PromiseFuture.Listener<T> listener, ExecutorService listenerExecutor) {
    PromiseFuture<T> future = PromiseFuture.create(listener, listenerExecutor);
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        try {
          T result = task.call();
          future.set(result);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    return future;
  }

}
