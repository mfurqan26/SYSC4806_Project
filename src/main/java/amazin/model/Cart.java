package amazin.model;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.*;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.io.Serializable;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    //@Autowired
    //private BookRepository books;

    private ArrayList<CartItem> items;

    @OneToOne
    private Customer customer;

    public static class CartItem implements Serializable {
        private BookId book;
        private int amount;
        public CartItem(BookId book, int amount) {
            this.book = book;
            this.amount = amount;
        }
        public BookId getBook(){return this.book;}
        public int getAmount(){return this.amount;}
    }

    Cart() {
        this.items = new ArrayList<>();
    }

    Cart(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public ArrayList<CartItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
    	return 0.0;
      //  return items.stream().map( (cartItem) ->
       //     books.findById(cartItem.getBook()).get().getPrice() * cartItem.getAmount())
      //          .reduce(0.0, Double::sum);
    }
}