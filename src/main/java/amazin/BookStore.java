package amazin;

import amazin.model.*;
import amazin.repository.*;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@SpringBootApplication(exclude={MongoAutoConfiguration.class})
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@EnableReactiveMongoRepositories(
    basePackageClasses = BookRepository.class)
public class BookStore extends AbstractReactiveMongoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(BookStore.class);
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "Amazin";
    }

    @Bean
    public CommandLineRunner demo(
            BookRepository books, 
            AccountRepository accounts) {
        return (args) -> {
            Book book1 = new Book("978-0-122453-12-1", 1, "Book1", "desc 1", "abc", 5, 10.0);
            Book book2 = new Book("948-0-123456-47-2", 1, "Book2", "desc 2", "abc", 3, 20.0);

            Flux<Book> bookFlux = books.saveAll(Flux.just(book1, book2));

            Customer c1 = new Customer("customer1", "123");
            Vendor v1 = new Vendor("vendor1", "123");

            Flux<Account> accountFlux = accounts.saveAll(Flux.just(c1, v1));

            // Block until all the data is saved, this should be used only in non-reactive context
            bookFlux.thenMany(accountFlux).blockLast();
        };
    }

}