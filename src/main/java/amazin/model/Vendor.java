package amazin.model;

import jakarta.persistence.Entity;
//import amazin.model.Book.BookId;
import amazin.model.Account;

@Entity
public class Vendor extends Account {
	public Vendor(String userName) {
		super(userName, Account.Type.VENDOR);
	}

	public Vendor() {
		super();
	}
}