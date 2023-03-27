package amazin.controller;

import amazin.model.Account;
import amazin.model.Book;
import amazin.model.Book.BookId;
import amazin.model.Customer;
import amazin.model.Vendor;
import amazin.repository.BookRepository;
import amazin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Optional;


@Controller
public class FrontController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String Landing(Model model) {
        model.addAttribute("bookId", new BookId());
        return "Landing";
    }

    @GetMapping("/SignUp")
    public String SignUp(Model model) {
        return "SignUp";
    }

    @PostMapping(value = "/SignUp", params = "SignUp")
    public String newAccountSignUp(@RequestParam(name="username", required=false, defaultValue="") String username,
                                   @RequestParam(name="password", required=false, defaultValue="") String password,
                                   @RequestParam(name="type", required=false, defaultValue="Customer") String type,
                                   Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username).blockOptional();
        Account account = null;
        if (!result.isPresent()) {
            if(!username.equals("") && !password.equals("")) {
                //Vendor Account
                if(type.equals("Vendor")) {
                    account = new Vendor(username,password);
                    accountRepository.saveAll(Flux.just(account));
                    return "redirect:/VendorLogin";
                }
                //Customer Account
                else {
                    account = new Customer(username,password);
                    accountRepository.saveAll(Flux.just(account));
                    return "redirect:/CustomerLogin";
                }
            }
            //Some Input not set
            else {
                model.addAttribute("signUpError", "Username or Password input is empty. Please set something.");
                return "SignUp";
            }
        }
        //Username already exits
        else {
            model.addAttribute("signUpError", "Username already used, choose a different username!");
            return "SignUp";
        }
    }

    @GetMapping("/CustomerLogin")
    public String CustomerLogin(Model model) {return "CustomerLogin";}

    @PostMapping( value = "/CustomerLogin", params = "customerLogin")
    public String checkCustomerLogin(@RequestParam(name="username", required=false, defaultValue="") String username,
                                     @RequestParam(name="password", required=false, defaultValue="") String password,
                                     Model model) {
        Optional<Account> result = accountRepository
            .findAccountByUserName(username).blockOptional();
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
            String accountPassword = account.getPassword();
            if(account.getType().equals(Account.Type.CUSTOMER) && accountPassword.equals(password)) {
                model.addAttribute("account", account);
                return "redirect:/Shop";
            }
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "CustomerLogin";
    }

    @GetMapping("/VendorLogin")
    public String VendorLogin() {
        return "VendorLogin";
    }

    @PostMapping( value = "/VendorLogin", params = "vendorLogin")
    public String checkVendorLogin(@RequestParam(name="username", required=false, defaultValue="") String username,
                                   @RequestParam(name="password", required=false, defaultValue="") String password,
                                   Model model) {
        Optional<Account> result = accountRepository
            .findAccountByUserName(username).blockOptional();
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
            String accountPassword = account.getPassword();
            if(account.getType().equals(Account.Type.VENDOR) && accountPassword.equals(password)){
                model.addAttribute("account", account);
                return "redirect:/Vendor";
            }
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "VendorLogin";
    }
}