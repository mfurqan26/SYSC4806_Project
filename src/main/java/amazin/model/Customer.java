package amazin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

//import amazin.model.Book.BookId;
import amazin.model.Account;

@Entity
public class Customer extends Account {

    @OneToMany
    private List<Book> purchasedBooks;
    @OneToMany
    private Map<String, CustomerReview> bookReviews;

    //TODO add sensitive info to purchasing.
    //private creditInfo

    public Customer(String userName) {
        super(userName, Account.Type.CUSTOMER);
        purchasedBooks = new ArrayList<>();
        bookReviews = new HashMap<>();
    }

    public Customer() {
        super();
    }

    public String getName() {
        return this.userName;
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

    public Map<String, CustomerReview> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Map<String, CustomerReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public void addBookReview(String book, CustomerReview rating){
        bookReviews.put(book, rating);
    }

    public CustomerReview getBookReview(String book){
        return bookReviews.get(book);
    }


    @Override
    public String toString() {
        return "amazin.model.Customer{" +
                "userName='" + this.userName + '\'' +
                ", id=" + this.getId() +
                ", purchasedBooks=" + this.purchasedBooks.toString() +
                ", bookReviews=" + this.bookReviews +
                '}';
    }
}
