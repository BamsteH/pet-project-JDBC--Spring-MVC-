package com.example.demo.mockedEntities.department.dto;

import com.example.demo.department.dto.request.DepartmentAddRequest;

public class DepartmentAddRequestMock {

    public static DepartmentAddRequest departmentAddRequest1(){
        return new DepartmentAddRequest("DefaultDepartment");
    }

    public static DepartmentAddRequest departmentAddRequest2(){
        return new DepartmentAddRequest("Another Department");
    }


}
