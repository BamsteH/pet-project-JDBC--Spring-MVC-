package com.example.demo.employee.virtualization.application.client;

import com.example.demo.employee.virtualization.application.client.model.Accrual;
import com.example.demo.employee.virtualization.application.client.util.concurrent.PromiseApi;
import com.example.demo.employee.virtualization.application.client.util.concurrent.PromiseFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HistoryAccrualClientApi implements HistoryAccrualClient {

  private final String httpClient;
  private static final String getAccrual = "/getAccrual";

  public HistoryAccrualClientApi(String httpClient) {
    this.httpClient = httpClient;
  }

  @Override
  public Accrual getAccrual(LocalDate from, LocalDate to) throws ExecutionException, InterruptedException {
    Future<Accrual> future = new PromiseApi()
            .promise(() -> {
                      Thread.sleep(1000);
                      System.out.println("GET " + httpClient + getAccrual + "?from= " + from + "&to=" + to);
                      Accrual accrual = new Accrual();
                      accrual.setAmount("1000");
                      accrual.setCardNumber("123456789");
                      accrual.setFop("FOP");
                      accrual.setId("1");
                      return accrual;
                    }, new PromiseFuture.Listener<Accrual>() {
                      @Override
                      public void settableSet(Accrual result, Exception e) {
                        System.out.println("result = " + result);
                      }
                    },
                    Executors.newCachedThreadPool()
            );
    return future.get();
  }



}
