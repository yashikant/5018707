package bookstore.bookstoreapi_11.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private Double price;
}
