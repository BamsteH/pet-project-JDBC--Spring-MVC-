package com.example.demo.controller;

import com.example.demo.dao.EmployeeResponse;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping()
    public void createEmployee(@RequestBody Employee employee) {
        this.service.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeResponse readEmployeeById(@PathVariable long id) {
        return this.service.readEmployeeById(id);
    }

    @GetMapping("/all/{page}/{limit}")
    public List<EmployeeResponse> readAllEmployee(@PathVariable int limit,
                                                  @PathVariable int page) {
        return this.service.readAllEmployee(page, limit);
    }

    @GetMapping("/search/{startWith}/{page}/{limit}")
    public List<EmployeeResponse> readByStartsWith(@PathVariable String startWith,
                                                   @PathVariable int limit,
                                                   @PathVariable int page) {
        return this.service.readByStartsWith(startWith, page, limit);

    }

    @PutMapping("")
    public void updateEmployee(@RequestBody Employee employee) {
        this.service.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public boolean deleteEmployeeById(@PathVariable long id) {
        return this.service.deleteEmployeeById(id);
    }

}
