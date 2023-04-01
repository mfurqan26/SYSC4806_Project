package amazin.controller;

import amazin.model.*;
import amazin.repository.AccountRepository;
import amazin.repository.BookRepository;
import amazin.repository.CartItemRepository;
import amazin.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("account", account.get());
        model.addAttribute("books", books);
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "AllBooks")
    public String SearchAllBook(Model model, HttpSession session){
        Iterable<Book> books = bookRepository.findAll();

        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            return "redirect:/CustomerLogin";
        }

        model.addAttribute("books", books);
        model.addAttribute("account", account.get());
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "SearchBook")
    public String SearchBook(
            @RequestParam(name="search", required=false, defaultValue = "") String search,
            @RequestParam(name="filter", required=false, defaultValue = "") String filter,
            Model model, HttpSession session){
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

        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty()|| account.get().getType() != Account.Type.CUSTOMER) {
            return "redirect:/CustomerLogin";
        }

        model.addAttribute("account", account.get());
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "buyBook")
    public String buyBook(
        @RequestParam(name="isbn", required=false, defaultValue = "") String isbn,
        @RequestParam(name="version", required=false, defaultValue = "") int version,
        HttpSession session, Model model){
        Book.BookId id = new Book.BookId(isbn,version);
        Optional<Book> book = bookRepository.findById(id);
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if(book.isPresent() && account.isPresent()){
            model.addAttribute("account", account.get());
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
            HttpSession session){
        Book.BookId id = new Book.BookId(isbn,version);
        Optional<Book> book = bookRepository.findById(id);
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);


        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);

        if(book.isPresent() && cart.isPresent() && account.isPresent()){
            Customer customer = (Customer) account.get();
            CartItem cartItem = new CartItem(book.get(), quantity, cartId);
            cartItemRepository.save(cartItem);
            book.get().removeStock(quantity);

            Cart myCart = cart.get();
            myCart.addCartItem(cartItem);
            customer.setCart(myCart);
            accountRepository.save(customer);
            cartRepository.save(myCart);
        }

        return "redirect:/Shop";
    }

    @GetMapping("/ShoppingCart")
    public String shoppingCart(Model model, HttpSession session){
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            return "redirect:/CustomerLogin";
        }

        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);
        List<CartItem> items = new ArrayList<>();

        if (cart.isPresent() && cart.get().getItems().size() != 0) {
            List<CartItem> cartItems = cart.get().getItems();

            for (CartItem cartItem : cartItems) {
                items.add(cartItem);
            }
        }
        model.addAttribute("account", account.get());
        model.addAttribute("purchasedItems", items);
        return "ShoppingCart";
    }

    @GetMapping("/Checkout")
    public String getCheckout(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            return "redirect:/CustomerLogin";
        }

        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);

        List<CartItem> items = new ArrayList<>();
        double price = 0.0;

        if (cart.isPresent() && cart.get().getItems().size() != 0) {
            List<CartItem> cartItems = cart.get().getItems();

            for( CartItem cartItem : cartItems){
                items.add(cartItem);
                price += cartItem.getPrice();
            }

        }

        model.addAttribute("account", account.get());
        model.addAttribute("purchasedItems", items);
        model.addAttribute("totalCost", price);
        return "Checkout";
    }

    @Transactional
    @PostMapping(value="/Checkout", params="Checkout")
    public String confirmCheckout(HttpSession session) {
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);
        
        //Add Purchased Books to Customer account
        if (cart.isPresent() && account.isPresent() && cart.get().getItems().size() != 0) {

            List<CartItem> cartItems = cart.get().getItems();
            Customer customer = (Customer) account.get();
            for( CartItem cartItem : cartItems){
                Book book = cartItem.getBook();
                customer.addPurchasedBook(book);
            }

            //Create new Empty Cart for customer
            cartRepository.deleteCartById(cartId);
            Cart emptyCart = new Cart(userName);
            cartRepository.save(emptyCart);
            customer.setCart(emptyCart);
            accountRepository.save(customer);
            session.setAttribute("cartId",emptyCart.getId());
        }

        return "redirect:/PurchasedBooks";
    }

    @GetMapping("/PurchasedBooks")
    public String purchasedBooks(Model model, HttpSession session){
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }

        Customer customer = (Customer) account.get();
        List<Book> books = customer.getPurchasedBooks();
        if(books.size() == 0){
            model.addAttribute("emptyBooks", "You have not purchased any books yet.");
        }
        model.addAttribute("account", account.get());
        model.addAttribute("books",books);
        return "PurchasedBooks";
    }

    @GetMapping("/CustomerLogout")
    public String CustomerLogout(HttpSession session) {
        session.setAttribute("username",null);
        session.setAttribute("cartId",null);
        return "redirect:/CustomerLogin";
    }
}
