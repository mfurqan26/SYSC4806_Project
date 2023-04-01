package amazin.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart implements Serializable {

	@OneToMany(fetch = FetchType.EAGER)
	private List<CartItem> items;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String userName;
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;

	public Cart() {
		this.items = new ArrayList<>();
	}

	public Cart(String userName) {
		this.userName = userName;
		this.items = new ArrayList<>();
	}

	public String getUserName() {return userName;}

	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public Customer getCustomer() {return customer;}

	public void setId(Customer customer) {this.customer = customer;}
	public void setUserName(String userName) {this.userName = userName;}

	public List<CartItem> getItems() {
		return this.items;
	}

	public void setItems(List<CartItem> items) {this.items = items;}

	public void addCartItem(CartItem cartItem) {this.items.add(cartItem);}

	public ArrayList<Book> getCartBooks() {
		ArrayList<Book> books = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			books.add(items.get(i).getBook());
		}
		return books;
	}

	public double getPrice() {
		double price = 0;
		for (int i = 0; i < items.size(); i++) {
			price += items.get(i).getPrice();
		}
		return price;
	}

	public void resetCart() {this.items = new ArrayList<>();}
}