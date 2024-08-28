package bookstore.bookstoreapi_14.controller;

import bookstore.bookstoreapi_14.model.Customer;
import bookstore.bookstoreapi_14.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use a separate application-test.properties for test configuration
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateCustomer() throws Exception {
        String customerJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"address\":\"123 Main St\"}";

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
    }

    @Test
    @Transactional
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St");
        customerRepository.save(customer);

        mockMvc.perform(get("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
    }

    @Test
    @Transactional
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St");
        customerRepository.save(customer);

        String updatedCustomerJson = "{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\",\"address\":\"456 Another St\"}";

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCustomerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    @Transactional
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St");
        customerRepository.save(customer);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
