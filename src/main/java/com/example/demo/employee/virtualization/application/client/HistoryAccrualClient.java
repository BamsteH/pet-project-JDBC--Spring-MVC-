package com.example.demo.employee.virtualization.application.client;

import com.example.demo.employee.virtualization.application.client.model.Accrual;
import com.example.demo.employee.virtualization.application.client.util.concurrent.PromiseFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

public interface HistoryAccrualClient {

  Accrual getAccrual(LocalDate from, LocalDate to) throws ExecutionException, InterruptedException;

}
