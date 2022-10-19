package com.example.demo.controller;

import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.dto.department.DepartmentUpdateRequest;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping()
    public void createDepartment(@RequestBody Department department){
        this.service.create(department);
    }

    @PutMapping()
    public DepartmentResponse updateDepartment(@RequestBody DepartmentUpdateRequest request){
        return this.service.update(request);
    }

    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable long id){
        return this.service.getById(id);
    }

    @GetMapping("")
    public List<DepartmentResponse> getAll(@RequestParam(defaultValue = "10") int limit,
                                           @RequestParam(defaultValue = "1") int page){
        return this.service.getAll(page,limit);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepartment(@PathVariable long id){
       return this.service.delete(id);
    }

}
