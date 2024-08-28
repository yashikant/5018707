package com.example.BookstoreAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    private String author;

    @Min(value = 0, message = "Price should be greater than or equal to 0")
    private double price;

    @NotNull(message = "ISBN cannot be null")
    @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters")
    private String isbn;

    @Version
    private Long version; // Optimistic locking
}
