package amazin;

import amazin.model.Book;
import amazin.repository.BookRepository;
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
    public CommandLineRunner demo(BookRepository repository1) {
        return (args) -> {
            Book book1 = new Book("978-0-122453-12-1", 1, 
                "Book1" , "desc 1", "abc");
            Book book2 = new Book("948-0-123456-47-2", 1, 
                "Book2" , "desc 2", "abc");
            repository1.save(book1);
            repository1.save(book2);
        };
    }

}