package com.example.demo.mockedEntities.employee.dto;

import com.example.demo.employee.dto.request.EmployeeUpdateRequest;

public class EmployeeUpdateRequestMock {

    public static EmployeeUpdateRequest employeeUpdateRequest(){
        return new EmployeeUpdateRequest("BamstehQ",true,1);
    }

    public static EmployeeUpdateRequest employeeUpdateRequestDisable() {
        return new EmployeeUpdateRequest("Bamsteh", false, 1);
    }

}
