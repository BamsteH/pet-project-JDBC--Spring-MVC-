package com.example.demo.employee.virtualization;

import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// I will add to method sortEmployeesAndAccrualWages() receiving messages from the rabbitMq message
// and do some logic


@Component("virtualEmployeeMessageController")
public class EmployeeMessageController {

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages1(
          String message
  ) {
    System.out.println("Received1 <" + message + ">");
  }

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages2(
          String message
  ) {
    System.out.println("Received2 <" + message + ">");
  }


  @QueueBinding(  )


}
