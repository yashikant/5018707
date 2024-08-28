import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricsService {

    private final Counter bookCreationCounter;

    public CustomMetricsService(MeterRegistry meterRegistry) {
        this.bookCreationCounter = meterRegistry.counter("bookstore.books.created", "type", "book");
    }

    public void incrementBookCreationCounter() {
        bookCreationCounter.increment();
    }
}
