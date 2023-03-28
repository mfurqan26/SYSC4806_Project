package amazin.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import amazin.model.Book.BookId;
import amazin.repository.BookRepository;

public class Cart {

	@Autowired
	BookRepository books;

	private ArrayList<CartItem> items;

	public static class CartItem {
		public BookId book;
		public int amount;
		CartItem(BookId book, int amount) {
			this.book = book;
			this.amount = amount;
		}
	}

	Cart() {
		this.items = new ArrayList<>();
	}

	public ArrayList<CartItem> getItems() {
		return this.items;
	}

	public double getPrice() {
		return items.stream().map( (cartItem) ->
			books.findById(cartItem.book).get().getPrice() * cartItem.amount)
				.reduce(0.0, Double::sum);
	}
}