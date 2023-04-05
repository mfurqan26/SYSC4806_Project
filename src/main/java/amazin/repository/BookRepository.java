package amazin.repository;

import amazin.model.Book;
import amazin.model.Book.BookId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, BookId> {
    Iterable<Book> findBooksByName(String name);
    Iterable<Book> findBooksByIsbn(String isbn);
    Iterable<Book> findBooksByPublisher(String publisher);
    Iterable<Book> findBooksByAuthor(String author);
    Optional<Book> findById(BookId bookId);

    //Find All Books and order by recommended First
    Iterable<Book> findAllByOrderByRecommendedDesc();
}