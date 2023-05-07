package com.example.demo.service;

import com.example.demo.employee.dto.response.EmployeeResponse;
import com.example.demo.employee.repository.EmployeeRepository;
import com.example.demo.employee.service.EmployeeService;
import com.example.demo.application.utils.PaginationPointCalculator;
import com.example.demo.application.utils.transferObject.EmployeeTransferObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static com.example.demo.mockedEntities.employee.EmployeeMock.*;
import static com.example.demo.mockedEntities.employee.dto.EmployeeAddRequestMock.*;
import static com.example.demo.mockedEntities.employee.dto.EmployeeUpdateRequestMock.employeeUpdateRequest;
import static org.mockito.Mockito.*;
import static com.example.demo.mockedEntities.employee.dto.EmployeeResponseMock.*;

class EmployeeServiceTest {

    private EmployeeService service;
    private EmployeeRepository repository;


    @BeforeEach
    public void before() {
        this.repository = mock(EmployeeRepository.class);
        this.service = new EmployeeService(this.repository);
    }

    @Test
    void createBlank1() {
        try (MockedStatic<EmployeeTransferObj> transfer
                     = mockStatic(EmployeeTransferObj.class)) {
            transfer.when(() -> EmployeeTransferObj
                            .toEmployeeResponse(employeeBlank1()))
                    .thenReturn(employeeResponseBlank1());
            when(this.repository.create(employeeBlank1()))
                    .thenReturn(employeeBlank1());
            transfer.when(() -> EmployeeTransferObj
                            .fromEmployeeAddRequest((employeeAddRequestBlank1())))
                    .thenReturn(employeeBlank1());

            EmployeeResponse act = this.service
                    .create(employeeAddRequestBlank1());
            EmployeeResponse exp = employeeResponseBlank1();

            Assertions.assertEquals(exp, act);
        }

    }

    @Test
    void createBlank2() {
        try (MockedStatic<EmployeeTransferObj> transfer
                     = mockStatic(EmployeeTransferObj.class)) {
            transfer.when(() -> EmployeeTransferObj.toEmployeeResponse(employeeBlank2()))
                    .thenReturn(employeeResponseBlank2());
            when(this.repository.create(employeeBlank2()))
                    .thenReturn(employeeBlank2());
            transfer.when(() -> EmployeeTransferObj
                            .fromEmployeeAddRequest((employeeAddRequestBlank2())))
                    .thenReturn(employeeBlank2());

            EmployeeResponse act = this.service
                    .create(employeeAddRequestBlank2());
            EmployeeResponse exp = employeeResponseBlank2();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void readEmployeeByIdBlank1() {
        try (MockedStatic<EmployeeTransferObj> transfer
                     = mockStatic(EmployeeTransferObj.class)) {
            transfer.when(() -> EmployeeTransferObj.toEmployeeResponse(employeeBlank1()))
                    .thenReturn(employeeResponseBlank1());
            when(this.repository.getById(1L))
                    .thenReturn(Optional.of(employeeBlank1()));

            EmployeeResponse act = this.service
                    .readEmployeeById(1L);
            EmployeeResponse exp = employeeResponseBlank1();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void readEmployeeByIdBlank2() {
        try (MockedStatic<EmployeeTransferObj> transfer
                     = mockStatic(EmployeeTransferObj.class)) {
            transfer.when(() -> EmployeeTransferObj.toEmployeeResponse(employeeBlank2()))
                    .thenReturn(employeeResponseBlank2());
            when(this.repository.getById(2L))
                    .thenReturn(Optional.of(employeeBlank2()));

            EmployeeResponse act = this.service
                    .readEmployeeById(2L);
            EmployeeResponse exp = employeeResponseBlank2();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void readAllEmployee() {
        try (MockedStatic<PaginationPointCalculator> pointer
                     = mockStatic(PaginationPointCalculator.class)) {
            try (MockedStatic<EmployeeTransferObj> transfer
                         = mockStatic(EmployeeTransferObj.class)) {
                pointer.when(() -> PaginationPointCalculator
                                .getStartPointLimit(0, 10))
                        .thenReturn(0);
                transfer.when(() -> EmployeeTransferObj
                                .toListEmployeeResponse(List.of(employeeBlank1(),
                                        employeeBlank2())))
                        .thenReturn(List.of(employeeResponseBlank1(),
                                employeeResponseBlank2()));
                when(this.repository.getAll(0, 10))
                        .thenReturn(List.of(employeeBlank1(),
                                employeeBlank2()));

                List<EmployeeResponse> act = this.service
                        .readAllEmployee(1, 10);
                List<EmployeeResponse> exp = List.of(employeeResponseBlank1()
                        , employeeResponseBlank2());

                Assertions.assertEquals(exp, act);
            }
        }
    }

    @Test
    void readByStartsWith() {
        try (MockedStatic<PaginationPointCalculator> pointer
                     = mockStatic(PaginationPointCalculator.class)) {
            try (MockedStatic<EmployeeTransferObj> transfer
                         = mockStatic(EmployeeTransferObj.class)) {
                pointer.when(() -> PaginationPointCalculator
                                .getStartPointLimit(0, 10))
                        .thenReturn(0);
                transfer.when(() -> EmployeeTransferObj
                                .toListEmployeeResponse(List.of(employeeBlank1())))
                        .thenReturn(List.of(employeeResponseBlank1()));
                when(this.repository.getByStartsWith("B", 0, 10))
                        .thenReturn(List.of(employeeBlank1()));

                List<EmployeeResponse> act = this.service
                        .readByStartsWith("B", 1, 10);
                List<EmployeeResponse> exp = List.of(employeeResponseBlank1());

                Assertions.assertEquals(exp, act);
            }
        }
    }

    @Test
    void readByStartsWithNonExist() {
        try (MockedStatic<PaginationPointCalculator> pointer
                     = mockStatic(PaginationPointCalculator.class)) {
            try (MockedStatic<EmployeeTransferObj> transfer
                         = mockStatic(EmployeeTransferObj.class)) {
                pointer.when(() -> PaginationPointCalculator
                                .getStartPointLimit(0, 10))
                        .thenReturn(0);
                when(this.repository.getByStartsWith("B", 0, 10))
                        .thenReturn(List.of());

                List<EmployeeResponse> act = this.service
                        .readByStartsWith("b", 1, 10);
                List<EmployeeResponse> exp = List.of();

                Assertions.assertEquals(exp, act);
            }
        }
    }

    @Test
    void update() {
        try (MockedStatic<EmployeeTransferObj> transfer
                     = mockStatic(EmployeeTransferObj.class)) {
            transfer.when(() -> EmployeeTransferObj
                            .fromEmployeeUpdateRequest(employeeUpdateRequest()))
                    .thenReturn(employeeForUpdateChanges());
            when(this.repository.update(employeeForUpdateChanges(), 1L))
                    .thenReturn(Optional.of(employeeUpdated()));
            transfer.when(() -> EmployeeTransferObj
                            .toEmployeeResponse(employeeUpdated()))
                    .thenReturn(employeeResponseForChanges());


            EmployeeResponse act = this.service
                    .update(employeeUpdateRequest(), 1L);
            EmployeeResponse exp = employeeResponseForChanges();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void delete() {
        when(this.repository.delete(1L))
                .thenReturn(true);

        boolean act = this.service
                .delete(1L);

        Assertions.assertTrue(act);
    }
}