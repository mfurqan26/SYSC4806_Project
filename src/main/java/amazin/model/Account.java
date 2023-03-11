package amazin.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public abstract class Account {

	public Account(Type type) {
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private final Type type;

	public enum Type {
		VENDOR,
		CUSTOMER
	}


	public long getId() {
		return this.id;
	} 

	public Type getType() {
		return this.type;
	}
}