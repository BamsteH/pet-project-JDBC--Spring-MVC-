package com.example.demo.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest {

    private long id;
    private String name;
    private boolean active;
    private long departmentId;

}
