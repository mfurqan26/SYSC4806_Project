package org.example;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> BookCollection;

    /** Creates a new instance of Inventory */
    public Inventory() {BookCollection = new ArrayList<>();}

    public Inventory(int id) {
        BookCollection = new ArrayList<>();
        this.id = id;
    }

    public void setBookCollection(List<Book> BookCollection) {this.BookCollection = BookCollection;}

    public void addBook(Book Book){
        BookCollection.add(Book);
    }
    public void removeBook(Integer BookID){
        for (int i = 0; i<BookCollection.size(); i++){
            Book Book = BookCollection.get(i);
            if(Book.getId() == BookID){
                BookCollection.remove(Book);
            }
        }
    }

    public Book getBook(Integer BookID){
        for (int i = 0; i<BookCollection.size(); i++){
            Book Book = BookCollection.get(i);
            if(Book.getId() == BookID){
                return Book;
            }
        }
        return null;
    }

    public List<Book> getBookCollection(){return BookCollection;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Inventory other = (Inventory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String returnString = "Iventory id: "+id+"\n";
        return returnString;
    }

    public static void main(String[] args) {

    }
}