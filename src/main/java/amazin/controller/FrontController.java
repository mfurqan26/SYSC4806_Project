package amazin.controller;

import amazin.model.*;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
import amazin.repository.AccountRepository;
import amazin.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/")
    public String landing() {return "Landing";}

    @GetMapping("/SignUp")
    public String signUp() {return "SignUp";}

    @PostMapping("/SignUp")
    public String signUp(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            @RequestParam(name="type", required=false, defaultValue="Customer") String type,
            Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username);
        if (result.isEmpty()) {
            if(!username.equals("") && !password.equals("")) {
                //Vendor Account
                if(type.equals("Vendor")) {
                    Vendor account = new Vendor(username,password);
                    accountRepository.save(account);
                    return "redirect:/VendorLogin";
                }
                //Customer Account
                else {
                    Cart cart = new Cart(username);
                    cartRepository.save(cart);

                    Customer account = new Customer(username,password);
                    account.setCart(cart);

                    accountRepository.save(account);
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
    public String checkCustomerLogin(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            HttpSession session, Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username);
        Optional<Cart> cartResult = cartRepository.findByUserName(username);
        if (result.isPresent() && cartResult.isPresent()) {
            Account account = result.get();
            String accountPassword = account.getPassword();
            if(account.getType().equals(Account.Type.CUSTOMER) 
                    && accountPassword.equals(password)) {
                Long cartId = cartResult.get().getId();
                model.addAttribute("username", username);
                model.addAttribute("cartId", cartId);
                session.setAttribute("username", username);
                session.setAttribute("cartId", cartId);
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
    public String checkVendorLogin(
            @RequestParam(name="username", required=false, defaultValue="") String username,
            @RequestParam(name="password", required=false, defaultValue="") String password,
            HttpSession session, Model model) {
        Optional<Account> result = accountRepository.findAccountByUserName(username);
        Account account = null;
        if (result.isPresent()) {
            account = result.get();
            String accountPassword = account.getPassword();
            if(account.getType().equals(Account.Type.VENDOR) && accountPassword.equals(password)){
                model.addAttribute("username", username);
                session.setAttribute("username", username);
                return "redirect:/Vendor";
            }
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "VendorLogin";
    }
}