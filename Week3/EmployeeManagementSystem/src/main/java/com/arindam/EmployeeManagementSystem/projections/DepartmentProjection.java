package com.arindam.EmployeeManagementSystem.projections;

import java.util.List;

public interface DepartmentProjection {
    String getName();
    List<EmpForDeptProj> getEmployees();
}
