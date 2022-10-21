package com.example.demo.controller;

import com.example.demo.dto.employee.EmployeeAddRequest;
import com.example.demo.dto.employee.EmployeeResponse;
import com.example.demo.dto.employee.EmployeeUpdateRequest;
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
    public EmployeeResponse createEmployee(@RequestBody EmployeeAddRequest employee) {
        return this.service.create(employee);
    }

    @GetMapping("/{id}")
    public EmployeeResponse readEmployeeById(@PathVariable long id) {
        return this.service.readEmployeeById(id);
    }

    @GetMapping("/all")
    public List<EmployeeResponse> readAllEmployee(@RequestParam(defaultValue = "10") int limit,
                                                  @RequestParam(defaultValue = "1") int page) {
        return this.service.readAllEmployee(page, limit);
    }

    @GetMapping("/search")
    public List<EmployeeResponse> readByStartsWith(@RequestParam String startWith,
                                                   @RequestParam(defaultValue = "10") int limit,
                                                   @RequestParam(defaultValue = "1") int page) {
        return this.service.readByStartsWith(startWith, page, limit);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable long id,
                                           @RequestBody EmployeeUpdateRequest request) {
        return this.service.update(request, id);
    }

    @DeleteMapping("{id}")
    public boolean deleteEmployeeById(@PathVariable long id) {
        return this.service.delete(id);
    }
}
