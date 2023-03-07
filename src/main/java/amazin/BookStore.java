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
            Book book1 = new Book("abc");
            Book book2 = new Book("book 2");
            repository1.save(book1);
            repository1.save(book2);
        };
    }

}