package amazin.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public abstract class Account {

	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private final Type type;

	@Id
	protected final String userName;

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

	public String getUserName() {return this.userName;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}

}