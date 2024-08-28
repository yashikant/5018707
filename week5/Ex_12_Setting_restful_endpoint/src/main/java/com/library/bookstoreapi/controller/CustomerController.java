package com.library.bookstoreapi.controller;

import com.library.bookstoreapi.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    private List<Customer> customerList = new ArrayList<>();
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customerList.size() + 1));
        customerList.add(customer);
        return customer;
    }

    @PostMapping("/register")
    public String registerCustomer(@RequestParam String name, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setId((long) (customerList.size() + 1));
        customer.setName(name);
        customer.setEmail(email);
        customerList.add(customer);
        return "Customer registered successfully!";
    }
}
