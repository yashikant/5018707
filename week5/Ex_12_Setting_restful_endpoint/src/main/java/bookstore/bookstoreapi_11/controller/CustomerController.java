package bookstore.bookstoreapi_11.controller;

import bookstore.bookstoreapi_11.model.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    // POST /customers - Create a new customer
    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    // POST /customers/register - Register a new customer using form data
    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customers.add(customer);
        return customer;
    }

    // GET /customers - Retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // GET /customers/{id} - Get a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id " + id));
    }

    // Update a customer by ID
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = getCustomerById(id);
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAddress(updatedCustomer.getAddress());
        return customer;
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }

}
