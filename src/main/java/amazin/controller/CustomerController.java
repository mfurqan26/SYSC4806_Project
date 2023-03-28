package amazin.controller;

import amazin.model.*;
import amazin.repository.AccountRepository;
import amazin.repository.BookRepository;
import amazin.repository.CartItemRepository;
import amazin.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

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

    @PostMapping(value="/Shop", params = "buyBook")
    public String buyBook(
        @RequestParam(name="isbn", required=false, defaultValue = "") String isbn,
        @RequestParam(name="version", required=false, defaultValue = "") int version,
        HttpSession session, Model model){
        Book.BookId id = new Book.BookId(isbn,version);
        Optional<Book> book = bookRepository.findById(id);
        Account account = (Account) session.getAttribute("account");
        if(!book.isEmpty()){
            model.addAttribute("account", account);
            model.addAttribute("book", book.get());
            return "ShopItem";
        }
        else{
            return "redirect:/Shop";
        }
    }

    @GetMapping("/ShopItem")
    public String getShopItem() {
        return "redirect:/Shop";
    }

    @PostMapping(value="/ShopItem", params = "ShopPage")
    public String ShopPage(){
        return "redirect:/Shop";
    }

    @PostMapping(value="/ShopItem", params = "AddToCart")
    public String AddToCart(
            @RequestParam(name="isbn", required=false, defaultValue = "") String isbn,
            @RequestParam(name="version", required=false, defaultValue = "") int version,
            @RequestParam(name="quantity", required=false, defaultValue = "") int quantity,
            HttpSession session, Model model){
        Book.BookId id = new Book.BookId(isbn,version);
        Optional<Book> book = bookRepository.findById(id);
        Customer account = (Customer) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        if(!book.isEmpty()){
            CartItem cartItem = new CartItem(book.get(), quantity, cart.getId());
            cartItemRepository.save(cartItem);

            book.get().removeStock(quantity);
            cart.addCartItem(cartItem);
            account.setCart(cart);
        }

        return "redirect:/Shop";
    }

    @PostMapping("/Checkout")
    public String checkout(Model model, HttpSession session) {
        Customer account = (Customer) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("account", account);

        if (cart != null) {
            Long cartId = cart.getId();
            Iterable<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
            List<CartItem> items = new ArrayList<>();
            double price = 0.0;

            for( CartItem cartItem : cartItems){
                items.add(cartItem);
                account.addPurchasedBook(cartItem.getBook());
                price += cartItem.getPrice();
            }

            model.addAttribute("purchasedItems", items);
            model.addAttribute("totalCost", price);
        }
        return "Checkout";
    }

    @GetMapping("/Checkout")
    public String getCheckout(Model model, HttpSession session) {
        Customer account = (Customer) session.getAttribute("account");
        if (account == null || account.getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }
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
