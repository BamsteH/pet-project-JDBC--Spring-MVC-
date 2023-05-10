package com.example.demo.employee.virtualization.controller;

import com.example.demo.employee.virtualization.dto.EmployeeOriginal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("virtualEmployeeMessageController")
public class MessageController {

  private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages1(
          String message
  ) {
    logger.debug("1)receive message: {}", message);
    System.out.println("Received1 <" + message + ">");
  }

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages2(
          String message
  ) {
    logger.debug("2)receive message: {}", message);
    if (JsonAssembler.isJsonArray(message)) {
      EmployeeOriginal[] employees = JsonAssembler.toEmployees(message);
      for (EmployeeOriginal employee : employees) {
        System.out.println("Received2 <" + employee + ">");
      }
    } else {
      EmployeeOriginal employee = JsonAssembler.toEmployee(message);
      System.out.println("Received2 <" + employee + ">");
    }
  }

  private static class JsonAssembler {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private static boolean isJsonArray(String json) {
      try {
        return objectMapper.readTree(json).isArray();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    private static EmployeeOriginal toEmployee(String json) {
        try {
            return objectMapper.readValue(json, EmployeeOriginal.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static EmployeeOriginal[] toEmployees(String json) {
        try {
            return objectMapper.readValue(json, EmployeeOriginal[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  }

}
