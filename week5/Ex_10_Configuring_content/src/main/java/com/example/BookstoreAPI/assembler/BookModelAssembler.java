package com.example.BookstoreAPI.assembler;

import com.example.BookstoreAPI.controller.BookController;
import com.example.BookstoreAPI.entity.Book;
import com.example.BookstoreAPI.model.BookModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    public BookModelAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(Book book) {
        BookModel model = new BookModel(book);
        model.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
        model.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return model;
    }
}
