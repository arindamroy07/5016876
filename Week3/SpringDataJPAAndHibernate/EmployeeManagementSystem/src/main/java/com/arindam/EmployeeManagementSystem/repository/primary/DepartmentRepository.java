package com.arindam.EmployeeManagementSystem.repository.primary;

import com.arindam.EmployeeManagementSystem.entity.primary.Department;
import com.arindam.EmployeeManagementSystem.projections.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT d from Department d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Department> findByName(String name);

    @Query("SELECT d FROM Department d")
    List<DepartmentProjection> findDepartments();
}
