package com.arindam.EmployeeManagementSystem.controller;

import com.arindam.EmployeeManagementSystem.entity.primary.Department;
import com.arindam.EmployeeManagementSystem.projections.DepartmentProjection;
import com.arindam.EmployeeManagementSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        Department department = departmentService.getDepartmentById(id);
        if (department!= null)
            return new ResponseEntity<>(department, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        try {
            Department department1 = departmentService.createDepartment(department);
            return new ResponseEntity<>(department1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public  ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        Department updateDepartment = departmentService.updateDepartment(id, department);
        if (updateDepartment != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<Department>> findByName(@PathVariable("name") String name) {
        List<Department> departments = departmentService.findByName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/projection")
    public ResponseEntity<List<DepartmentProjection>> getAllDepartmentProjection() {
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
    }
}
