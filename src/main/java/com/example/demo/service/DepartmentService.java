package com.example.demo.service;

import com.example.demo.dao.DepartmentResponse;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.PaginationPointCalculator.getStartPointLimit;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public String getName(long id){
        return this.repository.getById(id).getDpName();
    }

    public DepartmentResponse getById(long id){
        Department department = this.repository.getById(id);
        return new DepartmentResponse(department.getDpID(),department.getDpName());
    }

    public void createNewDepartment(Department department){
        this.repository.create(department);
    }

    public void updateDepartment(Department department){
        this.repository.updateDepartment(department);
    }

    public boolean deleteDepartment(long id){
        return this.repository.deleteDepartment(id);
    }

    public List<DepartmentResponse> getAll(int page, int limit){
        int startPoint = getStartPointLimit(page, limit);
        List<Department> all = this.repository.getAll(startPoint, startPoint + limit);
        return all.stream().map(department -> {
            return new DepartmentResponse(department.getDpID(),department.getDpName());
        }).collect(Collectors.toList());
    }
}
