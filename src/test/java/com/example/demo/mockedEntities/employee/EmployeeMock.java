package com.example.demo.mockedEntities.employee;

import com.example.demo.employee.entity.Employee;

public class EmployeeMock {

    public static Employee employeeBlank1() {
        return new Employee(1,
                "Bamsteh",
                Boolean.TRUE,
                1L,
                "DefaultDepartment");
    }

    public static Employee employeeForUpdateChanges() {
        return new Employee(1,
                "BamstehQ",
                Boolean.TRUE,
                1L,
                "DefaultDepartment");
    }

    public static Employee employeeUpdated() {
        return new Employee("BamstehQ",
                Boolean.TRUE,
                1L);
    }

    public static Employee employeeNotActive() {
        return new Employee(1,
                "Bamsteh",
                Boolean.TRUE,
                1L,
                "DefaultDepartment");
    }

    public static Employee employeeBlank2() {
        return new Employee(2,
                "Andrii",
                Boolean.TRUE,
                1L,
                "DefaultDepartment");
    }

}
