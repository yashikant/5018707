package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>();

    // POST - Create a new customer using JSON request body
    @PostMapping
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        customerList.add(newCustomer);
        return newCustomer;
    }

    // GET - Retrieve all customers (optional for testing)
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }
}
