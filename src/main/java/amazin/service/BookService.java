package amazin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import amazin.repository.BookRepository;
import amazin.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class BookService {
     
    @Autowired
    BookRepository bookRepository;

    public Flux<Book> findAll() {
      return bookRepository.findAll();
    }
}