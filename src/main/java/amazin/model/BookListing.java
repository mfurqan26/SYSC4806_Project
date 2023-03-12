
package amazin.model;

import jakarta.persistence.*;

import java.io.Serializable;


@IdClass(BookListing.BookListingId.class)
@Entity
public class BookListing implements Serializable {
	
	public static class BookListingId {
		private long vendorId;
		private String isbn;
		private int version;

		public BookListingId(long vendorId, 
				String isbn, int version) {
			this.isbn = isbn;
			this.vendorId = vendorId;
			this.version = version;
		}
	}

	@Id
	private long vendorId;

	@Id
    protected String isbn;

    @Id
    protected int version;

	private int stock;
	private float price;
	private String description;

	public BookListing(long vendorId, String isbn, int version,
			int stock, float price, String description) {
		this.vendorId = vendorId;
		this.isbn = isbn;
		this.version = version;
		this.stock = stock;
		this.price = price;
		this.description = description;
	}

}