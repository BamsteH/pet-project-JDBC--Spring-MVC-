package com.example.demo.service;

import com.example.demo.dto.department.DepartmentAddRequest;
import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.dto.department.DepartmentUpdateRequest;
import com.example.demo.entity.Department;
import com.example.demo.exceptions.DomainException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.utils.transferObject.DepartmentTransferObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.utils.PaginationPointCalculator.getStartPointLimit;
import static com.example.demo.utils.transferObject.DepartmentTransferObject.*;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentResponse getById(long id) {
        return this.repository
                .getById(id)
                .map(DepartmentTransferObject::toResponse)
                .orElseThrow(() -> new DomainException("department doesn't exist"));
    }

    public DepartmentResponse create(DepartmentAddRequest request) {
        return toResponse(this.repository.create(fromAddRequest(request)));
    }

    public DepartmentResponse update(DepartmentUpdateRequest request, Long id) {
        return toResponse(this.repository
                .update(fromUpdateRequest(request, id), id)
                .orElseThrow(() -> {
                    throw new DomainException("can't update department");
                }));
    }

    public boolean delete(long id) {
        return this.repository.delete(id);
    }

    public List<DepartmentResponse> getAll(int page, int limit) {
        int startPoint = getStartPointLimit(page, limit);
        return this.repository
                .getAll(startPoint, startPoint + limit)
                .stream()
                .map(DepartmentTransferObject::toResponse)
                .collect(Collectors.toList());
    }
}
