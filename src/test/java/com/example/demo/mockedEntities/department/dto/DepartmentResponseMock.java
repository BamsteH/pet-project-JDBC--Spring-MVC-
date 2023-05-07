package com.example.demo.mockedEntities.department.dto;

import com.example.demo.department.dto.response.DepartmentResponse;

public class DepartmentResponseMock {

    public static DepartmentResponse departmentResponseBlank1(){
        return new DepartmentResponse("DefaultDepartment", 1);
    }

    public static DepartmentResponse departmentResponseForChanges(){
        return new DepartmentResponse("DefaultDepartment_changed", 1);
    }
    public static DepartmentResponse departmentResponseBlank2(){
        return new DepartmentResponse("Another Department", 2);
    }

}
