package amazin.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartID;
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> items;

    public Cart(){
        items = new ArrayList<>();
    }

    public void addItem(Book book, int amount){
        for(int i = 0; i < amount; i++){
            items.add(book);
        }
    }

    public List<Book> getBookCart(Book book){
        List<Book> temp = new ArrayList<>();
        for (Book b:items){
            if (b == book){
                temp.add(b);
            }
        }
        return temp;
    }

    public void removeBook(Book book, int amount){
        int removed = 0;
        int amountInCart = getBookCart(book).size();
        if (amount > amountInCart){
            System.out.println("You only have "+amountInCart+" copies of "+book+" in your cart.");
        }else {
            for (Book b:items){
                if (b == book && removed <= amount){
                    items.remove(b);
                    removed++;
            }
        }
        }
    }

    public int getCost(){
        int price = 0;
        for (Book books:items){
            price+= books.getPrice();
        }
        return price;
    }

    public int cartSize(){
        return items.size();
    }

    public int getCartID(){
        return cartID;
    }





}
