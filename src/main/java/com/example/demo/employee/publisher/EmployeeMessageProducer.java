package com.example.demo.employee.publisher;

import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMessageProducer {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageProducer.class);

  @Value("${rabbitmq.queue}")
  private String QUEUE;

  private final RabbitTemplate rabbitTemplate;


  public EmployeeMessageProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(EmployeeResponse employee) {
    this.rabbitTemplate.convertAndSend(QUEUE, JsonAssembler.toJson(employee));
    logger.debug("send message");
  }

  public void sendMessage(EmployeeResponse... employees) {
    this.rabbitTemplate.convertAndSend(QUEUE, JsonAssembler.toJson(employees));
    logger.debug("send message");
  }

  private static class JsonAssembler {

    private static String toJson(EmployeeResponse employee) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        return objectMapper.writeValueAsString(employee);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

    private static String toJson(EmployeeResponse... employees) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        return objectMapper.writeValueAsString(employees);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

  }

}
