package amazin.model;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.io.Serializable;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Autowired
	private BookRepository books;

	private ArrayList<CartItem> items;

	public static class CartItem implements Serializable {
		BookId book;
		int amount;
		CartItem(BookId book, int amount) {
			this.book = book;
			this.amount = amount;
		}
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

	public double getPrice() {
		return items.stream().map( (cartItem) ->
			books.findById(cartItem.book).get().getPrice() * cartItem.amount)
				.reduce(0.0, Double::sum);
	}
}