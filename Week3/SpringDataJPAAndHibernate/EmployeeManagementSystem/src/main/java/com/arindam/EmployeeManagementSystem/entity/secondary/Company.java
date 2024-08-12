package com.arindam.EmployeeManagementSystem.entity.secondary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    private int id;
    private String name;
    private String address;
}

// Had to make this Company entity to test secondary data source as Department and Employee entity were needed
// to be in the same package as under same persistence unit due to oneToMany manyToOne relationship b/w them
