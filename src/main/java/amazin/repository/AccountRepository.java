package amazin.repository;

import amazin.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reactor.core.publisher.Mono;

@RepositoryRestResource
public interface AccountRepository extends ReactiveMongoRepository<Account, Long> {
	public Mono<Account> findAccountByUserName(String userName);
}