package amazin.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

import amazin.model.Book.BookId;
import amazin.repository.BookRepository;

public class Cart {

	HashMap<BookId, Book> books;

	ArrayList<CartItem> items;

	public static class CartItem {
		private BookId book;
		private int amount;
		CartItem(BookId book, int amount) {
			this.book = book;
			this.amount = amount;
		}

		public BookId getBook() {return book;}

		public int getAmount() {return amount;}
	}

	Cart() {
		this.items = new ArrayList<>();
	}

	public double getPrice() {
		return items.stream().map( (cartItem) ->
			books.get(cartItem.getBook()).getPrice() * cartItem.getAmount())
				.reduce(0.0, Double::sum);
	}
}