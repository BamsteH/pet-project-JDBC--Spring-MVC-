package com.example.demo.mockedEntities.department.dto;

import com.example.demo.department.dto.request.DepartmentUpdateRequest;

public class DepartmentUpdateRequestMock {

    public static DepartmentUpdateRequest departmentUpdateRequestBlank1(){
        return new DepartmentUpdateRequest("DefaultDepartment_changed");
    }


}
