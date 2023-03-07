package amazin.model;

import jakarta.persistence.*;

@Entity
public class StoreBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long bookID;
    private Integer stock;
    private Float price;

    public StoreBook(){}
    public StoreBook(Long bookID) {this.bookID = bookID;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Long getBookID() {return bookID;}
    public void setBookID(Long bookID) {this.bookID = bookID;}

    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}

    public Float getPrice() {return price;}
    public void setPrice(Float price) {this.price = price;}

}

