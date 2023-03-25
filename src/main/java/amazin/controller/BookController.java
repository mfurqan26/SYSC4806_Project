package amazin.controller;

import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import amazin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/book")
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @RequestMapping("/books") 
    public Flux<Book> list() {
        return this.bookService.findAll();
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Optional<Book> getBook(@ModelAttribute BookId id){
        Optional<Book> result = bookRepository.findById(id).blockOptional();
        if (result.isPresent()) {
            return result;
        } else {
            Optional<Book> none = Optional.empty();
            return none;
        }
    }
}
