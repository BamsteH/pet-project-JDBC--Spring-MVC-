package com.example.demo.employee.virtualization.controller;

import com.example.demo.employee.virtualization.dto.EmployeeOriginal;
import com.example.demo.employee.virtualization.dto.event.EmployeeAccrualSalaryEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("virtualEmployeeMessageController")
public class EmployeeMessageController {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageController.class);
  private static final BigDecimal SALARY_MIN_VALUE = BigDecimal.valueOf(8197.64);
  private final ApplicationEventPublisher applicationEventPublisher;

  public EmployeeMessageController(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages1(
          String message
  ) {
    logger.debug("1)receive message: {}", message);
    Object event;
    if (JsonAssembler.isJsonArray(message)) {
      EmployeeOriginal[] employees = JsonAssembler.toEmployees(message);
      List<Long> userIds = Arrays.stream(employees)
              .map(EmployeeOriginal::getId)
              .collect(Collectors.toList());
      event = new EmployeeAccrualSalaryEvent(userIds, SALARY_MIN_VALUE);
    } else {
      EmployeeOriginal employee = JsonAssembler.toEmployee(message);
      event = new EmployeeAccrualSalaryEvent(Collections.singletonList(employee.getId()), SALARY_MIN_VALUE);
    }
    applicationEventPublisher.publishEvent(event);
  }

  @RabbitListener(queues = "example-demo-queue")
  public void sortEmployeesAndAccrualWages2(
          String message
  ) {
    logger.debug("2)receive message: {}", message);
    Object event;
    if (JsonAssembler.isJsonArray(message)) {
      EmployeeOriginal[] employees = JsonAssembler.toEmployees(message);
      List<Long> userIds = Arrays.stream(employees)
              .map(EmployeeOriginal::getId)
              .collect(Collectors.toList());
      event = new EmployeeAccrualSalaryEvent(userIds, SALARY_MIN_VALUE);
    } else {
      EmployeeOriginal employee = JsonAssembler.toEmployee(message);
      event = new EmployeeAccrualSalaryEvent(Collections.singletonList(employee.getId()), SALARY_MIN_VALUE);
    }
    applicationEventPublisher.publishEvent(event);
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
