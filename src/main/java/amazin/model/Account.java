package amazin.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public abstract class Account {

	public Account(String userName, Type type) {
		this.userName = userName;
		this.type = type;
	}

	public Account() {
		this.userName = "";
		this.type = null;
	}

	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private final Type type;

	@Id
	protected final String userName;

	public enum Type {
		VENDOR,
		CUSTOMER
	}


	public long getId() {
		return this.id;
	} 

	public void setId(long id) {
		this.id = id;
	}

	public Type getType() {
		return this.type;
	}

	public String userName() {
		return this.userName;
	}
}