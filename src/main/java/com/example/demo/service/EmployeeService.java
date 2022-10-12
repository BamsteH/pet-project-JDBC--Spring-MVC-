package com.example.demo.service;

import com.example.demo.dao.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.DomainException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.demo.utils.PaginationPointCalculator.getStartPointLimit;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository repository, DepartmentService departmentService) {
        this.repository = repository;
        this.departmentService = departmentService;
    }

    public void createEmployee(Employee employee) {
        try{
            this.departmentService.getById(employee.getEmp_dpID());
            this.repository.create(employee);
        } catch (SQLNonTransientException e) {
            throw new DomainException("department doens't exist");
        }
    }

    public EmployeeResponse readEmployeeById(long id) {
        Employee employee = null;
        try {
            employee = this.repository.getById(id);
        } catch (SQLException| EmptyResultDataAccessException e) {
            throw new DomainException("User with this id doesn't exist");
        }
        String departmentName = "";
        if (Objects.nonNull(employee.getEmp_dpID())){
            departmentName = departmentService.getName(employee.getEmp_dpID());
        }
        return new EmployeeResponse(employee.getEmpId(),
                employee.getEmpName(),
                employee.isEmpActive(),
                departmentName);

    }

    public List<EmployeeResponse> readAllEmployee(int page, int limit) {
        int pointLimit = getStartPointLimit(page, limit);
        return this.transferObjectToResponse(this.repository.getAll(pointLimit, pointLimit + limit));
    }

    public List<EmployeeResponse> readByStartsWith(String startsWith, int page, int limit) {
        int pointLimit = getStartPointLimit(page, limit);
        try{
            return this.transferObjectToResponse(this.repository
                    .getByStartsWith(startsWith, pointLimit, pointLimit + limit));
        } catch (EmptyResultDataAccessException ex){
            throw new DomainException("NotFound");
        }
    }

    public void updateEmployee(Employee employee) {
        this.repository.updateEmployee(employee);
    }

    public boolean deleteEmployeeById(long id) {
        return this.repository.deleteEmployee(id);
    }

    public List<EmployeeResponse> transferObjectToResponse(List<Employee> employees){
        return employees.stream().map(employee -> {

            EmployeeResponse employeeResponse = new EmployeeResponse(employee.getEmpId(),
                    employee.getEmpName(),
                    employee.isEmpActive(),
                    null);
            if (Objects.nonNull(employee.getEmp_dpID())){
                employeeResponse.setDepartment(departmentService.getName(employee.getEmp_dpID()));
            }
            return employeeResponse;
        }).collect(Collectors.toList());
    }


}
