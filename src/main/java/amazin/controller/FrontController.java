package amazin.controller;

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
public class FrontController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/landing")
    public String greeting(Model model) {
        model.addAttribute("bookId", new BookId());
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("bookRepo", books);
        return "Landing";
    }

    @GetMapping("/login")
    public String login(@RequestParam(
            value = "accountId", required=true) long accountId,
            Model model) {
        Optional<Account> result = accountRepository.findById(accountId);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
        } else {
            return "400";
        }

        if (account.getType() == Account.Type.CUSTOMER) {
            model.addAttribute("customerId", account.getId());
        } else if (account.getType() == Account.Type.VENDOR) {
            model.addAttribute("vendorId", account.getId());
        } 
        return "400"; 
    }
}