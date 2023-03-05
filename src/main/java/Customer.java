import java.util.*;

public class Customer {
    private String name;
    private Integer id;
    private List<Book> purchasedBooks;
    private Map<Book, Integer> bookReviews;

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

    public List<Book> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<Book> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public void addPurchasedBook(Book book){
        purchasedBooks.add(book);
    }

    public Map<Book, Integer> getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Map<Book, Integer> bookReviews) {
        this.bookReviews = bookReviews;
    }

    public void addBookReview(Book book, Integer rating){
        bookReviews.put(book, rating);
    }

    public Integer getBookReview(Book book){
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
