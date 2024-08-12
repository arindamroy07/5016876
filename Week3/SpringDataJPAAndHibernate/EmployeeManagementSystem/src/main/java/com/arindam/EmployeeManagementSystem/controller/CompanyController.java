package com.arindam.EmployeeManagementSystem.controller;

import com.arindam.EmployeeManagementSystem.entity.secondary.Company;
import com.arindam.EmployeeManagementSystem.repository.secondary.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/load")
    public String loadData() {
        List<Company> company = new ArrayList<>(Arrays.asList(new Company(1, "Cognizant", "Kolkata,India")));
        companyRepository.saveAll(company);
        return "Success";
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getCompany() {
        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
    }
}
