package com.arindam.BookstoreAPI.controller;

import com.arindam.BookstoreAPI.dto.CustomerDTO;
import com.arindam.BookstoreAPI.entity.Customer;
import com.arindam.BookstoreAPI.mapper.CustomerMapper;
import com.arindam.BookstoreAPI.service.CustomerService;
import com.arindam.BookstoreAPI.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of(new Customer(1, "Name", "email", "Address")));
        when(customerMapper.customersToCustomerDTOs(anyList())).thenReturn(List.of(new CustomerDTO(1, "Name", "email", "Address")));

        mockMvc.perform(get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Name"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        when(customerService.getCustomerById(1)).thenReturn(new Customer(1, "Name", "email", "Address"));
        when(customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(new CustomerDTO(1, "Name", "email", "Address"));

        mockMvc.perform(get("/customers/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(1, "Name", "email", "Address");
        Customer customer = new Customer(1, "Name", "email", "Address");
        when(customerMapper.customerDTOToCustomer(any(CustomerDTO.class))).thenReturn(customer);
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);
        when(customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(1, "Updated Name", "Updated email", "Updated Address");
        Customer customer = new Customer(1, "Updated Name", "Updated email", "Updated Address");
        when(customerService.getCustomerById(1)).thenReturn(customer);
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(customer);
        when(customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(customerDTO);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer(1, "Name", "email", "Address");
        when(customerService.getCustomerById(1)).thenReturn(customer);
        doNothing().when(customerService).deleteCustomer(1);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}
