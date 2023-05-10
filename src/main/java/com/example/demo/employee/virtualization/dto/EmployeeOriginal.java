package com.example.demo.employee.virtualization.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeOriginal {

  private Long id;
    private String nameEmployee;
    // in message departmentId is not received for
    // testing JsonIgnoreProperties for unknown properties
    private Long departmentId;
    private boolean isActive;

}
