package amazin.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@IdClass(Book.BookId.class)
@Entity
public class Book implements Serializable{

    @Id
    protected String isbn;

    @Id
    protected int version;
    private String name;
    private String description;
    private String publisher;
    private int stock;
    private double price;

    public static class BookId implements Serializable  {
        protected String isbn;
        protected int version;

        public BookId(String isbn, int version) {
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

    public Book(String isbn,
            String name, String description, 
            String publisher, int stock, double price) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.stock = stock;
        this.price = price;
    }

    public Book(String isbn, int version,
                String name, String description,
                String publisher, int stock, double price) {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
        this.publisher = publisher;
        this.stock = stock;
        this.price = price;
        this.version = version;
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

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getVersion(){return this.version;}
    public void setVersion(int version) {this.version = version;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getIsbn() {return this.isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public BookId getId() {return new BookId(this.isbn, this.version);}



    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
    public void addStock(int amount){this.stock += amount;}

    /** Return true if removing was successful
     * Else returns false if it can not remove*/
    public Boolean removeStock(int amount){
        int currentStock = this.stock;
        if(currentStock >= amount){
            this.stock -= amount;
            return true;
        }
        else{
            return false;
        }
    }

    public double getPrice() {return price;}
    public void setPrice(float price) {this.price = price;}

}
