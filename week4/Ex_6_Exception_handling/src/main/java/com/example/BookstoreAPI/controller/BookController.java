package com.example.BookstoreAPI.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // GET - Retrieve a book by ID with custom headers
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + id + " not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());

                HttpHeaders headers = new HttpHeaders();
                headers.add("X-Bookstore-Custom-Header", "Book-Updated-Successfully");

                return new ResponseEntity<>(book, headers, HttpStatus.OK);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}