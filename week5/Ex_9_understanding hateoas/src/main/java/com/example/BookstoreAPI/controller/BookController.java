package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.assembler.BookModelAssembler;
import com.example.BookstoreAPI.entity.Book;
import com.example.BookstoreAPI.model.BookModel;
import com.example.BookstoreAPI.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookModelAssembler assembler;

    @GetMapping
    public CollectionModel<BookModel> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookModel> bookModels = books.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(bookModels,
                linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<BookModel> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setIsbn(bookDetails.getIsbn());
            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(assembler.toModel(updatedBook));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
