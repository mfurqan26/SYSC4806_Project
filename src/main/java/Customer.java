import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany
    //FIXME
    private List<BookId> purchasedBooks;
    @OneToMany
    private Map<BookId, CustomerReview> bookReviews;
    private String name;

    //TODO add sensitive info to purchasing.
    //private creditInfo

    public Customer(){
        purchasedBooks = new ArrayList<>();
        bookReviews = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BookId> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<BookId> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public void addPurchasedBook(BookId book){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) && id.equals(customer.id) && Objects.equals(purchasedBooks, customer.purchasedBooks) && Objects.equals(bookReviews, customer.bookReviews);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", purchasedBooks=" + purchasedBooks +
                ", bookReviews=" + bookReviews +
                '}';
    }
}
