package amazin.repository;

import amazin.model.Book;
import amazin.model.Book.BookId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@RepositoryRestResource
public interface BookRepository extends ReactiveMongoRepository<Book, BookId> {
    Flux<Book> findBooksByName(String name);
    Flux<Book> findBooksByIsbn(String isbn);
    Flux<Book> findBooksByPublisher(String publisher);
    Mono<Book> findById(BookId bookId);

    Flux<Book> findAllBy();
}