package com.arindam.BookstoreAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.arindam.BookstoreAPI.dto.CustomerDTO;
import com.arindam.BookstoreAPI.entity.Customer;
import com.arindam.BookstoreAPI.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    String token;

    @BeforeEach
    void setUp() throws Exception {
        customerRepository.deleteAll();

        String userJson = "{\"username\":\"jon\",\"password\":\"jon@123\"}";
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andReturn();

        String token = mvcResult.getResponse().getContentAsString();
        this.token = "Bearer " + token;
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
        Customer customer = new Customer(1, "Name1", "email1", "Address1");
        customerRepository.save(customer);

        mockMvc.perform(get("/customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Name1"))
                .andExpect(jsonPath("$[0].email").value("email1"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1, "Name1", "email1", "Address1");
        customer = customerRepository.save(customer);

        mockMvc.perform(get("/customers/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.email").value("email1"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(1, "Name1", "email1", "Address1");

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.email").value("email1"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer(1, "Name1", "email1", "Address1");
        customer = customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), "Updated Name", "Updated email", "Updated Address");

        mockMvc.perform(put("/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("Updated email"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer(1, "Name1", "email1", "Address1");
        customer = customerRepository.save(customer);

        mockMvc.perform(delete("/customers/" + customer.getId())
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/customers/" + customer.getId())
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isNotFound());
    }
}
