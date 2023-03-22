package amazin.model;

import jakarta.persistence.Entity;
import amazin.model.Book.BookId;
import amazin.model.Account;

@Entity
public class Vendor extends Account {
	public Vendor(String userName, String password) {
		super(userName, password, Account.Type.VENDOR);
	}

	public Vendor() {
		super();
	}
}