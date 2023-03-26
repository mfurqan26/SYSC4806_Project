package amazin.model;


import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import amazin.model.Book.BookId;
import amazin.model.Account;

@Document(collection = "AccountRepository")
public class Customer extends Account {
    private List<Book> purchasedBooks;
    private Map<BookId, CustomerReview> bookReviews;

    //TODO add sensitive info to purchasing.
    //private creditInfo

    public Customer(String userName, String password) {
        super(userName, password, Account.Type.CUSTOMER);
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
