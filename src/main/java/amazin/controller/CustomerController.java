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

    public void resetRecommendations(){
        //Reset all books to not recommended
        Iterable<Book> allBooks = bookRepository.findAll();
        for(Book book: allBooks){
            book.setRecommended(false);
            bookRepository.save(book);
        }
    }

    private void setRecommendedBooks(Customer customer){

        //Get All other customer accounts other than current customer
        Iterable<Account> allAccounts = accountRepository.findAll();
        List<Customer> allOtherCustomers = new ArrayList<>();
        for(Account account : allAccounts){
            if(account.getType().equals(Account.Type.CUSTOMER) && !account.equals(customer)){
                allOtherCustomers.add( (Customer) account);
            }
        }

        double thresholdPercentage = 50.0;
        List<Book> purchasedBooks = customer.getPurchasedBooks();

        //For each other customer
        for(Customer otherCustomer : allOtherCustomers){
            List<Book> otherPurchasedBooks = otherCustomer.getPurchasedBooks();
            int counter = 0;

            //Check number of purchasedBooks matching BookIds with current customer purchased books
            for (Book purchasedBook: purchasedBooks){
                if(otherPurchasedBooks.contains(purchasedBook)){
                    counter += 1;
                }
            }
            //Convert to percentage compared to current customer total  number of books
            double similarityPercentage = (double) (counter * 100) / purchasedBooks.size();

            //Check percentage compared to threshold
            if(similarityPercentage >= thresholdPercentage){
                //If true, set all books that the other customer purchased to recommended true
                for (Book otherPurchasedBook: otherPurchasedBooks){

                    //Set recommended only if current customer didn't already purchase the other book
                    if(!purchasedBooks.contains(otherPurchasedBook)){
                        otherPurchasedBook.setRecommended(true);
                        bookRepository.save(otherPurchasedBook);
                    }
                }
            }
        }

    }

    @GetMapping("/Shop")
    public String customer(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("username");
        Optional<Account> account = accountRepository.findAccountByUserName(userName);
        if (account.isEmpty() || account.get().getType() != Account.Type.CUSTOMER) {
            // redirect to login page
            return "redirect:/CustomerLogin";
        }
        //Set recommendedBooks for customer
        Customer customer = (Customer) account.get();

        //Reset recommendations and set Recommended books if customer does have previous purchased books.
        resetRecommendations();
        if(customer.getPurchasedBooks().size() > 0){
            setRecommendedBooks(customer);
        }

        //Thymeleaf attribute passing
        Iterable<Book> books = bookRepository.findAllByOrderByRecommendedDesc();
        model.addAttribute("account", customer);
        model.addAttribute("books", books);
        return "Shop";
    }

    @PostMapping(value="/Shop", params = "AllBooks")
    public String SearchAllBook(Model model, HttpSession session){
        Iterable<Book> books = bookRepository.findAllByOrderByRecommendedDesc();

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
            else if(filter.equals("by-author")){
                books = bookRepository.findBooksByAuthor(search);
            }
            else if(filter.equals("by-name")){
                books = bookRepository.findBooksByName(search);
            }
            else {
                books = bookRepository.findAllByOrderByRecommendedDesc();
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

        //If quantity is 0 or less for some reason, don't add item to add and return to shop page
        if(quantity <= 0){
            return "redirect:/Shop";
        }

        Long cartId = (Long) session.getAttribute("cartId");
        Optional<Cart> cart = cartRepository.findById(cartId);

        if(book.isPresent() && cart.isPresent() && account.isPresent()){
            Customer customer = (Customer) account.get();
            Book purchasingBook = book.get();
            Cart myCart = cart.get();
            boolean sameBookNotInCart = true;

            //If purchasing book already exists in cart, then update cart item quantity.
            if (myCart.getItems().size() != 0) {
                List<CartItem> items = myCart.getItems();
                for (int i = 0; i < items.size() ;i++) {
                    CartItem cartItem = items.get(i);
                    if (cartItem.getBook().equals(purchasingBook) && sameBookNotInCart) {
                        cartItem.setAmount(cartItem.getAmount() + quantity);
                        cartItemRepository.save(cartItem);
                        items.set(i, cartItem);
                        sameBookNotInCart = false;
                    }
                }
                myCart.setItems(items);
            }
            //Else create new cart item for book
            if(sameBookNotInCart) {
                CartItem cartItem = new CartItem(purchasingBook, quantity, cartId);
                cartItemRepository.save(cartItem);
                myCart.addCartItem(cartItem);
            }

            purchasingBook.removeStock(quantity);
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
        model.addAttribute("totalCost", String.format("%.2f",price));
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
