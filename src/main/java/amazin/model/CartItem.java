package amazin.model;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CartItem implements Serializable{
    @OneToOne(fetch = FetchType.EAGER)
    private Book book;
    private int amount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long cartId;

    @ManyToOne
    @JoinColumn(name = "cart")
    private Cart cart;

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

    public Cart getCart() {return cart;}

    public void setCart(Cart cart) {this.cart = cart;}
    public Book getBook() {return book;}

    public void setBook(Book book) {this.book = book;}

    public int getAmount() {return amount;}

    public void setAmount(int amount) {this.amount = amount;}

    public double getPrice(){return book.getPrice()*amount;}
}
