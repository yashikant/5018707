import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private CustomMetricsService customMetricsService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // Increment the custom metric
        customMetricsService.incrementBookCreationCounter();
        // Logic to save the book
        return bookService.save(book);
    }
}
