package amazin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    @RequestMapping("/book")
    public Book createBook(){
        Book addressBook = new Book();
        bookRepository.save(addressBook);
        return addressBook;
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Book getBook(@RequestParam("id") Long id){
        return bookRepository.findBookById(id);
    }
}