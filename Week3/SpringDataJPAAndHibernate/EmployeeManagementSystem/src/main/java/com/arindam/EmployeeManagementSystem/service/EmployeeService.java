package com.arindam.EmployeeManagementSystem.service;

import com.arindam.EmployeeManagementSystem.entity.primary.Employee;
import com.arindam.EmployeeManagementSystem.projections.EmployeeDTO;
import com.arindam.EmployeeManagementSystem.projections.EmployeeProjection;
import com.arindam.EmployeeManagementSystem.repository.primary.EmployeeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeesById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee updateEmployee = employeeRepository.findById(id).orElse(null);
        if (updateEmployee != null) {
            updateEmployee.setName(employee.getName());
            updateEmployee.setEmail(employee.getEmail());
            updateEmployee.setDepartment(employee.getDepartment());
            return employeeRepository.save(updateEmployee);
        }
        return null;
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchByNameOrEmail(String keyword) {
        return employeeRepository.findByNameContainingOrEmailContaining(keyword, keyword);
    }

    public List<Employee> findByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeProjection> getAllEmployeesProjection() {
        return employeeRepository.findAllEmployees();
    }

    public EmployeeDTO getById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Transactional
    public void batchInsertEmployees(List<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            employeeRepository.save(employees.get(i));
            if (i % 20 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
