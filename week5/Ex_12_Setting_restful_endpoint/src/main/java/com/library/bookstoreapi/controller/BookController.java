package com.library.bookstoreapi.controller;


import com.library.bookstoreapi.Services.BookServices;
import com.library.bookstoreapi.model.Book;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    Book book = new Book();
    private List<Book> bookList = new ArrayList<>();
    public List<Book> getAllBooks() {
        return bookList;
    }
    @PostMapping
    public Book addBook(@RequestBody Book book) {

        bookList.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book updatedBook = bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setIsbn(book.getIsbn());
                    return existingBook;
                })
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBook(@PathVariable Long id)
    {
        bookList.removeIf(book -> book.getId().equals(id));
        return "Book with ID " + id + " has been deleted.";
    }
    @GetMapping(value = "/books/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public EntityModel<Book> getBookById(@PathVariable Long id)
    {
        BookServices bookService= new BookServices();
        Book book = bookService.findBookById(id); // Assume this service exists

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");

        return EntityModel.of(book, selfLink, allBooksLink);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author)
    {
        return bookList.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                        (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Book> addBookWithHeaders(@RequestBody Book book)
    {
        book.setId((long) (bookList.size() + 1));
        bookList.add(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookAddedSuccessfully");

        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

}
