package amazin.model;

import jakarta.persistence.*;

import java.util.*;

import amazin.model.Book.BookId;
import amazin.model.Account;

@Entity
public class Customer extends Account {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> purchasedBooks;
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart;


    public Customer(String userName, String password) {
        super(userName, password, Account.Type.CUSTOMER);
        this.purchasedBooks = new ArrayList<>();
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

    /** Add purchased book if book with same isbn and version does Not exist already*/
    public void addPurchasedBook(Book book){
        boolean bookNotContained = true;
        for(Book purchasedBook : purchasedBooks){
            if(purchasedBook.getIsbn().equals(book.getIsbn()) && purchasedBook.getVersion() == book.getVersion()){
                bookNotContained = false;
            }
        }
        if(bookNotContained){
            purchasedBooks.add(book);
        }
    }
}
