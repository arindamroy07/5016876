package com.arindam.BookstoreAPI.mapper;

import com.arindam.BookstoreAPI.dto.CustomerDTO;
import com.arindam.BookstoreAPI.entity.Customer;

import java.util.List;


public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> customersToCustomerDTOs(List<Customer> customers);
}
