package com.example.demo.controller;

import com.example.demo.employee.dto.request.EmployeeAddRequest;
import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.controller.EmployeeController;
import com.example.demo.employee.publisher.EmployeeMessageProducer;
import com.example.demo.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static com.example.demo.mockedEntities.employee.dto.EmployeeAddRequestMock.*;
import static com.example.demo.mockedEntities.employee.dto.EmployeeResponseMock.*;
import static com.example.demo.mockedEntities.employee.dto.EmployeeUpdateRequestMock.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    private EmployeeController controller;
    private EmployeeService service;
    private ApplicationEventPublisher publisher;
    private EmployeeMessageProducer employeeMessageProducer;

    @BeforeEach
    public void before() {
        this.service = mock(EmployeeService.class);
        this.publisher = mock(ApplicationEventPublisher.class);
        this.employeeMessageProducer = mock(EmployeeMessageProducer.class);
        this.controller = new EmployeeController(this.service, this.publisher, this.employeeMessageProducer);
    }

    @Test
    void createEmployeeBlank1() {
        EmployeeAddRequest request = employeeAddRequestBlank1();
        when(this.service.create(request))
                .thenReturn(employeeResponseBlank1());

        EmployeeResponse act = this.controller
                .createEmployee(employeeAddRequestBlank1());
        EmployeeResponse exp = employeeResponseBlank1();

        Assertions.assertEquals(exp, act);
    }


    @Test
    void createEmployeeBlank2() {
        EmployeeAddRequest request = employeeAddRequestBlank2();
        when(this.service.create(request))
                .thenReturn(employeeResponseBlank2());

        EmployeeResponse act = this.controller
                .createEmployee(employeeAddRequestBlank2());
        EmployeeResponse exp = employeeResponseBlank2();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void readEmployeeByIdBlank1() {
        when(this.service.readEmployeeById(1))
                .thenReturn(employeeResponseBlank1());

        EmployeeResponse act = this.controller
                .readEmployeeById(1);
        EmployeeResponse exp = employeeResponseBlank1();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void readEmployeeByIdBlank2() {
        when(this.service.readEmployeeById(2))
                .thenReturn(employeeResponseBlank2());

        EmployeeResponse act = this.controller
                .readEmployeeById(2);
        EmployeeResponse exp = employeeResponseBlank2();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void readAllEmployee() {
        when(this.service.readAllEmployee(1, 10))
                .thenReturn(List.of(employeeResponseBlank1(),
                        employeeResponseBlank2()));

        List<EmployeeResponse> act = this.controller
                .readAllEmployee(10, 1);
        List<EmployeeResponse> exp = List.of(employeeResponseBlank1(),
                employeeResponseBlank2());

        Assertions.assertEquals(exp, act);
    }

    @Test
    void readByStartsWith() {
        when(this.service.readByStartsWith("B", 1, 10))
                .thenReturn(List.of(employeeResponseBlank1()));

        List<EmployeeResponse> act = this.controller
                .readByStartsWith("B", 10, 1);
        List<EmployeeResponse> exp = List.of(employeeResponseBlank1());

        Assertions.assertEquals(exp, act);
    }


    @Test
    void readByStartsWithEmpty() {
        when(this.service.readByStartsWith("b", 1, 10))
                .thenReturn(List.of());

        List<EmployeeResponse> act = this.controller
                .readByStartsWith("b", 10, 1);
        List<EmployeeResponse> exp = List.of();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void updateEmployee() {
        when(this.service.update(employeeUpdateRequest(), 1L))
                .thenReturn(employeeResponseForChanges());

        EmployeeResponse act = this.controller
                .updateEmployee(1, employeeUpdateRequest());
        EmployeeResponse exp = employeeResponseForChanges();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void updateEmployeeDisActivate() {
        when(this.service.update(employeeUpdateRequestDisable(), 1L))
                .thenReturn(employeeResponseNotActive());

        EmployeeResponse act = this.controller
                .updateEmployee(1, employeeUpdateRequestDisable());
        EmployeeResponse exp = employeeResponseNotActive();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void deleteEmployeeById() {
        when(this.controller.deleteEmployeeById(1))
                .thenReturn(Boolean.TRUE);

        boolean act = this.controller.deleteEmployeeById(1);

        Assertions.assertTrue(act);
    }

    @Test
    void deleteEmployeeByIdNotExist() {
        when(this.controller.deleteEmployeeById(5))
                .thenReturn(Boolean.FALSE);

        boolean act = this.controller.deleteEmployeeById(5);

        Assertions.assertFalse(act);
    }
}