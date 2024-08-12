package com.arindam.EmployeeManagementSystem.controller;

import com.arindam.EmployeeManagementSystem.entity.primary.Employee;
import com.arindam.EmployeeManagementSystem.projections.EmployeeDTO;
import com.arindam.EmployeeManagementSystem.projections.EmployeeProjection;
import com.arindam.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeesById(id);
        if (employee!= null)
            return new ResponseEntity<>(employee, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee employee1 = employeeService.createEmployee(employee);
            return new ResponseEntity<>(employee1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public  ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(id, employee);
        if (updateEmployee != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeesById(id);
        if (employee != null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<Employee>> searchByNameOrEmail(@PathVariable("keyword") String keyword) {
        return new ResponseEntity<>(employeeService.searchByNameOrEmail(keyword), HttpStatus.OK);
    }

    @GetMapping("/departmentName/{departmentName}")
    public ResponseEntity<List<Employee>> findEmployeeByDepartmentName(@PathVariable("departmentName") String departmentName) {
        return new ResponseEntity<>(employeeService.findByDepartmentName(departmentName), HttpStatus.OK);
    }

    @GetMapping("/page")
    public Page<Employee> getAllEmployees(Pageable pageable) {
        Pageable sortByName = PageRequest.of(0, 3, Sort.by("name"));
        return employeeService.getAllEmployees(sortByName);
    }

    @GetMapping("/projection")
    public ResponseEntity<List<EmployeeProjection>> getAllEmployeesProjection() {
        return new ResponseEntity<>(employeeService.getAllEmployeesProjection(), HttpStatus.OK);
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeProjectionById(@PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.getById(id);
        if (employeeDTO!= null)
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/batch")
    public void saveAllEmployees(@RequestBody List<Employee> employees) {
        employeeService.batchInsertEmployees(employees);
    }
}
