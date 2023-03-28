package amazin.model;

import jakarta.persistence.*;

import java.util.*;

import amazin.model.Book.BookId;
import amazin.model.Account;

@Entity
public class Customer extends Account {

    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> purchasedBooks;
    @OneToMany(fetch = FetchType.EAGER)
    private Map<BookId, CustomerReview> bookReviews;
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart;

    //TODO add sensitive info to purchasing.
    //private creditInfo

    public Customer(String userName, String password) {
        super(userName, password, Account.Type.CUSTOMER);
        this.purchasedBooks = new ArrayList<>();
        this.bookReviews = new HashMap<>();
        this.cart = new Cart(userName);
    }

    public Customer() {
        super();
    }

    public String getName() {
        return this.userName;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {this.cart = cart;}

    public void addCartItem(CartItem cartItem) {
        this.cart.addCartItem(cartItem);
    }

    public ArrayList<Book> getCartBooks() {
        return  this.cart.getCartBooks();
    }

    public List<Book> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<Book> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public void addPurchasedBook(Book book){
        purchasedBooks.add(book);
    }

    public Map<BookId, CustomerReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Map<BookId, CustomerReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public void addBookReview(BookId book, CustomerReview rating){
        bookReviews.put(book, rating);
    }

    public CustomerReview getBookReview(BookId book){
        return bookReviews.get(book);
    }

}
