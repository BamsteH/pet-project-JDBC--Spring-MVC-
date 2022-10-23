package com.example.demo.service;

import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.utils.PaginationPointCalculator;
import com.example.demo.utils.transferObject.DepartmentTransferObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Optional;

import static com.example.demo.mockedEntities.department.DepartmentMock.*;
import static com.example.demo.mockedEntities.department.dto.DepartmentResponseMock.*;
import static com.example.demo.mockedEntities.department.dto.DepartmentAddRequestMock.*;
import static com.example.demo.mockedEntities.department.dto.DepartmentUpdateRequestMock.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    private DepartmentService service;
    private DepartmentRepository repository;


    @BeforeEach
    public void before() {
        this.repository = mock(DepartmentRepository.class);
        this.service = new DepartmentService(this.repository);
    }


    @Test
    void getByIdBlank1() {
        try (MockedStatic<DepartmentTransferObject> transfer
                     = mockStatic(DepartmentTransferObject.class)) {

            when(this.repository.getById(1L))
                    .thenReturn(Optional.of(departmentBlank1()));
            transfer.when(() -> DepartmentTransferObject.toResponse(departmentBlank1()))
                    .thenReturn(departmentResponseBlank1());

            DepartmentResponse act = this.service.getById(1);
            DepartmentResponse exp = departmentResponseBlank1();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void getByIdBlank2() {
        try (MockedStatic<DepartmentTransferObject> transfer
                     = mockStatic(DepartmentTransferObject.class)) {
            when(this.repository.getById(2L))
                    .thenReturn(Optional.of(departmentBlank2()));
            transfer.when(() -> DepartmentTransferObject.toResponse(departmentBlank2()))
                    .thenReturn(departmentResponseBlank2());

            DepartmentResponse act = this.service.getById(2);
            DepartmentResponse exp = departmentResponseBlank2();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void createBlank1() {
        try (MockedStatic<DepartmentTransferObject> transfer
                     = mockStatic(DepartmentTransferObject.class)) {
            transfer.when(() -> DepartmentTransferObject
                            .fromAddRequest(departmentAddRequest1()))
                    .thenReturn(departmentBlank1());
            when(this.repository.create(departmentBlank1()))
                    .thenReturn(departmentBlank1());
            transfer.when(() -> DepartmentTransferObject
                            .toResponse(departmentBlank1()))
                    .thenReturn(departmentResponseBlank1());

            DepartmentResponse act = this.service
                    .create(departmentAddRequest1());
            DepartmentResponse exp = departmentResponseBlank1();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void createBlank2() {
        try (MockedStatic<DepartmentTransferObject> transfer
                     = mockStatic(DepartmentTransferObject.class)) {
            transfer.when(() -> DepartmentTransferObject
                            .fromAddRequest(departmentAddRequest2()))
                    .thenReturn(departmentBlank2());
            when(this.repository.create(departmentBlank2()))
                    .thenReturn(departmentBlank2());
            transfer.when(() -> DepartmentTransferObject
                            .toResponse(departmentBlank2()))
                    .thenReturn(departmentResponseBlank2());

            DepartmentResponse act = this.service
                    .create(departmentAddRequest2());
            DepartmentResponse exp = departmentResponseBlank2();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void update() {
        try (MockedStatic<DepartmentTransferObject> transfer
                     = mockStatic(DepartmentTransferObject.class)) {
            transfer.when(() -> DepartmentTransferObject
                            .fromUpdateRequest(departmentUpdateRequestBlank1(), 1L))
                    .thenReturn(departmentBlank1());
            when(this.repository.update(departmentBlank1(), 1L))
                    .thenReturn(Optional.of(departmentBlank1ForChanges()));
            transfer.when(() -> DepartmentTransferObject
                            .toResponse(departmentBlank1ForChanges()))
                    .thenReturn(departmentResponseForChanges());

            DepartmentResponse act = this.service
                    .update(departmentUpdateRequestBlank1(), 1L);
            DepartmentResponse exp = departmentResponseForChanges();

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    void delete() {
        when(this.repository.delete(1L))
                .thenReturn(Boolean.TRUE);

        boolean act = this.service
                .delete(1);

        Assertions.assertTrue(act);
    }

    @Test
    void deleteNotExist() {
        when(this.repository.delete(4L))
                .thenReturn(Boolean.FALSE);

        boolean act = this.service
                .delete(4);

        Assertions.assertFalse(act);
    }

    @Test
    void getAll() {
        try (MockedStatic<PaginationPointCalculator> point
                     = mockStatic(PaginationPointCalculator.class)) {
            try (MockedStatic<DepartmentTransferObject> transfer =
                         mockStatic(DepartmentTransferObject.class)) {
                point.when(() -> PaginationPointCalculator
                                .getStartPointLimit(1, 10))
                        .thenReturn(0);
                transfer.when(() -> DepartmentTransferObject
                                .toResponse(departmentBlank1()))
                        .thenReturn(departmentResponseBlank1());
                transfer.when(() -> DepartmentTransferObject
                                .toResponse(departmentBlank2()))
                        .thenReturn(departmentResponseBlank2());
                when(this.repository.getAll(0, 10))
                        .thenReturn(List.of(departmentBlank1(),
                                departmentBlank2()));

                List<DepartmentResponse> act = this.service
                        .getAll(1, 10);
                List<DepartmentResponse> exp = List.of(departmentResponseBlank1(),
                        departmentResponseBlank2());

                Assertions.assertEquals(exp, act);
            }
        }
    }
    
    @Test
    void getAllNextPage() {
        try (MockedStatic<PaginationPointCalculator> point
                     = mockStatic(PaginationPointCalculator.class)) {
            try (MockedStatic<DepartmentTransferObject> transfer =
                         mockStatic(DepartmentTransferObject.class)) {
                point.when(() -> PaginationPointCalculator
                                .getStartPointLimit(2, 10))
                        .thenReturn(10);
                when(this.repository.getAll(10, 20))
                        .thenReturn(List.of());

                List<DepartmentResponse> act = this.service
                        .getAll(1, 10);
                List<DepartmentResponse> exp = List.of();

                Assertions.assertEquals(exp, act);
            }
        }
    }
}