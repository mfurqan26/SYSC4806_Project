package amazin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStore {

    private static final Logger log = LoggerFactory.getLogger(BookStore.class);

    public static void main(String[] args) {
        SpringApplication.run(BookStore.class);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository1) {
        return (args) -> {
            Book book1 = new Book("abc");
            repository1.save(book1);
        };
    }

}