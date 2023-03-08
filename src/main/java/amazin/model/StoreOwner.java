package amazin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
public class StoreOwner {
    @Id
    @GeneratedValue
    private long storeID;
    @OneToMany
    private List<Book> books;
    @OneToMany
    private HashMap<Long, StoreBook> inventory;

    public StoreOwner(){
        this.books = new ArrayList<>();
        this.inventory = new HashMap<>();
    }

    public long getStoreID() {return storeID;}
    public void setStoreID(long storeID) {this.storeID = storeID;}

    public List<Book> getBooks() {return books;}
    public void setBooks(List<Book> books) {this.books = books;}

    public void addBook(Book book, Integer stock, Float price){
        Long bookID = book.getId();
        if(books.contains(book)){
            StoreBook storeBook = inventory.get(bookID);
            storeBook.setPrice(price);
            storeBook.setStock(stock);
            inventory.replace(bookID, storeBook);
        }
        else{
            books.add(book);
            StoreBook storeBook = new StoreBook(bookID);
            storeBook.setPrice(price);
            storeBook.setStock(stock);
            inventory.put(bookID, storeBook);
        }
    }

    public Boolean removeBook(Book book){
        Long bookID = book.getId();
        if(books.contains(book)){
            books.remove(book);
            if(inventory.containsKey(bookID)){
                inventory.remove(bookID);
            }
            return true;
        }
        else{return false;}
    }

    public Integer getStock(Book book){
        Long bookID = book.getId();
        if(inventory.containsKey(bookID)){
            return inventory.get(bookID).getStock();
        }
        else{return null;}
    }
}
