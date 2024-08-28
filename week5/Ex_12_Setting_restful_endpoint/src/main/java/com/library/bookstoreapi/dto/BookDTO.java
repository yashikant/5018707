package com.library.bookstoreapi.dto;

import lombok.Data;

@Data
public class BookDTO
{
    private Long id;
    private String title;
    private String author;
    private Double price;
}
