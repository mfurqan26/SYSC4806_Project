package amazin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document(collection = "AccountRepository")
public abstract class Account {

	private long id;

	private Type type;

	@Id
	protected String userName;

	private String password;

	public Account(String userName, String password, Type type) {
		this.userName = userName;
		this.password = password;
		this.type = type;
	}

	public Account() {
		this.userName = "";
		this.password = "";
		this.type = null;
	}

	public enum Type {
		VENDOR,
		CUSTOMER
	}


	public long getId() {return this.id;}

	public void setId(long id) {this.id = id;}

	public Type getType() {return this.type;}

	public void setType(Type type){this.type = type;}

	public String getUserName() {return this.userName;}

	public void setUserName(String userName){this.userName = userName;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}

}