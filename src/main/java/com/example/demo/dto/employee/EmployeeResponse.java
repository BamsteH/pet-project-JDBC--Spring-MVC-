package com.example.demo.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private String nameEmployee;
    private long id;
    private boolean isActive;
    private String departmentName;

}
