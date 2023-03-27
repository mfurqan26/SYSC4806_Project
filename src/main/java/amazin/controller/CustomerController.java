package amazin.controller;

import amazin.model.Book;
import amazin.model.Cart;
import amazin.model.Customer;
import amazin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/Shop")
    public String Customer(Model model){
        Iterable<Book> books = bookRepository.findAll();
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
    public String checkout(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "Checkout";
    }

    @GetMapping("/ShoppingCart")
    public String Customer(){
        return "ShoppingCart";
    }

}
