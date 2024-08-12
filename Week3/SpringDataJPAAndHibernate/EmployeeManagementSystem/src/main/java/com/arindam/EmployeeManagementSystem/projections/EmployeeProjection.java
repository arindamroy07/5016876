package com.arindam.EmployeeManagementSystem.projections;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    String getName();
    String getEmail();
    @Value("#{target.department.name}")
    String getDepartment();
}
