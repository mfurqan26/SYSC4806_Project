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

    @GetMapping("/CustomerLogin")
    public String CustomerLogin(Model model) {
        return "CustomerLogin";
    }

    @PostMapping( value = "/CustomerLogin", params = "customerLogin")
    public String checkCustomerLogin(@RequestParam(name="username", required=false, defaultValue="") String username,Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
            if(account.getType() == Account.Type.CUSTOMER) {
                model.addAttribute("account", account);
                return "redirect:/Shop";
            }
        }
        model.addAttribute("loginError", "Invalid username");
        return "CustomerLogin";
    }

    @GetMapping("/VendorLogin")
    public String VendorLogin(Model model) {
        return "VendorLogin";
    }

    @PostMapping( value = "/VendorLogin", params = "vendorLogin")
    public String checkVendorLogin(@RequestParam(name="username", required=false, defaultValue="") String username,Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
            if(account.getType() == Account.Type.VENDOR){
                model.addAttribute("account", account);
                return "redirect:/Vendor";
            }
        }
        model.addAttribute("loginError", "Invalid username");
        return "VendorLogin";
    }
}