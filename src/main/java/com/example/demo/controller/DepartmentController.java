package com.example.demo.controller;

import com.example.demo.dao.DepartmentResponse;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping()
    public void createDepartment(@RequestBody Department department){
        this.service.createNewDepartment(department);
    }

    @PutMapping()
    public void updateDepartment(@RequestBody Department department){
        this.service.updateDepartment(department);
    }

    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable long id){
        return this.service.getById(id);
    }

    @GetMapping("/{page}/{limit}")
    public List<DepartmentResponse> getAll(@PathVariable int limit, @PathVariable int page){
        return this.service.getAll(page,limit);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepartment(@PathVariable long id){
       return this.service.deleteDepartment(id);
    }

}
