package com.example.demo.employee.controller;

import com.example.demo.application.notification.NewEmployeeEventMessage;
import com.example.demo.employee.dto.request.EmployeeAddRequest;
import com.example.demo.employee.dto.request.EmployeeUpdateRequest;
import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService service;
  private final ApplicationEventPublisher publisher;
  private final RabbitTemplate rabbitTemplate;

  public EmployeeController(
          EmployeeService service,
          ApplicationEventPublisher publisher,
          RabbitTemplate rabbitTemplate
  ) {
    this.service = service;
    this.publisher = publisher;
    this.rabbitTemplate = rabbitTemplate;
  }

  @PostMapping()
  public EmployeeResponse createEmployee(@RequestBody EmployeeAddRequest employee) {
    EmployeeResponse newEmployee = this.service.create(employee);

    Object message = new NewEmployeeEventMessage(
            newEmployee.getId(),
            newEmployee.getDepartmentName()
    );
    this.publisher.publishEvent(message);

    return newEmployee;
  }

  @GetMapping("/{id}")
  public EmployeeResponse readEmployeeById(@PathVariable long id) {
    return this.service.readEmployeeById(id);
  }

  @GetMapping("/all")
  public List<EmployeeResponse> readAllEmployee(@RequestParam(defaultValue = "10") int limit,
                                                @RequestParam(defaultValue = "1") int page) {
    return this.service.readAllEmployee(page, limit);
  }

  @GetMapping("/search")
  public List<EmployeeResponse> readByStartsWith(@RequestParam String startWith,
                                                 @RequestParam(defaultValue = "10") int limit,
                                                 @RequestParam(defaultValue = "1") int page) {
    return this.service.readByStartsWith(startWith, page, limit);
  }

  @PutMapping("/{id}")
  public EmployeeResponse updateEmployee(@PathVariable long id,
                                         @RequestBody EmployeeUpdateRequest request) {
    return this.service.update(request, id);
  }

  @DeleteMapping("{id}")
  public boolean deleteEmployeeById(@PathVariable long id) {
    return this.service.delete(id);
  }

  @PatchMapping("/accrue-salary")
  public void accrueSalary() {
    EmployeeResponse employeeResponse = new EmployeeResponse("nameEmployee", 1, true, "departmentName");

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonEmployee = objectMapper.writeValueAsString(employeeResponse);
      rabbitTemplate.convertAndSend(
              "example-demo-queue",
              jsonEmployee
      );
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
