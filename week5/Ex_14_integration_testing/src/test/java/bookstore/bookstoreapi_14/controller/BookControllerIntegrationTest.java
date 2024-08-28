package bookstore.bookstoreapi_14.controller;

import bookstore.bookstoreapi_14.model.Book;
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
import bookstore.bookstoreapi_14.repository.BookRepository;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use a separate application-test.properties for test configuration
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateBook() throws Exception {
        String bookJson = "{\"id\":1,\"title\":\"Test Book\",\"author\":\"Test Author\",\"price\":19.99,\"isbn\":\"1234567890123\"}";

        mockMvc.perform((org.springframework.test.web.servlet.RequestBuilder) post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(bookJson)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Book"));
    }

    @Test
    @Transactional
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Test Book", "Test Author", 19.99, "1234567890123");
        bookRepository.save(book);

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Book"));
    }

    @Test
    @Transactional
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Old Title", "Old Author", 19.99, "1234567890123");
        bookRepository.save(book);

        String updatedBookJson = "{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"price\":29.99,\"isbn\":\"9876543210987\"}";

        mockMvc.perform((org.springframework.test.web.servlet.RequestBuilder) put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(updatedBookJson)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

    @Test
    @Transactional
    public void testDeleteBook() throws Exception {
        Book book = new Book(1L, "Test Book", "Test Author", 19.99, "1234567890123");
        bookRepository.save(book);

        mockMvc.perform(delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
