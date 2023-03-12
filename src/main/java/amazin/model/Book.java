package amazin.model;

import jakarta.persistence.*;

import java.io.Serializable;

@IdClass(Book.BookId.class)
@Entity
public class Book {

    public static class BookId implements Serializable  {
        protected String isbn;
        protected int version;

        protected BookId(String isbn, int version) {
            this.isbn = isbn;
            this.version = version;
        }

        public BookId() {}

        public String getIsbn() {
            return this.isbn;
        }

        public int getVersion() {
            return this.version;
        }

        @Override
        public String toString() {
            return isbn + ":" + version; 
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    @Id
    protected String isbn;

    @Id
    protected int version;

    public final String name;
    private String description;
    public final String publisher;
    //private ByteArrayInputStream cover;

    public Book(String isbn, int version, 
            String name, String description, 
            String publisher) {
        this.isbn = isbn;
        this.version = version;
        this.name = name;
        this.description = description;
        this.publisher = publisher;
    }

    public Book() {
        this.name = "";
        this.publisher = "";
    }

    /**
     * since the id should never be set this method
     * shouldn't be here, but we're only commenting it
     * out for now because spirng boot may require it
     * (in which case we'd need to remove final)
     *
    public void setId(BookId id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public BookId getId() {
        return new BookId(this.isbn, this.version);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

}
