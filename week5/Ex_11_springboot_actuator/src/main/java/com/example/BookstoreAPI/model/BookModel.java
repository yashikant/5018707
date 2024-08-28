package com.example.BookstoreAPI.model;

import com.example.BookstoreAPI.entity.Book;
import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookModel extends RepresentationModel<BookModel> {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;

    public BookModel(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.isbn = book.getIsbn();
    }
}
