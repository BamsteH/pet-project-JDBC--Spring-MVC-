package com.example.demo.controller;

import com.example.demo.dto.department.DepartmentAddRequest;
import com.example.demo.dto.department.DepartmentExtendResponse;
import com.example.demo.dto.department.DepartmentResponse;
import com.example.demo.dto.department.DepartmentUpdateRequest;
import com.example.demo.search.department.SearchDepartmentFactory;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentService service;
  private final SearchDepartmentFactory searchDepartmentFactory;

  @PostMapping()
  public DepartmentResponse createDepartment(@RequestBody DepartmentAddRequest request) {
    return this.service.create(request);
  }

  @PutMapping("/{id}")
  public DepartmentResponse updateDepartment(@PathVariable long id,
                                             @RequestBody DepartmentUpdateRequest request) {
    return this.service.update(request, id);
  }

  @GetMapping("/{id}")
  public DepartmentResponse getById(@PathVariable long id) {
    return this.service.getById(id);
  }

  @GetMapping("")
  public List<DepartmentResponse> getAll(
          @RequestParam(defaultValue = "10") int limit,
          @RequestParam(defaultValue = "1") int page
  ) {
    return this.service.getAll(page, limit);
  }

  @GetMapping("/extended")
  public DepartmentExtendResponse getExtendedById(@RequestParam long id) {
    return searchDepartmentFactory.getDepartmentSearchFunction()
            .andThen(searchDepartmentFactory.getCreateEmployeesSetFunction())
            .apply(id);
  }

  @DeleteMapping("/{id}")
  public boolean deleteDepartment(@PathVariable long id) {
    return this.service.delete(id);
  }

}
