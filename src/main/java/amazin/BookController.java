package amazin;

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
    public Book getBook(@RequestParam("id") Long id){
        return bookRepository.findBookById(id);
    }
}