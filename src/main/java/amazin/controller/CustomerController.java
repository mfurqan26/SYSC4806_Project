package amazin.controller;

import amazin.model.Account;
import amazin.model.Book;
import amazin.model.Cart;
import amazin.model.Customer;
import amazin.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Shop")
    public String customer(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("account", account);
        model.addAttribute("books", books);
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "AllBooks")
    public String SearchAllBook(Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "SearchBook")
    public String SearchBook(
            @RequestParam(name="search", required=false, defaultValue = "") String search,
            @RequestParam(name="filter", required=false, defaultValue = "") String filter,
            Model model){
        if(!search.equals("")){
            Iterable<Book> books;
            if(filter.equals("by-publisher")){
                books = bookRepository.findBooksByPublisher(search);
            }
            else if(filter.equals("by-name")){
                books = bookRepository.findBooksByName(search);
            }
            else {
                books = bookRepository.findAll();
            }

            //If any book is Found
            if(books.iterator().hasNext()){
                model.addAttribute("books",books);
            }
            else {
                model.addAttribute("searchError", "No Books Found With that Name.");
            }
        }
        else{
            Iterable<Book> books = bookRepository.findAll();
            model.addAttribute("books",books);
        }
        return "Shop";
    }

    @PostMapping("/Checkout")
    public String checkout(Model model, @ModelAttribute("account") Customer account) {
        model.addAttribute("account", account);
        Cart cart = account.getCart();
        if (cart != null) {
            model.addAttribute("totalCost", cart.getPrice());
        }
        return "Checkout";
    }

    @GetMapping("/ShoppingCart")
    public String Customer(HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }
        return "ShoppingCart";
    }

}
