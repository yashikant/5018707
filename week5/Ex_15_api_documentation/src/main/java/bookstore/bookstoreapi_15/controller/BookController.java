package bookstore.bookstoreapi_15.controller;

import bookstore.bookstoreapi_15.metrics.CustomMetrics;
import bookstore.bookstoreapi_15.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book API", description = "Operations related to books")
public class BookController {

    private final List<Book> books = new ArrayList<>();
    private final CustomMetrics customMetrics;

    // Constructor injection of CustomMetrics
    public BookController(CustomMetrics customMetrics) {
        this.customMetrics = customMetrics;
    }

    // GET /books - Get all books
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Book> getAllBooks() {
        customMetrics.trackBookCall();
        return books;
    }


    // GET /books/{id} - Get book by ID in JSON or XML
    @Operation(summary = "Get a book by ID", description = "Retrieve a book by its ID")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book getBookById(@PathVariable Long id) {
        customMetrics.trackBookCall();
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // GET /books/search - Search books by title and/or author
    @Operation(summary = "Search books", description = "Search for books by title and/or author")
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                        (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .toList();
    }

    // POST /books - Add a new book in JSON or XML
    @Operation(summary = "Add a new book", description = "Create a new book record")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        customMetrics.trackBookCall();
        books.add(book);
        return book;
    }

    // PUT /books/{id} - Update an existing book
    @Operation(summary = "Update a book", description = "Update an existing book record")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPrice(updatedBook.getPrice());
                    book.setIsbn(updatedBook.getIsbn());
                });
        return updatedBook;
    }
    
    // DELETE /books/{id} - Delete a book by ID
    @Operation(summary = "Delete a book", description = "Remove a book record by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

}
