package amazin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontController {
    private BookRepository bookRepository;

    @Autowired
    public FrontController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/landing")
    public String greeting(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("bookRepo", books);
//        model.addAttribute("bookRepo", bookRepository);
        return "landing";
    }
}