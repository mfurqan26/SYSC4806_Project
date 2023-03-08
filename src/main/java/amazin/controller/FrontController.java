package amazin.controller;

import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    private BookRepository bookRepository;

    @Autowired
    public FrontController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/landing")
    public String greeting(Model model) {
        model.addAttribute("bookId", new BookId());
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("bookRepo", books);
        return "Landing";
    }
}