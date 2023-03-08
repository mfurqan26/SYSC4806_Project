package amazin.controller;

import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/book")
    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Book getBook(@RequestParam("id") BookId id){
        return bookRepository.findById(id).get();
    }
}