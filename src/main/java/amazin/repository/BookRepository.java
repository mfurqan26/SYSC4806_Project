package amazin.repository;

import amazin.model.Book;
import amazin.model.Book.BookId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, BookId> {
    Iterable<Book> findBooksByName(String name);
    Book findBookByIsbn(String isbn);
}