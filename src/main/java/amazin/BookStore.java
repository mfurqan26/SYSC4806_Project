package amazin;

import amazin.model.*;
import amazin.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStore{

    public static void main(String[] args) {
        SpringApplication.run(BookStore.class);
    }

    @Bean
    public CommandLineRunner demo(
            BookRepository books, 
            AccountRepository accounts,
            CartRepository carts) {
        return (args) -> {

            Book book1 = new Book("978-0-122453-12-1", 1,
                "Golden Days" , "Slice of life novel about a high school in Japan.", "Japan Publishing House", "Tora Kaze",5,10.0);
            Book book2 = new Book("948-0-123456-47-2",1,
                "The Light of Carleton" , "Coming of age novel starring an Engineering class in Ottawa, Canada.", "Smythe Publishing", "James Green",3,20.0);
            Book book3 = new Book("348-0-127456-27-3",1,
                    "How to Cook Steak" , "A book for teaching you how to cook steak.", "Ferocious Kitchen Books", "Bob Dylan",7,15.0);
            Book book4 = new Book("968-7-123456-47-4",1,
                    "Nine Lives of a Mobster" , "An autobiography from a former gang member.", "Noir Publishing", "Frank Sinatra",8,14.0);
            Book book5 = new Book("948-0-654321-47-5",1,
                    "Chemical Engineering" , "Introductory lessons to chemical engineering.", "O'Reilly", "Tim O'Reilly",9,129.99);
            books.save(book1);
            books.save(book2);
            books.save(book3);
            books.save(book4);
            books.save(book5);

            Cart cart1 = new Cart("customer1");
            carts.save(cart1);
            Customer c1 = new Customer("customer1","123");
            c1.setCart(cart1);
            accounts.save(c1);

            Cart cart2 = new Cart("customer2");
            carts.save(cart2);
            Customer c2 = new Customer("customer2","123");
            c2.setCart(cart2);
            accounts.save(c2);

            Cart cart3 = new Cart("customer3");
            carts.save(cart3);
            Customer c3 = new Customer("customer3","123");
            c3.setCart(cart3);
            accounts.save(c3);

            Vendor v1 = new Vendor("vendor1","123");
            accounts.save(v1);
        };
    }
}