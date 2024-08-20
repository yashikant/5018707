package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // GET - Retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookList;
    }

    // GET - Retrieve a book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookList.stream()
                       .filter(book -> book.getId() == id)
                       .findFirst()
                       .orElse(null);
    }

    // POST - Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book newBook) {
        bookList.add(newBook);
        return newBook;
    }

    // PUT - Update an existing book by ID
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                return book;
            }
        }
        return null;
    }

    // DELETE - Remove a book by ID
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookList.removeIf(book -> book.getId() == id);
        return "Book removed";
    }
}
