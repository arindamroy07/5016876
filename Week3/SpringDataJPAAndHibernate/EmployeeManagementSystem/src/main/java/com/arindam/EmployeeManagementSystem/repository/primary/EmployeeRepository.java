package com.arindam.EmployeeManagementSystem.repository.primary;

import com.arindam.EmployeeManagementSystem.entity.primary.Employee;
import com.arindam.EmployeeManagementSystem.projections.EmployeeDTO;
import com.arindam.EmployeeManagementSystem.projections.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingOrEmailContaining(String name, String email);

    @Query(name = "Employee.findByDepartmentName")
    List<Employee> findByDepartmentName(String departmentName);

    @Override
    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllEmployees();

    EmployeeDTO findEmployeeById(int id);
}
