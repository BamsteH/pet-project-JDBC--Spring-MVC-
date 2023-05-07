package com.example.demo.mockedEntities.department;

import com.example.demo.department.entity.Department;

public class DepartmentMock {

    public static Department getDepartmentEmpty() {
        return new Department();
    }

    public static Department departmentBlank1() {
        return new Department(1,
                "DefaultDepartment");
    }

    public static Department departmentBlank1ForChanges() {
        return new Department(1,
                "DefaultDepartment_changed");
    }

    public static Department departmentBlank2() {
        return new Department(2,
                "Another Department");
    }


}
