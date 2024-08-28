package bookstore.bookstoreapi_15.controller;


import bookstore.bookstoreapi_15.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        // Add more expectations as needed
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Title", "Author", 10.0, "1234567890");

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
        // Add more expectations as needed
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1L, "Title", "Author", 10.0, "1234567890");

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Title\",\"author\":\"Author\",\"price\":10.0,\"isbn\":\"1234567890\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
        // Add more expectations as needed
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Updated Title", "Updated Author", 15.0, "0987654321");

        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"price\":15.0,\"isbn\":\"0987654321\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
        // Add more expectations as needed
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        // Add more expectations as needed
    }
}
