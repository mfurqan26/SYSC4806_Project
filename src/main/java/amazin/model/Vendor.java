package amazin.model;

import org.springframework.data.mongodb.core.mapping.Document;
import amazin.model.Book.BookId;
import amazin.model.Account;

@Document(collection = "AccountRepository")
public class Vendor extends Account {
	public Vendor(String userName, String password) {
		super(userName, password, Account.Type.VENDOR);
	}

	public Vendor() {
		super();
	}
}