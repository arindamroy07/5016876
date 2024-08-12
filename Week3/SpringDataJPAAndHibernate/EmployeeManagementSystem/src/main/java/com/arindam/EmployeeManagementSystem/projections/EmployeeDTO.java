package com.arindam.EmployeeManagementSystem.projections;

import com.arindam.EmployeeManagementSystem.entity.primary.Department;

public class EmployeeDTO {
    private String name;
    private String email;
    private Department department;

    public EmployeeDTO(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department.getName();
    }
}
