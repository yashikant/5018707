package bookstore.bookstoreapi_14.repository;

import bookstore.bookstoreapi_14.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can define custom query methods here if needed
}
