package amazin;

import amazin.model.*;
import amazin.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStore {

    public static void main(String[] args) {
        SpringApplication.run(BookStore.class);
    }

    @Bean
    public CommandLineRunner demo(
            BookRepository books, 
            AccountRepository accounts) {
        return (args) -> {

            Book book1 = new Book("978-0-122453-12-1", 1,
                "Book1" , "desc 1", "abc",5,10.0);
            Book book2 = new Book("948-0-123456-47-2",1,
                "Book2" , "desc 2", "abc",3,20.0);
            books.save(book1);
            books.save(book2);

            Customer c1 = new Customer("customer1");
            Vendor v1 = new Vendor("vendor1");
            accounts.save(c1);
            accounts.save(v1);
        };
    }

}