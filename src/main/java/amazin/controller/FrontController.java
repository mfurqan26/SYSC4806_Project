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
            value = "userName", required=false) String userName,
            Model model) {
        if (userName == null || userName.equals("")) {
            return "Login";
        }

        Optional<Account> result = accountRepository
            .findAccountByUserName(userName);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
        } else {
            model.addAttribute("errorCode", "404");
            model.addAttribute("errorMessage", "account not found");
            return "Error";
        }

        if (account.getType() == Account.Type.CUSTOMER) {
            model.addAttribute("customerId", account.getId());
            return "Shop";
        } else if (account.getType() == Account.Type.VENDOR) {
            model.addAttribute("vendorId", account.getId());
            return "Vendor";
        } 
        model.addAttribute("errorCode", "400");
        model.addAttribute("errorMessage", "Bad account");
        return "Error";
    }
}