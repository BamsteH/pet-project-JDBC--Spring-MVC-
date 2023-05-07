package com.example.demo.mockedEntities.employee.dto;

import com.example.demo.employee.dto.request.EmployeeAddRequest;

public class EmployeeAddRequestMock {

    public static EmployeeAddRequest employeeAddRequestBlank1() {
        return new EmployeeAddRequest("Bamsteh",
                true,
                1);
    }

    public static EmployeeAddRequest employeeAddRequestBlank2() {
        return new EmployeeAddRequest("Bamsteh",
                true,
                1);
    }

}
