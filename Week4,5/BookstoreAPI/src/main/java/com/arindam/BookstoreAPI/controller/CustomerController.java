package com.arindam.BookstoreAPI.controller;

import com.arindam.BookstoreAPI.dto.CustomerDTO;
import com.arindam.BookstoreAPI.entity.Customer;
import com.arindam.BookstoreAPI.mapper.CustomerMapper;
import com.arindam.BookstoreAPI.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Controller", description = "API for managing customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Add a new customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
            customerService.createCustomer(customer);
            return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new book", description = "Registering a new customer for the bookstore")
    public ResponseEntity<Customer> registerCustomer(@RequestParam String name, @RequestParam String email,
                                        @RequestParam String address) {
        Customer newCustomer = customerService.registerCustomer(name, email, address);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all customers", description = "Retrieve list of all customers")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customerMapper.customersToCustomerDTOs(customers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by Id", description = "Retrieve a customer by it's Id")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null)
            return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer", description = "Updating a customer's details by it's Id")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = Optional.ofNullable(customerService.getCustomerById(id));
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            customer = customerService.updateCustomer(customer);
            return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Removing a customer by it's Id")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
    }
}
