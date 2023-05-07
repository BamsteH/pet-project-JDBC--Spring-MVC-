package com.example.demo.controller;

import com.example.demo.department.controller.DepartmentController;
import com.example.demo.department.dto.request.DepartmentAddRequest;
import com.example.demo.department.dto.response.DepartmentResponse;
import com.example.demo.department.dto.request.DepartmentUpdateRequest;
import com.example.demo.department.search.SearchDepartmentFactory;
import com.example.demo.department.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.demo.mockedEntities.department.dto.DepartmentAddRequestMock.*;
import static com.example.demo.mockedEntities.department.dto.DepartmentResponseMock.*;
import static com.example.demo.mockedEntities.department.dto.DepartmentUpdateRequestMock.departmentUpdateRequestBlank1;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    private DepartmentController controller;
    private DepartmentService service;
    private SearchDepartmentFactory searchDepartmentFactory;

    @BeforeEach
    public void before() {
        this.service = mock(DepartmentService.class);
        this.searchDepartmentFactory = mock(SearchDepartmentFactory.class);
        this.controller = new DepartmentController(this.service, this.searchDepartmentFactory);
    }


    @Test
    void createDepartmentBlank1() {
        DepartmentAddRequest request = departmentAddRequest1();
        when(this.service.create(request))
                .thenReturn(departmentResponseBlank1());

        DepartmentResponse exp = departmentResponseBlank1();
        DepartmentResponse act = controller.createDepartment(request);

        Assertions.assertEquals(exp, act);
    }

    @Test
    void createDepartmentBlank2() {
        DepartmentAddRequest request = departmentAddRequest2();
        when(this.service.create(request))
                .thenReturn(departmentResponseBlank2());

        DepartmentResponse act = this.controller.createDepartment(request);
        DepartmentResponse exp = departmentResponseBlank2();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void updateDepartment() {
        DepartmentUpdateRequest request = departmentUpdateRequestBlank1();
        when(this.service.update(request, 1L))
                .thenReturn(departmentResponseForChanges());

        DepartmentResponse act = this.controller
                .updateDepartment(1, request);
        DepartmentResponse exp = departmentResponseForChanges();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void getById() {
        when(this.service.getById(1))
                .thenReturn(departmentResponseBlank1());

        DepartmentResponse act = this.controller
                .getById(1);
        DepartmentResponse exp = departmentResponseBlank1();

        Assertions.assertEquals(exp, act);
    }

    @Test
    void getAll() {
        when(this.service.getAll(1, 10))
                .thenReturn(List.of(departmentResponseBlank1(),
                        departmentResponseBlank2()));

        List<DepartmentResponse> act = this.controller
                .getAll(10, 1);
        List<DepartmentResponse> exp = List.of(departmentResponseBlank1(),
                departmentResponseBlank2());

        Assertions.assertEquals(exp, act);
    }

    @Test
    void deleteDepartment() {
        when(this.service.delete(1)).thenReturn(true);

        boolean act = this.controller.deleteDepartment(1);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    void deleteDepartmentNonExist() {
        when(this.service.delete(4)).thenReturn(false);

        boolean act = this.controller.deleteDepartment(4);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }
}