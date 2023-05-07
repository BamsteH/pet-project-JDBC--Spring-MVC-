package com.example.demo.department.search;

import com.example.demo.department.dto.response.DepartmentExtendResponse;
import com.example.demo.employee.controller.EmployeeMessageController;
import com.example.demo.department.service.DepartmentService;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Component("searchDepartmentFactory")
public class SearchDepartmentFactory {

  private final DepartmentService departmentService;
  private final EmployeeMessageController employeeMessageController;

    public SearchDepartmentFactory(
            DepartmentService departmentService,
            EmployeeMessageController employeeMessageController
    ) {
        this.departmentService = departmentService;
        this.employeeMessageController = employeeMessageController;
    }

    public Function<Long, DepartmentExtendResponse> getDepartmentSearchFunction() {
      return new SearchDepartmentCommand(departmentService);
    }

    public UnaryOperator<DepartmentExtendResponse> getCreateEmployeesSetFunction() {
      return new CreateEmployeesSetCommand(employeeMessageController);
    }


}
