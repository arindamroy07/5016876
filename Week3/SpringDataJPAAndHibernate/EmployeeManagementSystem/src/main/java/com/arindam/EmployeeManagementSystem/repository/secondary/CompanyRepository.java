package com.arindam.EmployeeManagementSystem.repository.secondary;

import com.arindam.EmployeeManagementSystem.entity.secondary.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

// Had to make this Company repository to test secondary data source as Department and Employee repository were
// needed to be in the same package as under same persistence unit due to oneToMany manyToOne relationship b/w them
