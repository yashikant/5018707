package com.example.BookstoreAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.BookstoreAPI.model.Book;

import main.java.com.example.BookstoreAPI.exception.ResourceNotFoundException;

import com.example.BookstoreAPI.model.Book;

import main.java.com.example.BookstoreAPI.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();

    // GET - Retrieve a book by ID with custom headers
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Bookstore-Custom-Header", "Book-Details-Fetched");

        return ResponseEntity.ok()
                .headers(headers)
                .body(book);
    }

    // PUT - Update a book with a custom header in the response
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
