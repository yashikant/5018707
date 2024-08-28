package com.example.BookstoreAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
    private int id;

    @JsonProperty("book_title")
    private String title;

    private String author;

    @JsonProperty("book_price")
    private double price;

    @JsonIgnore
    private String isbn;

    // Getters and Setters
    // ...
}
