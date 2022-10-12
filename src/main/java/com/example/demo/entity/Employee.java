package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long empId;
    private String empName;
    private boolean empActive;
    private Long emp_dpID;
    private String dpName;
}
