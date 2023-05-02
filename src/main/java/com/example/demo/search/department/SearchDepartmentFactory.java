package com.example.demo.search.department;

import com.example.demo.dto.department.DepartmentExtendResponse;
import com.example.demo.remote.EmployeeMessageController;
import com.example.demo.service.DepartmentService;
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
