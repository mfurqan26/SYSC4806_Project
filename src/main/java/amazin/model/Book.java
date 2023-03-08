package amazin.model;

import jakarta.persistence.*;

import java.io.Serializable;

@IdClass(Book.BookId.class)
@Entity
public class Book {


    public static class BookId implements Serializable  {
        protected ISBN isbn;
        protected int version;

        protected BookId(ISBN isbn, int version) {
            this.isbn = isbn;
            this.version = version;
        }

        public BookId() {

        }
    }

    protected static class ISBN extends Number {
        Number[] value;

        protected ISBN(Number[] isbn) {
            this.value = isbn;
        }

        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }

    @Id
    protected ISBN isbn;

    @Id
    protected int version;

    public BookId getId() {
        return new BookId(this.isbn, this.version);
    }

    public int id;

    public final String name;
    private String description;
    public final String publisher;

    //private ByteArrayInputStream cover;
    public Book(Number[] isbn, int version, String name, String description, String publisher) {
        this.isbn = new ISBN(isbn);
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

}
