package amazin.controller;

import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import amazin.model.Account;
import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import amazin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class VendorController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Vendor")
    public String Vendor(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Vendor";
    }

    @GetMapping("/VendorCreate")
    public String VendorCreate(Model model) {
        return "VendorCreate";
    }

    @GetMapping("/VendorEdit")
    public String VendorEdit(Model model) {
        return "VendorEdit";
    }

}