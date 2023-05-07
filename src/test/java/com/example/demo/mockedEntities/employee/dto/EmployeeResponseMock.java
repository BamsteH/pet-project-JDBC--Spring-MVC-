package com.example.demo.mockedEntities.employee.dto;

import com.example.demo.employee.dto.response.EmployeeResponse;

public class EmployeeResponseMock {

    public static EmployeeResponse employeeResponseBlank1() {
        return new EmployeeResponse("Bamsteh",
                1,
                Boolean.TRUE,
                "Default Department");
    }

    public static EmployeeResponse employeeResponseForChanges() {
        return new EmployeeResponse("BamstehQ",
                1,
                Boolean.TRUE,
                "Default Department");
    }

    public static EmployeeResponse employeeResponseNotActive() {
        return new EmployeeResponse("Bamsteh",
                1,
                Boolean.FALSE,
                "Default Department");
    }

    public static EmployeeResponse employeeResponseBlank2() {
        return new EmployeeResponse("Andrii",
                2,
                Boolean.TRUE,
                "Default Department");
    }


}
