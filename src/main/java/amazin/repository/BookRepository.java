package amazin.repository;

import amazin.model.Book;
import amazin.model.Book.BookId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, BookId> {
    Optional<Iterable<Book>> findBooksByName(String name);
}