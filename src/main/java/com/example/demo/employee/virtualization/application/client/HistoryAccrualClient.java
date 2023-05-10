package com.example.demo.employee.virtualization.application.client;

import com.example.demo.employee.virtualization.application.client.model.Accrual;
import com.example.demo.employee.virtualization.application.client.util.concurrent.Promise;

import java.time.LocalDate;

public interface HistoryAccrualClient {

  Promise<Accrual> getAccrual(LocalDate from, LocalDate to);

}
