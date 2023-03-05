
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import jakarta.persistence.*;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;

@Entity
@Table(name="Inventory")
@RepositoryRestResource(path = "books")
public interface BookRepository extends CrudRepository<Book, BookId> {
    List<Book> findByName(String bookName);
    Optional<Book> findById(BookId id);
}
