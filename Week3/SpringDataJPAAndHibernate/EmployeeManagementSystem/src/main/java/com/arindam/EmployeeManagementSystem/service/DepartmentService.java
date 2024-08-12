package com.arindam.EmployeeManagementSystem.service;

import com.arindam.EmployeeManagementSystem.entity.primary.Department;
import com.arindam.EmployeeManagementSystem.projections.DepartmentProjection;
import com.arindam.EmployeeManagementSystem.repository.primary.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(int id, Department department) {
        Department updateDepartment = departmentRepository.findById(id).orElse(null);
        if (updateDepartment != null) {
            updateDepartment.setName(department.getName());
            return departmentRepository.save(updateDepartment);
        }
        return null;
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    public List<DepartmentProjection> getAllDepartment() {
        return departmentRepository.findDepartments();
    }
}
