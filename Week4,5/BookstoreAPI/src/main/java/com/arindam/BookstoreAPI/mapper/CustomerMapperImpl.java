package com.arindam.BookstoreAPI.mapper;

import com.arindam.BookstoreAPI.dto.CustomerDTO;
import com.arindam.BookstoreAPI.entity.Customer;
import com.arindam.BookstoreAPI.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }
        Customer customer = new Customer();
        customer = modelMapper.map(customerDTO, Customer.class);
        return customer;
    }

    @Override
    public List<CustomerDTO> customersToCustomerDTOs(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }
        return customerRepository.findAll()
                .stream()
                .map(this::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
