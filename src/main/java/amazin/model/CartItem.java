package amazin.model;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;
@Entity
public class CartItem {
    @OneToOne(fetch = FetchType.EAGER)
    private Book book;
    private int amount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long cartId;

    public CartItem(){
        this.book = new Book();
        this.amount = 0;
        this.cartId = 0;
    }
    public CartItem(Book book, int amount, long cartId) {
        this.book = book;
        this.amount = amount;
        this.cartId = cartId;
    }

    public long getId() {return id;}

    public void setId(int id) {this.id = id;}

    public long getCartId() {return cartId;}

    public void setCartId(int cartId) {this.cartId = cartId;}

    public Book getBook() {return book;}

    public void setBook(Book book) {this.book = book;}

    public int getAmount() {return amount;}

    public void setAmount(int amount) {this.amount = amount;}

    public double getPrice(){return book.getPrice()*amount;}
}
